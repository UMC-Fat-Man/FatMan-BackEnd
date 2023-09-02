package UMCFatMan.fatman.domain.history.DTO;

import UMCFatMan.fatman.domain.history.History;
import UMCFatMan.fatman.domain.history.Repository.GetHistoryMapping;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponseDto {

    private Long id;
    private LocalDateTime date;
    private int monsterNum;
    private double distance;

    public static HistoryResponseDto toDTO(GetHistoryMapping entity){
        return HistoryResponseDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .monsterNum(entity.getMonsterNum())
                .distance(entity.getDistance())
                .build();
    }

}
