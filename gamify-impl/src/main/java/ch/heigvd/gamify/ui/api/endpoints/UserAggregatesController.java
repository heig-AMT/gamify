package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.UsersApi;
import ch.heigvd.gamify.api.model.Ranking;
import ch.heigvd.gamify.domain.aggregate.RankingRepository;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    private ServletRequest request;

    @Transactional
    @Override
    public ResponseEntity<List<Ranking>> getUserAggregate(
            @ApiParam(value = "id of the end user", required = true) @PathVariable("id") String id,
            @ApiParam(value = "array of categories to be fetches for the user") @Valid @RequestParam(value = "categories", required = false) List<String> categories) {

        var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);

        if (categories == null) {
            var pageable = PageRequest.of(0, Integer.MAX_VALUE);
            categories = categoryRepository
                    .findAllByIdCategory_App(app, pageable)
                    .stream()
                    .map(category -> category.getTitle())
                    .collect(Collectors.toList());
        }

        var rankings = new ArrayList<Ranking>();
        try {
            categories.forEach(category -> {
                var ranking = rankingRepository.findRankingEntryForUserAndCategory(app.getName(), id, category)
                        .stream()
                        .map(rankingEntry -> new Ranking()
                                .category(rankingEntry.getCategory())
                                .userId(rankingEntry.getUser())
                                //.rank(rankingEntry.getRank()) TODO: Fix the rank thingy
                                .points(rankingEntry.getTotal())
                                .badges(List.of()))
                        .collect(Collectors.toList());
                rankings.add(ranking.get(0));
            });
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rankings);
    }
}
