package UMCFatMan.fatman.domain.users.controller;

import UMCFatMan.fatman.domain.users.dto.SignUpRequestDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /*
    //   일반 회원가입 [임시]
    */
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        try {
            userService.signUp(signUpRequestDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }


    }
}
