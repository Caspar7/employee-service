package com.dyc.employee.exception;

public enum ErrorCode {

    /**
     * 服务内部错误
     */
    SUCCESS(0,"SUCCESS"),

    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(20000,"internal server error"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(20001,"parameter error"),

    /**
     * 员工不存在
     */
    EMPLOYEE_NOT_FOUND(20002,"employee not found"),
    ;

    private int code;
    private String msg;

    ErrorCode(int code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
