package info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record AttemptInfo(
        @JsonProperty("x") String x,
        @JsonProperty("y") String y,
        @JsonProperty("r") String r,
        @JsonProperty("res") boolean res,
        @JsonProperty("message") String message,
        @JsonIgnore Duration execTime,
        @JsonIgnore LocalDateTime currTime
) {

    public static AttemptInfo fromHit(Instant startTime, double x, double y, double r, boolean res, String message) {
        return new AttemptInfo(String.valueOf(x), String.valueOf(y), String.valueOf(r), res, message, getDiff(startTime), LocalDateTime.now());
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
            return getDiff(start);
        } catch (ClassCastException e) {
            return Duration.ZERO;
        }
    }
    private static Duration getDiff(Instant start) {
        Instant finish = Instant.now();
        Duration duration = Duration.between(start, finish);
        return duration;
    }

    public void toHtmlRow(StringBuilder builder, int num) {
        builder.append("<tr>");
        toDataCell(builder, num);
        toDataCell(builder, x);
        toDataCell(builder, y);
        toDataCell(builder, r);
        toDataCell(builder, message);
        toDataCell(builder, execTime.toNanos()/1000 +" mks");
        toDataCell(builder, currTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
        builder.append("</tr>");
    }
    private void toDataCell(StringBuilder builder, Object value) {
        builder.append("<td class=\"history-cell\">").append(value).append("</td>");
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
