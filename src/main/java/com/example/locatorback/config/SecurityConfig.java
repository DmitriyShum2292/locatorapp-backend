package com.example.locatorback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery(
                        "select email, password, active from my_new_user " +
                                "where email=?")
                .authoritiesByUsernameQuery(
                        "select email, authority from my_new_user " +
                                "where email=?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/details/**","/personal/**","/add","/personal/addtrusted").hasAnyRole("USER","ADMIN")
                .antMatchers("/registration/**","/login","/personal/*","/personal/addtrusted").hasRole("USER")
                .antMatchers("/registration/**","/registration/login").hasRole("ADMIN")
                .antMatchers("/registration/**","/registration/login").permitAll()
                .and().httpBasic();
    }
}
