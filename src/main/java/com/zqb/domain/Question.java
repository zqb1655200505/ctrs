package com.zqb.domain;

import java.util.Date;

/**
 * Created by zqb on 2016/12/10.
 */
public class Question {

    int questionId;

    int userId;

    String theme;

    String content;

    Date publishTime;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", userId=" + userId +
                ", theme='" + theme + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }
}
