/*
package com.example.medhub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigureAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigureAdapter {

    // If you have a custom UserDetailsService, you should @Autowire it here

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() // If CORS is needed
                .csrf().disable() // Disable CSRF protection for stateless session
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // URLs that are publicly accessible
                .anyRequest().authenticated() // Other URLs are secured
                .and()
                .httpBasic(); // Use HTTP Basic Authentication
        // If you're using JWT or form login, you will need to configure them here
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure authentication manager
        // Example: auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

*/
