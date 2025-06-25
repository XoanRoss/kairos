package org.kairos.prices.exception.domain.exception;

public class MissingParameterException extends RuntimeException {

    public MissingParameterException(String message) {
        super("Parameter missing: " + message);
    }
}
