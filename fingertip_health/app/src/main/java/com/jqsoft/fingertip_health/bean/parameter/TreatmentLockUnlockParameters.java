package com.jqsoft.fingertip_health.bean.parameter;

import com.jqsoft.fingertip_health.annotation.HttpParameter;
import com.jqsoft.fingertip_health.bean.parameter.base.CommonParameters;

/**
 * Created by Administrator on 2017-06-02.
 */

public class TreatmentLockUnlockParameters extends CommonParameters {
    @HttpParameter
    private String id;          //诊断ID

    public TreatmentLockUnlockParameters() {
    }

    public TreatmentLockUnlockParameters(String id) {
        this.id = id;
    }

    public TreatmentLockUnlockParameters(String appkey, String timestamp, String token, String sig, String v, String id) {
        super(appkey, timestamp, token, sig, v);
        this.id = id;
    }
}
