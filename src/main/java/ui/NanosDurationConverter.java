package ui;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import java.time.Duration;
@FacesConverter("nanosDurationConverter")
public class NanosDurationConverter implements Converter<Duration> {

    @Override
    public Duration getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (!value.contains("ns")) throw new ConverterException("not in nanos");
            long num = Long.parseLong(value.replace("ns", ""));
            return Duration.ofNanos(num);
        } catch (Exception e) {
            throw new ConverterException(e.getMessage());
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Duration value) {
        return value.toNanos()+"ns";
    }
}
