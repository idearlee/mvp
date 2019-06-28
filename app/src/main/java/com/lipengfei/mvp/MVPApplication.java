package com.lipengfei.mvp;

import android.app.Application;

public class MVPApplication extends Application {

    public static MVPApplication mApplication;

    public static MVPApplication getContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
