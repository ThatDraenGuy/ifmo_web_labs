package draen.storage;

import draen.domain.users.UserAttemptInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAttemptInfoRepository extends CrudRepository<UserAttemptInfo, Long> {
    Iterable<UserAttemptInfo> findByUserIdEquals(Long user_id);
    Optional<UserAttemptInfo> findByIdAndUserIdEquals(Long id, Long userId);

}
