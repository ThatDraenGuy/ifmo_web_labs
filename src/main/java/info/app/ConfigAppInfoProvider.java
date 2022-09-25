package info.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ConfigAppInfoProvider implements AppInfoProvider {
    private final String fileName = "config/config.json";
    @Override
    public AppInfo get() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(input, AppInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new SampleAppInfoProvider().get();
        }
    }
}
