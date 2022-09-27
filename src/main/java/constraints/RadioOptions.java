package constraints;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RadioOptions extends Options{
    public static final String NAME = "radio_options";
    public RadioOptions(@JsonProperty("options") double[] options) {
        super(options);
    }
}
