package draen.domain.quadrants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmptyQuadrant extends AbstractQuadrant {
    public static final String NAME = "empty";
    @JsonCreator
    public EmptyQuadrant(@JsonProperty("xMul") double xMul, @JsonProperty("yMul") double yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}