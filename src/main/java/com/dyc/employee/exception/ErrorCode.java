package com.dyc.employee.exception;

public enum ErrorCode {

    /**
     * 服务内部错误
     */
    SUCCESS(0),

    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(20000),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(20001),

    /**
     * 员工不存在
     */
    EMPLOYEE_NOT_FOUND(20002),
    ;

    private int code;

    ErrorCode(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
}
