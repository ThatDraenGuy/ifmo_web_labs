package draen.rest.controllers.authorized;

import draen.components.AreaShooterComponent;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import draen.dto.UserAttemptInfoDto;
import draen.dto.CoordInfoDto;
import draen.dto.DtoMapper;
import draen.exceptions.UserIdNotFoundException;
import draen.rest.controllers.UserController;
import draen.rest.modelassemblers.UserAttemptInfoDtoModelAssembler;
import draen.storage.UserAttemptInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users/id/{userId}/attempts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAttemptsController extends UserController {
    private final AreaShooterComponent areaShooterComponent;
    private final UserAttemptInfoRepository repository;
    private final UserAttemptInfoDtoModelAssembler assembler;
    private final DtoMapper dtoMapper;



    @GetMapping
    public CollectionModel<EntityModel<UserAttemptInfoDto>> allAttempts(@PathVariable long userId) {
        return assembler.toCollectionModel(dtoMapper.toUserAttemptInfoDtos(repository.findAttemptInfosByUserIdEquals(userId)));
    }

    @GetMapping("/{attemptId}")
    public EntityModel<UserAttemptInfoDto> oneAttempt(@PathVariable long userId, @PathVariable long attemptId) {
        return assembler.toModel(dtoMapper.toUserAttemptInfoDto(repository.findAttemptInfoByIdAndUserIdEquals(attemptId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "attempt not found"))));
    }

    @PostMapping("/shoot")
    public EntityModel<UserAttemptInfoDto> shoot(@RequestBody CoordInfoDto coordInfoDto, @PathVariable long userId) {
        try {
            User user = getUser(userId);
            CoordInfo coordInfo = dtoMapper.toCoordInfo(coordInfoDto);
            UserAttemptInfo attemptInfo = areaShooterComponent.shoot(coordInfo, user);
            return assembler.toModel(dtoMapper.toUserAttemptInfoDto(attemptInfo));
        } catch (UserIdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
