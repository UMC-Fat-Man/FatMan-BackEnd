package UMCFatMan.fatman.domain.weekRank.Controller;

import UMCFatMan.fatman.domain.weekRank.DTO.WeekRankPutRequestDto;
import UMCFatMan.fatman.domain.weekRank.DTO.WeekRankResponseDto;
import UMCFatMan.fatman.domain.weekRank.Service.WeekRankService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekRank")
public class WeekRankController {

    @Autowired
    WeekRankService weekRankService;

    @GetMapping("/")
    public ResponseEntity<?> getWeekRank() {
        return new ResponseEntity<>(weekRankService.getWeekRank(), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> createWeekRank(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody WeekRankPutRequestDto dto) {
        return new ResponseEntity<>(weekRankService.putWeekRank(user,dto),HttpStatus.OK);
    }

    @GetMapping("/{year}/{week}")
    public ResponseEntity<?>  getTopWeekRank(@PathVariable("year") int year, @PathVariable("week") int week){
        return new ResponseEntity<>(weekRankService.getTopWeekRank(year,week),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public String updateWeekRank(@PathVariable("id") Long id) {
        return "updateWeekRank";
    }

    @DeleteMapping("/{id}")
    public String delteWeekRank(@PathVariable("id") Long id){
        return "deleteWeekRank";
    }

}
