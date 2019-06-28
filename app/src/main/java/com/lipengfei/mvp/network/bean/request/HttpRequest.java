package com.lipengfei.mvp.network.bean.request;

import com.lipengfei.mvp.network.bean.response.BaseBean;
import com.lipengfei.mvp.network.retrofit.APIServices;
import com.lipengfei.mvp.network.retrofit.GlobalRetrofit;
import com.lipengfei.mvp.network.rxjava.HttpRxCallback;
import com.lipengfei.mvp.network.rxjava.HttpRxObservable;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.TreeMap;

import io.reactivex.Observable;

public class HttpRequest {

    public static final String API_URL = "API_URL";

    public void request(Method method, TreeMap<String, Object> prams, HttpRxCallback callback) {

        Observable<BaseBean> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, callback).subscribe();

    }

    private Observable<BaseBean> handleRequest(Method method, TreeMap<String, Object> prams) {

        TreeMap<String, Object> request = getBaseRequest();
        request.putAll(prams);
        String apiUrl = "";
        if (request.containsKey(API_URL)) {
            apiUrl = String.valueOf(request.get(API_URL));
            request.remove(API_URL);
        }

        Observable<BaseBean> apiObservable;
        switch (method) {
            case GET:
                apiObservable = GlobalRetrofit.getRetrofit().create(APIServices.class).get(apiUrl, request);
                break;
            case POST:
                apiObservable = GlobalRetrofit.getRetrofit().create(APIServices.class).post(apiUrl, request);
                break;
            default:
                apiObservable = GlobalRetrofit.getRetrofit().create(APIServices.class).post(apiUrl, request);
                break;
        }
        return apiObservable;
    }

    private TreeMap<String, Object> getBaseRequest() {
        TreeMap<String, Object> map = new TreeMap<>();
        return map;
    }

    public void request(Method method, TreeMap<String, Object> prams, LifecycleProvider lifecycle, HttpRxCallback callback) {
        Observable<BaseBean> apiObservable = handleRequest(method, prams);
        HttpRxObservable.getObservable(apiObservable, lifecycle, callback).subscribe(callback);
    }

    public void request(Method method, TreeMap<String, Object> prams, LifecycleProvider<ActivityEvent> lifecycle, ActivityEvent event, HttpRxCallback callback) {
        Observable<BaseBean> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, lifecycle, event, callback).subscribe(callback);
    }

    public void request(Method method, TreeMap<String, Object> prams, LifecycleProvider<FragmentEvent> lifecycle, FragmentEvent event, HttpRxCallback callback) {
        Observable<BaseBean> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, lifecycle, event, callback).subscribe(callback);
    }

    public enum Method {
        GET,
        POST
    }

}
