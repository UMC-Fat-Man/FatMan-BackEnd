package UMCFatMan.fatman.domain.totalRank.Service;

import UMCFatMan.fatman.domain.history.DTO.HistoryTotalRankDto;
import UMCFatMan.fatman.domain.totalRank.DTO.TotalRankResponseDto;
import UMCFatMan.fatman.domain.totalRank.Repository.TotalRankRepository;
import UMCFatMan.fatman.domain.totalRank.TotalRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TotalRankService {

    private final TotalRankRepository totalRankRepository;

    @Transactional
    public List<TotalRankResponseDto> getTotalRank() {
        List<TotalRank> totalRanks = totalRankRepository.findAll();
        List<TotalRankResponseDto> totalRankResponseDtos = totalRanks.stream().map(TotalRankResponseDto::toDTO).toList();
        return totalRankResponseDtos;
    }

    @Transactional
    public List<TotalRankResponseDto> getTopTotalRank() {
        List<TotalRank> Top10TotalRanks = totalRankRepository.findTop10ByOrderByDistanceDesc();
        List<TotalRankResponseDto> totalRankResponseDtos = Top10TotalRanks.stream().map(TotalRankResponseDto::toDTO).toList();
        return totalRankResponseDtos;
    }

    @Transactional
    public TotalRankResponseDto updateTotalRank(String state, HistoryTotalRankDto dto) {

        Optional<TotalRank> totalRank = totalRankRepository.findByUser(dto.getUser());

        if (totalRank != null) {
            if (state == "patch") {
                dto.setDistance(dto.getDistance() + totalRank.get().getDistance());
                dto.setMonsterNum(dto.getMonsterNum() + totalRank.get().getMonsterNum());
            } else if (state == "delete") {
                dto.setDistance(totalRank.get().getDistance() - dto.getDistance());
                dto.setMonsterNum(totalRank.get().getMonsterNum() - dto.getMonsterNum());
            }

        }

        TotalRank savedTotalRank = totalRankRepository.save(TotalRank.toEntity(dto));

        return TotalRankResponseDto.toDTO(savedTotalRank);
    }
}
