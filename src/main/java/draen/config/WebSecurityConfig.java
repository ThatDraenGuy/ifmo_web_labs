package draen.config;

import draen.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(); //TODO read
        http.authorizeRequests()
                        .antMatchers("/auth/**").not().authenticated()
                        .antMatchers("/public/**").permitAll()
                        .antMatchers("/api/public/**").permitAll()
                        .antMatchers("/api/users/id/{userId}/**").access("@userSecurity.hasUserId(authentication,#userId)")
                        .antMatchers("/api/users/{username}/**").access("@userSecurity.hasUsername(authentication, #username)")
                        .anyRequest().authenticated()
                        .and()
//                        .anyRequest().permitAll();
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/auth/login/process")
                .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
