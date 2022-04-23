package com.bht.dimage.common;

public class RestResult<T> {
    private boolean success;
    private int code;
    private String message;
    private T data = null;

    public static RestResult Success(){
        RestResult res = new RestResult();
        res.setSuccess(ResultCode.SUCCESS.isSuccess());
        res.setCode(ResultCode.SUCCESS.getCode());
        res.setMessage(ResultCode.SUCCESS.getMessage());
        return res;
    }

    public static RestResult Fail(){
        RestResult res = new RestResult();
        res.setSuccess(ResultCode.ERROR.isSuccess());
        res.setCode(ResultCode.ERROR.getCode());
        res.setMessage(ResultCode.ERROR.getMessage());
        return res;
    }

    public RestResult setResult(ResultCode result) {
        RestResult res = new RestResult();
        res.setSuccess(result.isSuccess());
        res.setCode(result.getCode());
        res.setMessage(result.getMessage());
        return res;
    }

    public RestResult code(int code) {
        this.setCode(code);
        return this;
    }

    public RestResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public RestResult data(T data) {
        this.setData(data);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
