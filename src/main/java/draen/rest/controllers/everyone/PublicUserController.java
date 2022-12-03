package draen.rest.controllers.everyone;

import draen.domain.users.User;
import draen.dto.user.UserGetDto;
import draen.dto.user.UserPostDto;
import draen.dto.user.UserPublicDto;
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
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PublicUserController {
    private final UserControllerUtils utils;

    @GetMapping("/{username}")
    public EntityModel<UserPublicDto> userByUsername(@PathVariable String username) {
        return utils.getWrapper().assemble(utils.getUserOr(username), UserPublicDto.class);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserPublicDto>> all() {
        return utils.getWrapper().assembleAll(utils.getRepository().findAll(), UserPublicDto.class);
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
