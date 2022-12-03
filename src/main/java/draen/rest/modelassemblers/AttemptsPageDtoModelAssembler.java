package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import draen.dto.attempt.AttemptsPageDto;
import draen.rest.controllers.authorized.UserAttemptsController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class AttemptsPageDtoModelAssembler implements DtoModelAssembler<AttemptsPageDto>{
    @Override
    public @NonNull EntityModel<AttemptsPageDto> toModel(@NonNull AttemptsPageDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserAttemptsController.class).allAttempts(entity.getUserId())).withRel("attempts")
                );
    }
}
