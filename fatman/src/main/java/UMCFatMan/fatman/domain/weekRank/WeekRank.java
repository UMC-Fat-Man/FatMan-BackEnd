package UMCFatMan.fatman.domain.weekRank;

import UMCFatMan.fatman.domain.history.History;
import UMCFatMan.fatman.global.BaseRankEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "week_rank")
public class WeekRank extends BaseRankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "weekRank")
    private List<History> historyList = new ArrayList<>();

}
