package UMCFatMan.fatman.domain.history.Controller;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.history.Service.HistoryService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/history")
public class HistoryController {


    @Autowired
    HistoryService historyService;

    @GetMapping
    public ResponseEntity<?> getHistoryByDate(@AuthenticationPrincipal UserDetailsImpl user, @RequestParam("date") LocalDate date) {
        return new ResponseEntity<>(historyService.getHistoryByDate(user, date), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getHistory(@AuthenticationPrincipal UserDetailsImpl user) {
        return new ResponseEntity<>(historyService.getHistory(user), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createHistory(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody HistoryRequestDto historyRequestDto) {
        System.out.println(user.getUser().getName());
        return new ResponseEntity<>(historyService.postHistory(user, historyRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistory(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("id") Long id) {
        System.out.println("user = " + user);
        historyService.deleteHistory(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
