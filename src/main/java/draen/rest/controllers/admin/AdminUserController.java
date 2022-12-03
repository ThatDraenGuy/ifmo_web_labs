package draen.rest.controllers.admin;

import draen.domain.users.User;
import draen.dto.user.UserGetDto;
import draen.dto.user.UserPostDto;
import draen.exceptions.DtoException;
import draen.rest.controllers.UserControllerUtils;
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
public class AdminUserController {
    private final UserControllerUtils utils;


    @GetMapping("/id/{userId}")
    public EntityModel<UserGetDto> userById(@PathVariable long userId) {
        return utils.getWrapper().assemble(utils.getUserOr(userId), UserGetDto.class);
    }
    @GetMapping("/{username}")
    public EntityModel<UserGetDto> userByUsername(@PathVariable String username) {
        return utils.getWrapper().assemble(utils.getUserOr(username), UserGetDto.class);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserGetDto>> all() {
        return utils.getWrapper().assembleAll(utils.getRepository().findAll(), UserGetDto.class);
    }

    @PostMapping
    public EntityModel<UserGetDto> add(@RequestBody UserPostDto userPostDto) {
        try {
            User user = utils.getRepository().save(utils.getWrapper().unwrap(userPostDto, User.class));
            return utils.getWrapper().assemble(user, UserGetDto.class);
        } catch (DtoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return utils.getRepository().existsByUsername(username);
    }
}
