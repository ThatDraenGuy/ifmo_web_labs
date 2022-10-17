package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@Named("clockBean")
@SessionScoped
public class ClockBean implements Serializable {

    private String timezone = ZoneId.systemDefault().getId();

    public String getTime() {
        return formattedTime();
    }

    private String formattedTime() {
        return DateTimeFormatter.ofPattern("hh:mm:ss").format(ZonedDateTime.now(ZoneId.systemDefault()));
    }
}
