package com.dyc.employee.exception;

public class ErrorCodeResponse {

    private int errcode;

    private String errmsg;

    public ErrorCodeResponse(int errcode,String errmsg)
    {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public ErrorCodeResponse(ErrorCode errorCode)
    {
        this.errcode = errorCode.getCode();
        this.errmsg = errorCode.name();
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
