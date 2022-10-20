package domain.quadrants;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//supports squares & rectangles
public class SquareQuadrant extends AbstractQuadrant {
    public static final String NAME = "square";
    @JsonCreator
    public SquareQuadrant(@JsonProperty("xMul") double xMul, @JsonProperty("yMul") double yMul) {
        super(xMul, yMul);
    }

    @Override
    protected boolean calcHit(double x, double y, double r) {
        return abs(x)<=rX(r) && abs(y)<=rY(r);
    }

    @Override
    public String getName() {
        return NAME;
    }
}