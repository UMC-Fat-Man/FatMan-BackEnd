package UMCFatMan.fatman.domain.users.repository;


import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    boolean existsByEmail(String email);
    Optional<Users> findById(Long Id);
    Optional<Users> findUsersByEmail(String username);
}