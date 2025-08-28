package com.rsarabia.jtodolistappback.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF, común en APIs REST
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/tasks/**").permitAll() // Permite todas las solicitudes a /api/v1/tasks/
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                );
        return http.build();
    }
}
