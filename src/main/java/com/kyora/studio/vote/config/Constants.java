package com.kyora.studio.vote.config;


/**
 * Application constants.
 */
public final class Constants {

    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    public static final String SYSTEM_ACCOUNT = "system";

    public interface OAUTH_CONSTANTS {
        String DEFAULT_CLIENT_ID = "abbeb89a-2c00-4c17-b276-008800ed2be7";
        String RESOURCE_IDS = "eVote";
        String GRANT_CLIENT_CREDENTIALS = "client_credentials";
        String AUTHORITIES_ROLE_USER = "ROLE_USER";
        int ACCESS_TOKEN_VALIDITY = 86400;
        int REFRESH_TOKEN_VALIDITY = 87000;
        int CLIENT_ID_LENGTH = 45;
        int CLIENT_SECRET_LENGTH = 45;
    }

}
