package com.dyc.employee.exception;

public class ErrorCodeException extends RuntimeException {

    private ErrorCode errorCode;

    public ErrorCodeException(String message) {
        super(message);
    }


    public ErrorCodeException(ErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
