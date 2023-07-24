package UMCFatMan.fatman.domain.fatman.repository;

import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FatmanRepository extends JpaRepository<Fatman, Long> {
    Optional<Fatman> findById(Long Id);

}
