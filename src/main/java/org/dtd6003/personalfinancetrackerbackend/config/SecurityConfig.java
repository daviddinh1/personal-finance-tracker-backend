package org.dtd6003.personalfinancetrackerbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disable CSRF for our JSON API
                .csrf(csrf -> csrf.disable())

                // declare which endpoints are public vs. secured
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()   // allow register & login
                        .anyRequest().authenticated()                     // everything else requires a token
                )

                // make it stateless; no sessions
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // disable default form login & HTTP Basic
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())

        // NOTE: you'll need to add your JWT filter here if you want to protect other endpoints
        ;

        return http.build();
    }
}
