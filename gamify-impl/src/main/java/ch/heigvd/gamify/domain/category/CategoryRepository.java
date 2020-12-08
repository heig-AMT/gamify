package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {

  Iterable<Category> findAllByIdCategory_App(App app);
  Boolean existsByIdCategory_AppAndIdCategory_Name(App app, String name);
  Boolean existsByIdCategory_Name(String name);
  void deleteByIdCategory_AppAndIdCategory_Name(App app, String name);
  Optional<Category> findByIdCategory_AppAndIdCategory_Name(App app, String name);
}
