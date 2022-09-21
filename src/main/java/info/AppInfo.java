package info;

import storage.HistoryManager;

public record AppInfo(SharedInfo sharedInfo, boolean isCheckingConstraints, HistoryManager<AttemptInfo> historyManager) {
    public final static String NAME = "appInfo";
}
