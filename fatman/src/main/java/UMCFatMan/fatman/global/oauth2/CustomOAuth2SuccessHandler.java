package UMCFatMan.fatman.global.oauth2;

import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import UMCFatMan.fatman.global.exception.user.UserNotFoundException;
import UMCFatMan.fatman.global.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// OAuth2 로그인 성공 시 처리할 로직을 정의하는 핸들러
@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTUtil jwtUtil; // JWT 생성 유틸리티

    @Autowired
    private UsersRepository userRepository; // 사용자 저장소

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // OAuth2 로그인 성공한 사용자 정보 가져오기
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // DB에서 사용자 조회
        Users user = (Users) userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        // JWT 토큰 생성
        String token = jwtUtil.makeAuthToken(user);

        // 응답에 JWT 토큰 설정
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"token\":\"" + token + "\"}");
        out.flush();
    }
}