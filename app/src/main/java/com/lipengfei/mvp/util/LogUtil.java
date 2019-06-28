package com.lipengfei.mvp.util;

import android.util.Log;

import com.lipengfei.mvp.BuildConfig;

public class LogUtil {

    private static final String TAG = LogUtil.class.getSimpleName();
    private static boolean isDebug = BuildConfig.DEBUG;

    public static void d(String tag, String log) {
        if (isDebug) {
            Log.d(tag, log);
        }
    }

    public static void e(String tag, String log) {
        if (isDebug) {
            Log.e(tag, log);
        }
    }

    public static void i(String tag, String log) {
        if (isDebug) {
            Log.i(tag, log);
        }
    }

    public static void w(String tag, String log) {
        if (isDebug) {
            Log.w(tag, log);
        }
    }

    public static void d(String log) {
        if (isDebug) {
            Log.d(TAG, log);
        }
    }

    public static void e(String log) {
        if (isDebug) {
            Log.e(TAG, log);
        }
    }

    public static void i(String log) {
        if (isDebug) {
            Log.i(TAG, log);
        }
    }

    public static void w(String log) {
        if (isDebug) {
            Log.w(TAG, log);
        }
    }
}
