

package com.example.demo.bean.vo;

import com.example.demo.bean.vo.ResponseContent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResultContent<T> extends ResponseContent implements Cloneable {
    private Integer errorCode;
    private T result;

    public ResultContent(int code, String status, T result, String errorMessage) {
        super(code, status, errorMessage);
        this.result = result;
    }

    public ResultContent(int code, String status, String errorMessage) {
        super(code, status, errorMessage);
    }

    public ResultContent() {
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> ResultContent<T> getSuccessResult() {
        return new ResultContent(0, "success", (String) null);
    }

    public static <T> ResultContent<T> createSuccessResult(T result) {
        return new ResultContent(0, "success", result, (String) null);
    }

    public static <T> ResultContent<T> getErrorResult() {
        return new ResultContent(-1, "fail", "异常错误！");
    }

    public static <T> ResultContent<T> createErrorResult(String errorMessage) {
        return new ResultContent(-1, "fail", errorMessage);
    }

    public static <T> ResultContent<T> getErrorResult(ResultContent<T> resultContent, String errorMessage) {
        resultContent.setCode(-1);
        resultContent.setStatus("fail");
        resultContent.setErrorMessage(errorMessage);
        return resultContent;
    }

    public String toString() {
        return "ResultContent [result=" + this.result + ", getCode()=" + this.getCode() + ", getStatus()=" + this.getStatus() + ", getErrorMessage()=" + this.getErrorMessage() + ", toString()=" + super.toString() + ", getClass()=" + this.getClass() + ", hashCode()=" + this.hashCode() + "]";
    }
}
