package com.lipengfei.mvp.network.retrofit;

import com.lipengfei.mvp.network.okhttp.OkHttp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalRetrofit {

    private static Retrofit sRetrofit;

    private GlobalRetrofit() {

    }

    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(APIServices.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttp.INSTANCE.getOkHttpClient())
                    .build();
        }
        return sRetrofit;

    }
}