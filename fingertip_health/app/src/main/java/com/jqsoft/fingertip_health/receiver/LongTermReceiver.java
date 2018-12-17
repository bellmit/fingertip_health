package com.jqsoft.fingertip_health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.resident.PushNotificationCustomMessageExtra;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

//import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017-09-07.
 */

public class LongTermReceiver extends BroadcastReceiver {
    public LongTermReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        LogUtil.i("LongTermReceiver onReceive intent:"+intent);
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Gson gson = new Gson();
            PushNotificationCustomMessageExtra msgExtra = new PushNotificationCustomMessageExtra();
            String pi = Constants.EMPTY_STRING;
            String userUuid = Constants.EMPTY_STRING;
            try {
                msgExtra = gson.fromJson(extras, PushNotificationCustomMessageExtra.class);
                pi = msgExtra.getPushNotificationIntent();
                userUuid = msgExtra.getUserUuid();
            } catch (Exception e) {
                e.printStackTrace();
            }

            LogUtil.i("received event, actioin:" + intent.getAction());
            LogUtil.i("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            String action = intent.getAction();
            if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action)) {

                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                LogUtil.i("接收到推送下来的通知的ID: " + notifactionId);


            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(action)) {
                RxBus.getDefault().post(Constants.EVENT_TYPE_INDEX_REFRESH_INTELLIGENT_HONOUR_AGREEMENT, true);
                RxBus.getDefault().post(Constants.EVENT_TYPE_REFRESH_CHAT_PERSON_LIST, true);
                RxBus.getDefault().post(Constants.EVENT_TYPE_CHATDETAILACTIVTY, true);
                String rawTitle = bundle.getString(JPushInterface.EXTRA_TITLE, Constants.APP_NAME);
                String rawMsg = bundle.getString(JPushInterface.EXTRA_MESSAGE, Constants.HINT_YOU_RECEIVE_NEW_MESSAGE);
                String processedTitle = Util.trimString(rawTitle);
                String processedMsg = Util.trimString(rawMsg);
             //   processedMsg = Util.getDecodedBase64String(processedMsg);
            //    processedMsg =processedMsg;;
                LogUtil.i("收到推送自定义消息,rawTitle:" + rawTitle + " rawMsg:" + rawMsg +
                        " processedTitle:" + processedTitle + " processedMsg:" + processedMsg);
                Util.showNotification(context, processedTitle,  processedMsg, pi, userUuid);


            }
        }
    }


    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    LogUtil.i("This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LogUtil.i("Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}
