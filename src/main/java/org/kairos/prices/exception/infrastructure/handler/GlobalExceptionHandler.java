package org.kairos.prices.exception.infrastructure.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.kairos.prices.exception.domain.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse notFound(ResourceNotFoundException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ExceptionResponse missingParam(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        return new ExceptionResponse("Parameter missing: " + ex.getParameterName(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionResponse typeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        return new ExceptionResponse("Parameter not valid: " + ex.getName(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex) {
        log.error("Unexpected error: ", ex);
        return "Unexpected error occurred. Please try again later or contact support.";
    }
}
