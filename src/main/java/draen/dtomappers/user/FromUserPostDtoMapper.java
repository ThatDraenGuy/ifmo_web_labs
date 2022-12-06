package draen.dtomappers.user;

import draen.domain.users.User;
import draen.domain.users.UserFactory;
import draen.dtomappers.FromDtoMapper;
import draen.dto.user.UserPostDto;
import draen.exceptions.UsernameTakenException;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class FromUserPostDtoMapper implements FromDtoMapper<User, UserPostDto> {
    @Autowired
    private UserFactory userFactory;
    @Override
    public User fromDto(UserPostDto userPostDto) throws UsernameTakenException {
        return userFactory.create(userPostDto.getUsername(), userPostDto.getPassword());
    }
}
