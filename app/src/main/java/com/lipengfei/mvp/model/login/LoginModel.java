package com.lipengfei.mvp.model.login;


import com.lipengfei.mvp.model.BaseModel;
import com.lipengfei.mvp.network.bean.request.HttpRequest;
import com.lipengfei.mvp.network.rxjava.HttpRxCallback;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.TreeMap;


public class LoginModel extends BaseModel implements ILoginModel {

    @Override
    public void login(final String userName, final String password, LifecycleProvider lifecycle, final HttpRxCallback rxCallback) {

        TreeMap<String, Object> request = new TreeMap<>();
        request.put("username", userName);
        request.put("password", password);
        request.put(HttpRequest.API_URL, API_LOGIN);
        getRequest().request(HttpRequest.Method.POST, request, lifecycle, rxCallback);
    }
}
