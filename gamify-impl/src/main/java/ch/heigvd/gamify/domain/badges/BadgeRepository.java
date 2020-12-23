package ch.heigvd.gamify.domain.badges;

import ch.heigvd.gamify.domain.app.App;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRepository extends CrudRepository<Badge, BadgeIdentifier> {
  Iterable<Badge> findAllByIdBadge_App(App app);
}
