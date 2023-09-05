package UMCFatMan.fatman.domain.history.Repository;

import java.time.LocalDateTime;

public interface GetHistoryMapping {
    Long getId();

    LocalDateTime getDate();

    double getDistance();

    int getMonsterNum()
            ;

}
