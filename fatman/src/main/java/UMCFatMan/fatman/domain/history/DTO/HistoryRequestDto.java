package UMCFatMan.fatman.domain.history.DTO;

import UMCFatMan.fatman.domain.history.History;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequestDto {

    private LocalDateTime date;
    private int monsterNum;
    private double distance;

    public static HistoryRequestDto toDTO(History entity) {
        return HistoryRequestDto.builder()
                .date(entity.getDate())
                .monsterNum(entity.getMonsterNum())
                .distance(entity.getDistance())
                .build();
    }

}
