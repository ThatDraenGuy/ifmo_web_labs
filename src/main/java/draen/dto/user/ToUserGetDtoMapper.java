package draen.dto.user;

import draen.domain.users.User;
import draen.dto.DtoMapperDetails;
import draen.dto.ToDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ToUserGetDtoMapper extends ToDtoMapper<User, UserGetDto> {
}
