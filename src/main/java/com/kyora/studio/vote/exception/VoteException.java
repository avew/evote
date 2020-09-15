package com.kyora.studio.vote.exception;

import lombok.extern.slf4j.Slf4j;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.kyora.studio.vote.exception.ErrorConstants.VOTE_TYPE;

@Immutable
@Slf4j
public class VoteException extends AbstractThrowableProblem {

    public VoteException(String message) {
        super(VOTE_TYPE, "Warning", Status.BAD_REQUEST, message);
        log.warn(message);
    }

}
