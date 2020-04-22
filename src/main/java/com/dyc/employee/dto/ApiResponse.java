package com.dyc.employee.dto;

import com.dyc.employee.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T> {

    private int code;

    private String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    private ApiResponse(T data) {
        this.code = 0;
        this.message = "SUCCESS";
        this.data = data;
    }

    private ApiResponse(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.message = errorCode.name();
        this.data = data;
    }

    public static <T> ApiResponse ok() {
        ApiResponse<T> rsp = new ApiResponse(null);
        return rsp;
    }

    public static <T> ApiResponse ok(T data) {
        ApiResponse<T> rsp = new ApiResponse(data);
        return rsp;
    }

    public static <T> ApiResponse fail(ErrorCode errorCode) {
        ApiResponse<T> rsp = new ApiResponse(errorCode, null);
        return rsp;
    }

    public static <T> ApiResponse fail(ErrorCode errorCode, String message) {
        ApiResponse<T> rsp = new ApiResponse(errorCode, message);
        return rsp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
