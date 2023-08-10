package UMCFatMan.fatman.domain.history.DTO;

import UMCFatMan.fatman.domain.history.History;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponseDto {

    private Long id;
    private Users user;
    private String date;
    private int monsterNum;
    private double distance;

    public static HistoryResponseDto toDTO(History entity){
        return HistoryResponseDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .date(entity.getDate())
                .monsterNum(entity.getMonsterNum())
                .distance(entity.getDistance())
                .build();
    }

}
