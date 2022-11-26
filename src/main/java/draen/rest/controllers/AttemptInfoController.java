package draen.rest.controllers;

import draen.dto.AttemptInfoDto;
import draen.dto.DtoMapper;
import draen.rest.modelassemblers.AttemptInfoDtoModelAssembler;
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
    private final AttemptInfoDtoModelAssembler assembler;
    private final DtoMapper dtoMapper;



    @GetMapping("/users/{userId}/attempts")
    public CollectionModel<EntityModel<AttemptInfoDto>> allAttempts(@PathVariable long userId) {
        return assembler.toCollectionModel(dtoMapper.toAttemptInfoDtos(repository.findAttemptInfosByUserIdEquals(userId)));
    }

    @GetMapping("/users/{userId}/attempts/{attemptId}")
    public EntityModel<AttemptInfoDto> oneAttempt(@PathVariable long userId, @PathVariable long attemptId) {
        return assembler.toModel(dtoMapper.toAttemptInfoDto(repository.findAttemptInfoByIdAndUserIdEquals(attemptId, userId)
                .orElseThrow(() -> new RuntimeException("attempt not found"))));
    }
}
