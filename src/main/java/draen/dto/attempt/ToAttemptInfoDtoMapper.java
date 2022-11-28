package draen.dto.attempt;

import draen.domain.attempts.AttemptInfo;
import draen.dto.ToDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ToAttemptInfoDtoMapper implements ToDtoMapper<AttemptInfo, AttemptInfoDto> {
}
