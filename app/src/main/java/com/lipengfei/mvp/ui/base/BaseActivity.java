package com.lipengfei.mvp.ui.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lipengfei.mvp.R;
import com.lipengfei.mvp.network.rxjava.LifeCycleListener;
import com.lipengfei.mvp.util.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity implements BaseView, View.OnClickListener {

    public LifeCycleListener mListener;
    protected TextView mTvTitle;//toolbar标题
    protected LinearLayout mLlBack;//toolbar返回
    protected Unbinder unBinder;
    private RelativeLayout mActionBar;
    private ProgressDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        setContentView(getLayoutRes());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unBinder = ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
    }

    public void initToolBar() {
        mActionBar = findViewById(R.id.rl_toolbar);
        mTvTitle = findViewById(R.id.tv_title);
        mLlBack = findViewById(R.id.ll_image_back);
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (mLoading == null) {
            mLoading = new ProgressDialog(this);
        }
    }

    @Override
    public void showLoading() {
        if (mLoading != null) {
            mLoading.show();
        }
    }

    @Override
    public void hidLoading() {
        if (mLoading != null) {
            mLoading.dismiss();
        }
    }

    @Override
    public void showError(String errorMessage) {
        showToast(errorMessage);
    }

    public void showToast(String content) {
        ToastUtil.showToast(content);
    }

    public void setTitle(String title) {
        if (mActionBar != null) {
            mTvTitle.setText(title);
        }
    }

    public void setTitle(int res) {
        if (mActionBar != null) {
            mTvTitle.setText(res);
        }
    }

    public void showToast(int res) {
        ToastUtil.showToast(res);
    }

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }


}
