package org.kairos.prices.exception.domain.response;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String message;
    private String path;

    public ExceptionResponse(Exception exception, String path){
        this.message = exception.getMessage();
        this.path = path;
    }
}
