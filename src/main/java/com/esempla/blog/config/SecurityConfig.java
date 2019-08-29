package com.esempla.blog.config;

import com.esempla.blog.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("login","logout","/","index").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/edit/*").hasAnyRole("ADMIN","OPERATOR")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authentificationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/resources/**")
                .antMatchers("/static/**")
                .antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/webjars/**")
                .antMatchers("/dist/**")
                .antMatchers("/bootstrap/**")
                .antMatchers("/plugins/**")
                .antMatchers("/images/**");

    }

    @Bean
    public DaoAuthenticationProvider authentificationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder( new BCryptPasswordEncoder());
        return authProvider;
    }
}
