package draen.dto;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import draen.domain.users.UserData;
import draen.domain.users.UserFactory;
import draen.dto.attempt.AttemptInfoDto;
import draen.dto.attempt.CoordInfoDto;
import draen.dto.attempt.ShotInfoDto;
import draen.dto.attempt.UserAttemptInfoDto;
import draen.dto.user.UserDataDto;
import draen.dto.user.UserGetDto;
import draen.dto.user.UserPostDto;
import draen.dto.user.UserPublicDto;
import draen.exceptions.UsernameTakenException;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {DtoMapperDetails.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DtoMapper {
//    @Autowired
//    private UserFactory userFactory;
//
//    public abstract AttemptInfoDto toDto(AttemptInfo attemptInfo);
//    @Mapping(target = "userId", source = "user")
//    public abstract UserAttemptInfoDto toDto(UserAttemptInfo attemptInfo);
//    @Mapping(target = "userId", source = "user")
//    public abstract List<UserAttemptInfoDto> toDtos(Iterable<UserAttemptInfo> attemptInfos);
//
//    public abstract CoordInfoDto toDto(CoordInfo coordInfo);
//    public abstract CoordInfo fromDto(CoordInfoDto coordInfoDto);
//
//    public abstract ShotInfoDto toDto(ShotInfo shotInfo);
//    public abstract ShotInfo fromDto(ShotInfoDto shotInfoDto);
//
//    public User fromDto(UserPostDto userPostDto) throws UsernameTakenException {
//        return userFactory.create(userPostDto.getUsername(), userPostDto.getPassword());
//    }
//
//    public abstract UserGetDto toUserGetDto(User user);
//    public abstract List<UserGetDto> toUserGetDtos(Iterable<User> users);
//
//    public abstract UserPublicDto toUserPublicDto(User user);
//    public abstract List<UserPublicDto> toUserPublicDtos(Iterable<User> users);
//
//    public abstract UserDataDto toDto(UserData userData);
//    public abstract UserData fromDto(UserDataDto userDataDto);
}
