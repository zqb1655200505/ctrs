package com.zqb.domain;

import java.util.Date;

/**
 * Created by zqb on 2016/12/10.
 */
public class Homework {

    int homeworkId;

    int userId;

    int courseId;

    int state;

    Date commitTime;

    String savePath;

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "homeworkId=" + homeworkId +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", state=" + state +
                ", commitTime=" + commitTime +
                ", savePath='" + savePath + '\'' +
                '}';
    }
}
