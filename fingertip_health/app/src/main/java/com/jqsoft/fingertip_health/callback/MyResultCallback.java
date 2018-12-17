package com.jqsoft.fingertip_health.callback;

/**
 * Created by Administrator on 2017-05-18.
 */

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.utils.JsonUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * http请求回调类,回调方法在UI线程中执行
 *
 * @param <T>
 */
public abstract class MyResultCallback<T> extends Callback<T> {

    Type mType;
    private Handler mDelivery;

    public MyResultCallback() {
        mType = getSuperclassTypeParameter(getClass());
        mDelivery = new Handler(Looper.getMainLooper());
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        try {
            String str = response.body().string();
            Object object = JsonUtils.deserialize(str, mType);
            return (T) object;
        } catch (final Exception e) {
            return  null;
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        sendFailCallback(this, e);
    }

    @Override
    public void onResponse(T response, int id) {
        if (response!=null) {
            sendSuccessCallBack(this, response);
        } else {
            sendFailCallback(this, new Exception(Constants.RESPONSE_NULL));
        }
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    private void sendFailCallback(final MyResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final MyResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    /**
     * 请求成功回调
     *
     * @param response
     */
    public abstract void onSuccess(T response);

    /**
     * 请求失败回调
     *
     * @param e
     */
    public abstract void onFailure(Exception e);
}
