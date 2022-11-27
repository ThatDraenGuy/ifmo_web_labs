package draen.dto;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import draen.domain.users.UserData;
import draen.domain.users.UserFactory;
import draen.exceptions.UsernameTakenException;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {DtoMapperDetails.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DtoMapper {
    @Autowired
    private UserFactory userFactory;

    public abstract AttemptInfoDto toAttemptInfoDto(AttemptInfo attemptInfo);
    @Mapping(target = "userId", source = "user")
    public abstract UserAttemptInfoDto toUserAttemptInfoDto(UserAttemptInfo attemptInfo);
    @Mapping(target = "userId", source = "user")
    public abstract List<UserAttemptInfoDto> toUserAttemptInfoDtos(Iterable<UserAttemptInfo> attemptInfos);

    public abstract CoordInfoDto toCoordInfoDto(CoordInfo coordInfo);
    public abstract CoordInfo toCoordInfo(CoordInfoDto coordInfoDto);

    public abstract ShotInfoDto toShotInfoDto(ShotInfo shotInfo);
    public abstract ShotInfo toShotInfo(ShotInfoDto shotInfoDto);

    public User toUser(UserPostDto userPostDto) throws UsernameTakenException {
        return userFactory.create(userPostDto.getUsername(), userPostDto.getPassword());
    }

    public abstract UserGetDto toUserGetDto(User user);
    public abstract List<UserGetDto> toUserGetDtos(Iterable<User> users);

    public abstract UserPublicDto toUserPublicDto(User user);
    public abstract List<UserPublicDto> toUserPublicDtos(Iterable<User> users);

    public abstract UserDataDto toUserDataDto(UserData userData);
    public abstract UserData toUserData(UserDataDto userDataDto);
}
