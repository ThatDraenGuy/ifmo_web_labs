package draen.dto.attempt;

import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import draen.dto.ToDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ToAttemptInfoDtoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ToUserAttemptInfoDtoMapper implements ToDtoMapper<UserAttemptInfo, UserAttemptInfoDto> {
    @Override
    @Mapping(target = "userId", source = "user")
    public abstract UserAttemptInfoDto toDto(UserAttemptInfo item);

    @Override
    @Mapping(target = "userId", source = "user")
    public abstract Iterable<UserAttemptInfoDto> toDtos(Iterable<UserAttemptInfo> items);

    public Long userMapper(User user) {
        return user.getId();
    }
}
