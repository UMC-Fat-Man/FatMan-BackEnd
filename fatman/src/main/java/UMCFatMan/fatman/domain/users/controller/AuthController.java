package UMCFatMan.fatman.domain.users.controller;

import UMCFatMan.fatman.domain.users.dto.*;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.mapper.UserMapper;
import UMCFatMan.fatman.domain.users.repository.UsersRepository;
import UMCFatMan.fatman.domain.users.service.AuthService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
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
    */
    @GetMapping("/refresh")
    public ResponseEntity<Void> jwtAuthorize(HttpServletRequest request) {
        // 헤더에서 토큰 가져오기
        String refreshToken = request.getHeader("Refresh-Token").replace("refresh_token:", "");
        String accessToken = request.getHeader("Access-Token").replace("auth_token:", "");

        HttpHeaders headers = authService.jwtAuthorize(refreshToken, accessToken);
        return ResponseEntity.ok().headers(headers).build();
    }




}
