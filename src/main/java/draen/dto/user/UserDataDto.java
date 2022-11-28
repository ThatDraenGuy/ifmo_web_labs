package draen.dto.user;


import draen.domain.users.UserAttemptInfo;
import draen.domain.users.UserData;
import draen.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDataDto implements Dto<UserData> {
    private List<UserAttemptInfo> attempts;
}
