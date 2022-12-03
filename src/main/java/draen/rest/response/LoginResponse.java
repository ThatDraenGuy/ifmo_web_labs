package draen.rest.response;

import draen.dto.user.UserGetDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserGetDto user;
}
