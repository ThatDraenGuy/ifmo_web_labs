package draen.rest.controllers;

import draen.domain.users.User;
import draen.domain.users.UserRepository;
import draen.dto.DtoMapper;
import draen.dto.UserPostDto;
import draen.dto.UserGetDto;
import draen.exceptions.UserIdNotFoundException;
import draen.exceptions.UsernameTakenException;
import draen.rest.modelassemblers.UserGetDtoModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository repository;
    private final UserGetDtoModelAssembler userGetDtoModelAssembler;
    private final DtoMapper dtoMapper;

    @GetMapping("/id/{userId}")
    public EntityModel<UserGetDto> userById(@PathVariable long userId) {
        try {
            return userGetDtoModelAssembler.toModel(dtoMapper.toUserGetDto(getUser(userId)));
        } catch (UserIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user", e);
        }
    }
    @GetMapping("/{username}")
    public EntityModel<UserGetDto> userByUsername(@PathVariable String username) {
        User user = repository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user"));
        return userGetDtoModelAssembler.toModel(dtoMapper.toUserGetDto(user));
    }

    @GetMapping
    public CollectionModel<EntityModel<UserGetDto>> all() {
        return userGetDtoModelAssembler.toCollectionModel(dtoMapper.toUserGetDtos(repository.findAll()));
    }

    @PostMapping
    public EntityModel<UserGetDto> add(@RequestBody UserPostDto userPostDto) {
        try {
            User user = repository.save(dtoMapper.toUser(userPostDto));
            return userGetDtoModelAssembler.toModel(dtoMapper.toUserGetDto(user));
        } catch (UsernameTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return repository.existsUserByUsername(username);
    }

    public User getUser(long userId) throws UserIdNotFoundException {
        return repository.findById(userId).orElseThrow(() -> new UserIdNotFoundException("No such user"));
    }
}
