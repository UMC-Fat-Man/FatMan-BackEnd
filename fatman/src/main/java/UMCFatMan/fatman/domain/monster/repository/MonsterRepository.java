package UMCFatMan.fatman.domain.monster.repository;

import UMCFatMan.fatman.domain.monster.domain.Monster;
import UMCFatMan.fatman.domain.monster.domain.UserMonster;
import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MonsterRepository extends JpaRepository<Monster, String> {

    Optional<Monster> findByName(String monsterName);

    @Query(value = "SELECT * FROM monster order by RAND() limit 1",nativeQuery = true)
    Monster getRandomMonster();
}