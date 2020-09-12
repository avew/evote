package com.kyora.studio.vote.exception;

import java.net.URI;

@SuppressWarnings("SpellCheckingInspection")
public class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://api.app.com/problem";
    public static final URI NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/not-found");
    public static final URI NOT_ACTIVATED_USER_TYPE = URI.create(PROBLEM_BASE_URL + "/user-not-activated");
    public static final URI NOT_FOUND_USER_TYPE = URI.create(PROBLEM_BASE_URL + "/user-not-found");
    public static final URI NOT_FOUND_AUTHORITY_TYPE = URI.create(PROBLEM_BASE_URL + "/authority-not-found");
    public static final URI EXISTS_USER_TYPE = URI.create(PROBLEM_BASE_URL + "/username-is-exists");
    public static final URI AUTH_TYPE = URI.create(PROBLEM_BASE_URL + "/auth");


    private ErrorConstants() {
    }
}
