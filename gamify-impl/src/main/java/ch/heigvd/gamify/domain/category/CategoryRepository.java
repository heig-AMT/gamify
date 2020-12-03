package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {

  Iterable<Category> findAllByApp(App app);
}
