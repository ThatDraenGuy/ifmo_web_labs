package draen.rest.controllers;

import draen.config.QuadrantsInfoWrapper;
import draen.domain.quadrants.QuadrantsInfo;
import draen.rest.modelassemblers.QuadrantsInfoModelAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuadrantsInfoController {
    private final QuadrantsInfoWrapper quadrantsInfoWrapper;
    private final QuadrantsInfoModelAssembler assembler;

    @GetMapping("/quadrants")
    public EntityModel<QuadrantsInfo> get() {
        return assembler.toModel(quadrantsInfoWrapper.getQuadrantsInfo());
    }
}
