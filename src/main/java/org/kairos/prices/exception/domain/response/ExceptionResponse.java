package org.kairos.prices.exception.domain.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private String error;
    private String path;
    private LocalDateTime timestamp;

    public ExceptionResponse(String error, String path){
        this.error = error;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
