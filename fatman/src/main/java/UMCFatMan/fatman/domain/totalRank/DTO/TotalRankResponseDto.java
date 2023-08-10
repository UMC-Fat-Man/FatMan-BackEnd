package UMCFatMan.fatman.domain.totalRank.DTO;

import UMCFatMan.fatman.domain.totalRank.TotalRank;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TotalRankResponseDto {

    private Long id;

    private Users user;

    private int monsterNum;

    private double distance;

    public static TotalRankResponseDto toDTO(TotalRank entity){
        return TotalRankResponseDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .distance(entity.getDistance())
                .monsterNum(entity.getMonsterNum())
                .build();
    }
}
