package com.kyora.studio.vote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    @Autowired
    public OAuth2AuthorizationConfiguration(AuthenticationManager authenticationManager, DataSource dataSource, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    /**
     * Stores the client oauth authorization codes and client access tokens writer a jdbc datastore. This store is managed by oauth2.
     *
     * @return
     */
    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .tokenEnhancer(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenEnhancer(tokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds(3600); // 1 jam
        return tokenServices;
    }

}
