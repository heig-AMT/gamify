package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RankingRepository extends Repository<Rule, RuleIdentifier> {
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
