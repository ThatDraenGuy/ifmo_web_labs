package draen.domain.quadrants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public abstract class AbstractQuadrant implements Quadrant {
    @Getter
    @JsonProperty
    protected final double xMul;
    @Getter
    @JsonProperty
    protected final double yMul;
    @JsonCreator
    public AbstractQuadrant(@JsonProperty("xMul") double xMul, @JsonProperty("yMul") double yMul) {
        this.xMul = xMul;
        this.yMul = yMul;
    }
    @Override
    public boolean checkHit(double x, double y, double r) {
        if (checkSign(x,y)) return calcHit(x,y,r);
        return false;
    }
    private boolean checkSign(double x, double y) {
        return (sign(x)==sign(xMul) || x==0) && (sign(y)==sign(yMul) || y==0);
    }
    protected abstract boolean calcHit(double x, double y, double r);


    protected int sign(double num) {
        return num>0 ? 1 : -1;
    }
    protected double rX(double r) {
        return r*abs(this.xMul);
    }
    protected double rY(double r) {
        return r*abs(this.yMul);
    }
    protected static double abs(double num) {
        return Math.abs(num);
    }

    @Override
    public String toString() {
        return "Quadrant "+ getName() + "{" +
                "xMul=" + xMul +
                ", yMul=" + yMul +
                '}';
    }
}
