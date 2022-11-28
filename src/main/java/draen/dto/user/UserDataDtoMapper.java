package draen.dto.user;

import draen.domain.users.UserData;
import draen.dto.FullDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDataDtoMapper extends FullDtoMapper<UserData, UserDataDto> {
}
