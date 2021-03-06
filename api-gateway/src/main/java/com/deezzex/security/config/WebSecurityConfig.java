package com.deezzex.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuration class for securing API GATEWAY service
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@EnableWebFluxSecurity
public class WebSecurityConfig {

    /**
     * Definition bcrypt password encoder bean for encrypting password in DB
     * @return bcrypt password encoder, built object
     **/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Definition SecurityWebFilterChain bean for adjusting security behavior
     * @param httpSecurity object for adjusting security behavior
     * @return SecurityWebFilterChain, built object
     **/
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){

        httpSecurity
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();

        return httpSecurity.build();
    }
}
