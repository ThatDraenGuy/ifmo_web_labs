package draen.config;

import draen.domain.users.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                        .antMatchers("/auth/**").not().authenticated()
                        .antMatchers("/public/**").permitAll()
//                        .anyRequest().authenticated();
                        .anyRequest().permitAll();
        http.formLogin().loginPage("/login");

        return http.build();
    }

    @Bean
    @Autowired
    public UserDetailsService userDetailsService(DatabaseUserDetailsService databaseUserDetailsService) {
        return databaseUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
