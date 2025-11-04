package com.jtucke3.workoutapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // no browser forms or CSRF tokens for our JSON API
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())

                // let anonymous requests through to all auth endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                // keep anonymous enabled (default, but we’ll be explicit)
                .anonymous(Customizer.withDefaults())

                // don’t show the browser basic-auth popup (optional)
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
