//package com.jqsoft.grassroots_civil_administration_platform.receiver;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.ChatConfigurationBean;
//import com.jqsoft.fingertip_health.di.ui.activity.ChatActivity;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Iterator;
//
//import cn.jpush.android.api.JPushInterface;
//
//public class MyReceiver extends BroadcastReceiver {
//    private static final String TAG = "JPush";
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        try {
//            Bundle bundle = intent.getExtras();
////            LogUtil.i(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//
//            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                LogUtil.i(TAG, "[MyReceiver] 接收Registration Id : " + regId);
//                //send the Registration Id to your server...
//
//            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//                LogUtil.i(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//                processCustomMessage(context, bundle);
//
//            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                LogUtil.i(TAG, "[MyReceiver] 接收到推送下来的通知");
//                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//                LogUtil.i(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
//            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//                LogUtil.i(TAG, "[MyReceiver] 用户点击打开了通知");
//                processNotificationClick(context, bundle);
//
////                //打开自定义的Activity
////                Intent i = new Intent(context, TestActivity.class);
////                i.putExtras(bundle);
////                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
////                context.startActivity(i);
//
//            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//                LogUtil.i(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
//                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
//
//            } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
//                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//                LogUtil.i(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
//                if (connected){
//                    Util.showToast(context, Constants.HINT_CONNECTION_TO_PUSH_SERVER_SUCCESS);
//                } else {
//                    Util.showToast(context, Constants.HINT_CONNECTION_TO_PUSH_SERVER_FAILURE);
//                }
//            } else {
//                LogUtil.i(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
//            }
//        } catch (Exception e){
//            Util.showToast(context, Constants.HINT_EXCEPTION_OCCURRED_WHEN_PROCESSING_PUSH_NOTIFICATION_OR_CUSTOM_MESSAGE);
//        }
//
//    }
//
//    private void processNotificationClick(Context context, Bundle bundle){
//        try {
//            String rawTitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE, Constants.APP_NAME);
//            String rawContent = bundle.getString(JPushInterface.EXTRA_ALERT, Constants.HINT_YOU_RECEIVE_NEW_MESSAGE);
//            String processedTitle = Util.trimString(rawTitle);
//            String processedContent = Util.trimString(rawContent);
//            LogUtil.i("收到推送通知,rawTitle:"+rawTitle+" rawContent:"+rawContent+
//                    " processedTitle:"+processedTitle+" processedContent:"+processedContent);
//            processCustomMessageOrNotificationByLaunchingUI(context, bundle);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Util.showToast(context, Constants.HINT_EXCEPTION_OCCURRED_WHEN_PROCESSING_PUSH_NOTIFICATION);
//        }
//    }
//
//    private void processCustomMessage(Context context, Bundle bundle) {
//
//        try {
//            String rawTitle = bundle.getString(JPushInterface.EXTRA_TITLE, Constants.APP_NAME);
//            String rawMsg = bundle.getString(JPushInterface.EXTRA_MESSAGE, Constants.HINT_YOU_RECEIVE_NEW_MESSAGE);
//            String processedTitle = Util.trimString(rawTitle);
//            String processedMsg = Util.trimString(rawMsg);
//            LogUtil.i("收到推送自定义消息,rawTitle:"+rawTitle+" rawMsg:"+rawMsg+
//            " processedTitle:"+processedTitle+" processedMsg:"+processedMsg);
//            Util.showNotification(context, processedTitle, processedMsg);
//            processCustomMessageOrNotificationByLaunchingUI(context, bundle);
////        if (MainActivity.isForeground) {
////            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
////            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
////            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
////            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
////            if (!ExampleUtil.isEmpty(extras)) {
////                try {
////                    JSONObject extraJson = new JSONObject(extras);
////                    if (extraJson.length() > 0) {
////                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
////                    }
////                } catch (JSONException e) {
////
////                }
////
////            }
//////            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
////        }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Util.showToast(context, Constants.HINT_EXCEPTION_OCCURRED_WHEN_PROCESSING_PUSH_CUSTOM_MESSAGE);
//        }
//    }
//
//    public void processCustomMessageOrNotificationByLaunchingUI(Context context, Bundle bundle){
//        String fromId = getFromIdFromCustomMessage(bundle);
//        String toId = getToIdFromCustomMessage(bundle);
//        String content = getContentFromCustomMessage(bundle);
//        if (Util.isCurrentChatActivity(context)){
//            ChatConfigurationBean bean = new ChatConfigurationBean();
//            bean.setShouldRefresh(true);
//            bean.setFromId(fromId);
//            bean.setToId(toId);
//            RxBus.getDefault().post(Constants.EVENT_TYPE_SHOULD_REFRESH_CHAT_MESSAGE, bean);
//        } else if (Util.isAppVisibleToUser(context)){
//            Intent intent = new Intent(context, ChatActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(Constants.MESSAGE_FROM_USER_ID_KEY, fromId);
//            intent.putExtra(Constants.MESSAGE_TO_USER_ID_KEY, toId);
//            intent.putExtra(Constants.MESSAGE_CONTENT_KEY, content);
//            context.startActivity(intent);
//        } else if (Util.isAppRunning(context)){
//            Util.bringApplicationToForegroundFromChatMessageNotificationClick(context, fromId, toId, content);
////            Util.runApplicationFromScratch(context, Constants.PACKAGE_NAME);
//        } else {
//            Util.runApplicationFromScratch(context, Constants.PACKAGE_NAME);
//        }
//
//    }
//
//    public String getFromIdFromCustomMessage(Bundle bundle){
//        String fromId = Constants.EMPTY_STRING;
//        try {
//            String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE, Constants.EMPTY_STRING);
//            JSONObject extrasJson = new JSONObject(extras);
//            fromId = extrasJson.optString(Constants.MESSAGE_FROM_USER_ID_KEY, Constants.EMPTY_STRING);
//        } catch (Exception e) {
//            LogUtil.i(TAG, "Unexpected: extras is not a valid json:"+e.getMessage());
//        }
//        return fromId;
//    }
//
//    public String getToIdFromCustomMessage(Bundle bundle){
//        String toId = Constants.EMPTY_STRING;
//        try {
//            String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE, Constants.EMPTY_STRING);
//            JSONObject extrasJson = new JSONObject(extras);
//            toId = extrasJson.optString(Constants.MESSAGE_TO_USER_ID_KEY, Constants.EMPTY_STRING);
//        } catch (Exception e) {
//            LogUtil.i(TAG, "Unexpected: extras is not a valid json:"+e.getMessage());
//        }
//        return toId;
//    }
//
//    public String getContentFromCustomMessage(Bundle bundle){
//        String message = Constants.EMPTY_STRING;
//        try {
//            String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE, Constants.EMPTY_STRING);
//            JSONObject extrasJson = new JSONObject(extras);
//            message = extrasJson.optString(Constants.MESSAGE_CONTENT_KEY, Constants.EMPTY_STRING);
//        } catch (Exception e) {
//            LogUtil.i(TAG, "Unexpected: extras is not a valid json:"+e.getMessage());
//        }
//        return message;
//    }
//
//    // 打印所有的 intent extra 数据
//    private static String printBundle(Bundle bundle) {
//        StringBuilder sb = new StringBuilder();
//        for (String key : bundle.keySet()) {
//            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
//                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
//            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
//                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
//            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
//                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
//                    LogUtil.i(TAG, "This message has no Extra data");
//                    continue;
//                }
//
//                try {
//                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
//                    Iterator<String> it =  json.keys();
//
//                    while (it.hasNext()) {
//                        String myKey = it.next().toString();
//                        sb.append("\nkey:" + key + ", value: [" +
//                                myKey + " - " +json.optString(myKey) + "]");
//                    }
//                } catch (JSONException e) {
//                    LogUtil.i(TAG, "Get message extra JSON error!");
//                }
//
//            } else {
//                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
//            }
//        }
//        return sb.toString();
//    }
//
//
//}