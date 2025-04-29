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
 
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
 
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
 
 
 
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
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
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
        .requestMatchers(HttpMethod.POST,"/api/register","/api/login").permitAll()
        .requestMatchers(HttpMethod.POST,"/api/service").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/services/{id}",
        "/api/services","/api/services/serviceName").permitAll()
        .requestMatchers(HttpMethod.DELETE,"/api/services/{id}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/api/service/{id}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/feedbacks").hasAnyRole("USER")
        .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
        .anyRequest().authenticated())
        .exceptionHandling(exception-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);    
        return http.build();
    }
}
 