package com.kyora.studio.vote.exception;

import lombok.extern.slf4j.Slf4j;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.kyora.studio.vote.exception.ErrorConstants.EXISTS_USER_TYPE;

@Immutable
@Slf4j
public class UsernameExistsException extends AbstractThrowableProblem {

    public UsernameExistsException(String message) {
        super(EXISTS_USER_TYPE, "Warning", Status.FOUND, message);
        log.warn(message);
    }

}
