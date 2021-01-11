package com.example.demo.bean.vo;

public class ResponseContent {
    private int code;
    private String status;
    private String errorMessage;

    public ResponseContent(int code, String status, String errorMessage) {
        this.code = code;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public ResponseContent() {
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "ResponseContent [code=" + this.code + ", status=" + this.status + ", errorMessage=" + this.errorMessage + "]";
    }
}
