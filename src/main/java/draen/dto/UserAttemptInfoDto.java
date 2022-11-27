package draen.dto;

import draen.domain.users.UserAttemptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserAttemptInfoDto implements Dto<UserAttemptInfo> {
    private Long id;
    private int number;
    private AttemptInfoDto attemptInfo;
    private Long userId;
}
