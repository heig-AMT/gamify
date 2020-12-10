package ch.heigvd.gamify.domain.rule;

import ch.heigvd.gamify.domain.app.App;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RuleRepository extends PagingAndSortingRepository<Rule, RuleIdentifier> {

  List<Rule> findAllById_Category_IdCategory_App(App app, Pageable pageable);

  Optional<Rule> findById_Category_IdCategory_App_NameAndId_Name(String app, String name);

  Optional<Rule> findById_Category_IdCategory_AppAndIdName(App app, String name);
}
