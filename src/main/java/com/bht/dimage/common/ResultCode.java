package com.bht.dimage.common;

public enum ResultCode {
    SUCCESS(true,200,"成功"),
    ERROR(false,490,"后端出现错误"),
    NULL_POINTER_ERROR(false,491,"空指针异常"),
    INDEX_OUT_ERROR(false,492,"指针越界异常");

    private boolean success;
    private int code;
    private String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    public boolean isSuccess() { return success; }

    public int getCode() { return code; }

    public String getMessage() { return message; }
}
