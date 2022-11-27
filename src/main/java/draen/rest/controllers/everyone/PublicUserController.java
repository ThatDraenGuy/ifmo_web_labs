package draen.rest.controllers.everyone;

import draen.domain.users.User;
import draen.dto.UserGetDto;
import draen.dto.UserPostDto;
import draen.dto.UserPublicDto;
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
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PublicUserController {
    private final UserControllerUtils utils;

    @GetMapping("/{username}")
    public EntityModel<UserPublicDto> userByUsername(@PathVariable String username) {
        return utils.wrapToUserPublicDto(username);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserPublicDto>> all() {
        return utils.allToUserPublicDtos();
    }

    @PostMapping
    public EntityModel<UserGetDto> add(@RequestBody UserPostDto userPostDto) {
        try {
            User user = utils.getRepository().save(utils.getDtoMapper().toUser(userPostDto));
            return utils.getUserGetDtoModelAssembler().toModel(utils.getDtoMapper().toUserGetDto(user));
        } catch (UsernameTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return utils.getRepository().existsUserByUsername(username);
    }

}
