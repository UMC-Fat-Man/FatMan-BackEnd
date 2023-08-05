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
@RequestMapping("/api/history")
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
    public ResponseEntity<?> updateHistory(@PathVariable("id") Long id, @RequestBody HistoryRequestDto historyRequestDto) {
        historyService.updateHistory(id, historyRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable("id") Long id) {
        historyService.deleteHistory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
