package draen.domain.attempts;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AttemptInfoRepository extends CrudRepository<AttemptInfo, Long> {
    Collection<AttemptInfo> findAttemptInfosByUserIdEquals(Long user_id);
    Optional<AttemptInfo> findAttemptInfoByIdAndUserIdEquals(Long id, Long userId);

}
