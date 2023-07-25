package UMCFatMan.fatman.domain.users;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findUserByEmail(String email);

}