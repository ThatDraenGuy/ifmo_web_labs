package constraints;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Options implements Constraint {
    @JsonProperty
    private final double[] options;
    @JsonCreator
    public Options(@JsonProperty("options") double[] options) {
        this.options = options;
    }
    @Override
    public boolean checkValue(double value) {
        return Arrays.stream(options).anyMatch(option -> option==value);
    }
}
