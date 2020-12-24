package ch.heigvd.gamify.domain.endUser;

import ch.heigvd.gamify.domain.app.App;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EndUserRepository extends
    PagingAndSortingRepository<EndUser, EndUserIdentifier> {
Optional<EndUser> findByIdEndUser_UserIdAndIdEndUser_App(String username, App app);
}
