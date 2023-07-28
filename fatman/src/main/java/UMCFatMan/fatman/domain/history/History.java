package UMCFatMan.fatman.domain.history;

import UMCFatMan.fatman.domain.totalRank.TotalRank;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.domain.weekRank.WeekRank;
import UMCFatMan.fatman.global.BaseRankEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history")
public class History extends BaseRankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "week_rank_id")
    private WeekRank weekRank;

    @ManyToOne
    @JoinColumn(name = "total_rank_id")
    private TotalRank totalRank;

    @Column(name = "date")
    private Timestamp date;

}
