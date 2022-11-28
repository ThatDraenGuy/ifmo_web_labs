package draen.dto.attempt;

import draen.domain.attempts.CoordInfo;
import draen.dto.FullDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CoordInfoDtoMapper extends FullDtoMapper<CoordInfo, CoordInfoDto> {
}
