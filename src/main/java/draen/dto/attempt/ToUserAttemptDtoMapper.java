package draen.dto.attempt;

import draen.domain.users.User;
import draen.domain.users.UserAttempt;
import draen.dto.ToDtoMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ToAttemptMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ToUserAttemptDtoMapper implements ToDtoMapper<UserAttempt, UserAttemptDto> {
    @Override
    @Mapping(target = "userId", source = "user")
    public abstract UserAttemptDto toDto(UserAttempt item);

    @Override
    @Mapping(target = "userId", source = "user")
    public abstract Iterable<UserAttemptDto> toDtos(Iterable<UserAttempt> items);

    public Long userMapper(User user) {
        return user.getId();
    }
}
