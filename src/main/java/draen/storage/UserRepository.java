package draen.storage;

import draen.domain.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    boolean existsUserByUsername(String username);
}
