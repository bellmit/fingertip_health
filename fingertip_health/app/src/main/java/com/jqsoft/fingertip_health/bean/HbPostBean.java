package com.jqsoft.fingertip_health.bean;//

/**
 * Created by Mars on 2018/9/10.
 */

public class HbPostBean {
    private String  doctor;//	服务任务	;////String
    private String  idNo;//	服务任务	;////String

   private String  taskId;//	服务任务	;////String
    private String  id	;//主键	;////String
    private String  name;//	姓名	;////String
    private String  no	;//档案编号	;////String
    private String  flwDate	;//随访日期(yyyy-MM-dd)	;////String
    private String  flwType;//	随访方式 1 门诊 2 家庭 3 电话	;////Integer
    private String  followDownReason;//	失访原因 1外出打工 2迁居他处 3走失 4连续3次未到访 4其他	;////String
    private String  symptom	;//症状（1 无症状2 头痛头晕 3 恶心呕吐 4 眼花耳鸣 5 呼吸困难 6 心悸胸闷 7 鼻衄出血不止 8 四肢发麻 9 下肢水肿 10 其他）	;////String
    private String  otherSymptom;//	其他症状	;////String
    private String  sbp	;//收缩压（mmHg）	;////Integer
    private String  dbp	;//舒张压（mmHg）	;////Integer
    private String  heartRate;//	心率（次/分钟	;////Integer
    private String  height	;//身高	;////Double
    private String   weight;//	体重	;////Double
    private String   weightTarget	;//目标体重	;////Double
    private String   bmi	;//体质指数	;////Double
    private String   bmiTarget	;//目标体质指数	;////Double
    private String    otherSign	;//其他体征	;////String
    private String   dailySmoke;//	日吸烟量（支）	;////Integer
    private String   psychic	;//心理调整（1 良好 2 一般 3 差）	;////Integer
    private String    patientCompliance;//	遵医行为（1 良好 2 一般 3 差）	;////Integer
    private String    accessoryExamination	;//辅助检查	;////String
    private String    drugCompliance;//	服药依从性（1 规律 2 间断 3 不服药）	;////Integer
    private String    adverseReaction;//	药物不良反应（1无 2有	;////Integer
    private String    otherAdverseReaction;//	药物不良反应	;////String
    private String   sort	;//次随访分类（1 控制满意 2 控制不满意3 不良反 4并发症 ）	;////Integer
    private String   nextFlwDate;//	下次随访日期	;////String
    private String   doctorCode;//	随访医生签名编码	;////String
    private String  exercise;
    private String     exerciseTimes;
    private String  nextExercise;
    private String     nextExerciseTimes;
    private String isReferral;
    private String    referralReason;
    private String referralHosp;
    private String dailySmokeTarget;
    private String dailyDrink;
    private String dailyDrinkTarget;
    private  String saltUptake;
    private  String saltUptakeTarget;

    public HbPostBean(String taskId, String id, String name, String no, String flwDate, String flwType, String followDownReason, String symptom, String otherSymptom, String sbp, String dbp, String heartRate, String height, String weight, String weightTarget, String bmi, String bmiTarget, String otherSign, String dailySmoke, String psychic, String patientCompliance, String accessoryExamination, String drugCompliance, String adverseReaction, String otherAdverseReaction, String sort, String nextFlwDate, String doctorCode, String exercise, String exerciseTimes, String nextExercise, String nextExerciseTimes, String isReferral, String referralReason, String referralHosp, String dailySmokeTarget, String dailyDrink, String dailyDrinkTarget, String saltUptake, String saltUptakeTarget) {
        this.taskId = taskId;
        this.id = id;
        this.name = name;
        this.no = no;
        this.flwDate = flwDate;
        this.flwType = flwType;
        this.followDownReason = followDownReason;
        this.symptom = symptom;
        this.otherSymptom = otherSymptom;
        this.sbp = sbp;
        this.dbp = dbp;
        this.heartRate = heartRate;
        this.height = height;
        this.weight = weight;
        this.weightTarget = weightTarget;
        this.bmi = bmi;
        this.bmiTarget = bmiTarget;
        this.otherSign = otherSign;
        this.dailySmoke = dailySmoke;
        this.psychic = psychic;
        this.patientCompliance = patientCompliance;
        this.accessoryExamination = accessoryExamination;
        this.drugCompliance = drugCompliance;
        this.adverseReaction = adverseReaction;
        this.otherAdverseReaction = otherAdverseReaction;
        this.sort = sort;
        this.nextFlwDate = nextFlwDate;
        this.doctorCode = doctorCode;
        this.exercise = exercise;
        this.exerciseTimes = exerciseTimes;
        this.nextExercise = nextExercise;
        this.nextExerciseTimes = nextExerciseTimes;
        this.isReferral = isReferral;
        this.referralReason = referralReason;
        this.referralHosp = referralHosp;
        this.dailySmokeTarget = dailySmokeTarget;
        this.dailyDrink = dailyDrink;
        this.dailyDrinkTarget = dailyDrinkTarget;
        this.saltUptake = saltUptake;
        this.saltUptakeTarget = saltUptakeTarget;
    }

