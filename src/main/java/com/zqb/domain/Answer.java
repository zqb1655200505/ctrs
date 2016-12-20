package com.zqb.domain;

import java.util.Date;

/**
 * Created by zqb on 2016/12/10.
 */
public class Answer
{
    int answerId;

    int userId;

    int questionId;

    String content;

    Date answerTime;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", content='" + content + '\'' +
                ", answerTime=" + answerTime +
                '}';
    }
}
