package domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Entity
@Table(name = "attempts")
@Getter
@AllArgsConstructor
public class AttemptInfo {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double x;
    private double y;
    private double r;
    private boolean res;
    private String message;
    private Duration execTime;
    private LocalDateTime currTime;


    public AttemptInfo() {}
    private AttemptInfo(double x, double y, double r, boolean res, String message, Duration execTime, LocalDateTime currTime) {
        this.x=x;
        this.y=y;
        this.r=r;
        this.res=res;
        this.message=message;
        this.execTime=execTime;
        this.currTime=currTime;
    }

    public static AttemptInfo fromHit(Instant startTime, double x, double y, double r, boolean res, String message) {
        return new AttemptInfo(x, y, r, res, message, getDiff(startTime), LocalDateTime.now());
    }

    public static AttemptInfo empty() {
        return new AttemptInfo(0,0,0,false,"",Duration.ZERO,LocalDateTime.MIN);
    }

    private static Duration getDiff(Instant start) {
        Instant finish = Instant.now();
        return Duration.between(start, finish);
    }

    @Override
    public String toString() {
        return "AttemptInfo{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                ", res=" + res +
                ", message='" + message + '\'' +
                ", execTime=" + execTime.toNanos() + "ns" +
                ", currTime=" + currTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")) +
                '}';
    }
}
