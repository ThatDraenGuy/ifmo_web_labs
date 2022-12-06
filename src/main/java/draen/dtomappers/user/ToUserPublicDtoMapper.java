package draen.dtomappers.user;

import draen.domain.users.User;
import draen.dtomappers.ToDtoMapper;
import draen.dto.user.UserPublicDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ToUserPublicDtoMapper extends ToDtoMapper<User, UserPublicDto> {
}
