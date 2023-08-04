package UMCFatMan.fatman.global.jwt;


import UMCFatMan.fatman.domain.users.entity.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;

// JWT 생성하고 검증하는 기능을 제공
@Component
public class JWTUtil {


    public static final Algorithm ALGORITHM = Algorithm.HMAC256("hahaha");
    private static final long AUTH_TIME = 20 * 60;
    private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

    // 사용자 정보를 받아와서 인증 토큰을 생성
    public String makeAuthToken(Users user) {

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    // 사용자 정보를 받아와서 리프레시 토큰을 생성
    public String makeRefreshToken(Users user) {

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
                .sign(ALGORITHM);
    }

    // 주어진 JWT 토큰을 검증
    public static VerifyResultDto verify(String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return VerifyResultDto.builder().success(true)
                    .userEmail(verify.getSubject()).build();

        } catch (Exception ex) {
            DecodedJWT decode = JWT.decode(token);
            return VerifyResultDto.builder().success(false)
                    .userEmail(decode.getSubject()).build();
        }
    }


}
