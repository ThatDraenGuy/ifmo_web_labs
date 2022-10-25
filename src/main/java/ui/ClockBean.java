package ui;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@Named("clockBean")
@SessionScoped
public class ClockBean implements Serializable {

    private String timezone = "America/New_York";

    public String getTime() {
        return formattedTime();
    }

    private String formattedTime() {
        return DateTimeFormatter.ofPattern("HH:mm:ss VV").format(ZonedDateTime.now(ZoneId.of(timezone)));
    }

    public void updateTimezone() {
        timezone = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timezone");
    }
}
