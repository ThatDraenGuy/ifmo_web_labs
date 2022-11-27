package draen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserAttemptInfoDto {
    private Long id;
    private int number;
    private AttemptInfoDto attemptInfo;
    private Long userId;
}
