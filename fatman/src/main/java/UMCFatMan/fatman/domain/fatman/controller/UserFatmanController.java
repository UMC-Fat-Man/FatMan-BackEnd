package UMCFatMan.fatman.domain.fatman.controller;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.service.UserFatmanService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fatman")
public class UserFatmanController {

    private final UserFatmanService userFatmanService;

    /*
    //  팻맨 추가하기
     */
    @PostMapping("/{fatmanId}")
    public ResponseEntity<?> addFatman(
            @AuthenticationPrincipal UserDetailsImpl userDetails ,
            @PathVariable Long fatmanId ) {
        try {
            return userFatmanService.addUserFatman(fatmanId, userDetails);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fatman already exists for this user.");
        }
    }




    /*
    //  팻맨 조회하기
     */
    @GetMapping("/{userId}")
    public ResponseEntity<FatmanResponseDto> getFatmanIdsByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userId == null) {
//            return ResponseEntity.notFound().build();
//        }
        FatmanResponseDto responseDto = userFatmanService.getUserFatman(userDetails);
        return ResponseEntity.ok(responseDto);
    }

}