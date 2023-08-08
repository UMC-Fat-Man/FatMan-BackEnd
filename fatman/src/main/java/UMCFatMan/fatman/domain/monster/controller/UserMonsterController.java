package UMCFatMan.fatman.domain.monster.controller;

import UMCFatMan.fatman.domain.monster.dto.MonsterRequestDto;
import UMCFatMan.fatman.domain.monster.dto.UserMonsterRequestDto;
import UMCFatMan.fatman.domain.monster.service.MonsterService;
import UMCFatMan.fatman.domain.monster.service.UserMonsterService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserMonsterController {

    private final UserMonsterService userMonsterService;

    @GetMapping("/user_monster")
    public ResponseEntity<?> getUserMonster(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseEntity<>(userMonsterService.getUserMonster(userDetails), HttpStatus.OK);
    }

    @PostMapping("/user_monster")
    public ResponseEntity<?> postUserMonster(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @RequestBody UserMonsterRequestDto userMonsterRequestDto)
    {
        return new ResponseEntity<>(userMonsterService.postUserMonster(userMonsterRequestDto, userDetails), HttpStatus.OK );
    }

}
