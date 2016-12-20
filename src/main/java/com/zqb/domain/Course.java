package com.zqb.domain;

/**
 * Created by zqb on 2016/12/10.
 */
public class Course
{
    int courseId;

    int userId;


    String courseName;

    String remark;

    String userName;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", userId=" + userId +
                ", courseName='" + courseName + '\'' +
                ", remark='" + remark + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
