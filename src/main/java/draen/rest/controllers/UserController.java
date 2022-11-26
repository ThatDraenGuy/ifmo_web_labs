package draen.rest.controllers;

import draen.domain.users.User;
import draen.domain.users.UserRepository;
import draen.dto.DtoMapper;
import draen.dto.UserGetDto;
import draen.rest.modelassemblers.UserGetDtoModelAssembler;
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
    private final UserGetDtoModelAssembler userGetDtoModelAssembler;
    private final DtoMapper dtoMapper;

    @GetMapping("/users/{userId}")
    public EntityModel<UserGetDto> oneUser(@PathVariable long userId) {
        return userGetDtoModelAssembler.toModel(dtoMapper.toUserGetDto(getUser(userId)));
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<UserGetDto>> all() {
        return userGetDtoModelAssembler.toCollectionModel(dtoMapper.toUserGetDtos(repository.findAll()));
    }

    public User getUser(long userId) {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("No such user"));
    }
}
