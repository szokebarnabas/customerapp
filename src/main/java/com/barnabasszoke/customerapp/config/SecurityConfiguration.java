package com.barnabasszoke.customerapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.barnabasszoke.customerapp.security.AjaxAuthenticationFailureHandler;
import com.barnabasszoke.customerapp.security.AjaxAuthenticationSuccessHandler;
import com.barnabasszoke.customerapp.security.AjaxLogoutSuccessHandler;
import com.barnabasszoke.customerapp.security.Http401UnauthorizedEntryPoint;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.barnabasszoke.customerapp"})
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    /*@Bean
    public RememberMeServices rememberMeServices() {
        return new CustomPersistentRememberMeServices(env, userDetailsService());
    }*/

    /*@Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider(env.getProperty("jhipster.security.rememberme.key"));
    }*/

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new com.barnabasszoke.customerapp.security.UserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/css/**")
            .antMatchers("/img/**")
            .antMatchers("/js/**")
            .antMatchers("/lib/**")
            .antMatchers("/partials/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
            /*.rememberMe()
                .rememberMeServices(rememberMeServices())
                .key(env.getProperty("jhipster.security.rememberme.key"))
                .and()*/
            .formLogin()
                .loginProcessingUrl("/api/account/authentication")
                .successHandler(ajaxAuthenticationSuccessHandler)
                .failureHandler(ajaxAuthenticationFailureHandler)
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/api/account/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/api/account/authenticate").permitAll()
                .antMatchers("/api/account/logs/**").hasRole("ADMIN")
                .antMatchers("/api/**").authenticated()
                .antMatchers("/websocket/tracker").hasRole("ADMIN")
                .antMatchers("/websocket/**").permitAll()
                .antMatchers("/metrics/**").hasRole("ADMIN");
    }
}
