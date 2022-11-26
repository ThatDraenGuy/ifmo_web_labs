package draen.rest.controllers;

import draen.domain.users.User;
import draen.domain.users.UserRepository;
import draen.rest.modelassemblers.UserModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler userModelAssembler;

    @GetMapping("/users/{userId}")
    public EntityModel<User> oneUser(@PathVariable long userId) {
        return userModelAssembler.toModel(getUser(userId));
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        return userModelAssembler.toCollectionModel(repository.findAll());
    }

    public User getUser(long userId) {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("No such user"));
    }
}
