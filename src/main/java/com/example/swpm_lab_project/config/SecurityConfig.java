package com.example.swpm_lab_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity // This is the "switch" that turns on @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for easier testing in Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/dashboard").permitAll()
                        .anyRequest().authenticated() // All URLs require login
                )
                .httpBasic(withDefaults()) // Allows testing via Postman "Basic Auth"
                .formLogin(withDefaults()); // Allows testing via a browser login page

        return http.build();
    }

    // Creating temporary users for your lab presentation
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails student = User.withDefaultPasswordEncoder()
                .username("student")
                .password("1234")
                .roles("STUDENT")
                .build();

        UserDetails teacher = User.withDefaultPasswordEncoder()
                .username("teacher")
                .password("1234")
                .roles("TEACHER")
                .build();

        return new InMemoryUserDetailsManager(student, teacher);
    }
}