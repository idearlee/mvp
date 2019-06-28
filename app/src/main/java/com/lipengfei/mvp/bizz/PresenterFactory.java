package com.lipengfei.mvp.bizz;

import com.lipengfei.mvp.bizz.base.BasePresenter;
import com.lipengfei.mvp.bizz.login.LoginPresenter;
import com.lipengfei.mvp.ui.base.BaseActivity;
import com.lipengfei.mvp.ui.base.BaseView;
import com.lipengfei.mvp.ui.view.ILoginView;
import com.trello.rxlifecycle2.LifecycleProvider;

public class PresenterFactory implements IPresenterFactory {

    private BaseView mView;
    private BaseActivity mActivity;

    public PresenterFactory(BaseView view, BaseActivity activity) {
        this.mView = view;
        this.mActivity = activity;
    }

    @Override
    public BasePresenter create() {
        if (mView instanceof ILoginView) {
            return new LoginPresenter((ILoginView) mView, mActivity);
        } else
            return new BasePresenter<>(mView, mActivity);
    }
}
