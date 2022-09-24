package info;

import com.fasterxml.jackson.annotation.JsonProperty;
import storage.HistoryManager;

public record AppInfo(@JsonProperty QuadrantsInfo quadrantsInfo, @JsonProperty ConstraintsInfo constraintsInfo) {
    public final static String NAME = "appInfo";
}
