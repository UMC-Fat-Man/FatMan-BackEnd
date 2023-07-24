package UMCFatMan.fatman.domain.users.entity;

import UMCFatMan.fatman.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth")
    private String birth;

    @Column(name = "social")
    private String social;

    @Column(name = "address")
    private String address;

    @Column(name = "activated", nullable = false)
    protected boolean activated = Boolean.TRUE;


}
