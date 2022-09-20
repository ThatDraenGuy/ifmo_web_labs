package info;

import lombok.Getter;

public class AppInfo {
    @Getter
    private final SharedInfo info;
    @Getter
    private final boolean checkingConstraints = false;

    private AppInfo() {
        this.info = AppInfo.infoProvider.get();
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
