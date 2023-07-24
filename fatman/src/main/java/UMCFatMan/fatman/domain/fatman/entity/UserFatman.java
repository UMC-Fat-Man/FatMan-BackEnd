package UMCFatMan.fatman.domain.fatman.entity;

import UMCFatMan.fatman.domain.users.entity.Users;
import UMCFatMan.fatman.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_fatman")
public class UserFatman extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fatman_id")
    private Fatman fatman;

}

