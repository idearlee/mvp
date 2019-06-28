package com.lipengfei.mvp.model;

import com.lipengfei.mvp.network.bean.request.HttpRequest;

public class BaseModel {


    protected HttpRequest mHttpRequest;

    public BaseModel() {
        mHttpRequest = new HttpRequest();
    }

    protected HttpRequest getRequest() {
        if (mHttpRequest == null) {
            mHttpRequest = new HttpRequest();
        }
        return mHttpRequest;
    }

}
