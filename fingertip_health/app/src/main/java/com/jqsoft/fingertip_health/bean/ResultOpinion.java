package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/8.
 */

public class ResultOpinion {
    private String displayName;
    private String finishTime;
    private String operator;
    private String variable;
    private String taskName;



    public ResultOpinion() {
    }

    public ResultOpinion(String displayName, String finishTime, String operator, String variable,String taskName) {
        this.displayName = displayName;
        this.finishTime = finishTime;
        this.operator = operator;
        this.variable = variable;
        this.taskName = taskName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
