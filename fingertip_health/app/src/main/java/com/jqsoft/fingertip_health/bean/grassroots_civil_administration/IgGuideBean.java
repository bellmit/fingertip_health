package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 智能引导bean
 */

public class IgGuideBean implements Serializable {
    private List<AnswerListBean> answerList;
    private String questionId;
    private String answerType;
    private String question;
    private String questionType;

    public IgGuideBean() {
        super();
    }
    public IgGuideBean(String question, String answerType,String questionType) {
        this.question = question;
        this.answerType=  answerType;
        this.questionType = questionType;

    }


    public List<AnswerListBean> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerListBean> answerList) {
        this.answerList = answerList;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }








}
