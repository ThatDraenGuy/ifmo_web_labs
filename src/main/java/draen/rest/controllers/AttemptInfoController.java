package draen.rest.controllers;

import draen.rest.modelassemblers.AttemptInfoModelAssembler;
import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.AttemptInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AttemptInfoController {
    private final AttemptInfoRepository repository;
    private final AttemptInfoModelAssembler assembler;


    @GetMapping("/attempts")
    public CollectionModel<EntityModel<AttemptInfo>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/attempts/{id}")
    public EntityModel<AttemptInfo> one(@PathVariable Long id) {
        AttemptInfo attemptInfo = repository.findById(id).orElseThrow(() -> new RuntimeException("bruh no id"));
        return assembler.toModel(attemptInfo);
    }
}
