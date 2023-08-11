package UMCFatMan.fatman.domain.totalRank;

import UMCFatMan.fatman.domain.history.DTO.HistoryTotalRankDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.BaseRankEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "total_rank")
public class TotalRank extends BaseRankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public static TotalRank toEntity(HistoryTotalRankDto dto) {
        return TotalRank.builder()
                .user(dto.getUser())
                .distance(dto.getDistance())
                .monsterNum(dto.getMonsterNum())
                .build();
    }
}
