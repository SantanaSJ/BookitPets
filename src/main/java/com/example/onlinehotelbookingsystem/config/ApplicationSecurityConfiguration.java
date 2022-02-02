package com.example.onlinehotelbookingsystem.config;

import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http


                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/assets/**","/", "/users/login", "/users/register", "/resources/**").permitAll()
                .antMatchers("/statistics").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers("/**").authenticated()

                .and()
//                .csrf()
//                .ignoringAntMatchers("/users/login", "/users/logout")
//                .and()

                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("email")
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/", true)
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                .logoutUrl("/users/logout")
//                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
