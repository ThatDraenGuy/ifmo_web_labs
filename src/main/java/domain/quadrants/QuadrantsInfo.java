package domain.quadrants;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

public class QuadrantsInfo implements Serializable {
    @Getter
    @JsonProperty
    private List<Quadrant> quadrants;


    @Override
    public String toString() {
        return "QuadrantsInfo{" +
                "quadrants=" + quadrants +
                '}';
    }
}
