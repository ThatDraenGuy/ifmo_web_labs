package draen.rest.controllers.admin;

import draen.domain.users.User;
import draen.dto.user.UserGetDto;
import draen.dto.user.UserPostDto;
import draen.exceptions.DtoException;
import draen.rest.controllers.UserControllerUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/admin/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminUserController {
    private final UserControllerUtils utils;


    @GetMapping("/id/{userId}")
    public ResponseEntity<UserGetDto> userById(@PathVariable long userId) {
        return utils.getWrapper().wrapOk(utils.getUserOr(userId), UserGetDto.class);
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserGetDto> userByUsername(@PathVariable String username) {
        return utils.getWrapper().wrapOk(utils.getUserOr(username), UserGetDto.class);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserGetDto>> all() {
        return utils.getWrapper().wrapAllOk(utils.getRepository().findAll(), UserGetDto.class);
    }

    @PostMapping
    public ResponseEntity<UserGetDto> add(@RequestBody UserPostDto userPostDto) {
        try {
            User user = utils.getRepository().save(utils.getWrapper().unwrap(userPostDto, User.class));
            return utils.getWrapper().wrapOk(user, UserGetDto.class);
        } catch (DtoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return utils.getRepository().existsByUsername(username);
    }
}
