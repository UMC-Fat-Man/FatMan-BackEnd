package UMCFatMan.fatman.domain.monster.dto;


import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.domain.UserMonster;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserMonsterRequestDto {

    private String monsterName;

    public UserMonster toUserMonster(Monster monster, Users user) {
        return UserMonster.builder()
                .monster(monster)
                .user(user)
                .build();
    }
}
