package com.dyc.employee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorCodeResponse handleException(HttpServletRequest request,
                                             Exception exception) {
        LOGGER.error("error: ", exception);
        return new ErrorCodeResponse(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorCodeResponse handleException(HttpServletRequest request,
                                             MethodArgumentNotValidException exception) {
        String message = String.join(", ", exception.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + " is required")
                .collect(Collectors.toList()));
        LOGGER.error("error: " + message);
        return new ErrorCodeResponse(ErrorCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(ErrorCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorCodeResponse handleNotFoundEmployeeException(HttpServletRequest request,
                                                             ErrorCodeException errorCodeException) {
        LOGGER.error(errorCodeException.getMessage());
        return new ErrorCodeResponse(errorCodeException.getErrorCode());
    }
}
