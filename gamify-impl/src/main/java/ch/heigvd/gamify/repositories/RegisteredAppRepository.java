package ch.heigvd.gamify.repositories;

import ch.heigvd.gamify.entities.RegisteredAppEntity;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredAppRepository extends CrudRepository<RegisteredAppEntity, String> {

}
