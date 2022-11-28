package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import draen.dto.user.UserGetDto;
import draen.rest.controllers.authorized.UserAttemptsController;
import draen.rest.controllers.authorized.AuthorizedUserController;
import draen.rest.controllers.everyone.PublicUserController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserGetDtoModelAssembler implements DtoModelAssembler<UserGetDto> {
    @Override
    public @NonNull EntityModel<UserGetDto> toModel(@NonNull UserGetDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AuthorizedUserController.class).userById(entity.getId())).withSelfRel(),
                linkTo(methodOn(PublicUserController.class).userByUsername(entity.getUsername())).withSelfRel(),
                linkTo(methodOn(UserAttemptsController.class).allAttempts(entity.getId())).withRel("attempts"),
                linkTo(methodOn(PublicUserController.class).all()).withRel("users")
        );
    }
}