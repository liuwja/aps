package com.peg.model.smt;

import java.util.Date;

/**
 * SMT日志
 * @createTime 2019-06-19 16:03
 */
public class SmtLog {
    private String type;    // 类型
    private String objectName;  // 对象
    private String failureReason;   // 失败原因
    private Date createTime;  // 创建时间
    private String createStartTime; // 创建开始时间
    private String createEndTime;   // 创建结束时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public SmtLog() {
    }

    public SmtLog(String type, String objectName, String failureReason) {
        this.type = type;
        this.objectName = objectName;
        this.failureReason = failureReason;
    }
}
