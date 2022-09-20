package coordinates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Only supports circles, no ellipses
public class CircleQuadrant extends AbstractQuadrant {
    @JsonCreator
    public CircleQuadrant(@JsonProperty("xMul") double xMul, @JsonProperty("yMul") double yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return Math.pow(x,2)+Math.pow(y,2) <= Math.pow(rX(r),2);
    }
}
