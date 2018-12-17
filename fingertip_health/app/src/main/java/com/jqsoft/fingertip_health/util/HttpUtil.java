package com.jqsoft.fingertip_health.util;

import com.jqsoft.fingertip_health.callback.MyResultCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017-05-18.
 */

public class HttpUtil<T> {

    public void doHttpRequest(String url, Map<String, String> params, MyResultCallback<T> callback){
        PostFormBuilder builder = OkHttpUtils.post().url(url);
        if (params!=null){
            Set<Map.Entry<String,String>> set = params.entrySet();
            for (Map.Entry<String, String> entry:set){
                String key = entry.getKey();
                String value = entry.getValue();
                builder.addParams(key, value);
            }
        }
        builder.build().execute(callback);
    }

}
