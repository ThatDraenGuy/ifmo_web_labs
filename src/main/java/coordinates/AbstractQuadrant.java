package coordinates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractQuadrant implements Quadrant {
    @JsonProperty
    private final int xSign;
    @JsonProperty
    private final int ySign;
    @JsonCreator
    public AbstractQuadrant(@JsonProperty("xSign") int xSign, @JsonProperty("ySign") int ySign) {
        this.xSign = xSign;
        this.ySign = ySign;
    }
    @Override
    public boolean checkHit(double x, double y, double r) {
        if (checkSign(x,y)) return calcHit(x,y,r);
        return false;
    }
    private boolean checkSign(double x, double y) {
        return (sign(x)==xSign || x==0) && (sign(y)==ySign || y==0);
    }
    protected abstract boolean calcHit(double x, double y, double r);

    protected int sign(int num) {
        return num>0 ? 1 : -1;
    }
    protected int sign(double num) {
        return num>0 ? 1 : -1;
    }

    @Override
    public String toString() {
        return "AbstractQuadrant{" +
                "xSign=" + xSign +
                ", ySign=" + ySign +
                '}';
    }
}
