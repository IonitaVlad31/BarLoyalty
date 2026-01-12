package com.barLoyalty.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Dezactivam CSRF pentru ca folosim API-uri (Postman/Frontend)
            .authorizeHttpRequests(auth -> auth
                    // Lasam acces liber la endpoint-urile de autentificare si testul QR
                    .requestMatchers("/api/auth/**", "/api/test/**").permitAll()
                    // Toate celelalte endpoint-uri necesita autentificare
                    .anyRequest().authenticated()
            )
                .httpBasic(basic -> {});
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
