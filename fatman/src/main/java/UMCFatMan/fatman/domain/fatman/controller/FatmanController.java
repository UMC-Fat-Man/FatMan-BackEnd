package UMCFatMan.fatman.domain.fatman.controller;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.service.FatmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fatman")
public class FatmanController {

    private final FatmanService fatmanService;

    /*
    //  팻맨 추가하기
     */
    @PostMapping("/{fatmanId}")
    public ResponseEntity<String> addFatman(
            @PathVariable Long fatmanId,
            @RequestBody AddFatmanRequestDto addFatmanRequestDto) {
        try {
            return fatmanService.addFatman(fatmanId, addFatmanRequestDto);
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
    public ResponseEntity<FatmanResponseDto> getFatmanIdsByUserId(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.notFound().build();
        }
        FatmanResponseDto responseDto = fatmanService.getFatman(userId);
        return ResponseEntity.ok(responseDto);
    }

}