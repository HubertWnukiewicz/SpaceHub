package com.example.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @CrossOrigin(origins = "http://localhost:8090")
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER");

//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8090")
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("ADMIN")
                .antMatchers("/product/all").hasRole("ADMIN")
                .antMatchers("/mission/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout().permitAll();

    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //just for development
        return new BCryptPasswordEncoder();
    }
}