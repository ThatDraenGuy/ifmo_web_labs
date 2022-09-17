package coordinates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TriangleQuadrant extends AbstractQuadrant{
    @JsonCreator
    public TriangleQuadrant(@JsonProperty("xSign") int xSign, @JsonProperty("ySign") int ySign) {
        super(xSign, ySign);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return x+y <= r;
    }
}
