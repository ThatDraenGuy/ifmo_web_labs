package draen.rest.controllers.everyone;

import draen.domain.users.User;
import draen.dto.UserGetDto;
import draen.dto.UserPostDto;
import draen.dto.UserPublicDto;
import draen.exceptions.UsernameTakenException;
import draen.rest.controllers.UserController;
import draen.rest.modelassemblers.UserGetDtoModelAssembler;
import draen.rest.modelassemblers.UserPublicDtoModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/public/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PublicUserController extends UserController {
//    private final UserRepository repository;
    private final UserPublicDtoModelAssembler userPublicDtoModelAssembler;
    private final UserGetDtoModelAssembler userGetDtoModelAssembler;
//    private final DtoMapper dtoMapper;

    @GetMapping("/{username}")
    public EntityModel<UserPublicDto> userByUsername(@PathVariable String username) {
        return getWrappedUser(username, dtoMapper::toUserPublicDto, userPublicDtoModelAssembler::toModel);
//        User user = repository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user"));
//        return userPublicDtoModelAssembler.toModel(dtoMapper.toUserPublicDto(user));
    }

    @GetMapping
    public CollectionModel<EntityModel<UserPublicDto>> all() {
        return getWrappedUsers(dtoMapper::toUserPublicDtos, userPublicDtoModelAssembler::toCollectionModel);
//        return userPublicDtoModelAssembler.toCollectionModel(dtoMapper.toUserPublicDtos(repository.findAll()));
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

}
