package UMCFatMan.fatman.domain.users.service;

import UMCFatMan.fatman.domain.users.dto.*;
import UMCFatMan.fatman.domain.users.entity.AuthProvider;
import UMCFatMan.fatman.domain.users.entity.Role;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.mapper.UserMapper;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import UMCFatMan.fatman.global.exception.user.InvalidTokenException;
import UMCFatMan.fatman.global.exception.user.UserNotFoundException;
import UMCFatMan.fatman.global.jwt.JWTUtil;
import UMCFatMan.fatman.global.jwt.VerifyResultDto;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import com.amazonaws.services.kms.model.NotFoundException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private HttpServletResponse response;


    /*
    //   유저 생성 및 JWT 토큰 생성
    //   새로운 유저 -> 이메일과 이름 등 기본 정보 저장후 JWT 토큰 발행
    //   기존 유저  -> JWT 토큰만 발행
    */
    @Transactional
    public SocialLoginResponseDto socialLogin(SocialLoginRequestDto socialLoginRequestDto) {
        String token = socialLoginRequestDto.getToken();

        // OAuth2 User Request 생성
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, token, null, null);
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("google");
        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, accessToken);

        // 구글 서버와 통신하여 사용자 정보 검증 및 조회
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        // 사용자 정보 가져오기
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<Users> userOptional = usersRepository.findByEmail(email);
        if (userOptional.isPresent()) {                             // 기존 회원일 경우
            return handleExistingUser(userOptional.get());
        } else {                                                    // 신규 유저에 대한 응답 반환
            return handleNewUser(email, name);
        }
    }



    /*
    //   소셜 신규 유저 회원가입시 추가 정보 받기 및 회원 정보 수정
    */
    @Transactional
    public Users updateDetailUser(SocialDetailRequestDto socialDetailRequestDto, UserDetailsImpl userDetails) throws IOException  {
        Users user = userDetails.getUser();
        UserMapper.updateUserEntity(user, socialDetailRequestDto);
        return usersRepository.save(user);
    }




    /*
    //   Jwt 검증 및 재발급
    //   헤더로 리프레시 토큰과 액세스 토큰을 받아와 검증, 리프레시 토큰의 만료 시간을 확인
    //   1. 새로운 액세스 토큰을 발급  2. 만료 기간이 1주일 미만일 경우 새로운 리프레시 토큰을 발급
    */
    @Transactional
    public HttpHeaders jwtAuthorize(String refreshToken, String accessToken) {
        // 토큰 검증
        VerifyResultDto refreshVerifyResult = jwtUtil.verify(refreshToken); // 리프레시 토큰 검증

        // 리프레시 토큰이 유효하지 않을 경우 예외 처리
        if (!refreshVerifyResult.isSuccess()) {
            throw new InvalidTokenException();
        }

        Users user = usersRepository.findByEmail(refreshVerifyResult.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException());

        HttpHeaders responseHeaders = new HttpHeaders();

        // 액세스 토큰을 생성
        String newAccessToken = jwtUtil.makeAuthToken(user);
        responseHeaders.set("Access-Token", "auth_token:" + newAccessToken);

        // 리프레시 토큰의 만료 시간 확인
        DecodedJWT decodedRefreshToken = JWT.decode(refreshToken);
        long tokenExpirationTime = decodedRefreshToken.getClaim("exp").asLong();
        long currentTime = Instant.now().getEpochSecond();

        // 만료 기간이 1주일 미만일 경우 새로운 리프레시 토큰을 발급
        if (tokenExpirationTime - currentTime < 7 * 24 * 60 * 60) {
            String newRefreshToken = jwtUtil.makeRefreshToken(user);
            responseHeaders.set("Refresh-Token", "refresh_token:" + newRefreshToken);
        } else {
            // 만료 기간이 1주일 이상 남았다면 기존의 리프레시 토큰을 그대로 반환
            responseHeaders.set("Refresh-Token", "refresh_token:" + refreshToken);
        }

        return responseHeaders;
    }




    /*
         기존 유저 로그인 로직
     */
    private SocialLoginResponseDto handleExistingUser(Users user) {
        String jwtToken = jwtUtil.makeAuthToken(user);
        String refreshToken = jwtUtil.makeRefreshToken(user);
        return new SocialLoginResponseDto(false, jwtToken, refreshToken);
    }

    /*
    신규 유저 로그인시 -> 생성 로직
     */
    private SocialLoginResponseDto handleNewUser(String email, String name) {
        UserEmailNameDto userEmailNameDto = new UserEmailNameDto();
        userEmailNameDto.setEmail(email);
        userEmailNameDto.setName(name);
        Users user = UserMapper.toUserEntity(userEmailNameDto);
        usersRepository.save(user);

        String jwtToken = jwtUtil.makeAuthToken(user);
        String refreshToken = jwtUtil.makeRefreshToken(user);
        return new SocialLoginResponseDto(true, jwtToken, refreshToken);
    }




}
