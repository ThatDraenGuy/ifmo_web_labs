package ui;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@FacesConverter("localeDateTimeConverter")
public class ZonedDateTimeConverter implements Converter<ZonedDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss O").withZone(ZoneId.of(CDI.current().select(TimeZoneBean.class).get().getTimezone()));
    @Override
    public ZonedDateTime getAsObject(FacesContext context, UIComponent component, String value) {
        return ZonedDateTime.parse(value, formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ZonedDateTime value) {
        return formatter.format(value);
    }
}
