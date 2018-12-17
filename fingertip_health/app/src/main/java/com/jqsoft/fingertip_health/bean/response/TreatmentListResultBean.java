package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-02.
 */

public class TreatmentListResultBean {
    public static final String TREATMENT_LOCK="1";
    public static final String TREATMENT_UNLOCK="0";

    private String id;
    private String code;
    private String name;
    private String lockStatus;//锁定状态  1锁定   0未锁定

    public TreatmentListResultBean() {
    }

    public TreatmentListResultBean(String id, String code, String name, String lockStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.lockStatus=lockStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }
}
