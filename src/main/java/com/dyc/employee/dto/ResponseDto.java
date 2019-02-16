package com.dyc.employee.dto;

import com.dyc.employee.exception.ErrorCode;

public class ResponseDto<T> {

    private int code;

    private String msg;

    private T data;

    public ResponseDto(T data){
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    public ResponseDto(ErrorCode errorCode, T data){
        this.code = errorCode.getCode();
        this.msg = errorCode.name();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
