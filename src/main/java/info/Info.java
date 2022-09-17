package info;


import com.fasterxml.jackson.annotation.JsonProperty;
import constraints.Constraint;
import coordinates.Quadrant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Info {

    @JsonProperty("quadrants")
    private List<Quadrant> quadrants = new ArrayList<>(4);
    public List<Quadrant> getQuadrants() {
        return quadrants;
    }
    @JsonProperty("constraints")
    private Map<String, Constraint> constraints = new HashMap<>(3);
    public Map<String,Constraint> getConstraints() {
        return constraints;
    }

    @Override
    public String toString() {
        return "Info{" +
                "quadrants=" + quadrants +
                ", constraints=" + constraints +
                '}';
    }
}
