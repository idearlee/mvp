package com.lipengfei.mvp.bizz;

import com.lipengfei.mvp.bizz.base.BasePresenter;

public interface IPresenterFactory<T extends BasePresenter> {

    T create();
}
