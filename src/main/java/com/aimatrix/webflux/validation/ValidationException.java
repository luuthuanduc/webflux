package com.aimatrix.webflux.validation;

import am.ik.yavi.core.ConstraintViolation;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class ValidationException extends RuntimeException {

    List<ConstraintViolation> errors;

    @Override
    public String getMessage() {
        return errors.stream()
            .map(ConstraintViolation::message)
            .reduce("ConstraintViolations: ",
                (messageA, messageB) -> String.join("\n\t", messageA, messageB));
    }
}
