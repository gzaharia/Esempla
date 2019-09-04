package com.esempla.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(authentificationProvider());

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
                .antMatchers("/index/**").hasAnyRole("USER","ADMIN")
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

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .antMatchers("/i18n/**")
//                .antMatchers("/content/**")
//                .antMatchers("/h2-console/**")
//                .antMatchers("/resources/**")
//                .antMatchers("/static/**")
//                .antMatchers("/js/**")
//                .antMatchers("/css/**")
//                .antMatchers("/webjars/**")
//                .antMatchers("/dist/**")
//                .antMatchers("/bootstrap/**")
//                .antMatchers("/plugins/**")
//                .antMatchers("/images/**");
//
//    }

//    @Bean
//    public DaoAuthenticationProvider authentificationProvider(){
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder( new BCryptPasswordEncoder());
//        return authProvider;
//    }
}
