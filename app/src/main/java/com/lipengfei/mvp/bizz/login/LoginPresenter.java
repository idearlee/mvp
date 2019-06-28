package com.lipengfei.mvp.bizz.login;

import android.text.TextUtils;

import com.lipengfei.mvp.bizz.base.BasePresenter;
import com.lipengfei.mvp.model.login.LoginModel;
import com.lipengfei.mvp.network.bean.response.UserInfo;
import com.lipengfei.mvp.network.rxjava.HttpRxCallback;
import com.lipengfei.mvp.ui.base.BaseActivity;
import com.lipengfei.mvp.ui.view.ILoginView;
import com.lipengfei.mvp.util.LogUtil;
import com.trello.rxlifecycle2.LifecycleProvider;

public class LoginPresenter extends BasePresenter implements ILoginPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();


    private LoginModel mLoginModel;
    private ILoginView mLoginView;

    public LoginPresenter(ILoginView view, LifecycleProvider activity) {
        super(view, activity);
        mLoginModel = new LoginModel();
        mLoginView = view;
    }

    @Override
    public void login() {
        checkAttachView();
        if (TextUtils.isEmpty(mLoginView.getUsername()) || TextUtils.isEmpty(mLoginView.getPassword())) {
            mLoginView.showError("用户名，密码不能为空,请重试！");
            return;
        }
        mLoginView.showLoading();
        mLoginModel.login(mLoginView.getUsername(), mLoginView.getPassword(), (BaseActivity) getActivity(), new HttpRxCallback<UserInfo>(TAG) {
            @Override
            public void onError(int code, String desc) {
                mLoginView.hidLoading();
                mLoginView.showError(desc);
            }

            @Override
            public void onSuccess(UserInfo... inFos) {
                mLoginView.hidLoading();
                if (inFos == null) {
                    return;
                }
                for (int i = 0; i < inFos.length; i++) {
                    LogUtil.d(TAG, inFos[i].getUserId() + "  " + inFos[i].getPassword());
                }
                UserInfo info = inFos[0];
                mLoginView.showResult("用户名是：" + info.getUserId() + " 密码是：" + info.getPassword());
            }

            @Override
            public void onCancel() {
                mLoginView.hidLoading();
            }
        });
    }
}
