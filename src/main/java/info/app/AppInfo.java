package info.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.ConstraintsInfo;
import info.QuadrantsInfo;

public record AppInfo(@JsonProperty QuadrantsInfo quadrantsInfo, @JsonProperty ConstraintsInfo constraintsInfo) {
    public final static String NAME = "appInfo";
}
