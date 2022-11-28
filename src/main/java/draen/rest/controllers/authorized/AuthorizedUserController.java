package draen.rest.controllers.authorized;

import draen.dto.user.UserGetDto;
import draen.rest.controllers.UserControllerUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizedUserController {
    private final UserControllerUtils utils;
    @GetMapping("/id/{userId}")
    public EntityModel<UserGetDto> userById(@PathVariable long userId) {
        return utils.getWrapper().wrap(utils.getUserOr(userId), UserGetDto.class);
    }
}
