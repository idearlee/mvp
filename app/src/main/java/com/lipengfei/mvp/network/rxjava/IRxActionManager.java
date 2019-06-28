package com.lipengfei.mvp.network.rxjava;

import io.reactivex.disposables.Disposable;

public interface IRxActionManager<T> {

    void add(T tag, Disposable disposable);

    void remove(T tag);

    void cancel(T tag);
}
