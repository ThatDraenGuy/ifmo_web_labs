package draen.security;

import draen.storage.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserSecurity {
    private final UserRepository userRepository;
    public boolean hasUserId(Authentication authentication, Long userId) {
        try {
            String name = authentication.getName();
            Long id = userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("No such user")).getId();
            return id.equals(userId);
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    public boolean hasUsername(Authentication authentication, String username) {
        return authentication.getName().equals(username);
    }
}
