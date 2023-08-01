package UMCFatMan.fatman.global;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public class BaseRankEntity {

    @Column(name = "monster_num")
    private int monsterNum;

    @Column(name = "distance")
    private int distance;

}
