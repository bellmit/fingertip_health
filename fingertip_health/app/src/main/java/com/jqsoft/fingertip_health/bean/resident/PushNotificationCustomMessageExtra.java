package com.jqsoft.fingertip_health.bean.resident;


import com.jqsoft.fingertip_health.base.Constants;

/**
 * Created by Administrator on 2017-09-15.
 */

public class PushNotificationCustomMessageExtra {
    private String pushNotificationIntent= Constants.EMPTY_STRING;
    private String userUuid=Constants.EMPTY_STRING;//推送通知发送目标用户uuid

    public PushNotificationCustomMessageExtra() {
        super();
    }

    public PushNotificationCustomMessageExtra(String pushNotificationIntent, String userUuid) {
        this.pushNotificationIntent = pushNotificationIntent;
        this.userUuid = userUuid;
    }

    public String getPushNotificationIntent() {
        return pushNotificationIntent;
    }

    public void setPushNotificationIntent(String pushNotificationIntent) {
        this.pushNotificationIntent = pushNotificationIntent;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
