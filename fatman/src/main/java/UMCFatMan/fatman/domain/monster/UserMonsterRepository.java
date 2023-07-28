package UMCFatMan.fatman.domain.monster;

import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMonsterRepository extends JpaRepository<UserMonster, String> {

    List<UserMonster> findByUser(Users user);
}