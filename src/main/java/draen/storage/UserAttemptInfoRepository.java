package draen.storage;

import draen.domain.users.UserAttemptInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserAttemptInfoRepository extends PagingAndSortingRepository<UserAttemptInfo, Long> {
    Iterable<UserAttemptInfo> findByUserIdEquals(Long userId);
    Optional<UserAttemptInfo> findByIdAndUserIdEquals(Long id, Long userId);

    void deleteByUserIdEquals(Long userId);

    Page<UserAttemptInfo> findAllByUserIdEquals(Long userId, Pageable pageable);
}
