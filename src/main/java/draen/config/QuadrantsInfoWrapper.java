package draen.config;

import draen.domain.quadrants.QuadrantsInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "quadrants")
@ReadingConverter
public class QuadrantsInfoWrapper {
    @Getter
    @Setter
    @Value("${quadrants.source}")
    private QuadrantsInfo quadrantsInfo;
}
