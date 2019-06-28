package com.lipengfei.mvp.network.bean.response;


public class BaseBean<T> {

    private int errorCode;
    private String errorMsg;
    private int rectCode;

    private T data;

    public boolean isSuccess() {
        return errorCode == 0;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getRectCode() {
        return rectCode;
    }

    public void setRectCode(int rectCode) {
        this.rectCode = rectCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
