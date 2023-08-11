package UMCFatMan.fatman.domain.history.DTO;

import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryWeekRankDto {

    private Users user;

    private int weekNum;

    private int yearNum;

    private int monsterNum;

    private double distance;

    public static HistoryWeekRankDto toHistoryWeekRankDto(HistoryRequestDto dto, Users user,int year, int week) {
        return HistoryWeekRankDto.builder()
                .user(user)
                .monsterNum(dto.getMonsterNum())
                .distance(dto.getDistance())
                .weekNum(week)
                .yearNum(year)
                .build();
    }
}
