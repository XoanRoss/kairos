package org.kairos.prices.exception.infrastructure.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.kairos.prices.exception.domain.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse notFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return new ExceptionResponse(ex, request.getRequestURI());
    }
}
