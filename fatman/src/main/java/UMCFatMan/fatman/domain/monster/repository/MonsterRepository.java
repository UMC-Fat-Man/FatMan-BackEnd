package UMCFatMan.fatman.domain.monster.repository;

import UMCFatMan.fatman.domain.monster.domain.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, String> {

    Monster findByName(String monsterName);

    Object deleteByName(String name);
}