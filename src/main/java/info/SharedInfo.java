package info;


import com.fasterxml.jackson.annotation.JsonProperty;
import constraints.Constraint;
import coordinates.Quadrant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedInfo {

    @JsonProperty("quadrants")
    @Getter
    private List<Quadrant> quadrants = new ArrayList<>(4);
    @JsonProperty("constraints")
    @Getter
    private Map<String, Constraint> constraints = new HashMap<>(3);


    @Override
    public String toString() {
        return "Info{" +
                "quadrants=" + quadrants +
                ", constraints=" + constraints +
                '}';
    }
}
