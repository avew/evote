package com.kyora.studio.vote.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.kyora.studio.vote.exception.ErrorConstants.NOT_FOUND_AUTHORITY_TYPE;

@Immutable
public class AuthorityNotFoundException extends AbstractThrowableProblem {

    public AuthorityNotFoundException(String message) {
        super(NOT_FOUND_AUTHORITY_TYPE, "Warning", Status.NOT_FOUND, message);
    }

}
