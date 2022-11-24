package draen;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/database.properties")
public class AppConfig {
}
