package com.kyora.studio.vote.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by avew on 12/22/17.
 */
@Component
@Slf4j
public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

    public static final String BEARER_AUTHENTICATION = "Bearer ";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        removeToken(request, tokenStore);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.getWriter().flush();
    }

    public static void removeToken(HttpServletRequest request, TokenStore tokenStore) {
        String authHeader = request.getHeader("Authorization");
        log.info("token: {}", authHeader);

        if (authHeader != null) {
            String tokenValue = authHeader.replace(BEARER_AUTHENTICATION, "").trim();
            final OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
            OAuth2RefreshToken oAuth2RefreshToken = tokenStore.readRefreshToken(tokenValue);
            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
            if (oAuth2RefreshToken != null) {
                tokenStore.removeRefreshToken(oAuth2RefreshToken);
            }
        }
    }
}
