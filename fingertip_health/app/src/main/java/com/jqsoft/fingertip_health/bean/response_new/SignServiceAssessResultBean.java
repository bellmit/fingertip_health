package com.jqsoft.fingertip_health.bean.response_new;

/**
 * Created by Administrator on 2017-07-05.
 */
//签约服务评价
public class SignServiceAssessResultBean {
    private String servicePlanId;//项目执行情况表主键
    private String name;//居民姓名
    private String age;//年龄
    private String fwmc;//服务包名称
    private String xmmc;//服务项目名称
    private String addDT;//录入时间
    private String evaluation;//评价星级
    private String evaluationTime;//评价时间
    private String evaluationNote;//评价内容
    private String count;//总数
    private String shouldExecCount;//应该执行次数
    private String hadExecCount;//已经执行次数
    private String evaluationState;//评价状态
    private String serviceClassName;//                  服务包分类
    private String photoUrl;//                         图片Url

    public SignServiceAssessResultBean() {
        super();
    }

    public SignServiceAssessResultBean(String servicePlanId, String name, String age, String fwmc, String xmmc, String addDT, String evaluation, String evaluationTime, String evaluationNote, String count, String shouldExecCount, String hadExecCount, String evaluationState, String serviceClassName, String photoUrl) {
        this.servicePlanId = servicePlanId;
        this.name = name;
        this.age = age;
        this.fwmc = fwmc;
        this.xmmc = xmmc;
        this.addDT = addDT;
        this.evaluation = evaluation;
        this.evaluationTime = evaluationTime;
        this.evaluationNote = evaluationNote;
        this.count = count;
        this.shouldExecCount = shouldExecCount;
        this.hadExecCount = hadExecCount;
        this.evaluationState = evaluationState;
        this.serviceClassName = serviceClassName;
        this.photoUrl = photoUrl;
    }

    public String getServicePlanId() {
        return servicePlanId;
    }

    public void setServicePlanId(String servicePlanId) {
        this.servicePlanId = servicePlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFwmc() {
        return fwmc;
    }

    public void setFwmc(String fwmc) {
        this.fwmc = fwmc;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getAddDT() {
        return addDT;
    }

    public void setAddDT(String addDT) {
        this.addDT = addDT;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(String evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getEvaluationNote() {
        return evaluationNote;
    }

    public void setEvaluationNote(String evaluationNote) {
        this.evaluationNote = evaluationNote;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getShouldExecCount() {
        return shouldExecCount;
    }

    public void setShouldExecCount(String shouldExecCount) {
        this.shouldExecCount = shouldExecCount;
    }

    public String getHadExecCount() {
        return hadExecCount;
    }

    public void setHadExecCount(String hadExecCount) {
        this.hadExecCount = hadExecCount;
    }

    public String getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(String evaluationState) {
        this.evaluationState = evaluationState;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
