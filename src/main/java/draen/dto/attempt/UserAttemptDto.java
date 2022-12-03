package draen.dto.attempt;

import draen.domain.users.UserAttempt;
import draen.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserAttemptDto implements Dto<UserAttempt> {
    private Long id;
    private int number;
    private AttemptDto attempt;
    private Long userId;
}
