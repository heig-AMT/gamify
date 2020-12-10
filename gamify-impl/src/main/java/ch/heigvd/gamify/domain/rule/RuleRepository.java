package ch.heigvd.gamify.domain.rule;

import ch.heigvd.gamify.domain.app.App;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, RuleIdentifier> {

  Iterable<Rule> findAllById_Category_IdCategory_App(App app);
}
