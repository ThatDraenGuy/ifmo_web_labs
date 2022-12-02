package draen.storage;

import draen.domain.users.UserAttemptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
public class PageOfUserAttemptInfo {
    private Page<UserAttemptInfo> page;
    private long userId;
}
