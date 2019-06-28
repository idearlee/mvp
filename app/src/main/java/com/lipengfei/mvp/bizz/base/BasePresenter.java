package com.lipengfei.mvp.bizz.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lipengfei.mvp.bizz.IPresenter;
import com.lipengfei.mvp.network.rxjava.LifeCycleListener;
import com.lipengfei.mvp.ui.base.BaseActivity;
import com.lipengfei.mvp.util.LogUtil;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BasePresenter<V, T> implements IPresenter<V>, LifeCycleListener {

    private static final String TAG = BasePresenter.class.getSimpleName();
    private Reference<V> mViewRef;
    private Reference<T> mActivityRef;
    private T mActivity;
    private V mView;

    public BasePresenter(V view, T activity) {
        attachView(view);
        attachActivity(activity);
        setListener(activity);
    }

    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
        mView = mViewRef.get();
    }

    private void attachActivity(T activity) {
        mActivityRef = new WeakReference<>(activity);
        mActivity = mActivityRef.get();
    }

    private void setListener(T activity) {
        if (getActivity() != null) {
            ((BaseActivity) activity).setOnLifeCycleListener(this);
        }
    }

    public T getActivity() {
        if (mActivityRef == null) {
            return null;
        }
        return mActivityRef.get();
    }

    public void checkAttachView() {
        if (!isAttachView()) {
            throw new ViewNotAttachedException();
        }
    }

    public boolean isAttachView() {
        return mView != null;
    }

    public V getView() {
        if (mViewRef == null) {
            return null;
        }
        return mViewRef.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.d(TAG, "--onCreate--");
    }

    @Override
    public void onStart() {
        LogUtil.d(TAG, "--onStart--");
    }

    @Override
    public void onRestart() {
        LogUtil.d(TAG, "--onRestart--");
    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "--onResume--");
    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, "--onPause--");
    }

    @Override
    public void onStop() {
        LogUtil.d(TAG, "--onStop--");
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "--onDestroy--");
        detachView();
        detachActivity();
        onDestroyed();
    }

    public void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    private void detachActivity() {
        LogUtil.d(TAG, "--detachActivity--");
        if (isActivityAttached()) {
            mActivityRef.clear();
            mActivityRef = null;
        }
    }

    @Override
    public void onDestroyed() {
        LogUtil.d(TAG, "presenter need destroyed !");
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public boolean isActivityAttached() {
        return mActivityRef != null && mActivityRef.get() != null;
    }

    @Override
    public void onAttach(Activity activity) {
        LogUtil.d(TAG, "--onAttach--");
    }

    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.d(TAG, "--onCreateView--");
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        LogUtil.d(TAG, "--onActivityCreated--");
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, "--onDestroyView--");
        detachView();
        detachActivity();
    }

    @Override
    public void onDetach() {
        LogUtil.d(TAG, "--onDetach--");
        detachView();
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }
}
