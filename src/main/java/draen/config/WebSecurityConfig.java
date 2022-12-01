package draen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final String[] PUBLIC_URLS = new String[]{"/public/**", "/users", "/users/{username}"};
    private final String[] NON_AUTH_ONLY_URLS = new String[]{"/api/auth/**"};
    private final String[] ADMIN_URLS = new String[]{"/admin/**"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(); //TODO read
        http.authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers(NON_AUTH_ONLY_URLS).not().authenticated()
                .antMatchers(ADMIN_URLS).hasAuthority("ADMIN")
//                .antMatchers("/users/id/{userId}/**").access("@userSecurity.hasUserId(authentication,#userId) or hasAuthority('ADMIN')")
//                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/auth/login/process")
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
