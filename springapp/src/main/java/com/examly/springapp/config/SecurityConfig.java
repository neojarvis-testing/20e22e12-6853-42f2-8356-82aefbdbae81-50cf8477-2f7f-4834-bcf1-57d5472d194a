package com.examly.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String API_FEEDBACK = "/api/feedback"; // Constant for "/api/feedback"
    private static final String ROLE_ADMIN = "ADMIN"; // Constant for "ADMIN"

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public void configure(AuthenticationManagerBuilder authority) throws Exception {
        authority.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/appointment/{appointmentId}", "/api/appointment/{userId}")
                .hasAnyRole("USER")
                .requestMatchers(HttpMethod.GET, API_FEEDBACK, "/api/services/{id}", "/api/appointment").permitAll()
                .requestMatchers(HttpMethod.POST, API_FEEDBACK).hasAnyRole("USER")
                .requestMatchers(HttpMethod.GET, API_FEEDBACK).hasAnyRole("USER")
                .requestMatchers(HttpMethod.GET, API_FEEDBACK + "/user/{userId}", "/api/services/service").permitAll()
                .requestMatchers(HttpMethod.DELETE, API_FEEDBACK + "/{id}").hasAnyRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/services").hasAnyRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/appointment").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/services/{id}", "/api/appointment/{appointmentId}")
                .hasAnyRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/register", "/api/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/service").hasAnyRole(ROLE_ADMIN)
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .anyRequest().permitAll())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}