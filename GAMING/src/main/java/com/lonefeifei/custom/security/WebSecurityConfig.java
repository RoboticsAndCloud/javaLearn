/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.custom.security;

import com.lonefeifei.custom.security.userAuth.MyAuthenticationProvider;
import com.lonefeifei.service.user.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by baidu on 16/8/12.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String OP = "op-engineer";
    public static final String RD = "rd-engineer";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Autowired
    private MyAuthenticationProvider provider;//自定义验证

//    @Autowired
//    private UserDetailsService userDetailsService;//自定义用户服务
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
//    }

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/games/**").hasRole(ADMIN)
                .anyRequest().authenticated()
//                .antMatchers("/games/**").hasRole(ADMIN)
//                .antMatchers("/hello")
//                .hasAnyRole(USER, ADMIN)
//                .antMatchers("/game").hasRole(ADMIN)
//                .and()
//                .rememberMe()
                .and()
                .formLogin()
//                        .loginPage("/login")
                .permitAll()
//                .and()
//                .sessionManagement()
//                .maximumSessions(1)
//                .and()
////                .rememberMe()
//                .and()
//                .logout()
//                .permitAll()
//                .deleteCookies("remove")
        .and().csrf().disable();
//               .invalidateHttpSession(false);
//                .logoutUrl("/custom-logout")
//                .logoutSuccessUrl("/logout-success")
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password")
                .roles(USER)
                .and()
                .withUser("admin").password("admin").roles(ADMIN);
        auth.userDetailsService(customUserService());
//        auth.authenticationProvider(provider);

    }
}
