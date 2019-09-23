package com.esempla.blog.frontend.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username , password , enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, r.\"name\" from users u \n" +
                        "\tinner join user_roles ur on u.id = ur.user_id \n" +
                        "\tinner join roles r on ur.role_name = r.\"name\"\n" +
                        "\twhere u.username = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/appUser/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/comment/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/category/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/blog/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/**")
                .permitAll()
                .and().formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/loginPage")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and().logout()
                .logoutUrl("/logoutPage")
                .logoutSuccessUrl("/loginPage?logout")
                .permitAll()
                .and().httpBasic();
    }
}
