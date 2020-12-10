package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.RulesApi;
import ch.heigvd.gamify.api.model.Rule;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.rule.RuleRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController implements RulesApi {

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

  private static Rule toDTO(ch.heigvd.gamify.domain.rule.Rule rule) {
    return new Rule()
        .category(rule.getId().getCategory().getIdCategory().getName())
        .event(rule.getEventType())
        .name(rule.getId().getName())
        .points(rule.getPoints());
  }
}
