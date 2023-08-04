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
            return userFatmanService.addUserFatman(fatmanId, addFatmanRequestDto);
    }


    /*
    //  유저의 팻맨 조회하기
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserFatmanResponseDto> getUserFatman(@PathVariable Long userId) {
        UserFatmanResponseDto responseDto = userFatmanService.getUserFatman(userId);
        return ResponseEntity.ok(responseDto);
    }


}