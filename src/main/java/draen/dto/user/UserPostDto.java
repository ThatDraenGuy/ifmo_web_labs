package draen.dto.user;

import draen.domain.users.User;
import draen.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPostDto implements Dto<User> {
    private String username;
    private String password;
}
