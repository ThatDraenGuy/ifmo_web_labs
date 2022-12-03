package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import draen.dto.attempt.UserAttemptDto;
import draen.rest.controllers.authorized.UserAttemptsController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class UserAttemptDtoModelAssembler implements DtoModelAssembler<UserAttemptDto> {
    @Override
    public @NonNull EntityModel<UserAttemptDto> toModel(@NonNull UserAttemptDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserAttemptsController.class).oneAttempt(entity.getUserId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(UserAttemptsController.class).allAttempts(entity.getUserId())).withRel("attempts"));
    }
}
