package UMCFatMan.fatman.domain.weekRank;

import UMCFatMan.fatman.domain.history.DTO.HistoryWeekRankDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.BaseRankEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder

@NoArgsConstructor
@Getter
@Entity
@Table(name = "week_rank")
public class WeekRank extends BaseRankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "week_num")
    private int weekNum;

    @Column(name = "year_num")
    private int yearNum;

    public static WeekRank toEntity(HistoryWeekRankDto dto) {
        return WeekRank.builder()
                .user(dto.getUser())
                .weekNum(dto.getWeekNum())
                .yearNum(dto.getYearNum())
                .distance(dto.getDistance())
                .monsterNum(dto.getMonsterNum())
                .build();
    }
}
