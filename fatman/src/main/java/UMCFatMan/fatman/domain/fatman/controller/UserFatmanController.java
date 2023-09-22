package UMCFatMan.fatman.domain.fatman.controller;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.UserFatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.service.UserFatmanService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/userfatman")
public class UserFatmanController {

    private final UserFatmanService userFatmanService;

    /*
    //  유저 팻맨 구매하기
     */
    @PostMapping("/{fatmanId}")
    public ResponseEntity<String> addUserFatman(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long fatmanId ) {
            return userFatmanService.addUserFatman(userDetails, fatmanId);
    }


    /*
    //  유저의 팻맨 조회하기
     */
    @GetMapping
    public ResponseEntity<UserFatmanResponseDto> getUserFatman(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserFatmanResponseDto responseDto = userFatmanService.getUserFatman(userDetails);
        return ResponseEntity.ok(responseDto);
    }


}