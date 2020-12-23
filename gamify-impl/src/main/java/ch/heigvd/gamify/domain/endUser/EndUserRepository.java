package ch.heigvd.gamify.domain.endUser;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EndUserRepository extends
    PagingAndSortingRepository<EndUser, String> {
Optional<EndUser> findByUserId(String username);
}
