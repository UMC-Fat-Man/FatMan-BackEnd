package UMCFatMan.fatman.domain.history.Controller;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.history.Service.HistoryService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {


    @Autowired
    HistoryService historyService;

    @GetMapping()
    public ResponseEntity<?> getHistory() {
        return new ResponseEntity<>(historyService.getHistory(), HttpStatus.OK);
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
