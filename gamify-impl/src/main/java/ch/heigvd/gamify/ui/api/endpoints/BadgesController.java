package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.BadgesApi;
import ch.heigvd.gamify.api.model.Badge;
import ch.heigvd.gamify.api.model.Category;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.badges.BadgeRepository;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
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
public class BadgesController implements BadgesApi
{
    @Autowired
    ServletRequest request;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<List<Badge>> getBadges()
    {
        return ResponseEntity.ok(
                StreamSupport.stream(badgeRepository
                        .findAllByApp((App) request.getAttribute(ApiKeyFilter.APP_KEY))
                        .spliterator(), false)
                        .map(BadgesController::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<Badge> getBadge(String name)
    {
        return badgeRepository.findById(name)
                .map(BadgesController::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> putBadge(String name, @Valid Badge badge)
    {
        if (! badge.getName().equals(name))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var category=categoryRepository.findById(badge.getCategory());
        if(category.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        badgeRepository.save(ch.heigvd.gamify.domain.badges.Badge.builder()
                .app((App) request.getAttribute(ApiKeyFilter.APP_KEY))
                .title(badge.getTitle())
                .description(badge.getDescription())
                .name(badge.getName())
                .category(category.get())
                .pointsLower(badge.getPointsLower()==null ? 0 : badge.getPointsLower())
                .pointsUpper(badge.getPointsUpper()==null ? Integer.MAX_VALUE: badge.getPointsUpper())
                .build());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> deleteBadge(String name) {
        var exists = badgeRepository.existsById(name);
        if (exists) {
            badgeRepository.deleteById(name);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private static Badge toDto(ch.heigvd.gamify.domain.badges.Badge badge)
    {
        Badge badgeTemp= new Badge()
                .title(badge.getTitle())
                .description(badge.getDescription())
                .name(badge.getName())
                .category(badge.getCategory().getName());
                badgeTemp.setPointsLower(badge.getPointsLower());
                badgeTemp.setPointsUpper(badge.getPointsUpper());
        return badgeTemp;
    }
}
