package com.example.codebrawl.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(c -> c.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth->auth
                    .requestMatchers("/api/auth/signup",
                            "/api/auth/login",
                            "/api/auth/refresh",
//                            "/api/**",
                            "/login").permitAll()
                    .anyRequest().authenticated())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    return configuration.getAuthenticationManager();
}


}