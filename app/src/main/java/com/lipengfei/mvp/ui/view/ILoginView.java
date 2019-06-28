package com.lipengfei.mvp.ui.view;

import com.lipengfei.mvp.ui.base.BaseView;

public interface ILoginView extends BaseView {

    String getUsername();

    String getPassword();

    void showResult(String result);
}
