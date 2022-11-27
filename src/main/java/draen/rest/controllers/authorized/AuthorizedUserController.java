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
//    private final UserRepository repository;
    private final UserGetDtoModelAssembler userGetDtoModelAssembler;
//    private final DtoMapper dtoMapper;

    @GetMapping("/id/{userId}")
    public EntityModel<UserGetDto> userById(@PathVariable long userId) {
        return getWrappedUser(userId, dtoMapper::toUserGetDto, userGetDtoModelAssembler::toModel);
//        try {
//            return userGetDtoModelAssembler.toModel(dtoMapper.toUserGetDto(getUser(userId)));
//        } catch (UserIdNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user", e);
//        }
    }
    @GetMapping("/{username}")
    public EntityModel<UserGetDto> userByUsername(@PathVariable String username) {
        return getWrappedUser(username, dtoMapper::toUserGetDto, userGetDtoModelAssembler::toModel);
//        User user = repository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user"));
//        return userGetDtoModelAssembler.toModel(dtoMapper.toUserGetDto(user));
    }

//    @GetMapping
//    public RedirectView all() {
//        //TODO think
////        return userGetDtoModelAssembler.toCollectionModel(dtoMapper.toUserGetDtos(repository.findAll()));
//        return new RedirectView("/api/public/users");
//    }



    @GetMapping("/exists/{username}")
    public boolean usernameExists(@PathVariable String username) {
        return repository.existsUserByUsername(username);
    }
}
