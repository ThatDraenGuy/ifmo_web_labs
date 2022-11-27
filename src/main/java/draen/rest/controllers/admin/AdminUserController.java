package draen.rest.controllers.admin;

import draen.domain.users.User;
import draen.dto.UserGetDto;
import draen.dto.UserPostDto;
import draen.exceptions.UserIdNotFoundException;
import draen.exceptions.UsernameTakenException;
import draen.rest.controllers.UserController;
import draen.rest.modelassemblers.UserGetDtoModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/admin/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminUserController extends UserController {
//    private final UserRepository repository;
    private final UserGetDtoModelAssembler userGetDtoModelAssembler;
//    private final DtoMapper dtoMapper;

    @GetMapping("/id/{userId}")
    public EntityModel<UserGetDto> userById(@PathVariable long userId) {
        return wrapToUserGetDto(userId);
    }
    @GetMapping("/{username}")
    public EntityModel<UserGetDto> userByUsername(@PathVariable String username) {
        return wrapToUserGetDto(username);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserGetDto>> all() {
        return allToUserGetDtos();
    }

    @PostMapping
    public EntityModel<UserGetDto> add(@RequestBody UserPostDto userPostDto) {
        try {
            User user = repository.save(dtoMapper.toUser(userPostDto));
            return wrapToUserGetDto(user);
        } catch (UsernameTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return repository.existsUserByUsername(username);
    }
}
