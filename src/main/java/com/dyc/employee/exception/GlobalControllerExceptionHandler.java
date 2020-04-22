package com.dyc.employee.exception;

import com.dyc.employee.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    //返回通用结构体 异常捕获
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse handleException(Exception exception) {
        LOGGER.error("error: ", exception);
        return ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleException(MethodArgumentNotValidException exception) {
        String message = String.join(",", exception.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .collect(Collectors.toList()));
        LOGGER.error("handleError: " + message);
        LOGGER.error("handException: ", exception);
        return ApiResponse.fail(ErrorCode.PARAMETER_ERROR, message);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse handleErrorCodeException(BusinessException businessException) {
        LOGGER.error("handException: ", businessException);
        return ApiResponse.fail(businessException.getErrorCode());
    }
}
