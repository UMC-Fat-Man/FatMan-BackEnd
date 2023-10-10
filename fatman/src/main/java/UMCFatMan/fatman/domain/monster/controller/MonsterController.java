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
@RequestMapping("/api")
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/random-monster")
    public ResponseEntity<?> getRandomMonster(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return new ResponseEntity<>(monsterService.getRandomMonster(userDetails), HttpStatus.OK);
    }

    @GetMapping("/monster")
    public ResponseEntity<?> getMonster(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return new ResponseEntity<>(monsterService.getAllMonster(userDetails), HttpStatus.OK);
    }


    @PostMapping("/monster")
    public ResponseEntity<?> addMosnter(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestParam("name") String monsterName,
                                        @RequestParam("image") MultipartFile multipartFile) throws IOException
    {
        return new ResponseEntity<>(monsterService.addMonster(userDetails, monsterName, multipartFile), HttpStatus.OK);
    }

    @PutMapping("/monster")
    public ResponseEntity<?> updateMonster(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestParam("name") String monsterName,
                                           @RequestParam("image") MultipartFile multipartFile) throws IOException
    {
        return new ResponseEntity<>(monsterService.updateMonster(userDetails, monsterName, multipartFile), HttpStatus.OK);
    }

    @DeleteMapping("/monster")
    public ResponseEntity<?> deleteMonster(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestParam("name") String monsterName)
    {
        monsterService.deleteMonster(userDetails, monsterName);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
