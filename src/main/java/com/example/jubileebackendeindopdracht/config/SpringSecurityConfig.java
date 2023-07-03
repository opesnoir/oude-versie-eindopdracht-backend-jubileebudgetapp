package com.example.jubileebackendeindopdracht.config;

import com.example.jubileebackendeindopdracht.filter.JwtRequestFilter;
import com.example.jubileebackendeindopdracht.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    // authorization with jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()

                // user
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users/{username}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/users/{username}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/users/{username}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("ADMIN")

                // account
                .requestMatchers(HttpMethod.POST, "/accounts").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/accounts/{id}").hasRole("ADMIN")

                // balance
                .requestMatchers(HttpMethod.POST, "/balances").permitAll()

                // saving goal
                .requestMatchers(HttpMethod.POST, "/saving_goals").permitAll()
                .requestMatchers(HttpMethod.GET, "/saving_goals").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/saving_goals/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/saving_goals/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/saving_goals/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/saving_goals/{id}").hasAnyRole("ADMIN", "USER")

                // transaction
                .requestMatchers(HttpMethod.POST, "/transactions").permitAll()
                .requestMatchers(HttpMethod.GET, "/transactions").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/transactions/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/transactions/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/transactions/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/transactions/{id}").hasAnyRole("ADMIN", "USER")

                // upload
                .requestMatchers(HttpMethod.POST, "/uploads").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/uploads/{fileId}/download").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/uploads/{fileId}").hasAnyRole("ADMIN", "USER")

                // authentication
                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
