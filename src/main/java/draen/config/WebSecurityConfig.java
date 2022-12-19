package draen.config;

import draen.security.jwt.AuthEntryPoint;
import draen.security.jwt.AuthJwtFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final String[] PUBLIC_URLS = new String[]{"/public/**", "/api/users/current"};
    private final String[] NON_AUTH_ONLY_URLS = new String[]{"/api/auth/**"};
    private final String[] AUTH_ONLY_URLS = new String[]{"/api/**"};
    private final String[] ADMIN_URLS = new String[]{"/api/admin/**"};

    @Setter(onMethod = @__(@Autowired))
    private AuthEntryPoint authEntryPoint;

    @Bean
    public AuthJwtFilter authJwtFilter() {
        return new AuthJwtFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(); //TODO read
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers(NON_AUTH_ONLY_URLS).not().authenticated()
                .antMatchers(AUTH_ONLY_URLS).authenticated()
                .antMatchers(ADMIN_URLS).hasAuthority("ADMIN")
                .antMatchers("/users/id/{userId}/**").access("@userSecurity.hasUserId(authentication,#userId) or hasAuthority('ADMIN')")
                .anyRequest().permitAll();

        http.addFilterBefore(authJwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authEntryPoint);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
