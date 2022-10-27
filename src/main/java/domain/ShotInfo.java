package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "shot-attempts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShotInfo {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean res;
    private String message;
    private Duration execTime;

    public static ShotInfo create(boolean res, String message, Instant startTime) {
        return new ShotInfo(null,res,message,getDiff(startTime));
    }

    private static Duration getDiff(Instant start) {
        Instant finish = Instant.now();
        return Duration.between(start, finish);
    }

    @Override
    public String toString() {
        return "ShotInfo{" +
                "id=" + id +
                ", res=" + res +
                ", message='" + message + '\'' +
                ", execTime=" + execTime +
                '}';
    }
}

