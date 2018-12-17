package com.jqsoft.fingertip_health.bean.fingertip;

import java.util.List;

/**
 * Created by Administrator on 2018-09-07.
 */

public class OutpatientWrapperBeanForFingertip {
    private List<OutpatientBeanForFingertip> persons;//	人员信息列表
    private String outpCompensateCost;//	家庭累计补偿

    public OutpatientWrapperBeanForFingertip() {
        super();
    }

    public OutpatientWrapperBeanForFingertip(List<OutpatientBeanForFingertip> persons, String outpCompensateCost) {
        this.persons = persons;
        this.outpCompensateCost = outpCompensateCost;
    }

    public List<OutpatientBeanForFingertip> getPersons() {
        return persons;
    }

    public void setPersons(List<OutpatientBeanForFingertip> persons) {
        this.persons = persons;
    }

    public String getOutpCompensateCost() {
        return outpCompensateCost;
    }

    public void setOutpCompensateCost(String outpCompensateCost) {
        this.outpCompensateCost = outpCompensateCost;
    }
}
