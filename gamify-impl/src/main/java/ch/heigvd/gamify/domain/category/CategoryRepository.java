package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
  Iterable<Category> findAllByApp(App app);
  Boolean existsByAppAndName(App app, String name);
}
