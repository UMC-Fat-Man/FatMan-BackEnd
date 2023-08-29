package UMCFatMan.fatman.domain.users.controller;

import UMCFatMan.fatman.domain.fatman.dto.UserFatmanResponseDto;
import UMCFatMan.fatman.domain.users.dto.SignupRequestDto;
import UMCFatMan.fatman.domain.users.dto.UserDetailResponseDto;
import UMCFatMan.fatman.domain.users.service.UserService;
import UMCFatMan.fatman.global.exception.user.UserEmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /*
    //   일반 회원가입
    */
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignupRequestDto signupRequestDto){
        try {
            userService.signUp(signupRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new UserEmailAlreadyExistsException();
        }
    }


    /*
    //   내 정보 조회
    */
    @GetMapping
    public ResponseEntity<UserDetailResponseDto> getUserInfo (@AuthenticationPrincipal UserDetails userDetails) {
        UserDetailResponseDto userDetailResponseDto = userService.getUserInfo(userDetails);
        return ResponseEntity.ok(userDetailResponseDto);
    }


    /*
    //   유저 탈퇴 -> 휴면계정을 전환
    */
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
