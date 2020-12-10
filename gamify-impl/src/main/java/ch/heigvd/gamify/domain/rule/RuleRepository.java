package ch.heigvd.gamify.domain.rule;

import ch.heigvd.gamify.domain.app.App;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, RuleIdentifier> {

  Iterable<Rule> findAllById_Category_IdCategory_App(App app);

  Optional<Rule> findById_Category_IdCategory_App_NameAndId_Name(String app, String name);
}
