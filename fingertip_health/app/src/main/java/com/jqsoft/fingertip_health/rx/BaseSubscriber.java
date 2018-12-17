package com.jqsoft.fingertip_health.rx;

import android.content.Context;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.Util;

import rx.Subscriber;

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private Context context;

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!Util.isNetworkConnected(context)) {

//               Toast.makeText(context, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            // **一定要主动调用下面这一句**
            onCompleted();

            return;
        }
        // 显示进度条
        Util.showGifProgressDialog(context);
    }

    @Override
    public void onCompleted() {
        //关闭等待进度条
        Util.hideGifProgressDialog(context);

    }


    @Override
    public void onError(Throwable e) {
        if(e instanceof ExceptionHandle.ResponseThrowable){
//            onError((ExceptionHandle.ResponseThrowable)e);
            ExceptionHandle.ResponseThrowable rt = (ExceptionHandle.ResponseThrowable)e;
            Util.showToast(context, rt.getMessage());
        } else {
//            onError(new ExceptionHandle.ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
            Util.showToast(context, Constants.HINT_LOADING_DATA_FAILURE);
        }
        Util.hideGifProgressDialog(context);
    }
}