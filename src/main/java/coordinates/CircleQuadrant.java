package coordinates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CircleQuadrant extends AbstractQuadrant {
    @JsonCreator
    public CircleQuadrant(@JsonProperty("xSign") int xSign, @JsonProperty("ySign") int ySign) {
        super(xSign, ySign);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return Math.pow(x,2)+Math.pow(y,2) <= Math.pow(r,2);
    }
}
