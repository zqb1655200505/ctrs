package com.zqb.domain;

import java.util.Date;

/**
 * Created by zqb on 2016/12/10.
 */
public class Resource {
    int resourceId;

    int resourceType;

    int userId;

    //下载次数
    int downloadTimes;

    Date uploadTime;

    String savePath;

    //备注信息
    String remark;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceType=" + resourceType +
                ", userId=" + userId +
                ", downloadTimes=" + downloadTimes +
                ", uploadTime=" + uploadTime +
                ", savePath='" + savePath + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
