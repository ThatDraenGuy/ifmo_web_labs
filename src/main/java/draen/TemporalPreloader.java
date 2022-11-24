package draen;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.AttemptInfoRepository;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TemporalPreloader implements CommandLineRunner {
    private final AttemptInfoRepository attemptInfoRepository;

    @Override
    public void run(String... args) throws Exception {
        this.attemptInfoRepository.save(AttemptInfo.fromHit(CoordInfo.create(1,1,1), ShotInfo.create(true, "That's a hit!", Instant.now())));
    }
}
