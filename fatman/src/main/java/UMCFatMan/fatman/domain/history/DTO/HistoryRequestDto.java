package UMCFatMan.fatman.domain.history.DTO;

import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequestDto {

    private Users user;
    private Timestamp date;
    private int monsterNum;
    private int distance;

}
