package UMCFatMan.fatman.domain.monster;

import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/user_monster")
    public ResponseEntity<?> getUserMonster(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseEntity<>(monsterService.getUserMonster(userDetails), HttpStatus.OK);
    }

    @PostMapping("/user_monster")
    public ResponseEntity<?> postUserMonster(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserMonsterRequestDto userMonsterRequestDto){
        return new ResponseEntity<>(monsterService.postUserMonster(userMonsterRequestDto, userDetails), HttpStatus.OK );
    }
}
