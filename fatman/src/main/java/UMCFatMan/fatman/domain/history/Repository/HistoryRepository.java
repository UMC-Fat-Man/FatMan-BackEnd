package UMCFatMan.fatman.domain.history.Repository;

import UMCFatMan.fatman.domain.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {

    List<GetHistoryMapping> findAllByUser_IdAndDateBetween(Long id, LocalDateTime start, LocalDateTime end);
}
