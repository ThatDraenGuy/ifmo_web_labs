package draen.rest.controllers.admin;

import draen.domain.users.User;
import draen.dto.UserGetDto;
import draen.dto.UserPostDto;
import draen.exceptions.UsernameTakenException;
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
        return utils.wrapToUserGetDto(userId);
    }
    @GetMapping("/{username}")
    public EntityModel<UserGetDto> userByUsername(@PathVariable String username) {
        return utils.wrapToUserGetDto(username);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserGetDto>> all() {
        return utils.allToUserGetDtos();
    }

    @PostMapping
    public EntityModel<UserGetDto> add(@RequestBody UserPostDto userPostDto) {
        try {
            User user = utils.getRepository().save(utils.getDtoMapper().toUser(userPostDto));
            return utils.wrapToUserGetDto(user);
        } catch (UsernameTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return utils.getRepository().existsUserByUsername(username);
    }
}
