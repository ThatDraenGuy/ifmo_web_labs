package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import draen.domain.attempts.AttemptInfo;
import draen.rest.controllers.AttemptInfoController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AttemptInfoModelAssembler implements RepresentationModelAssembler<AttemptInfo, EntityModel<AttemptInfo>> {
    @Override
    public @NonNull EntityModel<AttemptInfo> toModel(@NonNull AttemptInfo entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AttemptInfoController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(AttemptInfoController.class).all()).withRel("attempts"));
    }
}
