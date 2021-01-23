package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LeaderboardRepository extends Repository<Rule, RuleIdentifier> {

  @Query(nativeQuery = true, value =
        "SELECT SUM(p.points) AS total, p.idx_end_user_user_id AS userId, ROW_NUMBER() OVER (ORDER BY SUM(p.points) DESC) as rank "
      + "FROM end_user_point_award p "
      + "WHERE p.idx_end_user_app_name = :app "
      + "AND p.idx_category_name = :category "
      + "GROUP BY p.idx_end_user_user_id "
      + "LIMIT :pagesize OFFSET :offset")
  List<LeaderboardEntry> findLeaderboardEntries(String app, String category, int pagesize, long offset);
}