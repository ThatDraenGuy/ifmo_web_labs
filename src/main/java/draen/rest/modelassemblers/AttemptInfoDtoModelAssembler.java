package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import draen.dto.AttemptInfoDto;
import draen.rest.controllers.AttemptInfoController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AttemptInfoDtoModelAssembler implements RepresentationModelAssembler<AttemptInfoDto, EntityModel<AttemptInfoDto>> {
    @Override
    public @NonNull EntityModel<AttemptInfoDto> toModel(@NonNull AttemptInfoDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AttemptInfoController.class).oneAttempt(entity.getUserId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(AttemptInfoController.class).allAttempts(entity.getUserId())).withRel("attempts"));
    }
}
