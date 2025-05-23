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
 
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
 
    @Autowired
    public void configure(AuthenticationManagerBuilder authority)throws Exception{
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
        .cors(cors->cors.disable())
        .authorizeHttpRequests(auth->auth
 
        .requestMatchers(HttpMethod.GET,"/api/appointment/{appointmentId}","/api/appointment/{userId}").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET, "/api/feedback","/api/services/{id}","/api/appointment").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/feedback").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET,"/api/feedback").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET, "/api/feedback/user/{userId}","/api/services/service").permitAll()
        .requestMatchers(HttpMethod.DELETE,"/api/feedback/{id}").hasAnyRole("USER")
        .requestMatchers(HttpMethod.POST, "/api/services").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/appointment").hasAnyRole("USER")
        .requestMatchers(HttpMethod.PUT, "/api/services/{id}","/api/appointment/{appointmentId}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/register","/api/login").permitAll()
        .requestMatchers(HttpMethod.POST,"/api/service").hasAnyRole("ADMIN") 
        .requestMatchers(HttpMethod.POST,"/coupons/create").permitAll()
        .requestMatchers(HttpMethod.GET,"/coupons/user/{userId}").permitAll()
        .requestMatchers(HttpMethod.PUT,"/coupons/expire/{couponId}").permitAll()
        .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
        .anyRequest().permitAll())
        .exceptionHandling(exception-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);    
 
        return http.build();
    }
}

