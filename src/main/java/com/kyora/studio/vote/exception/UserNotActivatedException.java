package com.kyora.studio.vote.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.kyora.studio.vote.exception.ErrorConstants.NOT_ACTIVATED_USER_TYPE;

@Immutable
public class UserNotActivatedException extends AbstractThrowableProblem {

    public UserNotActivatedException(String message) {
        super(NOT_ACTIVATED_USER_TYPE, "Warning", Status.INTERNAL_SERVER_ERROR, message);
    }

}
