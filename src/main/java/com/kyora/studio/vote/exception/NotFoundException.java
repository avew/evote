package com.kyora.studio.vote.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.kyora.studio.vote.exception.ErrorConstants.NOT_FOUND_TYPE;

@Immutable
public class NotFoundException extends AbstractThrowableProblem {

    public NotFoundException(String id, String object) {
        super(NOT_FOUND_TYPE, "Warning", Status.NOT_FOUND, String.format("id %s on %s object not found", id, object));
    }

    @SuppressWarnings("rawtypes")
    public NotFoundException(Class clazz, String field, String search) {
        super(NOT_FOUND_TYPE, "Warning", Status.NOT_FOUND, String.format("find %s: %s on %s object not found", field, search, clazz.getSimpleName()));
    }
}
