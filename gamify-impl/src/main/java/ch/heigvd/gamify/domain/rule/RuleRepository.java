package ch.heigvd.gamify.domain.rule;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RuleRepository extends PagingAndSortingRepository<Rule, RuleIdentifier> {

  List<Rule> findAllById_App(String app, Pageable pageable);

  Optional<Rule> findById_AppAndId_Name(String app, String name);
}
