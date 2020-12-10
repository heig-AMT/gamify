package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
  List<Category> findAllByApp(App app, Pageable pageable);
  Boolean existsByAppAndName(App app, String name);
}
