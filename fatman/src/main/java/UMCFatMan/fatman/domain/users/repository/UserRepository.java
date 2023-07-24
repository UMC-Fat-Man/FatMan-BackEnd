package UMCFatMan.fatman.domain.users.repository;

import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
}
