package draen.dto.attempt;

import draen.domain.attempts.AttemptInfo;
import draen.dto.ToDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ShotInfoDtoMapper.class, CoordInfoDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
//@Component
public abstract class ToAttemptInfoDtoMapper implements ToDtoMapper<AttemptInfo, AttemptInfoDto> {
}
