package com.kyora.studio.vote.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by avew on 12/22/17.
 */
@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(final String login) {

        log.info("authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
//
//        //check user logged in
//        jdbcClientDetailsService
//                .listClientDetails()
//                .forEach(clientDetails -> {
//                    String clientId = clientDetails.getClientId();
//
//                    boolean blockLogin = epptProperties.getCustomFeature().isBlockLogin();
//
//                    if (blockLogin) {
//                        if (!lowercaseLogin.equalsIgnoreCase("admin"))
//                            tokenStore.findTokensByClientIdAndUserName(clientId, lowercaseLogin)
//                                .stream()
//                                .findFirst()
//                                .ifPresent(oAuth2AccessToken -> {
//                                    throw new UserLoggedInException(lowercaseLogin);
//                                });
//                    }
//                });
//
//
//        Optional<User> userFromDatabase = userQueryService.getUserWithAuthoritiesByLogin(lowercaseLogin);
//        return userFromDatabase
//                .map(user -> {
//                    if (!user.isActivated()) {
//                        throw new UserNotActivatedException("user " + lowercaseLogin + " was not activated");
//                    }
//
//                    List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//                            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
//                            .collect(Collectors.toList());
//
//                    org.springframework.security.core.userdetails.User userLoggedIn = new org.springframework.security.core.userdetails.User(lowercaseLogin,
//                            user.getPassword(),
//                            grantedAuthorities);
//
//                    userService.updateLoggedInAt(userLoggedIn.getUsername());
//
//                    return userLoggedIn;
//                }).orElseThrow(() -> new UsernameNotFoundException("user " + lowercaseLogin + " was not found writer the " +
//                        "database"));
        return null;
    }


}

