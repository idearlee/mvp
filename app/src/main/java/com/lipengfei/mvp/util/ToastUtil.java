package com.lipengfei.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.lipengfei.mvp.MVPApplication;


public class ToastUtil {

    private static final String TAG = ToastUtil.class.getSimpleName();

    private static String sToastTag;
    private static boolean sIsShowTag;

    private ToastUtil() {
        LogUtil.d(TAG, "Can't create Toast singleton");
    }

    public static void setToastTag(String tag, boolean showTag) {
        sToastTag = tag;
        sIsShowTag = showTag;
    }

    public static void showToast(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (sIsShowTag) {
            Toast.makeText(MVPApplication.getContext(), sToastTag + content, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MVPApplication.getContext(), content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(int content) {
        Context ctx = MVPApplication.getContext().getApplicationContext();
        if (content < 0 || TextUtils.isEmpty(ctx.getString(content))) {
            return;
        }
        if (sIsShowTag) {
            Toast.makeText(MVPApplication.getContext(), sToastTag + ctx.getString(content), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MVPApplication.getContext(), content, Toast.LENGTH_SHORT).show();
        }
    }


    public static void showToast(Activity ctx, int content) {
        if (ctx == null || ctx.isFinishing() || ctx.isDestroyed()) {
            return;
        }
        if (content < 0 || TextUtils.isEmpty(ctx.getString(content))) {
            return;
        }

        if (sIsShowTag) {
            Toast.makeText(MVPApplication.getContext(), sToastTag + ctx.getString(content), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MVPApplication.getContext(), content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Activity ctx, String content) {
        if (ctx == null || ctx.isFinishing() || ctx.isDestroyed()) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (sIsShowTag) {
            Toast.makeText(MVPApplication.getContext(), sToastTag + content, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MVPApplication.getContext(), content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLongToast(Activity ctx, int content) {

        if (ctx == null || ctx.isFinishing() || ctx.isDestroyed()) {
            return;
        }
        if (content < 0 || TextUtils.isEmpty(ctx.getString(content))) {
            return;
        }

        if (sIsShowTag) {
            Toast.makeText(MVPApplication.getContext(), sToastTag + ctx.getString(content), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MVPApplication.getContext(), content, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLongToast(Activity ctx, String content) {
        if (ctx == null || ctx.isFinishing() || ctx.isDestroyed()) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (sIsShowTag) {
            Toast.makeText(MVPApplication.getContext(), sToastTag + content, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MVPApplication.getContext(), content, Toast.LENGTH_LONG).show();
        }
    }
}
