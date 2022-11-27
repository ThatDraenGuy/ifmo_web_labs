package draen.storage;

import draen.domain.users.UserAttemptInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserAttemptInfoRepository extends CrudRepository<UserAttemptInfo, Long> {
    Collection<UserAttemptInfo> findAttemptInfosByUserIdEquals(Long user_id);
    Optional<UserAttemptInfo> findAttemptInfoByIdAndUserIdEquals(Long id, Long userId);

}
