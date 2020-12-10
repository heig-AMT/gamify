package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.CategoriesApi;
import ch.heigvd.gamify.api.model.Category;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.category.CategoryRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.ServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CategoriesController implements CategoriesApi {

  @Autowired
  ServletRequest request;

  @Autowired
  CategoryRepository categoryRepository;

  private static Category toDto(ch.heigvd.gamify.domain.category.Category category) {
    return new Category()
        .title(category.getTitle())
        .description(category.getDescription())
        .name(category.getName());
  }

  @Transactional
  @Override
  public ResponseEntity<Void> deleteCategory(String name) {
    var exists = categoryRepository.existsById(name);
    if (exists) {
      categoryRepository.deleteById(name);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<List<Category>> getCategories(@Valid Integer page, @Valid Integer size) {
    Pageable pageable = PageRequest.of(
        page == null ? 0 : page,
        size == null ? Integer.MAX_VALUE : size
    );

    var categories = categoryRepository
        .findAllByApp(
            (App) request.getAttribute(ApiKeyFilter.APP_KEY),
            pageable
        )
        .stream()
        .map(CategoriesController::toDto)
        .collect(Collectors.toList());

    return ResponseEntity.ok(
        categories
    );
  }

  @Override
  public ResponseEntity<Category> getCategory(String name) {
    return categoryRepository.findById(name)
        .map(CategoriesController::toDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Transactional
  @Override
  public ResponseEntity<Void> postCategory(@Valid Category category) {
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);

    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(category.getName());

    if (categoryRepository.existsByAppAndName(app, category.getName())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).location(location.toUri()).build();
    } else {
      categoryRepository.save(ch.heigvd.gamify.domain.category.Category.builder()
          .app(app)
          .name(category.getName())
          .title(category.getTitle())
          .description(category.getDescription())
          .build());

      return ResponseEntity.created(location.toUri()).build();
    }
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
