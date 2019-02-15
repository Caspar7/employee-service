package com.dyc.employee.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {


    private Integer status;
    private String error;
    private String exception;
    private String message;
    private String path;
    private Long timestamp = System.currentTimeMillis();

    public ErrorResponse(HttpStatus httpStatus, String exception, String message, String path) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.exception = exception;
        this.message = message;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
