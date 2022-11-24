package draen.domain.quadrants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TriangleQuadrant extends AbstractQuadrant{
    public static final String NAME = "triangle";
    @JsonCreator
    public TriangleQuadrant(@JsonProperty("xMul") double xMul, @JsonProperty("yMul") double yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return (rX(r) - abs(x)) / rX(r) * rY(r) >= abs(y);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
