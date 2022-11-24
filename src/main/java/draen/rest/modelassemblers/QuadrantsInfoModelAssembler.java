package draen.rest.modelassemblers;

import draen.domain.quadrants.QuadrantsInfo;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class QuadrantsInfoModelAssembler implements RepresentationModelAssembler<QuadrantsInfo, EntityModel<QuadrantsInfo>> {
    @Override
    public @NonNull EntityModel<QuadrantsInfo> toModel(@NonNull QuadrantsInfo entity) {
        return EntityModel.of(entity);
    }
}
