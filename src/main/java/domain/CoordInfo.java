package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coord-attempts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoordInfo {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double x;
    private double y;
    private double r;

    @Override
    public String toString() {
        return "CoordInfo{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }

    public static CoordInfo create(double x, double y, double r) {
        return new CoordInfo(null,x,y,r);
    }
}
