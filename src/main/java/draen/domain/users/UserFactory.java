package draen.domain.users;

import draen.exceptions.UsernameTakenException;
import draen.storage.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserFactory {
    private final UserRepository repository;

    public User create(String username, String password) throws UsernameTakenException {
        if (repository.existsByUsername(username)) throw new UsernameTakenException("Username "+username+" is already taken");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        return new User(null, username, encodedPassword, Set.of(UserRole.USER), UserData.create());
    }
}
