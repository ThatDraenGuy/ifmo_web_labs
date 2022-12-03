package draen.domain.arealogic;

import draen.domain.attempts.Attempt;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttempt;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuadrantsAreaShooter implements AreaShooter{
    private final QuadrantsAreaChecker areaChecker;
    @Override
    public UserAttempt shoot(CoordInfo coordInfo, User user) {
        Attempt attempt = areaChecker.check(coordInfo);
        return UserAttempt.create(attempt, user);
    }
}
