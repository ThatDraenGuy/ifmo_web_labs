package coordinates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TriangleQuadrant extends AbstractQuadrant{
    @JsonCreator
    public TriangleQuadrant(@JsonProperty("xMul") int xMul, @JsonProperty("yMul") int yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return (rX(r) - abs(x)) / rX(r) * rY(r) >= abs(y);
    }
}
