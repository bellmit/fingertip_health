package com.jqsoft.fingertip_health.bean.response_new;

/**
 * Created by Administrator on 2017-06-29.
 */

public class IntelligentHonourAgreementOverviewResultBean {
    private String execitem;  //
    private String timeout;  //
    private String serviceEvaluation;  //
    private String applySignDoctor;  //
    private String appointmentService;  //

    public IntelligentHonourAgreementOverviewResultBean() {
        super();
    }

    public IntelligentHonourAgreementOverviewResultBean(String execitem, String timeout, String serviceEvaluation, String applySignDoctor, String appointmentService) {
        this.execitem = execitem;
        this.timeout = timeout;
        this.serviceEvaluation = serviceEvaluation;
        this.applySignDoctor = applySignDoctor;
        this.appointmentService = appointmentService;
    }

    public String getExecitem() {
        return execitem;
    }

    public void setExecitem(String execitem) {
        this.execitem = execitem;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getServiceEvaluation() {
        return serviceEvaluation;
    }

    public void setServiceEvaluation(String serviceEvaluation) {
        this.serviceEvaluation = serviceEvaluation;
    }

    public String getApplySignDoctor() {
        return applySignDoctor;
    }

    public void setApplySignDoctor(String applySignDoctor) {
        this.applySignDoctor = applySignDoctor;
    }

    public String getAppointmentService() {
        return appointmentService;
    }

    public void setAppointmentService(String appointmentService) {
        this.appointmentService = appointmentService;
    }
}
