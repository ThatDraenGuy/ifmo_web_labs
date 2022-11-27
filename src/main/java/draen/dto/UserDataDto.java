package draen.dto;

import draen.domain.attempts.AttemptInfo;
import draen.domain.users.UserAttemptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDataDto {
    private List<UserAttemptInfo> attempts;
}
