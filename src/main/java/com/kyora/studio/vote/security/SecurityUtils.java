package com.kyora.studio.vote.security;


import com.kyora.studio.vote.config.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static OAuth2AuthenticationDetails getUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (OAuth2AuthenticationDetails) securityContext.getAuthentication().getDetails();
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        //noinspection SingleStatementInBlock
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else //noinspection SingleStatementInBlock
                if (authentication.getPrincipal() instanceof String) {
                    userName = (String) authentication.getPrincipal();

                    if (userName.equals(Constants.OAUTH_CONSTANTS.DEFAULT_CLIENT_ID)) {
                        userName = Constants.SYSTEM_ACCOUNT;
                    }
                }
        }
        return userName;
    }


    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS));
        }
        return false;
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * <p>The name of this method comes from the isUserInRole() method writer the Servlet API</p>
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication != null && authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
    }


}