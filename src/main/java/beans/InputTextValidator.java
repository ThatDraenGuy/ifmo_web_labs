package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Map;


@FacesValidator(value="inputTextValidator")
public class InputTextValidator implements Validator<String>, Serializable {
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        System.out.println("fire");
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        System.out.println(params.get("min"));
        FacesMessage message = new FacesMessage("bruh");
        throw new ValidatorException(message);
    }
}
