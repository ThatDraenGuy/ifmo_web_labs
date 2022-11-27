package draen.components;

import draen.domain.arealogic.AreaShooter;
import draen.domain.arealogic.QuadrantsAreaChecker;
import draen.domain.arealogic.QuadrantsAreaShooter;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttemptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaShooterComponent {
    private final AreaShooter areaShooter;

    public AreaShooterComponent(@Autowired QuadrantsInfoComponent quadrantsInfoComponent) {
        areaShooter = new QuadrantsAreaShooter(new QuadrantsAreaChecker(quadrantsInfoComponent.getQuadrantsInfo()));
    }

    public UserAttemptInfo shoot(CoordInfo coordInfo, User user) {
        return areaShooter.shoot(coordInfo,user);
    }
}
