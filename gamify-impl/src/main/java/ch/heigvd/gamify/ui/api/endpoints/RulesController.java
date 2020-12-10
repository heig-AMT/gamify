package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.RulesApi;
import ch.heigvd.gamify.api.model.Rule;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.domain.rule.RuleIdentifier;
import ch.heigvd.gamify.domain.rule.RuleRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.ServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RulesController implements RulesApi {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private ServletRequest request;

  @Override
  public ResponseEntity<List<Rule>> getRules() {
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);
    var rules = StreamSupport
        .stream(ruleRepository.findAllById_Category_IdCategory_App(app).spliterator(), false)
        .map(RulesController::toDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(rules);
  }

  @Transactional
  @Override
  public ResponseEntity<Void> postRule(@Valid Rule rule) {
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);
    var previous = ruleRepository.findById_Category_IdCategory_App_NameAndId_Name(
        app.getName(),
        rule.getName()
    );

    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(rule.getName());
    var category = categoryRepository
        .findByIdCategory_AppAndIdCategory_Name(app, rule.getCategory());

    if (previous.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).location(location.toUri()).build();
    }

    if (category.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    ruleRepository.save(ch.heigvd.gamify.domain.rule.Rule.builder()
        .eventType(rule.getEvent())
        .points(rule.getPoints())
        .id(RuleIdentifier.builder()
            .category(category.get())
            .name(rule.getName())
            .build())
        .build()
    );

    return ResponseEntity.created(location.toUri()).build();
  }

  private static Rule toDTO(ch.heigvd.gamify.domain.rule.Rule rule) {
    return new Rule()
        .category(rule.getId().getCategory().getIdCategory().getName())
        .event(rule.getEventType())
        .name(rule.getId().getName())
        .points(rule.getPoints());
  }
}
