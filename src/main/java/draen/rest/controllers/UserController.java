package draen.rest.controllers;

import static draen.rest.controllers.ControllerUtils.*;

import draen.domain.users.User;
import draen.dto.Dto;
import draen.dto.UserGetDto;
import draen.dto.UserPublicDto;
import draen.rest.modelassemblers.UserGetDtoModelAssembler;
import draen.rest.modelassemblers.UserPublicDtoModelAssembler;
import draen.storage.UserRepository;
import draen.dto.DtoMapper;
import draen.exceptions.UserIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

//@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class UserController {
    @Autowired
    protected UserRepository repository;
    @Autowired
    protected DtoMapper dtoMapper;
    @Autowired
    protected UserPublicDtoModelAssembler userPublicDtoModelAssembler;
    @Autowired
    protected UserGetDtoModelAssembler userGetDtoModelAssembler;


    protected EntityModel<UserGetDto> wrapToUserGetDto(Long id) throws ResponseStatusException {
        try {
            return wrapToUserGetDto(getUser(id));
        } catch (UserIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    protected EntityModel<UserGetDto> wrapToUserGetDto(String username) throws UsernameNotFoundException {
        try {
            return wrapToUserGetDto(getUser(username));
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    protected EntityModel<UserGetDto> wrapToUserGetDto(User user) {
        return wrap(user, dtoMapper::toUserGetDto, userGetDtoModelAssembler::toModel);
    }

    protected EntityModel<UserPublicDto> wrapToUserPublicDto(Long id) throws UserIdNotFoundException {
        try {
            return wrapToUserPublicDto(getUser(id));
        } catch (UserIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }    }
    protected EntityModel<UserPublicDto> wrapToUserPublicDto(String username) throws UsernameNotFoundException {
        try {
            return wrapToUserPublicDto(getUser(username));
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }        }
    protected EntityModel<UserPublicDto> wrapToUserPublicDto(User user) {
        return wrap(user, dtoMapper::toUserPublicDto, userPublicDtoModelAssembler::toModel);
    }

    protected CollectionModel<EntityModel<UserPublicDto>> allToUserPublicDtos() {
        return wrapToUserPublicDtos(repository.findAll());
    }
    protected CollectionModel<EntityModel<UserPublicDto>> wrapToUserPublicDtos(Iterable<User> users) {
        return wrap(users, dtoMapper::toUserPublicDtos, userPublicDtoModelAssembler::toCollectionModel);
    }

    protected CollectionModel<EntityModel<UserGetDto>> allToUserGetDtos() {
        return wrapToUserGetDtos(repository.findAll());
    }
    protected CollectionModel<EntityModel<UserGetDto>> wrapToUserGetDtos(Iterable<User> users) {
        return wrap(users, dtoMapper::toUserGetDtos, userGetDtoModelAssembler::toCollectionModel);
    }

    public User getUser(long userId) throws UserIdNotFoundException {
        return repository.findById(userId).orElseThrow(() -> new UserIdNotFoundException("No such user"));
    }
    public User getUser(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
