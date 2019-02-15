package com.dyc.employee.exception;

public class NotFoundEmployeeException extends RuntimeException {
    public NotFoundEmployeeException(String message) {
        super(message);
    }
}

