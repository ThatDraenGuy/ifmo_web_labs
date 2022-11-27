package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import draen.dto.UserAttemptInfoDto;
import draen.rest.controllers.authorized.UserAttemptsController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserAttemptInfoDtoModelAssembler implements RepresentationModelAssembler<UserAttemptInfoDto, EntityModel<UserAttemptInfoDto>> {
    @Override
    public @NonNull EntityModel<UserAttemptInfoDto> toModel(@NonNull UserAttemptInfoDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserAttemptsController.class).oneAttempt(entity.getUserId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(UserAttemptsController.class).allAttempts(entity.getUserId())).withRel("attempts"));
    }
}
