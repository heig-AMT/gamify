package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RankingRepository extends Repository<Rule, RuleIdentifier> {
/*
  @Query("SELECT r.category.idCategory.name AS category, e.user AS user, SUM(r.points) AS total, b.title AS badge "
          + "FROM Rule r "
          + "INNER JOIN Event e ON r.eventType = e.type AND r.id.app = e.app.name "
          + "INNER JOIN Badge b ON b.category = r.category AND b.idBadge.app = r.id.app "
          + "WHERE e.app.name = :app "
          + "AND e.user = :userId "
          + "AND r.category.idCategory.name = :category "
          + "GROUP BY r.category.idCategory.name, e.user, b.title, b.pointsUpper, b.pointsLower "
          + "HAVING SUM(r.points) > b.pointsLower "
          + "AND SUM(r.points) < b.pointsUpper "
          + "ORDER BY total DESC ")
*/

  @Query("SELECT r.category.idCategory.name AS category, e.user AS user, SUM(r.points) AS total "
      + "FROM Rule r "
      + "INNER JOIN Event e ON r.eventType = e.type AND r.id.app = e.app.name "
      + "WHERE e.app.name = :app "
      + "AND e.user = :userId "
      + "AND r.category.idCategory.name = :category "
      + "GROUP BY r.category.idCategory.name, e.user "
      + "ORDER BY total DESC ")
  List<RankingEntry> findRankingEntryForUserAndCategory(String app, String userId, String category);
}
