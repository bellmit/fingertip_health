package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 *
 * 智能引导中回答bean
 */

public class AnswerListBean implements Serializable {

    private String answerId;
    private String answer;
    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }





    public AnswerListBean() {
        super();
    }
    public AnswerListBean(String answerId, String answer,String questionType) {
        this.answerId = answerId;
        this.answer=  answer;


    }




}
