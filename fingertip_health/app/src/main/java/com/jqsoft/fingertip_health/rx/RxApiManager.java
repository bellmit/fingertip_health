package com.jqsoft.fingertip_health.rx;

import android.support.v4.util.ArrayMap;

import java.util.Set;

import rx.Subscription;

/**
 * 具体请求的subscription

 　Subscription subscription = Ｒｅｔｒｏｆｉｔ返回的subscription实例

 加入ｒｘＡｐｉ管理池

 RxApiManager.get().add("my", subscription);
 取消

 　RxApiManager.get().cancel("my");

 一般我们在在activity的 onDestroy(), Fragment的 onDestroyView()中调用

 也可以在onPause()中取消

 */

public class RxApiManager implements RxActionManager<Object> {

    private static RxApiManager sInstance = null;

    private ArrayMap<Object, Subscription> maps;

    public static RxApiManager get() {

        if (sInstance == null) {
            synchronized (RxApiManager.class) {
                if (sInstance == null) {
                    sInstance = new RxApiManager();
                }
            }
        }
        return sInstance;
    }

    //     @TargetApi(Build.VERSION_CODES.KITKAT)
    private RxApiManager() {
        maps = new ArrayMap<>();
    }

    //   @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void add(Object tag, Subscription subscription) {
        maps.put(tag, subscription);
    }

    //   @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void remove(Object tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    //  @TargetApi(Build.VERSION_CODES.KITKAT)
    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    //   @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancel(Object tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isUnsubscribed()) {
            maps.get(tag).unsubscribe();
            maps.remove(tag);
        }
    }

    //   @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<Object> keys = maps.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}
