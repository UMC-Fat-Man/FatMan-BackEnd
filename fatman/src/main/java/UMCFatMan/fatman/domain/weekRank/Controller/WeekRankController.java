package UMCFatMan.fatman.domain.weekRank.Controller;

import UMCFatMan.fatman.domain.weekRank.Service.WeekRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController
        ;

@RestController
@RequestMapping("/weekRank")
public class WeekRankController {

    @Autowired
    WeekRankService weekRankService;

    @GetMapping()
    public ResponseEntity<?> getWeekRank() {
        return new ResponseEntity<>(weekRankService.getWeekRank(), HttpStatus.OK);
    }

    @GetMapping("/{year}/{week}")
    public ResponseEntity<?> getTopWeekRank(@PathVariable("year") int year, @PathVariable("week") int week) {
        return new ResponseEntity<>(weekRankService.getTopWeekRank(year, week), HttpStatus.OK);
    }

}
