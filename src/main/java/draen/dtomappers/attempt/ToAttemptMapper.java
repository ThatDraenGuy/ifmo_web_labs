package draen.dtomappers.attempt;

import draen.domain.attempts.Attempt;
import draen.dto.attempt.AttemptDto;
import draen.dtomappers.ToDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ShotInfoDtoMapper.class, CoordInfoDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
//@Component
public abstract class ToAttemptMapper implements ToDtoMapper<Attempt, AttemptDto> {
}
