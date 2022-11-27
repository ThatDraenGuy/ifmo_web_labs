package draen.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import draen.domain.quadrants.QuadrantsInfo;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@Component
@ConfigurationPropertiesBinding
public class QuadrantsInfoConverter implements Converter<String, QuadrantsInfo> {
    @Override
    public QuadrantsInfo convert(@NonNull String source) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource(source).getInputStream();
            return mapper.readValue(inputStream, QuadrantsInfo.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid quadrants");
        }
    }
}
