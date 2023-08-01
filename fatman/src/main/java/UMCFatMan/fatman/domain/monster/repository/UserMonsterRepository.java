package UMCFatMan.fatman.domain.monster.repository;

import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.domain.UserMonster;
import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMonsterRepository extends JpaRepository<UserMonster, String> {

    List<UserMonster> findByUser(Users user);

    boolean existsByUserAndMonster(Users user, Monster monster);
}