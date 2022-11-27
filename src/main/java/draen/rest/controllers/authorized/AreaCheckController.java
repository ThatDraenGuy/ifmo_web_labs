package draen.rest.controllers.authorized;

import draen.components.AreaShooterComponent;
import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import draen.dto.UserAttemptInfoDto;
import draen.dto.CoordInfoDto;
import draen.dto.DtoMapper;
import draen.rest.modelassemblers.UserAttemptInfoDtoModelAssembler;
import draen.security.AppUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/areacheck")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AreaCheckController {
    private final AreaShooterComponent areaShooterComponent;
    private final DtoMapper dtoMapper;
    private final UserAttemptInfoDtoModelAssembler assembler;
    private final AuthorizedUserController userController;
    @PostMapping(value = "/shoot", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<UserAttemptInfoDto> shoot(@RequestBody CoordInfoDto coordInfoDto, @AuthenticationPrincipal AppUserDetails appUserDetails) {
        try {
            User user = userController.getUser(appUserDetails.getUsername());
            CoordInfo coordInfo = dtoMapper.toCoordInfo(coordInfoDto);
            UserAttemptInfo attemptInfo = areaShooterComponent.shoot(coordInfo, user);
            return assembler.toModel(dtoMapper.toUserAttemptInfoDto(attemptInfo));
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
