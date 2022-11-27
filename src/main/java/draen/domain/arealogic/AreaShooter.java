package draen.domain.arealogic;

import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;

public interface AreaShooter {
    UserAttemptInfo shoot(CoordInfo coordInfo, User user);
}
