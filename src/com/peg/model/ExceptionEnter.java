package com.peg.model;

import java.util.Date;

public class ExceptionEnter extends baseCommonVo{
    private Long id;

    private String factory;

    private String area;

    private String groupName;

    private String exceptionType;

    private String exceptionName;

    private Date occurTime;

    private Date createTime;

    private String createUser;

    private Date lastUpdateTime;

    private String lastUpdateUser;
    
    private String startTime;
    
    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType == null ? null : exceptionType.trim();
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName == null ? null : exceptionName.trim();
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
    
}