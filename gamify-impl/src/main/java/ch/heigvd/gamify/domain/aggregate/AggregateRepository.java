package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.category.CategoryIdentifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends
    PagingAndSortingRepository<Category, CategoryIdentifier> {

  List<Category> findAllByIdCategory_App(App app, Pageable pageable);

  boolean existsByIdCategory_AppAndIdCategory_Name(App app, String name);

  void deleteByIdCategory_AppAndIdCategory_Name(App app, String name);

  Optional<Category> findByIdCategory_AppAndIdCategory_Name(App app, String name);
}
