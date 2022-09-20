package info;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public record AttemptInfo(String x, String y, String r, boolean res, String message, Duration execTime, LocalDateTime currTime) {

    public static AttemptInfo fromHit(HttpServletRequest req, double x, double y, double r, boolean res, String message) {
        return new AttemptInfo(String.valueOf(x), String.valueOf(y), String.valueOf(r), res, message, getDiff(req), LocalDateTime.now());
    }

    public static AttemptInfo fail(HttpServletRequest req, String message) {
        return new AttemptInfo(req.getParameter("x"),req.getParameter("y"),req.getParameter("r"),false,message, getDiff(req), LocalDateTime.now());
    }

    public static AttemptInfo empty() {
        return new AttemptInfo("","","",false,"",Duration.ZERO,LocalDateTime.now());
    }

    private static Duration getDiff(HttpServletRequest req) {
        try {
            Instant start = (Instant) req.getAttribute("startTime");
            Instant finish = Instant.now();
            Duration duration = Duration.between(start, finish);
            System.out.println(duration.toNanos());
            return duration;
        } catch (ClassCastException e) {
            return Duration.ZERO;
        }
    }


    @Override
    public String toString() {
        return "AttemptInfo{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                ", res=" + res +
                ", message='" + message + '\'' +
                ", execTime=" + execTime +
                ", currTime=" + currTime +
                '}';
    }
}
