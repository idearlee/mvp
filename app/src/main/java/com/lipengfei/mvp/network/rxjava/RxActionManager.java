package com.lipengfei.mvp.network.rxjava;

import android.util.ArrayMap;

import io.reactivex.disposables.Disposable;

public enum RxActionManager implements IRxActionManager {
    /**
     * 枚举单例
     */
    INSTANCE;

    private ArrayMap<Object, Disposable> mMaps;//处理,请求列表


    RxActionManager() {
        mMaps = new ArrayMap<>();
    }

    @Override
    public void add(Object tag, Disposable disposable) {
        if (mMaps == null) {
            return;
        }
        mMaps.put(tag, disposable);
    }

    @Override
    public void remove(Object tag) {
        if (!mMaps.isEmpty()) {
            mMaps.remove(tag);
        }
    }

    @Override
    public void cancel(Object tag) {
        if (mMaps == null) {
            return;
        }
        if (mMaps.isEmpty()) {
            return;
        }
        if (mMaps.get(tag) == null) {
            return;
        }
        if (!isDisposed(tag)) {
            mMaps.get(tag).dispose();
        }
        mMaps.remove(tag);
    }

    public boolean isDisposed(Object tag) {
        return mMaps.get(tag).isDisposed();
    }
}
