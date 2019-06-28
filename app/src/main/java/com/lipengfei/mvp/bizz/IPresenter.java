package com.lipengfei.mvp.bizz;

public interface IPresenter<V> {

    void attachView(V view);

    void detachView();

    void onDestroyed();
}
