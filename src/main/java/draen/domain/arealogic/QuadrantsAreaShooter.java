package draen.domain.arealogic;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuadrantsAreaShooter implements AreaShooter{
    private final QuadrantsAreaChecker areaChecker;
    @Override
    public UserAttemptInfo shoot(CoordInfo coordInfo, User user) {
        AttemptInfo attemptInfo = areaChecker.check(coordInfo);
        return UserAttemptInfo.create(attemptInfo, user);
    }
}
