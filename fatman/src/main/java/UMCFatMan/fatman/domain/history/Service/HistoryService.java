package UMCFatMan.fatman.domain.history.Service;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.history.DTO.HistoryResponseDto;
import UMCFatMan.fatman.domain.history.History;
import UMCFatMan.fatman.domain.history.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Transactional
    public List<HistoryResponseDto> getHistory() {
        List<History> histories = historyRepository.findAll();
        return histories.stream().map(HistoryResponseDto::toDTO).toList();
    }

    @Transactional
    public Object postHistory(HistoryRequestDto dto) {
        History history = History.toHistory(dto);
        return historyRepository.save(history);
    }

}
