package UMCFatMan.fatman.domain.fatman.repository;

import UMCFatMan.fatman.domain.fatman.entity.UserFatman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFatmanRepository extends JpaRepository<UserFatman, Long> {

    Optional<UserFatman> findById(Long Id);

    List<UserFatman>findByUserId(Long UserId);

}
