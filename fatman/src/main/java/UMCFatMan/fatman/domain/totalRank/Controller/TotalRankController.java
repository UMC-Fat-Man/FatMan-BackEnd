package UMCFatMan.fatman.domain.totalRank.Controller;

import UMCFatMan.fatman.domain.totalRank.DTO.TotalRankRequestDto;
import UMCFatMan.fatman.domain.totalRank.DTO.TotalRankResponseDto;
import UMCFatMan.fatman.domain.totalRank.Service.TotalRankService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/totalRank")
public class TotalRankController {

    @Autowired
    TotalRankService totalRankService;

    @GetMapping("/")
    public ResponseEntity<?> getTotalRank() {

        return new ResponseEntity<>(totalRankService.getTotalRank(), HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopTotalRank() {
        return new ResponseEntity<>(totalRankService.getTopTotalRank(), HttpStatus.OK);
    }

    @PatchMapping("/")
    public ResponseEntity<?> postTotalRank(@AuthenticationPrincipal UserDetailsImpl user , @RequestBody TotalRankRequestDto dto) {
        return new ResponseEntity<>(totalRankService.putTotalRank(user, dto),HttpStatus.OK);
    }

}
