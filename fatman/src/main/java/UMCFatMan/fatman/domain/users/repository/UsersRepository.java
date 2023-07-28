package UMCFatMan.fatman.domain.users.repository;


import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findUserByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Users> findById(Long Id);

}