package draen.domain.attempts;

import draen.domain.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AttemptInfoRepository extends CrudRepository<AttemptInfo, Long> {

    Optional<AttemptInfo> findAttemptInfoByIdAndUser(Long id, User user);

    Collection<AttemptInfo> findAttemptInfosByUser(User user);
}
