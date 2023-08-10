package UMCFatMan.fatman.domain.history.DTO;

import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryTotalRankDto {

    private Users user;

    private int monsterNum;

    private double distance;

    public static HistoryTotalRankDto toHistoryTotalRankDto(HistoryRequestDto dto, Users user) {
        return HistoryTotalRankDto.builder()
                .user(user)
                .monsterNum(dto.getMonsterNum())
                .distance(dto.getDistance())
                .build();
    }

}
