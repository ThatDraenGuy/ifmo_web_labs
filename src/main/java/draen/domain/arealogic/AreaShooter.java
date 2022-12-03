package draen.domain.arealogic;

import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttempt;

public interface AreaShooter {
    UserAttempt shoot(CoordInfo coordInfo, User user);
}
