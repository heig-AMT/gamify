package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.CategoriesApi;
import ch.heigvd.gamify.api.model.Category;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController implements CategoriesApi {

  @Override
  public ResponseEntity<Void> deleteCategory(String name) {
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<Category> getCategory(String name) {
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<Void> putCategory(String name, @Valid Category category) {
    return ResponseEntity.notFound().build();
  }
}
