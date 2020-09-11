package com.kyora.studio.vote.config;

import com.kyora.studio.vote.security.AjaxLogoutSuccessHandler;
import com.kyora.studio.vote.security.Http401UnauthorizedEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import javax.annotation.PostConstruct;

/**
 * Created by avew on 12/21/17.
 */
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private UserDetailsService userDetailsService;


    @PostConstruct
    public void init() {
        try {
            log.debug("INIT AUTHENTICATION MANAGER");
            authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }


    @Bean
    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
        return new AjaxLogoutSuccessHandler();
    }

    @Bean
    public Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint() {
        return new Http401UnauthorizedEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/api/register")
                .antMatchers("/api/activate")
                .antMatchers("/api/log/**/stream")
                .antMatchers("/api/log/**/foldering/stream")
                .antMatchers("/api/account/reset_password/init")
                .antMatchers("/api/account/reset_password/finish")
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .httpBasic()
                .realmName("eppt")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/oauth/authorize")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/authorize").authenticated();
    }


    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
