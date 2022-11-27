package draen.domain.quadrants;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
public class QuadrantsInfo implements Serializable {
    @Getter
    @Setter
    @JsonProperty
    private List<Quadrant> quadrants;

    @Override
    public String toString() {
        return "QuadrantsInfo{" +
                "quadrants=" + quadrants +
                '}';
    }
}
