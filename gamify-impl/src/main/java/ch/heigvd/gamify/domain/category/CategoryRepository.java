package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, CategoryIdentifier> {

  Iterable<Category> findAllByIdCategory_App(App app);

  boolean existsByIdCategory_AppAndIdCategory_Name(App app, String name);

  void deleteByIdCategory_AppAndIdCategory_Name(App app, String name);

  Optional<Category> findByIdCategory_AppAndIdCategory_Name(App app, String name);
}
