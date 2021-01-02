package ch.heigvd.gamify.domain.endUserPoints;

import ch.heigvd.gamify.domain.category.Category;
import ch.heigvd.gamify.domain.endUser.EndUser;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EndUserPointsRepository extends PagingAndSortingRepository<EndUserPoints, EndUserPointsIdentifier> {
  Optional<EndUserPoints> findByIdEndUserPoints_IdxCategoryAndIdEndUserPoints_IdxUser(Category category, EndUser endUser);
}
