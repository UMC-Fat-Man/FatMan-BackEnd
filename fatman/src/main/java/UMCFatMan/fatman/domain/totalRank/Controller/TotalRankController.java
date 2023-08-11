package UMCFatMan.fatman.domain.totalRank.Controller;

import UMCFatMan.fatman.domain.totalRank.Service.TotalRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/totalRank")
@RequiredArgsConstructor
public class TotalRankController {

    private final TotalRankService totalRankService;
    @GetMapping()
    public ResponseEntity<?> getTotalRank() {
        return new ResponseEntity<>(totalRankService.getTotalRank(), HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopTotalRank() {
        return new ResponseEntity<>(totalRankService.getTopTotalRank(), HttpStatus.OK);
    }

}
