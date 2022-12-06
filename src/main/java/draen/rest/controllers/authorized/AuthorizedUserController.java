package draen.rest.controllers.authorized;

import draen.dto.user.UserGetDto;
import draen.rest.controllers.UserControllerUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizedUserController {
    private final UserControllerUtils utils;
    @GetMapping("/id/{userId}")
    public ResponseEntity<UserGetDto> userById(@PathVariable long userId) {
        return utils.getWrapper().wrapOk(utils.getUserOr(userId), UserGetDto.class);
    }

    @PostMapping("/id/{userId}/logout")
    public ResponseEntity<?> logout(@PathVariable String userId) {
        return ResponseEntity.ok(null);
    }
}
