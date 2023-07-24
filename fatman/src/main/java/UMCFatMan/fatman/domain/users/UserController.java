package UMCFatMan.fatman.domain.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("api/users/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequestDto signupRequestDto){
        userService.signUp(signupRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
