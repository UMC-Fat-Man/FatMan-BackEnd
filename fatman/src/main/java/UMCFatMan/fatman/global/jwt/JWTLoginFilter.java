package UMCFatMan.fatman.global.jwt;

import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.exception.ErrorResponse;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JWTUtil jwt = new JWTUtil();

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/users/signin");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest reqeust,
            HttpServletResponse response) throws AuthenticationException {

        String refreshToken = reqeust.getHeader("RefreshToken");

        LoginRequestDto userLogin = objectMapper.readValue(reqeust.getInputStream(), LoginRequestDto.class);

        if (refreshToken == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userLogin.getEmail(), userLogin.getPassword(), null);
            return getAuthenticationManager().authenticate(token);
        } else {

            VerifyResultDto verify = JWTUtil.verify(refreshToken);

            if (verify.isSuccess()) {
                return new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(), userLogin.getPassword(), null);
            } else {
                throw new IllegalArgumentException("Refresh token expired");
            }
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        Users user = userDetails.getUser();

        response.setHeader("Refresh-Token", "refresh_token:" + jwt.makeRefreshToken(user));
        response.setHeader("Access-Token", "auth_token:" + jwt.makeAuthToken(user));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        response.getOutputStream().write(objectMapper.writeValueAsBytes(user));

    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException, ServletException {

        SecurityContextHolder.clearContext();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(errorResponse));


    }


}