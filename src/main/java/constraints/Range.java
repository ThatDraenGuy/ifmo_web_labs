package constraints;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Range implements Constraint {
    @JsonProperty
    private final double min;
    @JsonProperty
    private final double max;
    @JsonCreator
    public Range(@JsonProperty("min") double min, @JsonProperty("max") double max) {
        this.min = min;
        this.max = max;
    }
    @Override
    public boolean checkValue(double value) {
        return min<=value && value<=max;
    }
}
