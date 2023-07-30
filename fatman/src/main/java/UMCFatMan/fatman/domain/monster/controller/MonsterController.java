package UMCFatMan.fatman.domain.monster.controller;

import UMCFatMan.fatman.domain.monster.dto.MonsterRequestDto;
import UMCFatMan.fatman.domain.monster.dto.UserMonsterRequestDto;
import UMCFatMan.fatman.domain.monster.service.MonsterService;
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
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/user_monster")
    public ResponseEntity<?> getUserMonster(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseEntity<>(monsterService.getUserMonster(userDetails), HttpStatus.OK);
    }

    @PostMapping("/user_monster")
    public ResponseEntity<?> postUserMonster(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @RequestBody UserMonsterRequestDto userMonsterRequestDto)
    {
        return new ResponseEntity<>(monsterService.postUserMonster(userMonsterRequestDto, userDetails), HttpStatus.OK );
    }

    @PostMapping("/monster")
    public ResponseEntity<?> addMosnter(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody MonsterRequestDto monsterRequestDto,
                                        @RequestParam("image") MultipartFile multipartFile) throws IOException
    {
        return new ResponseEntity<>(monsterService.addMonster(userDetails, monsterRequestDto, multipartFile), HttpStatus.OK);
    }

    @PutMapping("/monster")
    public ResponseEntity<?> updateMonster(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestBody MonsterRequestDto monsterRequestDto,
                                           @RequestParam("image") MultipartFile multipartFile) throws IOException
    {
        return new ResponseEntity<>(monsterService.updateMonster(userDetails, monsterRequestDto, multipartFile), HttpStatus.OK);
    }

    @DeleteMapping("/monster")
    public ResponseEntity<?> deleteMonster(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestBody MonsterRequestDto monsterRequestDto)
    {
        return new ResponseEntity<>(monsterService.deleteMonster(userDetails, monsterRequestDto), HttpStatus.OK);

    }
}
