package ui;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.Map;


@FacesValidator(value="inputTextValidator")
public class InputTextValidator implements Validator<Double> {
    @Override
    public void validate(FacesContext context, UIComponent component, Double value) throws ValidatorException {
        Map<String,Object> attributes = component.getAttributes();
        double min = (double) attributes.get("min");
        double max = (double) attributes.get("max");
        if (value > max || value < min) {
            FacesMessage message = new FacesMessage("value should be from "+min+" to "+max+"!");
            throw new ValidatorException(message);
        }
    }
}
