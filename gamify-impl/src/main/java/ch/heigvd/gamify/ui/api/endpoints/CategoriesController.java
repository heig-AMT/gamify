package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.CategoriesApi;
import ch.heigvd.gamify.api.model.Category;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController implements CategoriesApi {

  @Autowired
  ServletRequest request;

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public ResponseEntity<Void> deleteCategory(String name) {
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<List<Category>> getCategories() {
    var categories = StreamSupport.stream(categoryRepository
        .findAllByApp((App) request.getAttribute(ApiKeyFilter.APP_KEY))
        .spliterator(), false)
        .map(category ->
            new Category()
                .name(category.getName())
                .description(category.getDescription())
                .title(category.getTitle())
        )
        .collect(Collectors.toList());

    return ResponseEntity.ok(
        categories
    );
  }

  @Override
  public ResponseEntity<Category> getCategory(String name) {
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<Void> putCategory(String name, @Valid Category category) {
    if (!category.getName().equals(name)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);

    categoryRepository.save(ch.heigvd.gamify.domain.category.Category.builder()
        .app(app)
        .title(category.getTitle())
        .name(category.getName())
        .description(category.getDescription())
        .build());

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
