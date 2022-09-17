package coordinates;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//supports squares & rectangles
public class SquareQuadrant extends AbstractQuadrant {
    @JsonCreator
    public SquareQuadrant(@JsonProperty("xMul") int xMul, @JsonProperty("yMul") int yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return abs(x)<=rX(r) && abs(y)<=rY(r);
    }
}
