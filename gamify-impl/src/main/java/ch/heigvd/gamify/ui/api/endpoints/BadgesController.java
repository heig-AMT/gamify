package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.BadgesApi;
import ch.heigvd.gamify.api.model.Badge;
import ch.heigvd.gamify.api.model.Category;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.badges.BadgeIdentifier;
import ch.heigvd.gamify.domain.badges.BadgeRepository;
import ch.heigvd.gamify.domain.category.CategoryIdentifier;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class BadgesController implements BadgesApi {

  @Autowired
  ServletRequest request;

  @Autowired
  BadgeRepository badgeRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public ResponseEntity<List<Badge>> getBadges() {
    List<Badge> bd=StreamSupport.stream(badgeRepository
            .findAllByIdBadge_App((App) request.getAttribute(ApiKeyFilter.APP_KEY))
            .spliterator(), false)
            .map(BadgesController::toDto)
            .collect(Collectors.toList());
    return ResponseEntity.ok(bd);
  }

  @Override
  public ResponseEntity<Badge> getBadge(String name) {
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);
    return badgeRepository.findById(BadgeIdentifier.builder()
        .app(app).badgeName(name).build())
        .map(BadgesController::toDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Void> putBadge(String name, @Valid Badge badge) {
    if (!badge.getName().equals(name)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);
    var category = categoryRepository.findById(CategoryIdentifier.builder()
        .name(badge.getCategory())
        .app(app).build());
    if (category.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    badgeRepository.save(ch.heigvd.gamify.domain.badges.Badge.builder()
        .idBadge(BadgeIdentifier.builder()
            .app(app).badgeName(name).build())
        .title(badge.getTitle())
        .description(badge.getDescription())
        .category(category.get())
        .pointsLower(badge.getPointsLower().orElse(0))
        .pointsUpper(badge.getPointsUpper().orElse(Integer.MAX_VALUE))
        .build());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> deleteBadge(String name) {
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);
    var exists = badgeRepository.existsById(BadgeIdentifier
    .builder().app(app).badgeName(name).build());
    if (exists) {
      badgeRepository.deleteById(BadgeIdentifier
          .builder().app(app).badgeName(name).build());
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private static Badge toDto(ch.heigvd.gamify.domain.badges.Badge badge) {
    Badge badgeTemp = new Badge()
        .title(badge.getTitle()==null ? "t": badge.getTitle())
        .description(badge.getDescription()==null ? "d": badge.getDescription())
        .name(badge.getIdBadge().getBadgeName())
        .category(badge.getCategory().getIdCategory().getName());
    badgeTemp.setPointsLower(JsonNullable.of(badge.getPointsLower()));
    badgeTemp.setPointsUpper(JsonNullable.of(badge.getPointsUpper()));
    return badgeTemp;
  }
}
