package draen.dto;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import draen.domain.users.User;
import draen.domain.users.UserData;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collection;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = DtoMapperDetails.class)
public interface DtoMapper {
    @Mapping(target = "userId", source = "user")
    AttemptInfoDto toAttemptInfoDto(AttemptInfo attemptInfo);
    @Mapping(target = "userId", source = "user")
    List<AttemptInfoDto> toAttemptInfoDtos(Collection<AttemptInfo> attemptInfos);

    CoordInfoDto toCoordInfoDto(CoordInfo coordInfo);
    CoordInfo toCoordInfo(CoordInfoDto coordInfoDto);

    ShotInfoDto toShotInfoDto(ShotInfo shotInfo);
    ShotInfo toShotInfo(ShotInfoDto shotInfoDto);

    UserFullDto toUserFullDto(User user);
    User toUser(UserFullDto userFullDto);

    UserGetDto toUserGetDto(User user);
    List<UserGetDto> toUserGetDtos(Iterable<User> users);

    UserDataDto toUserDataDto(UserData userData);
    UserData toUserData(UserDataDto userDataDto);
}
