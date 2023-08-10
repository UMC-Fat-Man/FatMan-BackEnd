package UMCFatMan.fatman.domain.weekRank.Repository;

import UMCFatMan.fatman.domain.weekRank.WeekRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeekRankRepository extends JpaRepository<WeekRank,Long> {

    List<WeekRank> findTop10ByYearNumAndWeekNumOrderByDistanceDesc(int yearNum, int weekNum);

    Optional<WeekRank> findByYearNumAndWeekNum(int year, int week);
}
