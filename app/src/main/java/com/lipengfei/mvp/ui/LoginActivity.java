package com.lipengfei.mvp.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lipengfei.mvp.R;
import com.lipengfei.mvp.bizz.PresenterFactory;
import com.lipengfei.mvp.bizz.PresenterLoader;
import com.lipengfei.mvp.bizz.login.LoginPresenter;
import com.lipengfei.mvp.ui.base.BaseActivity;
import com.lipengfei.mvp.ui.view.ILoginView;
import com.lipengfei.mvp.util.LogUtil;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements ILoginView, LoaderManager.LoaderCallbacks<LoginPresenter> {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.login_user_id)
    EditText mUserIdEt;
    @BindView(R.id.login_user_pwd)
    EditText mUserPwdEt;
    @BindView(R.id.login_user_btn)
    Button mLoginBtn;

    private LoginPresenter mPresenter;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        LogUtil.d(TAG, "initView");
        initToolBar();
        setTitle(R.string.title_login);
    }

    @Override
    protected void initListener() {
        LogUtil.d(TAG, "initListener");
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public String getUsername() {
        return mUserIdEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return mUserPwdEt.getText().toString();
    }

    @Override
    public void showResult(String result) {
        showToast(result);
    }

    @Override
    public void showError(String errorMessage) {
        showToast(errorMessage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_user_btn) {
            mPresenter.login();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        mPresenter.detachView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroyed();
        super.onDestroy();
    }

    @Override
    public Loader<LoginPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new PresenterFactory(this, this));
    }

    @Override
    public void onLoadFinished(Loader<LoginPresenter> loader, LoginPresenter data) {
        this.mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<LoginPresenter> loader) {
        this.mPresenter = null;
    }
}
