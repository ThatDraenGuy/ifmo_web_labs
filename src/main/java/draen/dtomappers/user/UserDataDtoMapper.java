package draen.dtomappers.user;

import draen.domain.users.UserData;
import draen.dtomappers.FullDtoMapper;
import draen.dto.user.UserDataDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDataDtoMapper extends FullDtoMapper<UserData, UserDataDto> {
}
