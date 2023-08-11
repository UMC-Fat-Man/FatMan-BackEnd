package UMCFatMan.fatman.global.jwt;

import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.exception.ErrorResponse;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import UMCFatMan.fatman.global.security.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;


public class JWTCheckFilter extends BasicAuthenticationFilter {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private ObjectMapper objectMapper = new ObjectMapper();   // 사용자 상세 정보 설정

    public JWTCheckFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl) {
        super(authenticationManager);
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    //JWT 토큰 확인 후 인증 정보 설정 -> HTTP 요청이 들어올때마다 실행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String bearer = request.getHeader("Access-Token");

       if(bearer == null || bearer.startsWith("auth_token ")){
            chain.doFilter(request,response);
            return;
        }
       String token = bearer.substring("auth_token:".length());     // 헤더에서 JWT 토큰 문자열 추출

       VerifyResultDto result = JWTUtil.verify(token);              // 토큰 검증

       if(result.isSuccess()){   // 검증 성공시 -> 사용자 인증 정보 알수 있다.
           UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(result.getUserEmail());

           Users user = userDetailsImpl.getUser();


           UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                   userDetailsImpl,  null
           );

           Authentication userToken = new UsernamePasswordAuthenticationToken(
                   userDetailsImpl, null
           );
           System.out.println("userToken = " + userToken.getClass().getName());
           SecurityContextHolder.getContext().setAuthentication(userToken);

           chain.doFilter(request,response);
       } else {
           objectMapper.registerModule(new JavaTimeModule());
           objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

           ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다.");
           response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
           response.getOutputStream().write(objectMapper.writeValueAsBytes(errorResponse));
       }

    }
}
