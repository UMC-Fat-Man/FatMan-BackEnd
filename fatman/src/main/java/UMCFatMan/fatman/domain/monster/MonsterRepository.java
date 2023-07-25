package UMCFatMan.fatman.domain.monster;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, String> {

    Monster findByName(String monsterName);
}