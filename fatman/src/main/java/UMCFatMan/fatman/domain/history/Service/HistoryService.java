package UMCFatMan.fatman.domain.history.Service;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.history.DTO.HistoryResponseDto;
import UMCFatMan.fatman.domain.history.DTO.HistoryTotalRankDto;
import UMCFatMan.fatman.domain.history.DTO.HistoryWeekRankDto;
import UMCFatMan.fatman.domain.history.History;
import UMCFatMan.fatman.domain.history.Repository.GetHistoryDate;
import UMCFatMan.fatman.domain.history.Repository.GetHistoryMapping;
import UMCFatMan.fatman.domain.history.Repository.HistoryRepository;
import UMCFatMan.fatman.domain.totalRank.Service.TotalRankService;
import UMCFatMan.fatman.domain.weekRank.Service.WeekRankService;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    WeekRankService weekRankService;

    @Autowired
    TotalRankService totalRankService;

    @Transactional
    public List<GetHistoryDate> getHistory(UserDetailsImpl user) {

        List<GetHistoryDate> histories = historyRepository.findAllByUser_Id(user.getUser().getId());

        return histories;
    }

    @Transactional
    public List<HistoryResponseDto> getHistoryByDate(UserDetailsImpl user, LocalDate date) {
        LocalDateTime startDatetime = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),0,0,0);
        LocalDateTime endDatetime = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),23,59,59);

        List<GetHistoryMapping> histories = historyRepository.findAllByUser_IdAndDateBetween(user.getUser().getId(), startDatetime,endDatetime);

        return histories.stream().map(HistoryResponseDto::toDTO).toList();
    }

    @Transactional
    public History postHistory(UserDetailsImpl user,HistoryRequestDto dto) {

        try{
            Map<String, Integer> weekAndyear = stringTodate(dto.getDate());
            int week = weekAndyear.get("week");
            int year = weekAndyear.get("year");
            HistoryWeekRankDto historyWeekRankDto = HistoryWeekRankDto.toHistoryWeekRankDto(dto, user.getUser(), week, year);
            HistoryTotalRankDto historyTotalRankDto = HistoryTotalRankDto.toHistoryTotalRankDto(dto, user.getUser());
            weekRankService.updateWeekRank("patch",historyWeekRankDto);
            totalRankService.updateTotalRank("patch",historyTotalRankDto);
        }
        catch (Exception e){
            System.out.println(e);
        }

        History history = History.toHistory(dto, user.getUser());
        return historyRepository.save(history);
    }

    @Transactional
    public void deleteHistory(UserDetailsImpl user, Long id) {

        Optional<History> history = historyRepository.findById(id);
        HistoryRequestDto dto = HistoryRequestDto.toDTO(history.get());

        try{
            Map<String, Integer> weekAndyear = stringTodate(history.get().getDate());
            int week = weekAndyear.get("week");
            int year = weekAndyear.get("year");
            HistoryWeekRankDto historyWeekRankDto = HistoryWeekRankDto.toHistoryWeekRankDto(dto, user.getUser(), week, year);
            HistoryTotalRankDto historyTotalRankDto = HistoryTotalRankDto.toHistoryTotalRankDto(dto, user.getUser());
            weekRankService.updateWeekRank("delete",historyWeekRankDto);
            totalRankService.updateTotalRank("delete",historyTotalRankDto);
        }
        catch (Exception e){
        }

        historyRepository.deleteById(id);
    }


    //문자열을 시간으로
    public Map<String, Integer> stringTodate(LocalDateTime datestr) throws ParseException {

        int weekNumber = datestr.get(WeekFields.ISO.weekOfYear());

        Map<String, Integer> dateInfo = new HashMap<>();

        dateInfo.put("week", weekNumber);
        dateInfo.put("year", datestr.getYear());
        dateInfo.put("day", datestr.getDayOfMonth());
        return dateInfo;
    }
}
