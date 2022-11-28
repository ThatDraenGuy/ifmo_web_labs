package draen.rest.controllers;

import draen.domain.users.User;
import draen.rest.Wrapper;
import draen.storage.UserRepository;
import draen.exceptions.UserIdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Getter
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserControllerUtils {
    private UserRepository repository;
    private final Wrapper wrapper;

    public User getUserOr(long userId) throws ResponseStatusException {
        try {
            return getUser(userId);
        } catch (UserIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    public User getUserOr(String username) throws ResponseStatusException {
        try {
            return getUser(username);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    public User getUser(long userId) throws UserIdNotFoundException {
        return repository.findById(userId).orElseThrow(() -> new UserIdNotFoundException("No such user"));
    }
    public User getUser(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
