package draen.rest.modelassemblers;

import draen.dto.user.UserPublicDto;
import draen.rest.controllers.everyone.PublicUserController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserPublicDtoModelAssembler implements DtoModelAssembler<UserPublicDto> {
    @Override
    public @NonNull EntityModel<UserPublicDto> toModel(@NonNull UserPublicDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PublicUserController.class).userByUsername(entity.getUsername())).withSelfRel(),
                linkTo(methodOn(PublicUserController.class).all()).withRel("users")
                );
    }
}
