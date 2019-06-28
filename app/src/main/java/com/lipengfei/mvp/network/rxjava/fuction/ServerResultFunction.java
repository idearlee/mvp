package com.lipengfei.mvp.network.rxjava.fuction;

import com.google.gson.Gson;
import com.lipengfei.mvp.network.bean.response.BaseBean;
import com.lipengfei.mvp.network.exception.ServerException;
import com.lipengfei.mvp.util.LogUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class ServerResultFunction implements Function<BaseBean, Object> {


    @Override
    public Object apply(@NonNull BaseBean response) throws Exception {
        LogUtil.e("HttpResponse:" + response.toString());
        if (!response.isSuccess()) {
            int code = response.getErrorCode();
            String data = response.getErrorMsg();
            throw new ServerException(code, data);//抛出服务器错误
        }
        return new Gson().toJson(response.getData());
    }
}
