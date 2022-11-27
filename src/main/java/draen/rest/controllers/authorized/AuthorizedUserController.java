package draen.rest.controllers.authorized;

import draen.dto.UserGetDto;
import draen.rest.controllers.UserController;
import draen.rest.modelassemblers.UserGetDtoModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizedUserController extends UserController {
    private final UserGetDtoModelAssembler userGetDtoModelAssembler;

    @GetMapping("/id/{userId}")
    public EntityModel<UserGetDto> userById(@PathVariable long userId) {
        return wrapToUserGetDto(userId);
    }
    @GetMapping("/{username}")
    public EntityModel<UserGetDto> userByUsername(@PathVariable String username) {
        return wrapToUserGetDto(username);
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return repository.existsUserByUsername(username);
    }
}
