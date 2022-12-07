package draen.config;


import draen.components.QuadrantsInfoComponent;
import draen.converters.QuadrantsInfoConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/database.properties")

@EnableConfigurationProperties(QuadrantsInfoComponent.class)
//@EnableJpaRepositories
public class AppConfig implements WebMvcConfigurer {
    @Value("${rest.base-path}")
    private String REST_BASE_PATH;
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new QuadrantsInfoConverter());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(REST_BASE_PATH, HandlerTypePredicate.forAnnotation(RestController.class));
    }

    //    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
//    }
}
