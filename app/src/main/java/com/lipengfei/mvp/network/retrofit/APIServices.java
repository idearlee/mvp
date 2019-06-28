package com.lipengfei.mvp.network.retrofit;

import com.lipengfei.mvp.network.bean.response.BaseBean;
import com.lipengfei.mvp.network.bean.response.UserInfo;

import java.util.TreeMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface APIServices {

    String BASE_URL = "https://www.wanandroid.com/";

    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseBean<UserInfo>> login(@Field("username") String userId, @Field("password") String password);


    @GET
    Observable<BaseBean> get(@Url String url, @QueryMap TreeMap<String, Object> request);

    @FormUrlEncoded
    @POST
    Observable<BaseBean> post(@Url String url, @FieldMap TreeMap<String, Object> request);
}
