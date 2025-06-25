package org.kairos.prices.exception.infrastructure.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kairos.prices.exception.domain.exception.MissingParameterException;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.kairos.prices.exception.domain.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse notFound(ResourceNotFoundException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        return new ExceptionResponse(ex, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingParameterException.class)
    public ExceptionResponse badRequest(MissingParameterException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        return new ExceptionResponse(ex, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex) {
        log.error("Unexpected error: ", ex);
        return "Unexpected error occurred. Please try again later or contact support.";
    }
}
