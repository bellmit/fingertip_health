package com.jqsoft.fingertip_health.base;


import com.jqsoft.fingertip_health.rx.RxBus;

/**
 * Created by Administrator on 2017-09-26.
 */

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
//    private Context context;
    private Thread.UncaughtExceptionHandler defaultHandler;

    public MyUncaughtExceptionHandler(Thread.UncaughtExceptionHandler defaultHandler) {
        super();
//        this.context = context;
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Util.showToast(context, Constants.HINT_EXIT_BECAUSE_OF_EXCEPTION);
//                Looper.loop();
//            }
//        }).start();
        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY, true);
        if (defaultHandler!=null){
            defaultHandler.uncaughtException(t, e);
        }
        System.exit(0);
    }
}
