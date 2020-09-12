package com.kyora.studio.vote.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.kyora.studio.vote.exception.ErrorConstants.NOT_ACTIVATED_USER_TYPE;

@Immutable
public class UserNotFoundException extends AbstractThrowableProblem {

    public UserNotFoundException(String message) {
        super(NOT_ACTIVATED_USER_TYPE, "Warning", Status.NOT_FOUND, message);
    }

}
