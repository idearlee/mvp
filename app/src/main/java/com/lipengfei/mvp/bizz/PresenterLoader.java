package com.lipengfei.mvp.bizz;

import android.content.Context;
import android.content.Loader;

import com.lipengfei.mvp.bizz.base.BasePresenter;

public class PresenterLoader<T extends BasePresenter> extends Loader<T> {
    private T presenter;
    private PresenterFactory factory;

    public PresenterLoader(Context context, PresenterFactory factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {

        // 如果已经有Presenter实例那就直接返回
        if (presenter != null) {
            deliverResult((T) presenter);
            return;
        }

        // 如果没有 就促使加载
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // 实例化 Presenter
        presenter = (T) factory.create();
        // 返回 Presenter
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter.onDestroyed();
        presenter = null;
    }

}
