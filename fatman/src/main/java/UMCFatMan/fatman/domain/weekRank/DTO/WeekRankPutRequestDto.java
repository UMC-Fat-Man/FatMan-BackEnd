package UMCFatMan.fatman.domain.weekRank.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeekRankPutRequestDto {

    private int weekNum;

    private int yearNum;

    private int monsterNum;

    private int distance;

}
