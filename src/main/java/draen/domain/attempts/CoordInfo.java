package draen.domain.attempts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable public class CoordInfo {
    private double x;
    private double y;
    private double r;

    @Override
    public String toString() {
        return "CoordInfo{" +
                "  x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }

    public static CoordInfo create(double x, double y, double r) {
        return new CoordInfo(x,y,r);
    }
}