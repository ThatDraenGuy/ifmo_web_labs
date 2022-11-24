package draen.domain.attempts;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable public class ShotInfo {

    private boolean res;
    private String message;
    private Duration execTime;

    public static ShotInfo create(boolean res, String message, Instant startTime) {
        return new ShotInfo(res,message,getDiff(startTime));
    }

    private static Duration getDiff(Instant start) {
        Instant finish = Instant.now();
        return Duration.between(start, finish);
    }

    @Override
    public String toString() {
        return "ShotInfo{" +
                "res=" + res +
                ", message='" + message + '\'' +
                ", execTime=" + execTime +
                '}';
    }
}

