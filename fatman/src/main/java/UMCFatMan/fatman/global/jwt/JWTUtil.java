package UMCFatMan.fatman.global.jwt;


import UMCFatMan.fatman.domain.users.entity.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTUtil {


    public static final Algorithm ALGORITHM = Algorithm.HMAC256("hahaha");
    private static final long AUTH_TIME = 20 * 60;
    private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

    public String makeAuthToken(Users user) {

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    public String makeRefreshToken(Users user) {

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
                .sign(ALGORITHM);
    }

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
