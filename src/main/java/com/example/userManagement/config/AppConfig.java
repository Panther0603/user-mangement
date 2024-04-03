package com.example.userManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1= User.builder().username("Mohit").password(passwordEncoder().encode("Mohit")).roles("ADMIN").build();
//        UserDetails user2= User.builder().username("Rohit").password(passwordEncoder().encode("Rohit")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }


    // for the password encoder

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
