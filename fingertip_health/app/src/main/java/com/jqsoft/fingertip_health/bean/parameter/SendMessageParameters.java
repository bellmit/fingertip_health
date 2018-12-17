package com.jqsoft.fingertip_health.bean.parameter;

import com.jqsoft.fingertip_health.annotation.HttpParameter;
import com.jqsoft.fingertip_health.bean.parameter.base.CommonParameters;

/**
 * Created by Administrator on 2017-06-08.
 */

public class SendMessageParameters extends CommonParameters {
    @HttpParameter
    private String to;	//	发送给哪个用户ID
    @HttpParameter
    private String content;     //消息，长度500


    public SendMessageParameters() {
        super();
    }

    public SendMessageParameters(String to, String content) {
        this.to = to;
        this.content = content;
    }

    public SendMessageParameters(String appkey, String timestamp, String token, String sig, String v, String to, String content) {
        super(appkey, timestamp, token, sig, v);
        this.to = to;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
