package draen.config;

import draen.config.QuadrantsInfoConverter;
import draen.config.QuadrantsInfoWrapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/database.properties")

@EnableConfigurationProperties(QuadrantsInfoWrapper.class)
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new QuadrantsInfoConverter());
    }
}
