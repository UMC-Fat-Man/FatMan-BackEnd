package UMCFatMan.fatman.domain.totalRank.Repository;

import UMCFatMan.fatman.domain.totalRank.TotalRank;
import UMCFatMan.fatman.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TotalRankRepository extends JpaRepository<TotalRank,Long> {
    List<TotalRank> findTop10ByOrderByDistanceDesc();
    Optional<TotalRank> findByUser(Users user);
}
