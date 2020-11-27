package ch.heigvd.gamify.repositories;

import ch.heigvd.gamify.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, String >
{
}
