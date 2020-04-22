package com.dyc.employee.exception;

public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(String message) {
        super(message);
    }


    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
