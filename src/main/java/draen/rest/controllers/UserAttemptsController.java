package draen.rest.controllers;

import draen.dto.AttemptInfoDto;
import draen.dto.DtoMapper;
import draen.rest.modelassemblers.AttemptInfoDtoModelAssembler;
import draen.domain.attempts.AttemptInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users/id/{userId}/attempts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAttemptsController {
    private final AttemptInfoRepository repository;
    private final AttemptInfoDtoModelAssembler assembler;
    private final DtoMapper dtoMapper;



    @GetMapping
    public CollectionModel<EntityModel<AttemptInfoDto>> allAttempts(@PathVariable long userId) {
        return assembler.toCollectionModel(dtoMapper.toAttemptInfoDtos(repository.findAttemptInfosByUserIdEquals(userId)));
    }

    @GetMapping("/{attemptId}")
    public EntityModel<AttemptInfoDto> oneAttempt(@PathVariable long userId, @PathVariable long attemptId) {
        return assembler.toModel(dtoMapper.toAttemptInfoDto(repository.findAttemptInfoByIdAndUserIdEquals(attemptId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "attempt not found"))));
    }
}
