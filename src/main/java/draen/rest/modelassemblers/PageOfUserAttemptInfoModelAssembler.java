package draen.rest.modelassemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import draen.dto.attempt.PageOfUserAttemptInfoDto;
import draen.rest.controllers.authorized.UserAttemptsController;
import lombok.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class PageOfUserAttemptInfoModelAssembler implements DtoModelAssembler<PageOfUserAttemptInfoDto>{
    @Override
    public @NonNull EntityModel<PageOfUserAttemptInfoDto> toModel(@NonNull PageOfUserAttemptInfoDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserAttemptsController.class).allAttempts(entity.getUserId())).withRel("attempts")
                );
    }
}
