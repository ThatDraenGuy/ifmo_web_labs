package draen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserGetDto {
    private Long id;
    private String username;
    private UserDataDto data;
}
