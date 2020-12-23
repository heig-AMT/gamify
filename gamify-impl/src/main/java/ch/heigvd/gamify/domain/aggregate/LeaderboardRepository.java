package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LeaderboardRepository extends Repository<Rule, RuleIdentifier> {

  @Query(nativeQuery = true, value =
        "SELECT SUM(r.points) AS total, e.user_id AS user, ROW_NUMBER() OVER (ORDER BY SUM(r.points) DESC) as rank "
      + "FROM Rule r INNER JOIN Event e ON r.event_type = e.type AND r.app = e.app_name "
      + "WHERE e.app_name = :app "
      + "AND r.category_name = :category "
      + "GROUP BY e.user_id "
      + "LIMIT :pagesize OFFSET :offset")
  List<LeaderboardEntry> findLeaderboardEntries(String app, String category, int pagesize, int offset);
}