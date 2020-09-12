package com.kyora.studio.vote.exception;

/*
 * Developed by Asep Rojali on 12/19/18 4:07 PM
 * Last modified 12/19/18 4:07 PM
 * Copyright (c) 2018. All rights reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

import javax.validation.ConstraintViolationException;
import java.net.URI;

import static com.kyora.studio.vote.exception.ErrorConstants.*;


@ControllerAdvice
@Slf4j
public class ExceptionHandling implements ProblemHandling, SecurityAdviceTrait {

    @Override
    public URI defaultConstraintViolationType() {
        return URI.create(PROBLEM_BASE_URL);
    }

    @Override
    public boolean isCausalChainsEnabled() {
        return false;
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleConstraintViolated(final DataIntegrityViolationException exception,
                                                            final NativeWebRequest request) {
        if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException constraintViolationException =
                    (org.hibernate.exception.ConstraintViolationException) exception.getCause();
            return create(Status.CONFLICT, constraintViolationException.getSQLException(), request);
        }
        return create(Status.INTERNAL_SERVER_ERROR, exception, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleEmptyResult(final EmptyResultDataAccessException exception,
                                                     final NativeWebRequest request) {

        return create(Status.NOT_FOUND, exception, request, NOT_FOUND_TYPE);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleInternalAuthenticationServiceResult(final InternalAuthenticationServiceException exception,
                                                                             final NativeWebRequest request) {
        return create(Status.NOT_FOUND, exception, request, NOT_FOUND_TYPE);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleOauth2Result(final OAuth2Exception exception,
                                                            final NativeWebRequest request) {
        return create(Status.UNAUTHORIZED, exception, request, AUTH_TYPE);
    }

    //EmptyResultDataAccessException

    @SuppressWarnings("PlaceholderCountMatchesArgumentCount")
    @ExceptionHandler
    public ResponseEntity<Problem> handleNestedConstrainException(final TransactionException exception, final NativeWebRequest request) {
        Throwable rootCause = exception.getRootCause();
        if ((!(rootCause instanceof ConstraintViolationException))) {
            log.debug("Found TransactionException, but  rootCause is {}, using default handling", rootCause);
            return create(exception, request);
        }
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) rootCause;
        return handleConstraintViolation(constraintViolationException, request);
    }
}
