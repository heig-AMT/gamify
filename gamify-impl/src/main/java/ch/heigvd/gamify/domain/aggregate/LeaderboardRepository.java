package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LeaderboardRepository extends Repository<Rule, RuleIdentifier> {

  @Query("SELECT SUM(r.points) AS total, e.user AS user "
                  + "FROM Rule r INNER JOIN Event e ON r.eventType = e.type AND r.id.app = e.app "
                  + "WHERE e.app = :app "
                  + "AND r.category.idCategory.name = :category "
                  + "GROUP BY e.user "
                  + "ORDER BY total")
  List<LeaderboardEntry> findLeaderboardEntries(String app, String category);
}