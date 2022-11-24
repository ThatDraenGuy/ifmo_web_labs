package draen.domain.quadrants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
//@Component
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
