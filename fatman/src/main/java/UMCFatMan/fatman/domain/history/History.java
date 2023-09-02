package UMCFatMan.fatman.domain.history;

import UMCFatMan.fatman.domain.history.DTO.HistoryRequestDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.BaseRankEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "date")
    private LocalDateTime date;

    public static History toHistory(HistoryRequestDto dto, Users user) {
        return History.builder()
                .user(user)
                .date(dto.getDate())
                .monsterNum(dto.getMonsterNum())
                .distance(dto.getDistance())
                .build();
    }

}
