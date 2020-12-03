package ch.heigvd.gamify.repositories;

import ch.heigvd.gamify.entities.RegisteredAppEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredAppRepository extends CrudRepository<RegisteredAppEntity, String> {

  Optional<RegisteredAppEntity> findByToken(String token);
  Optional<RegisteredAppRepository> findByNameAndPassword(String name, String password);
}
