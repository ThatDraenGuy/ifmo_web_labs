package draen.dto;

import draen.domain.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserGetDto {
    private Long id;
    private String username;
    private Set<UserRole> roles;
//    private UserDataDto data;
}
