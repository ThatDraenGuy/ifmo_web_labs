package draen;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.AttemptInfoRepository;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import draen.domain.users.User;
import draen.domain.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TemporalPreloader implements CommandLineRunner {
//    private final AttemptInfoRepository attemptInfoRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.save(User.create("test", "thing"));
        user.getData().getAttempts().add(AttemptInfo.fromHit(CoordInfo.create(1,1,1), ShotInfo.create(true, "b", Instant.now()), user));
    }
}
