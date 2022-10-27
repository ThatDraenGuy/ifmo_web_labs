package ui;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

@SessionScoped
@Named("timeZoneBean")
public class TimeZoneBean implements Serializable {
    @Getter
    private String timezone = "America/New_York";

    public void updateTimezone() {
        timezone = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timezone");
    }
}
