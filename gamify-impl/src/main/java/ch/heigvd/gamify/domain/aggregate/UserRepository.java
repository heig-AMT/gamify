package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<Rule, RuleIdentifier> {

  List<LeaderboardEntry> findUserRankingForAllCategories(String app, String user);
}
