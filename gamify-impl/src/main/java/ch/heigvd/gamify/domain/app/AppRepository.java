package ch.heigvd.gamify.domain.app;

import ch.heigvd.gamify.domain.app.App;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository<App, String> {

  Optional<App> findByToken(String token);
  Optional<App> findByNameAndPassword(String name, String password);
}
