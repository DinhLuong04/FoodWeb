package com.DinhLuong.FoodDelivery.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomFillterSecurity {
     private static final List<String> PUBLIC_API_LIST = List.of(
            "/ws/**", "/login", "/swagger-ui", "/v3/api-docs", "/Restaurant/getRes", "/Order","/chat/**","/user/**"
            ,"/chatroom/**","/user"
            ,"/app/**"
            ,"/private-message/**"
            );

    @Autowired
    private CustomFillterJWT customFillterJWT;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configure(http)) 
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/**", "/swagger-ui/**", "/v3/api-docs/**","/Restaurant/getRes","/chat/**","/user/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/{ResId}/add-Rating/{UserId}","/Restaurant/getAllRes").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/Restaurant/update","/Restaurant/delete").hasRole("ADMIN")
                .requestMatchers("/Order/**").permitAll()
                .requestMatchers("/ws/**", "/login", "/swagger-ui", "/v3/api-docs", "/Restaurant/getRes", "/Order","/chat/**","/user/**"
            ,"/chatroom/**","/user"
            ,"/app/**"
            ,"/private-message/**").permitAll()
                
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(customFillterJWT, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
