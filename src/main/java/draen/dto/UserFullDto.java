package draen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserFullDto {
    private Long id;
    private String username;
    private String password;
    private UserDataDto data;
}
