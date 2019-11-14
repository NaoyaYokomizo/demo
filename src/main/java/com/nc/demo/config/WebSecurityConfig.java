package com.nc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/", "/login", "/login?*", "/sample2", "/fullcalendar-events", "/fullcalendar.html", "/fullcalendar/**", "/fullcalendar/**/**").permitAll()
                .antMatchers("/sampleA").hasRole("ADMIN")
                .antMatchers("/sampleU").hasRole("USER")
            .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("userid")
                    .passwordParameter("userpwd")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?loginError=true")
            .and()
                .logout()
                .logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}