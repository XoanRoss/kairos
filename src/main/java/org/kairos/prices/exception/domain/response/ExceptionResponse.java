package org.kairos.prices.exception.domain.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ExceptionResponse(Exception exception, String path){
        this.message = exception.getMessage();
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
