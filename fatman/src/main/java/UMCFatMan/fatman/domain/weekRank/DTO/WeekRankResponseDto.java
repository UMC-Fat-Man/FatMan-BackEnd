package UMCFatMan.fatman.domain.weekRank.DTO;

import UMCFatMan.fatman.domain.users.Users;
import UMCFatMan.fatman.domain.weekRank.WeekRank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeekRankResponseDto {

    private Long id;

    private Users user;

    private int weekNum;

    private int yearNum;

    private int monsterNum;

    private int distance;

    public static WeekRankResponseDto toDTO(WeekRank entity){
        return WeekRankResponseDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .weekNum(entity.getWeekNum())
                .yearNum(entity.getYearNum())
                .monsterNum(entity.getMonsterNum())
                .distance(entity.getDistance())
                .build();
    }
}
