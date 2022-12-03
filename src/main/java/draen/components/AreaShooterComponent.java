package draen.components;

import draen.domain.arealogic.AreaShooter;
import draen.domain.arealogic.QuadrantsAreaChecker;
import draen.domain.arealogic.QuadrantsAreaShooter;
import draen.domain.attempts.CoordInfo;
import draen.domain.users.User;
import draen.domain.users.UserAttempt;
import draen.storage.UserAttemptInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaShooterComponent {
    private final AreaShooter areaShooter;
    private final UserAttemptInfoRepository attemptInfoRepository;

    @Autowired
    public AreaShooterComponent(QuadrantsInfoComponent quadrantsInfoComponent, UserAttemptInfoRepository attemptInfoRepository) {
        areaShooter = new QuadrantsAreaShooter(new QuadrantsAreaChecker(quadrantsInfoComponent.getQuadrantsInfo()));
        this.attemptInfoRepository = attemptInfoRepository;
    }

    public UserAttempt shoot(CoordInfo coordInfo, User user) {
        UserAttempt userAttempt = areaShooter.shoot(coordInfo,user);
        UserAttempt saved = attemptInfoRepository.save(userAttempt);
        user.getData().getAttempts().add(saved);
        return saved;
    }
}
