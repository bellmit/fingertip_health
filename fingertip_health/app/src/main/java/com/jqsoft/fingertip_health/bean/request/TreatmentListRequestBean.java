package com.jqsoft.fingertip_health.bean.request;

import com.jqsoft.fingertip_health.bean.base.HttpListRequestBaseBean;

/**
 * Created by Administrator on 2017-06-02.
 */

public class TreatmentListRequestBean extends HttpListRequestBaseBean {

    public TreatmentListRequestBean() {
    }

    public TreatmentListRequestBean(String keyword, String area, int page, int size) {
        super(keyword, area, page, size);
    }

    public TreatmentListRequestBean(String v, String timestamp, String token, String appkey, String sig, String keyword, String area, int page, int size) {
        super(v, timestamp, token, appkey, sig, keyword, area, page, size);
    }
}
