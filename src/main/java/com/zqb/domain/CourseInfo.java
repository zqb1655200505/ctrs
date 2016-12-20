package com.zqb.domain;

/**
 * Created by zqb on 2016/12/20.
 */
public class CourseInfo {
    int courseId;

    String CourseName;

    String userName;

    int userId;

    String remark;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseId=" + courseId +
                ", CourseName='" + CourseName + '\'' +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
