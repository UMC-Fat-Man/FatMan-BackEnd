package UMCFatMan.fatman.domain.users.entity;

import UMCFatMan.fatman.global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Table(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth")
    private String birth;

    @Column(name = "auth_provider")
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider ;

    @Column(name = "address")
    private String address;

    @Column(name = "money")
    private int money;

    @Column(name = "activated", nullable = false)
    protected boolean activated = Boolean.TRUE;


    public Users(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void updateMoney(int money){
        this.money = money;
    }

}
