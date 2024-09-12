package com.Sem5.PharmEase.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure CSRF to ignore certain endpoints
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/medicals/**","/api/products/**","/api/bills/**") // Ignore CSRF protection for /api/medicals/** endpoints
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Store CSRF token in a cookie
                )
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers("/api/medicals/**","/api/products/**","/api/bills/**").permitAll() // Allow access without authentication
                        .anyRequest().authenticated() // Authenticate all other requests
                );

        return http.build();
    }
}




