package ch.heigvd.gamify.domain.app;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository<App, String> {

  Optional<App> findByToken(String token);

  Optional<App> findByNameAndPassword(String name, String password);
}
