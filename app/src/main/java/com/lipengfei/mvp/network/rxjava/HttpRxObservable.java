package com.lipengfei.mvp.network.rxjava;

import com.lipengfei.mvp.network.bean.response.BaseBean;
import com.lipengfei.mvp.network.rxjava.fuction.HttpResultFunction;
import com.lipengfei.mvp.network.rxjava.fuction.ServerResultFunction;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class HttpRxObservable {

    public static Observable getObservable(Observable<BaseBean> apiObservable, LifecycleProvider lifecycle, final HttpRxCallback callback) {
        Observable observable;
        if (lifecycle != null) {
            observable = apiObservable
                    .map(new ServerResultFunction())
                    .compose(lifecycle.bindToLifecycle())
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .doOnDispose(new Action() {
                        @Override
                        public void run() throws Exception {
                            if (callback != null)
                                callback.onCanceled();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable, callback);
        }
        return observable;
    }

    public static Observable getObservable(Observable<BaseBean> apiObservable, final HttpRxCallback callback) {
        Observable observable = apiObservable
                .map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction<>())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (callback != null)
                            callback.onCanceled();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Observable getObservable(Observable<BaseBean> apiObservable, LifecycleProvider<ActivityEvent> lifecycle, ActivityEvent event, final HttpRxCallback callback) {
        Observable observable;
        if (lifecycle != null) {
            observable = apiObservable
                    .map(new ServerResultFunction())
                    .compose(lifecycle.bindUntilEvent(event))
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .doOnDispose(new Action() {
                        @Override
                        public void run() throws Exception {
                            if (callback != null)
                                callback.onCanceled();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable, callback);
        }
        return observable;
    }

    public static Observable getObservable(Observable<BaseBean> apiObservable, LifecycleProvider<FragmentEvent> lifecycle, FragmentEvent event, final HttpRxCallback callback) {
        Observable observable;
        if (lifecycle != null) {
            observable = apiObservable
                    .map(new ServerResultFunction())
                    .compose(lifecycle.bindUntilEvent(event))
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .doOnDispose(new Action() {
                        @Override
                        public void run() throws Exception {
                            if (callback != null)
                                callback.onCanceled();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable, callback);
        }
        return observable;
    }

}
