package draen.storage;

import draen.domain.users.UserAttempt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserAttemptInfoRepository extends PagingAndSortingRepository<UserAttempt, Long> {
    Iterable<UserAttempt> findByUserIdEquals(Long userId);
    Optional<UserAttempt> findByIdAndUserIdEquals(Long id, Long userId);

    void deleteByUserIdEquals(Long userId);

    Page<UserAttempt> findAllByUserIdEquals(Long userId, Pageable pageable);
}
