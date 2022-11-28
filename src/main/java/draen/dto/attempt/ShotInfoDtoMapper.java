package draen.dto.attempt;

import draen.domain.attempts.ShotInfo;
import draen.dto.FullDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShotInfoDtoMapper extends FullDtoMapper<ShotInfo, ShotInfoDto> {
}
