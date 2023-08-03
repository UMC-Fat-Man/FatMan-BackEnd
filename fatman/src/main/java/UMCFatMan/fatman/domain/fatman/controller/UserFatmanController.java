package UMCFatMan.fatman.domain.fatman.controller;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.UserFatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.service.UserFatmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/userfatman")
public class UserFatmanController {

    private final UserFatmanService userFatmanService;

    /*
    //  유저 팻맨 추가하기
     */
    @PostMapping("/{fatmanId}")
    public ResponseEntity<String> addUserFatman(
            @PathVariable Long fatmanId,
            @RequestBody AddFatmanRequestDto addFatmanRequestDto) {
        try {
            return userFatmanService.addUserFatman(fatmanId, addFatmanRequestDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fatman already exists for this user.");
        }
    }


    /*
    //  유저의 팻맨 조회하기
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserFatmanResponseDto> getUserFatman(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.notFound().build();
        }
        UserFatmanResponseDto responseDto = userFatmanService.getUserFatman(userId);
        return ResponseEntity.ok(responseDto);
    }


}