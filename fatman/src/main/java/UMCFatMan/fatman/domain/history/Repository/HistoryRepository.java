package UMCFatMan.fatman.domain.history.Repository;

import UMCFatMan.fatman.domain.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
}
