package constraints;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckboxOptions extends Options{
    public static final String NAME = "checkbox_options";
    public CheckboxOptions(@JsonProperty("options") double[] options) {
        super(options);
    }
}
