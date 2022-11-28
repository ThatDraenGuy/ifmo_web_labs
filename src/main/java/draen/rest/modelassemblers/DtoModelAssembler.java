package draen.rest.modelassemblers;

import draen.dto.Dto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface DtoModelAssembler<T extends Dto<?>> extends RepresentationModelAssembler<T, EntityModel<T>> {
}
