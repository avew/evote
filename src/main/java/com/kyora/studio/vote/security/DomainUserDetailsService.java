package com.kyora.studio.vote.security;

import com.kyora.studio.vote.domain.User;
import com.kyora.studio.vote.exception.UserNotActivatedException;
import com.kyora.studio.vote.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by avew on 12/22/17.
 */
@Component("userDetailsService")
@Slf4j
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(final String login) {

        log.info("AUTHENTICATING {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);

        Optional<User> userFromDatabase = userQueryService.getUserWithAuthoritiesByLogin(lowercaseLogin);
        return userFromDatabase
                .map(user -> {
                    if (!user.isActivated()) {
                        throw new UserNotActivatedException("user " + lowercaseLogin + " was not activated");
                    }

                    List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                            .collect(Collectors.toList());

                    org.springframework.security.core.userdetails.User userLoggedIn = new org.springframework.security.core.userdetails.User(lowercaseLogin,
                            user.getPassword(),
                            grantedAuthorities);

//                    userService.updateLoggedInAt(userLoggedIn.getUsername());

                    return userLoggedIn;
                }).orElseThrow(() -> new UsernameNotFoundException("user " + lowercaseLogin + " was not found writer the " +
                        "database"));

    }


}

