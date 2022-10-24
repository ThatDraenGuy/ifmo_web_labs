package domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.sqm.UnknownEntityException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Entity
@Table(name = "attempts")
@Getter
public class AttemptInfo {
    @Setter
    @Id
    @GeneratedValue
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

//    public static AttemptInfo fail(HttpServletRequest req, String message) {
//        return new AttemptInfo(req.getParameter("x"),req.getParameter("y"),req.getParameter("r"),false,message, getDiff(req), LocalDateTime.now());
//    }

//    public static AttemptInfo empty() {
//        return new AttemptInfo(0,0,-1,false,"",Duration.ZERO,LocalDateTime.now());
//    }

//    private static Duration getDiff(HttpServletRequest req) {
//        try {
//            Instant start = (Instant) req.getAttribute("startTime");
//            return getDiff(start);
//        } catch (ClassCastException e) {
//            return Duration.ZERO;
//        }
//    }
    private static Duration getDiff(Instant start) {
        Instant finish = Instant.now();
        return Duration.between(start, finish);
    }

//    public void toHtmlRow(StringBuilder builder, int num) {
//        builder.append("<tr>");
//        toDataCell(builder, num);
//        toDataCell(builder, x);
//        toDataCell(builder, y);
//        toDataCell(builder, r);
//        toDataCell(builder, message);
//        toDataCell(builder, execTime.toNanos()/1000 +" mks");
//        toDataCell(builder, currTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
//        builder.append("</tr>");
//    }
//    private void toDataCell(StringBuilder builder, Object value) {
//        builder.append("<td class=\"history-cell\">").append(value).append("</td>");
//    }

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
