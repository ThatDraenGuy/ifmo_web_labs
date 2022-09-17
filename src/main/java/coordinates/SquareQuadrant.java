package coordinates;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SquareQuadrant extends AbstractQuadrant {
    @JsonCreator
    public SquareQuadrant(@JsonProperty("xSign") int xSign, @JsonProperty("ySign") int ySign) {
        super(xSign, ySign);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return x<=r && y<=r;
    }
}
