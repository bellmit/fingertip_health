package com.jqsoft.fingertip_health.bean;

/**
 * Created by Mars on 2018/9/11.
 */

public class DmPostBean {
    private   String doctor;
    private   String idNo;
    private String taskId;
  private String id;
  private String no;
  private String flwDate;
  private String flwType;
  private String followDownReason;
  private String symptom;
  private String otherSymptom;
  private String sbp;
  private String dbp;
  private String heartRate;
  private String height;
  private String weight;
  private String weightTarget;
  private String bmi;
  private String bmiTarget;
  private String otherSign;
  private String dorsalisPedisL;
  private String dorsalisPedisR;
  private String dailySmoke;
  private String dailySmokeTarget;
  private String dailyDrink;
  private String dailyDrinkTarget;
  private String exercise;
  private String exerciseTimes;
  private String nextExercise;
  private String nextExerciseTimes;
  private String staple;
  private String stapleAim;
  private String psychic;
  private String patientCompliance;
  private String fpg;
  private String pbg;
  private String accessoryExaminationHba1c;
  private String accessoryExaminationDate;
  private String drugCompliance;
  private String adverseReaction;
  private String lowSugarReaction;
  private String sort;
  private String insulinType;
  private String nextFlwDate;
  private String doctorCode;
  private String name;
  private String frequency;
  private String singleDose;
  private String unit;
    private String  isReferral;
    private String   referralReason;
    private String  referralHosp;
    private String otherAdverseReaction;

    public DmPostBean() {
    }

    public DmPostBean(String taskId, String id, String no, String flwDate, String flwType, String followDownReason, String symptom, String otherSymptom, String sbp, String dbp, String heartRate, String height, String weight, String weightTarget, String bmi, String bmiTarget, String otherSign, String dorsalisPedisL, String dorsalisPedisR, String dailySmoke, String dailySmokeTarget, String dailyDrink, String dailyDrinkTarget, String exercise, String exerciseTimes, String nextExercise, String nextExerciseTimes, String staple, String stapleAim, String psychic, String patientCompliance, String fpg, String pbg, String accessoryExaminationHba1c, String accessoryExaminationDate, String drugCompliance, String adverseReaction, String lowSugarReaction, String sort, String insulinType, String nextFlwDate, String doctorCode, String name, String frequency, String singleDose, String unit, String isReferral, String referralReason, String referralHosp, String otherAdverseReaction) {
        this.taskId = taskId;
        this.id = id;
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
        this.dorsalisPedisL = dorsalisPedisL;
        this.dorsalisPedisR = dorsalisPedisR;
        this.dailySmoke = dailySmoke;
        this.dailySmokeTarget = dailySmokeTarget;
        this.dailyDrink = dailyDrink;
        this.dailyDrinkTarget = dailyDrinkTarget;
        this.exercise = exercise;
        this.exerciseTimes = exerciseTimes;
        this.nextExercise = nextExercise;
        this.nextExerciseTimes = nextExerciseTimes;
        this.staple = staple;
        this.stapleAim = stapleAim;
        this.psychic = psychic;
        this.patientCompliance = patientCompliance;
        this.fpg = fpg;
        this.pbg = pbg;
        this.accessoryExaminationHba1c = accessoryExaminationHba1c;
        this.accessoryExaminationDate = accessoryExaminationDate;
        this.drugCompliance = drugCompliance;
        this.adverseReaction = adverseReaction;
        this.lowSugarReaction = lowSugarReaction;
        this.sort = sort;
        this.insulinType = insulinType;
        this.nextFlwDate = nextFlwDate;
        this.doctorCode = doctorCode;
        this.name = name;
        this.frequency = frequency;
        this.singleDose = singleDose;
        this.unit = unit;
        this.isReferral = isReferral;
        this.referralReason = referralReason;
        this.referralHosp = referralHosp;
        this.otherAdverseReaction = otherAdverseReaction;
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

    public String getDorsalisPedisL() {
        return dorsalisPedisL;
    }

    public void setDorsalisPedisL(String dorsalisPedisL) {
        this.dorsalisPedisL = dorsalisPedisL;
    }

    public String getDorsalisPedisR() {
        return dorsalisPedisR;
    }

    public void setDorsalisPedisR(String dorsalisPedisR) {
        this.dorsalisPedisR = dorsalisPedisR;
    }

    public String getDailySmoke() {
        return dailySmoke;
    }

    public void setDailySmoke(String dailySmoke) {
        this.dailySmoke = dailySmoke;
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

    public String getStaple() {
        return staple;
    }

    public void setStaple(String staple) {
        this.staple = staple;
    }

    public String getStapleAim() {
        return stapleAim;
    }

    public void setStapleAim(String stapleAim) {
        this.stapleAim = stapleAim;
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

    public String getFpg() {
        return fpg;
    }

    public void setFpg(String fpg) {
        this.fpg = fpg;
    }

    public String getPbg() {
        return pbg;
    }

    public void setPbg(String pbg) {
        this.pbg = pbg;
    }

    public String getAccessoryExaminationHba1c() {
        return accessoryExaminationHba1c;
    }

    public void setAccessoryExaminationHba1c(String accessoryExaminationHba1c) {
        this.accessoryExaminationHba1c = accessoryExaminationHba1c;
    }

    public String getAccessoryExaminationDate() {
        return accessoryExaminationDate;
    }

    public void setAccessoryExaminationDate(String accessoryExaminationDate) {
        this.accessoryExaminationDate = accessoryExaminationDate;
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

    public String getLowSugarReaction() {
        return lowSugarReaction;
    }

    public void setLowSugarReaction(String lowSugarReaction) {
        this.lowSugarReaction = lowSugarReaction;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getInsulinType() {
        return insulinType;
    }

    public void setInsulinType(String insulinType) {
        this.insulinType = insulinType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSingleDose() {
        return singleDose;
    }

    public void setSingleDose(String singleDose) {
        this.singleDose = singleDose;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getOtherAdverseReaction() {
        return otherAdverseReaction;
    }

    public void setOtherAdverseReaction(String otherAdverseReaction) {
        this.otherAdverseReaction = otherAdverseReaction;
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
