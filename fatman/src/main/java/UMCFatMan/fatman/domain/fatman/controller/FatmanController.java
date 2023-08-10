package UMCFatMan.fatman.domain.fatman.controller;

import UMCFatMan.fatman.domain.fatman.dto.FatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.mapper.FatmanMapper;
import UMCFatMan.fatman.domain.fatman.service.FatmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fatman")
public class FatmanController {

    private final FatmanService fatmanService ;


    /*
    //  팻맨 추가하기
     */
    @PostMapping
    public FatmanResponseDto addFatman (@ModelAttribute FatmanRequestDto fatmanRequestDto) throws IOException {
        Fatman fatman = fatmanService.addFatman(fatmanRequestDto);
        return FatmanMapper.fromEntity(fatman);
    }


    /*
    //  팻맨 수정하기
     */
    @PutMapping("/{fatmanId}")
    public FatmanResponseDto updateFatman (@PathVariable Long fatmanId, @ModelAttribute FatmanRequestDto fatmanRequestDto) throws IOException {
        FatmanResponseDto fatmanresponseDto = fatmanService.updateFatman(fatmanId, fatmanRequestDto);
        return fatmanresponseDto;
    }


    /*
    //  팻맨 삭제하기
     */
    @DeleteMapping("/{fatmanId}")
    public ResponseEntity<String> deleteFatman(@PathVariable Long fatmanId) {
        fatmanService.deleteFatman(fatmanId);
        return ResponseEntity.ok("Fatman deleted successfully");
    }



}
