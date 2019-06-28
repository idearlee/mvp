package com.lipengfei.mvp.ui.base;

public interface BaseView {

    void showLoading();

    void hidLoading();

    void showError(String errorMessage);

    void showToast(String content);
}