    public HbPostBean() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFlwDate() {
        return flwDate;
    }

    public void setFlwDate(String flwDate) {
        this.flwDate = flwDate;
    }

    public String getFlwType() {
        return flwType;
    }

    public void setFlwType(String flwType) {
        this.flwType = flwType;
    }

    public String getFollowDownReason() {
        return followDownReason;
    }

    public void setFollowDownReason(String followDownReason) {
        this.followDownReason = followDownReason;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getOtherSymptom() {
        return otherSymptom;
    }

    public void setOtherSymptom(String otherSymptom) {
        this.otherSymptom = otherSymptom;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightTarget() {
        return weightTarget;
    }

    public void setWeightTarget(String weightTarget) {
        this.weightTarget = weightTarget;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBmiTarget() {
        return bmiTarget;
    }

    public void setBmiTarget(String bmiTarget) {
        this.bmiTarget = bmiTarget;
    }

    public String getOtherSign() {
        return otherSign;
    }

    public void setOtherSign(String otherSign) {
        this.otherSign = otherSign;
    }

    public String getDailySmoke() {
        return dailySmoke;
    }

    public void setDailySmoke(String dailySmoke) {
        this.dailySmoke = dailySmoke;
    }

    public String getPsychic() {
        return psychic;
    }

    public void setPsychic(String psychic) {
        this.psychic = psychic;
    }

    public String getPatientCompliance() {
        return patientCompliance;
    }

    public void setPatientCompliance(String patientCompliance) {
        this.patientCompliance = patientCompliance;
    }

    public String getAccessoryExamination() {
        return accessoryExamination;
    }

    public void setAccessoryExamination(String accessoryExamination) {
        this.accessoryExamination = accessoryExamination;
    }

    public String getDrugCompliance() {
        return drugCompliance;
    }

    public void setDrugCompliance(String drugCompliance) {
        this.drugCompliance = drugCompliance;
    }

    public String getAdverseReaction() {
        return adverseReaction;
    }

    public void setAdverseReaction(String adverseReaction) {
        this.adverseReaction = adverseReaction;
    }

    public String getOtherAdverseReaction() {
        return otherAdverseReaction;
    }

    public void setOtherAdverseReaction(String otherAdverseReaction) {
        this.otherAdverseReaction = otherAdverseReaction;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNextFlwDate() {
        return nextFlwDate;
    }

    public void setNextFlwDate(String nextFlwDate) {
        this.nextFlwDate = nextFlwDate;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getExerciseTimes() {
        return exerciseTimes;
    }

    public void setExerciseTimes(String exerciseTimes) {
        this.exerciseTimes = exerciseTimes;
    }

    public String getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(String nextExercise) {
        this.nextExercise = nextExercise;
    }

    public String getNextExerciseTimes() {
        return nextExerciseTimes;
    }

    public void setNextExerciseTimes(String nextExerciseTimes) {
        this.nextExerciseTimes = nextExerciseTimes;
    }

    public String getIsReferral() {
        return isReferral;
    }

    public void setIsReferral(String isReferral) {
        this.isReferral = isReferral;
    }

    public String getReferralReason() {
        return referralReason;
    }

    public void setReferralReason(String referralReason) {
        this.referralReason = referralReason;
    }

    public String getReferralHosp() {
        return referralHosp;
    }

    public void setReferralHosp(String referralHosp) {
        this.referralHosp = referralHosp;
    }

    public String getDailySmokeTarget() {
        return dailySmokeTarget;
    }

    public void setDailySmokeTarget(String dailySmokeTarget) {
        this.dailySmokeTarget = dailySmokeTarget;
    }

    public String getDailyDrink() {
        return dailyDrink;
    }

    public void setDailyDrink(String dailyDrink) {
        this.dailyDrink = dailyDrink;
    }

    public String getDailyDrinkTarget() {
        return dailyDrinkTarget;
    }

    public void setDailyDrinkTarget(String dailyDrinkTarget) {
        this.dailyDrinkTarget = dailyDrinkTarget;
    }

    public String getSaltUptake() {
        return saltUptake;
    }

    public void setSaltUptake(String saltUptake) {
        this.saltUptake = saltUptake;
    }

    public String getSaltUptakeTarget() {
        return saltUptakeTarget;
    }

    public void setSaltUptakeTarget(String saltUptakeTarget) {
        this.saltUptakeTarget = saltUptakeTarget;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
