package UMCFatMan.fatman.global.jwt;

import UMCFatMan.fatman.domain.users.entity.Users;
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
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;


public class JWTCheckFilter extends BasicAuthenticationFilter {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private ObjectMapper objectMapper = new ObjectMapper();

    public JWTCheckFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl) {
        super(authenticationManager);
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String bearer = request.getHeader("Access-Token");

       if(bearer == null || bearer.startsWith("auth_token ")){
            chain.doFilter(request,response);
            return;
        }
       String token = bearer.substring("auth_token:".length());

       VerifyResultDto result = JWTUtil.verify(token);

       if(result.isSuccess()){
           UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(result.getUserEmail());
           Users user = userDetailsImpl.getUser();

           UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                   user.getEmail(), null
           );

           SecurityContextHolder.getContext().setAuthentication(userToken);
           chain.doFilter(request,response);
       } else {
           objectMapper.registerModule(new JavaTimeModule());
           objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

//           ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다.");
           response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//           response.getOutputStream().write(objectMapper.writeValueAsBytes(errorResponse));
       }

    }
}
