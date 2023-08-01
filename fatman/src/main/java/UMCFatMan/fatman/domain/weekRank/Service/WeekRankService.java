package UMCFatMan.fatman.domain.weekRank.Service;

import UMCFatMan.fatman.domain.weekRank.DTO.WeekRankPutRequestDto;
import UMCFatMan.fatman.domain.weekRank.DTO.WeekRankResponseDto;
import UMCFatMan.fatman.domain.weekRank.Repository.WeekRankRepository;
import UMCFatMan.fatman.domain.weekRank.WeekRank;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeekRankService {

    @Autowired
    WeekRankRepository weekRankRepository;

    public List<WeekRankResponseDto> getWeekRank() {
        List<WeekRank> weekRanks = weekRankRepository.findAll();
        List<WeekRankResponseDto> weekRankResponseDtos = weekRanks.stream()
                .map(WeekRankResponseDto::toDTO).toList();

        return weekRankResponseDtos;
    }

    public List<WeekRankResponseDto> getTopWeekRank(int year, int week) {
        List<WeekRank> Top10WeekRanks = weekRankRepository.findTop10ByWeekNumAndYearNumOrderByDistanceDesc(year, week);
        List<WeekRankResponseDto> weekRankResponseDtos = Top10WeekRanks.stream()
                .map(WeekRankResponseDto::toDTO).toList();

        return weekRankResponseDtos;
    }

    public WeekRankResponseDto putWeekRank(UserDetailsImpl user, WeekRankPutRequestDto dto) {

        Optional<WeekRank> weekRank = weekRankRepository.findByYearNumAndWeekNum(dto.getYearNum(), dto.getWeekNum());

        //해당 주차 정보 있을 떄
        if (weekRank != null) {
            dto.setDistance(dto.getDistance() + weekRank.get().getDistance());
            dto.setMonsterNum(dto.getMonsterNum() + weekRank.get().getMonsterNum());
        }
        WeekRank savedWeekRank = weekRankRepository.save(WeekRank.toEntity(dto, user.getUser()));

        return WeekRankResponseDto.toDTO(savedWeekRank);
    }
}
