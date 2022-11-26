package draen.dto;

import draen.domain.attempts.AttemptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDataDto {
    private List<AttemptInfo> attempts;
}
