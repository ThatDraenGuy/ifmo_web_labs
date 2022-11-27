package draen;

import draen.domain.users.UserFactory;
import draen.storage.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TemporalPreloader implements CommandLineRunner {
//    private final AttemptInfoRepository attemptInfoRepository;
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Override
    public void run(String... args) throws Exception {
//        User user = userRepository.save(userFactory.create("test2", "thing"));
//        user.getData().getAttempts().add(AttemptInfo.fromHit(CoordInfo.create(1,1,1), ShotInfo.create(true, "b", Instant.now()), user));
    }
}
