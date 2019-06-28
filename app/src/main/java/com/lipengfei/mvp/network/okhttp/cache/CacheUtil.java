package com.lipengfei.mvp.network.okhttp.cache;

import com.lipengfei.mvp.MVPApplication;
import com.lipengfei.mvp.util.CommonUtil;

import java.io.File;

import okhttp3.Cache;

public class CacheUtil {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(getCacheDir(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }

    private static File getCacheDir() {
        //设置缓存路径
        final File baseDir = CommonUtil.getCacheDirectory(MVPApplication.getContext(), null);
        final File cacheDir = new File(baseDir, "HttpResponseCache");
        return cacheDir;
    }
}
