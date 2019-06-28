package com.lipengfei.mvp.model.login;

import com.lipengfei.mvp.model.IModel;
import com.lipengfei.mvp.network.rxjava.HttpRxCallback;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface ILoginModel extends IModel {

    String API_LOGIN = "user/login";

    void login(String userName, String password, LifecycleProvider lifecycle, HttpRxCallback callBack);
}
