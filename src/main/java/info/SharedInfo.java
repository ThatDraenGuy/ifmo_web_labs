package info;


import com.fasterxml.jackson.annotation.JsonProperty;
import constraints.Constraint;
import coordinates.Quadrant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record SharedInfo(
        @JsonProperty("quadrants") List<Quadrant> quadrants,
        @JsonProperty("constraints") Map<String, Constraint> constraints,
        @JsonProperty("history") List<AttemptInfo> history
) {
    public SharedInfo() {
        this(new ArrayList<>(4), new HashMap<>(3), new ArrayList<>());
    }
    @Override
    public String toString() {
        return "Info{" +
                "quadrants=" + quadrants +
                ", constraints=" + constraints +
                '}';
    }
}
