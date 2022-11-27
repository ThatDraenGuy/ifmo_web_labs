package draen.dto;

import draen.domain.users.User;
import draen.domain.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserGetDto implements Dto<User>{
    private Long id;
    private String username;
    private Set<UserRole> roles;
}
