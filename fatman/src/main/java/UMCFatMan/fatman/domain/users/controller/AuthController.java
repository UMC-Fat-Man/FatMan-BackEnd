package UMCFatMan.fatman.domain.users.controller;

import UMCFatMan.fatman.domain.users.dto.SocialDetailRequestDto;
import UMCFatMan.fatman.domain.users.dto.SocialLoginRequestDto;
import UMCFatMan.fatman.domain.users.dto.SocialLoginResponseDto;
import UMCFatMan.fatman.domain.users.dto.UserDetailResponseDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.mapper.UserMapper;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import UMCFatMan.fatman.domain.users.service.AuthService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private AuthService authService;


    /*
    //   소셜 회원가입
    */
    @PostMapping
    public ResponseEntity<SocialLoginResponseDto> socialLogin(@RequestBody SocialLoginRequestDto socialLoginRequestDto) {
        SocialLoginResponseDto responseDto = authService.socialLogin(socialLoginRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Refresh-Token", "refresh_token:" + responseDto.getRefreshToken());
        headers.add("Access-Token", "auth_token:" + responseDto.getAccessToken());


        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }


    /*
    //   소셜 신규 유저 회원가입시 추가 정보 받기 및 회원 정보 수정
    */
    @PutMapping("/details")
    public ResponseEntity<UserDetailResponseDto> updateUserDetail(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                  @RequestBody @Validated SocialDetailRequestDto socialDetailRequestDto) throws IOException {
        Users updatedUser = authService.updateDetailUser(socialDetailRequestDto, userDetails);
        UserDetailResponseDto responseDto = UserMapper.toUserDetailResponseDto(updatedUser);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    /*
    //   Jwt 검증 및 재발급
    //   헤더로 리프레시 토큰과 액세스 토큰을 받아와 검증하고 액세스 토큰의 검증에 실패해도 리프레시 토큰이 성공하면 재발급
    */
//    @GetMapping("/refresh")
//    public ResponseEntity<Void> authorize(
//            @RequestHeader("Refresh-Token") String refreshToken,
//            @RequestHeader("Access-Token") String accessToken) {
//        authService.refreshToken(refreshToken, accessToken);
//        return ResponseEntity.ok().build();
//    }



}
