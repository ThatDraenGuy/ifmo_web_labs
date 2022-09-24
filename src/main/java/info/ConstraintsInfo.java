package info;

import com.fasterxml.jackson.annotation.JsonProperty;
import constraints.Constraint;
import constraints.NoConstraint;
import exceptions.ValueException;

import java.util.HashMap;
import java.util.Map;

public record ConstraintsInfo(@JsonProperty Map<String,Constraint> constraints, @JsonProperty boolean isCheckingConstraints) {

    public void check(String name, double value) throws ValueException {
        if (isCheckingConstraints) {
            Constraint constraint = constraints.getOrDefault(name, new NoConstraint());
            if (!constraint.checkValue(value))
                throw new ValueException("Value " + name + " didn't pass the constraint check");
        }
    }
}
