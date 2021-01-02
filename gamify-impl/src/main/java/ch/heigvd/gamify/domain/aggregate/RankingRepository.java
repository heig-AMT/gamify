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

@Query(nativeQuery = true, value =
        "SELECT p.points AS total, p.idx_user_user_id AS userId, ROW_NUMBER() OVER (ORDER BY p.points DESC) as rank "
      + "FROM end_user_points p "
      + "WHERE p.idx_user_app_name = :app "
      + "AND p.idx_category_name = :category "
      + "GROUP BY p.idx_user_user_id, p.points "
      + "LIMIT :pagesize OFFSET :offset")

 */
  @Query(nativeQuery = true, value =
          "SELECT p.points AS total "
        + "FROM end_user_points p "
        + "WHERE p.idx_user_app_name = :app "
        + "AND p.idx_user_user_id = :userId "
        + "AND p.idx_category_name = :category "
        + "GROUP BY p.idx_user_user_id, p.points "
        + "LIMIT 1")
  List<RankingEntry> findRankingEntryForUserAndCategory(String app, String userId, String category);
}
