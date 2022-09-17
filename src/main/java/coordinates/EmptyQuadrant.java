package coordinates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmptyQuadrant extends AbstractQuadrant {
    @JsonCreator
    public EmptyQuadrant(@JsonProperty("xMul") int xMul, @JsonProperty("yMul") int yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return false;
    }
}
