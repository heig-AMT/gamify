package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface LeaderboardRepository extends Repository<Rule, RuleIdentifier> {

  @Query("SELECT SUM(r.points) AS total, e.user AS user "
      + "FROM Rule r INNER JOIN Event e ON r.eventType = e.type AND r.id.app = e.app.name "
      + "WHERE e.app.name = :app "
      + "AND r.category.idCategory.name = :category "
      + "GROUP BY e.user "
      + "ORDER BY total DESC")
  List<LeaderboardEntry> findLeaderboardEntries(String app, String category);
}
