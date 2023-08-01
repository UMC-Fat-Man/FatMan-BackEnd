package UMCFatMan.fatman.domain.history;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.users.Users;
import UMCFatMan.fatman.global.BaseRankEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
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

    @Column(name = "date")
    private Timestamp date;

    public static History toHistory(HistoryRequestDto dto){
        return History.builder()
                .user(dto.getUser())
                .date(dto.getDate())
                .build();
    }
}
