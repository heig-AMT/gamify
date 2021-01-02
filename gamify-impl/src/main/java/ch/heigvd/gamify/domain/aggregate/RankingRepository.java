package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RankingRepository extends Repository<Rule, RuleIdentifier> {
  @Query(nativeQuery = true, value =
          "WITH subquery AS ( "
          + "SELECT p.points AS total, p.idx_user_user_id AS userId, ROW_NUMBER() OVER (ORDER BY p.points DESC) as rank "
          + "FROM end_user_points p "
          + "WHERE p.idx_user_app_name = :app "
          + "AND p.idx_category_name = :category "
          + ") "
          + "SELECT s.total, s.userId, s.rank "
          + "FROM subquery s "
          + "WHERE userId = :userId ")
  Optional<RankingEntry> findRankingEntryForUserAndCategory(String app, String userId, String category);
}
