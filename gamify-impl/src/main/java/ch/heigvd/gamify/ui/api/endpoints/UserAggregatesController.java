package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.UsersApi;
import ch.heigvd.gamify.api.model.Ranking;
import ch.heigvd.gamify.domain.aggregate.RankingRepository;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.badges.BadgeRepository;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import io.swagger.annotations.ApiParam;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserAggregatesController implements UsersApi {

  @Autowired
  private RankingRepository rankingRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private BadgeRepository badgeRepository;

  @Autowired
  private ServletRequest request;

  @Transactional
  @Override
  public ResponseEntity<List<Ranking>> getUserAggregate(
      @ApiParam(value = "id of the end user", required = true) @PathVariable("id") String id,
      @ApiParam(value = "array of categories to be fetches for the user") @Valid @RequestParam(value = "categories", required = false) List<String> categories) {

    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);

    if (categories == null) {
      Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
      categories = categoryRepository
          .findAllByIdCategory_App(app, pageable)
          .stream()
          .map(category -> category.getIdCategory().getName())
          .collect(Collectors.toList());
    }

    var badges = StreamSupport.stream(badgeRepository
        .findAllByIdBadge_App(app)
        .spliterator(), false)
        .map(BadgesController::toDto)
        .collect(Collectors.toList());

    var rankings = new ArrayList<Ranking>();
    categories.forEach(category -> {
      var badgesCategory = badges.stream().filter(badge -> badge.getCategory().equals(category))
          .collect(Collectors.toList());

      var optRankingEntry = rankingRepository.findRankingEntryForUserAndCategory(app.getName(), id, category);
      if (!optRankingEntry.isEmpty()) {
        var entry = optRankingEntry.get();
        var ranking = new Ranking()
                .category(category)
                .userId(id)
                .rank(entry.getRank())
                .points(entry.getTotal())
                .badges(badgesCategory.stream().filter(
                        badge -> badge.getPointsLower().orElse(0) < entry.getTotal()
                     && badge.getPointsUpper().orElse(Integer.MAX_VALUE) > entry.getTotal()
                ).collect(Collectors.toList()));
        rankings.add(ranking);
      }
    });

    return ResponseEntity.ok(rankings);
  }
}
