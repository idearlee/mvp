package com.lipengfei.mvp.network.okhttp;

public interface HttpRequestListener {

    void cancel();

    void onCanceled();
}
