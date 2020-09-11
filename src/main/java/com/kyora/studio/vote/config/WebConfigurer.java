package com.kyora.studio.vote.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@Slf4j
public class WebConfigurer implements ServletContextInitializer {


    private final Environment env;

    private final EvoteProperties epptProperties;


    public WebConfigurer(Environment env, EvoteProperties epptProperties) {

        this.env = env;
        this.epptProperties = epptProperties;
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //noinspection SingleStatementInBlock
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
        }
        log.info("Web application fully configured");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.info("Registering CORS filter");
            source.registerCorsConfiguration("/oauth/**", config);
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/ebupot/registration/**", config);
            source.registerCorsConfiguration("/management/**", config);
            source.registerCorsConfiguration("/v2/api-docs", config);
        }
        return new CorsFilter(source);
    }

}