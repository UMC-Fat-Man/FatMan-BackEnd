package UMCFatMan.fatman.domain.history.Controller;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.history.DTO.HistoryResponseDto;
import UMCFatMan.fatman.domain.history.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/")
    public ResponseEntity<?> getHistory() {
        return new ResponseEntity<>(historyService.getHistory(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createHistory(@RequestBody HistoryRequestDto historyRequestDto) {
        return new ResponseEntity<>(historyService.postHistory(historyRequestDto),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public String updateHistory(@PathVariable("id") Long id) {
        return "updateHistory";
    }

    @DeleteMapping ("/{id}")
    public String deleteHistory(@PathVariable("id") Long id) {
        return "deleteHistory";
    }
}
