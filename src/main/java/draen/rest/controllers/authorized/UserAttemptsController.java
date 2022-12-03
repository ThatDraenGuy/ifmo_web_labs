package draen.rest.controllers.authorized;


import draen.components.AreaShooterComponent;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttempt;
import draen.dto.attempt.AttemptsPageDto;
import draen.dto.attempt.UserAttemptDto;
import draen.rest.Wrapper;
import draen.dto.attempt.CoordInfoDto;
import draen.exceptions.DtoException;
import draen.exceptions.UserIdNotFoundException;
import draen.rest.controllers.UserControllerUtils;
import draen.storage.PageOfUserAttemptInfo;
import draen.storage.UserAttemptInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users/id/{userId}/attempts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAttemptsController {
    private final AreaShooterComponent areaShooterComponent;
    private final UserAttemptInfoRepository repository;
    private final UserControllerUtils utils;
    private final Wrapper wrapper;


    @GetMapping
    public CollectionModel<EntityModel<UserAttemptDto>> allAttempts(@PathVariable long userId) {
        return wrapper.wrapAll(repository.findByUserIdEquals(userId), UserAttemptDto.class);
    }

    @GetMapping("/page/{page}/{size}")
    public EntityModel<AttemptsPageDto> pageOfAttempts(@PathVariable int page, @PathVariable int size, @PathVariable long userId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("number").ascending());
        Page<UserAttempt> attemptsPage = repository.findAllByUserIdEquals(userId, pageable);
        return wrapper.wrap(new PageOfUserAttemptInfo(attemptsPage, userId), AttemptsPageDto.class);
    }

    @GetMapping("/{attemptId}")
    public EntityModel<UserAttemptDto> oneAttempt(@PathVariable long userId, @PathVariable long attemptId) {
        return wrapper.wrap(repository.findByIdAndUserIdEquals(userId, attemptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "attempt not found")),
                UserAttemptDto.class);
    }

    @PostMapping("/shoot")
    public EntityModel<UserAttemptDto> shoot(@RequestBody CoordInfoDto coordInfoDto, @PathVariable long userId) {
        try {
            User user = utils.getUser(userId);
            CoordInfo coordInfo = wrapper.unwrap(coordInfoDto, CoordInfo.class);
            UserAttempt attemptInfo = areaShooterComponent.shoot(coordInfo, user);
            return wrapper.wrap(attemptInfo, UserAttemptDto.class);
        } catch (UserIdNotFoundException | DtoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/clear")
    @Transactional
    public void clear(@PathVariable long userId) {
        repository.deleteByUserIdEquals(userId);
    }
}
