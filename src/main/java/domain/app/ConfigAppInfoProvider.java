package domain.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ConfigAppInfoProvider implements AppInfoProvider {
    @Override
    public AppInfo get() {
        try {
            String fileName = "config/config.json";
            InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(input, AppInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new SampleAppInfoProvider().get();
        }
    }
}
