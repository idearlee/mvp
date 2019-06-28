package com.lipengfei.mvp.network.rxjava;

import android.text.TextUtils;

import com.lipengfei.mvp.network.exception.ApiException;
import com.lipengfei.mvp.network.exception.ExceptionEngine;
import com.lipengfei.mvp.network.okhttp.HttpRequestListener;
import com.lipengfei.mvp.util.LogUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class HttpRxCallback<T> implements Observer<T>, HttpRequestListener {


    private String mTag;//请求标识

    public HttpRxCallback() {
        this.mTag = String.valueOf(System.currentTimeMillis());
    }

    public HttpRxCallback(String tag) {
        this.mTag = tag;
    }

    @Override
    public void onError(Throwable e) {
        RxActionManager.INSTANCE.remove(mTag);
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            int code = exception.getCode();
            String msg = exception.getMsg();
            onError(code, msg);
        } else {
            onError(ExceptionEngine.UN_KNOWN_ERROR, "未知错误");
        }

    }

    public abstract void onError(int code, String desc);

    @Override
    public void onComplete() {
        LogUtil.d("observer onComplete");
    }

    @Override
    public void onNext(@NonNull T value) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManager.INSTANCE.remove(mTag);
        }
        onSuccess(value);
    }

    public abstract void onSuccess(T... ts);

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManager.INSTANCE.add(mTag, d);
        }
    }

    @Override
    public void cancel() {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManager.INSTANCE.cancel(mTag);
        }
    }

    @Override
    public void onCanceled() {
        onCancel();
    }

    public abstract void onCancel();
}
