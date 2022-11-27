package draen.storage;

import draen.domain.users.UserAttemptInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserAttemptInfoRepository extends CrudRepository<UserAttemptInfo, Long> {
    Iterable<UserAttemptInfo> findUserAttemptInfosByUserIdEquals(Long user_id);
    Optional<UserAttemptInfo> findUserAttemptInfoByIdAndUserIdEquals(Long id, Long userId);

}
