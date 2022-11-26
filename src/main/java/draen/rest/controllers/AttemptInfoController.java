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

    private final UserController userController;

//
//    @GetMapping("/attempts")
//    public CollectionModel<EntityModel<AttemptInfo>> all() {
//        return assembler.toCollectionModel(repository.findAll());
//    }
//
//    @GetMapping("/attempts/{id}")
//    public EntityModel<AttemptInfo> one(@PathVariable Long id) {
//        return assembler.toModel(getAttemptInfo(id));
//    }


    @GetMapping("/users/{userId}/attempts")
    public CollectionModel<EntityModel<AttemptInfo>> allAttempts(@PathVariable long userId) {
        return assembler.toCollectionModel(repository.findAttemptInfosByUser(userController.getUser(userId)));
    }

    @GetMapping("/users/{userId}/attempts/{attemptId}")
    public EntityModel<AttemptInfo> oneAttempt(@PathVariable long userId, @PathVariable long attemptId) {
        return assembler.toModel(repository.findAttemptInfoByIdAndUser(attemptId, userController.getUser(userId))
                .orElseThrow(() -> new RuntimeException("attempt not found")));
    }


//    private AttemptInfo getAttemptInfo(Long id) {
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("bruh no id"));
//    }
}
