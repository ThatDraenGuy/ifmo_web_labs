package domain.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.ConstraintsInfo;
import domain.QuadrantsInfo;

public record AppInfo(@JsonProperty QuadrantsInfo quadrantsInfo, @JsonProperty ConstraintsInfo constraintsInfo) {
    public final static String NAME = "appInfo";
}
