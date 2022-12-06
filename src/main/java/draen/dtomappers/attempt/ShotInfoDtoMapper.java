package draen.dtomappers.attempt;

import draen.domain.attempts.ShotInfo;
import draen.dto.attempt.ShotInfoDto;
import draen.dtomappers.FullDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.Duration;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ShotInfoDtoMapper implements FullDtoMapper<ShotInfo, ShotInfoDto> {

    public long mapExecTime(Duration execTime) {
        return execTime.toNanos();
    }
    public Duration mapExecTime(long execTime) {
        return Duration.ofNanos(execTime);
    }
}
