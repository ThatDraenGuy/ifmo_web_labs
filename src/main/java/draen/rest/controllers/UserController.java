package draen.rest.controllers;

import draen.domain.users.User;
import draen.storage.UserRepository;
import draen.dto.DtoMapper;
import draen.exceptions.UserIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

//@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class UserController {
    @Autowired
    protected UserRepository repository;
    @Autowired
    protected DtoMapper dtoMapper;

    protected <T> EntityModel<T> getWrappedUser(Long id, Function<User, T> dto, Function<T, EntityModel<T>> model) throws ResponseStatusException {
        try {
            return model.apply(dto.apply(getUser(id)));
        } catch (UserIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    protected <T> EntityModel<T> getWrappedUser(String username, Function<User, T> dto, Function<T, EntityModel<T>> model) throws ResponseStatusException {
        try {
            return model.apply(dto.apply(getUser(username)));
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    protected <T> CollectionModel<EntityModel<T>> getWrappedUsers(Function<Iterable<User>, Iterable<T>> dto, Function<Iterable<T>,CollectionModel<EntityModel<T>>> model) {
        return model.apply(dto.apply(repository.findAll()));
    }

    public User getUser(long userId) throws UserIdNotFoundException {
        return repository.findById(userId).orElseThrow(() -> new UserIdNotFoundException("No such user"));
    }
    public User getUser(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
