package ui;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@FacesConverter("localeDateTimeConverter")
public class LocaleDateTimeConverter implements Converter<LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
        return LocalDateTime.parse(value, formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
        return formatter.format(value);
    }
}
