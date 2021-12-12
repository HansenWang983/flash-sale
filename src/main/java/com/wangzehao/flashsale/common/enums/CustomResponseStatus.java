package com.wangzehao.flashsale.common.enums;

public enum CustomResponseStatus{

    SUCCESS(0, "success"),
    FAILD(-1, "failed"),
    ERROR(-2, "error");

    private int code;
    private String message;

    private CustomResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public String getOutputName() {
        return this.name();
    }

    public String toString() {
        return this.getName();
    }

    private CustomResponseStatus(Object... args) {
        this.message = String.format(this.message, args);
    }
}

