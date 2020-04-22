package com.dyc.employee.exception;

import com.dyc.employee.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Controller
@ApiIgnore
public class GlobaleFrameworkErrorHandler implements ErrorController {

    private static final String ERR_PATH = "/error";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobaleFrameworkErrorHandler.class);

    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }

    @RequestMapping(value = ERR_PATH)
    @ResponseBody
    public Object handleError(HttpServletRequest request, HttpServletResponse response) {
        Object statusCodeObj = request.getAttribute("javax.servlet.error.status_code");
        Object execptionObj = request.getAttribute("javax.servlet.error.exception");
        if (null != statusCodeObj && null != execptionObj) {
//            Integer statusCode = (Integer) statusCodeObj;
            Exception exception = (Exception) statusCodeObj;

            if (exception instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
                String message = String.join(", ", ex.getBindingResult().getFieldErrors().stream()
                        .map(e -> e.getField() + ":" + e.getDefaultMessage())
                        .collect(Collectors.toList()));

                LOGGER.error("handleError: " + message);
                LOGGER.error("handException: ", exception);
                return ApiResponse.fail(ErrorCode.PARAMETER_ERROR, message);
            }

            LOGGER.error(ErrorCode.INTERNAL_SERVER_ERROR.getMsg(), exception);
        }

        return ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
