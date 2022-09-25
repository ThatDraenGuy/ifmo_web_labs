package info;


import com.fasterxml.jackson.annotation.JsonProperty;
import constraints.Constraint;
import coordinates.Quadrant;
import info.app.AppInfo;
import storage.HistoryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record SharedInfo(
        @JsonProperty("quadrants") List<Quadrant> quadrants,
        @JsonProperty("constraints") Map<String, Constraint> constraints,
        @JsonProperty("history") List<AttemptInfo> history
) {
    public SharedInfo() {
        this(new ArrayList<>(4), new HashMap<>(3), new ArrayList<>());
    }

    public SharedInfo(AppInfo appInfo, HistoryManager<AttemptInfo> historyManager) {
        this(appInfo.quadrantsInfo().quadrants(), appInfo.constraintsInfo().constraints(), historyManager.get());
    }
    @Override
    public String toString() {
        return "Info{" +
                "quadrantInfo=" + quadrants +
                ", constraintsInfo=" + constraints +
                '}';
    }
}
