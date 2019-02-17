package com.dyc.employee.exception;

import com.dyc.employee.dto.ResponseDto;
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

//    //错误码返回 异常捕获
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorCodeResponse handleException(HttpServletRequest request,
//                                             Exception exception) {
//        LOGGER.error("error: ", exception);
//        return new ErrorCodeResponse(ErrorCode.INTERNAL_SERVER_ERROR);
//    }


    //返回通用结构体 异常捕获
    @ExceptionHandler(Exception.class)
    public ResponseDto handleException(HttpServletRequest request,
                                       Exception exception) {
        LOGGER.error("error: ", exception);
        return new ResponseDto(ErrorCode.INTERNAL_SERVER_ERROR,null);
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
    public ErrorCodeResponse handleErrorCodeException(HttpServletRequest request,
                                                             ErrorCodeException errorCodeException) {
        LOGGER.error(errorCodeException.getMessage());
        return new ErrorCodeResponse(errorCodeException.getErrorCode());
    }
}
