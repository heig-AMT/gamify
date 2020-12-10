package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.LeaderboardsApi;
import ch.heigvd.gamify.api.model.Ranking;
import ch.heigvd.gamify.domain.aggregate.LeaderboardRepository;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryAggregatesController implements LeaderboardsApi {

  @Autowired
  private LeaderboardRepository leaderboardRepository;

  @Autowired
  private ServletRequest request;

  @Override
  public ResponseEntity<List<Ranking>> getLeaderboard(@ApiParam(value = "name of the category",required=true) @PathVariable("name") String name,
                                                      @ApiParam(value = "") @Valid @RequestParam(value = "page", required = false) Integer page,
                                                      @ApiParam(value = "") @Valid @RequestParam(value = "size", required = false) Integer size) {
    //var pageable = PageRequest.of(page == null ? 0 : page, size == null ? Integer.MAX_VALUE : size);
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);

    var leaderboard = leaderboardRepository.findLeaderboardEntries(app.getName(), name)
            .stream()
            .map(leaderboardEntry -> new Ranking()
                    .points(leaderboardEntry.getTotal())
                    .userId(leaderboardEntry.getUser()))
            .collect(Collectors.toList());

    return ResponseEntity.ok(leaderboard);
  }
}
