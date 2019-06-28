package com.lipengfei.mvp.network.okhttp;

import com.lipengfei.mvp.network.okhttp.cache.CacheUtil;
import com.lipengfei.mvp.network.okhttp.interceptor.OfflineCacheControlInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * [Http client]
 *
 * @author Win
 * @date 2018-05-07
 */
public enum OkHttp {

    /**
     * 枚举单例
     */
    INSTANCE;
    /**
     * 读超时时间
     */
    private static final int TIMEOUT_READ = 15;
    /**
     * 连接超时
     */
    private static final int TIMEOUT_CONNECTION = 15;
    /**
     * HTTP client
     */
    private final OkHttpClient okHttpClient;
    /**
     * 缓存 Interceptor
     */
    private Interceptor cacheInterceptor = new OfflineCacheControlInterceptor();

    /**
     * 默认构造方法
     */
    OkHttp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                //打印日志
                .addInterceptor(interceptor)

                //设置Cache目录
                .cache(CacheUtil.getCache())

                //设置缓存
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)

                //失败重连
                .retryOnConnectionFailure(true)

                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)

                .build()

        ;
    }

    /**
     * 获取HTTP Client
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
