package ch.heigvd.gamify.api.endpoints;

import ch.heigvd.gamify.api.CategoriesApi;
import ch.heigvd.gamify.api.model.Category;
import ch.heigvd.gamify.entities.CategoryEntity;
import ch.heigvd.gamify.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class CategoriesApiController implements CategoriesApi
{
    private final CategoryRepository repository;

    public CategoriesApiController(CategoryRepository repository){
        this.repository=repository;
    }

    @Override
    public ResponseEntity<Void> addCategory(@Valid Category category){
        var entity=this.repository.save(
                CategoryEntity.builder()
                        .name(category.getName())
                        .build()
        );
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(entity.getName());
        return ResponseEntity.created(location.toUri()).build();
    }
}
