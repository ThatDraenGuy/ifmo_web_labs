package draen.dto.attempt;

import draen.domain.attempts.CoordInfo;
import draen.dto.FullDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface CoordInfoDtoMapper extends FullDtoMapper<CoordInfo, CoordInfoDto> {
}
