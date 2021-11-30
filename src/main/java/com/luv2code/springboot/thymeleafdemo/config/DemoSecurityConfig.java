package com.luv2code.springboot.thymeleafdemo.config;

import com.luv2code.springboot.thymeleafdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    // add a reference to our security data source
    @Autowired
    private DataSource securityDataSource;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // use jdbc authentication ... oh yeah!!!

        auth.jdbcAuthentication().dataSource(securityDataSource)
                .authoritiesByUsernameQuery(
                        "SELECT u.username, a.authority " +
                                "FROM authorities a, users u " +
                                "WHERE u.username = ? " +
                                "AND u.authorities_id = a.authorities_id"
                ).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        String admin="ADMIN";
        http.authorizeRequests()
                .antMatchers("/books/list").permitAll()
                .antMatchers("/categories/list").permitAll()
                .antMatchers("/categories/add-category").hasRole(admin)
                .antMatchers("/categories/delete").hasRole(admin)
                .and()
                .formLogin()
                .loginPage("/login-page")
                .loginProcessingUrl("/authenticateTheUser").usernameParameter("username").passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");


    }
}

