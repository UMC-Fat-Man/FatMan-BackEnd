package UMCFatMan.fatman.domain.totalRank.Service;

import UMCFatMan.fatman.domain.totalRank.DTO.TotalRankRequestDto;
import UMCFatMan.fatman.domain.totalRank.DTO.TotalRankResponseDto;
import UMCFatMan.fatman.domain.totalRank.Repository.TotalRankRepository;
import UMCFatMan.fatman.domain.totalRank.TotalRank;
import UMCFatMan.fatman.global.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TotalRankService {

    @Autowired
    TotalRankRepository totalRankRepository;

    public List<TotalRankResponseDto> getTotalRank() {
        List<TotalRank> totalRanks = totalRankRepository.findAll();
        List<TotalRankResponseDto> totalRankResponseDtos = totalRanks.stream().map(TotalRankResponseDto::toDTO).toList();
        return totalRankResponseDtos;
    }

    public List<TotalRankResponseDto> getTopTotalRank() {
        List<TotalRank> Top10TotalRanks = totalRankRepository.findTop10byOrderByDistanceDesc();
        List<TotalRankResponseDto> totalRankResponseDtos = Top10TotalRanks.stream().map(TotalRankResponseDto::toDTO).toList();
        return totalRankResponseDtos;
    }

    public TotalRankResponseDto putTotalRank(UserDetailsImpl user, TotalRankRequestDto dto) {

        Optional<TotalRank> totalRank = totalRankRepository.findByUser(user.getUser());

        if (totalRank != null) {
            dto.setDistance(dto.getDistance() + totalRank.get().getDistance());
            dto.setMonsterNum(dto.getMonsterNum() + totalRank.get().getMonsterNum());
        }

        TotalRank savedTotalRank = totalRankRepository.save(TotalRank.toEntity(dto, user.getUser()));

        return TotalRankResponseDto.toDTO(savedTotalRank);
    }
}
