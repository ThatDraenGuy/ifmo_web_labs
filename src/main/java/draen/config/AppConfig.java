package draen.config;


import draen.components.QuadrantsInfoComponent;
import draen.converters.QuadrantsInfoConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/database.properties")

@EnableConfigurationProperties(QuadrantsInfoComponent.class)
//@EnableJpaRepositories
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new QuadrantsInfoConverter());
    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
//    }
}
