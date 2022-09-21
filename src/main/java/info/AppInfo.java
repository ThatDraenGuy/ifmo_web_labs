package info;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import storage.HistoryManager;
import storage.HttpSessionManager;

public class AppInfo {
    @Getter
    private final SharedInfo sharedInfo;
    @Getter
    private final boolean checkingConstraints = false;
    @Getter
    private final HttpSessionManager<AttemptInfo> historyManager = new HttpSessionManager<>();

    private AppInfo() {
        this.sharedInfo = AppInfo.infoProvider.get();
    }

    public void update(HttpServletRequest req) {
        historyManager.setSession(req.getSession());
    }


    private static final InfoProvider infoProvider = new SampleInfoProvider();

    private static AppInfo instance;
    public static AppInfo getInstance() {
        if (instance==null) {
            instance = new AppInfo();
        }
        return instance;
    }
}
