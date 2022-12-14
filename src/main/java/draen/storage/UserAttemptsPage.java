package draen.storage;

import draen.domain.users.UserAttempt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
public class UserAttemptsPage {
    private Page<UserAttempt> page;
    private long userId;
}
