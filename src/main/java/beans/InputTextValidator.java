package beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.Map;


@FacesValidator(value="inputTextValidator")
public class InputTextValidator implements Validator<String> {
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        Map<String,Object> attributes = component.getAttributes();
        Object min = attributes.get("min");
        Object max = attributes.get("max");
        try {
            double maxNum = Double.parseDouble(max.toString());
            double minNum = Double.parseDouble(min.toString());
            double valNum = Double.parseDouble(value);
            if (valNum > maxNum || valNum < minNum) {
                FacesMessage message = new FacesMessage("value should be from "+minNum+" to "+maxNum+"!");
                throw new ValidatorException(message);
            }
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage("value should be a number");
            throw new ValidatorException(message);
        }
    }
}
