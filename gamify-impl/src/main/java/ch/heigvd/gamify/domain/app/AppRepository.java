package ch.heigvd.gamify.domain.app;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppRepository extends PagingAndSortingRepository<App, String> {
  Optional<App> findByToken(String token);
  Optional<App> findByNameAndPassword(String name, String password);
}
