package com.gachigang.ontherun.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;

/**
 * Security configuration class.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class BaseSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * Configuration of security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
                new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
        );

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/businesses/*").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }
}
