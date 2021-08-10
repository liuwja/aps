package com.peg.model;

import java.util.Date;

public class TimeMatrx {
    private Long id;

    private String machineType;

    private String productionMonth;

    private String lnvalidFlags;

    private Date createTime;

    private String createUser;

    private String statisType;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType == null ? null : machineType.trim();
    }

    public String getProductionMonth() {
        return productionMonth;
    }

    public void setProductionMonth(String productionMonth) {
        this.productionMonth = productionMonth;
    }

    public String getLnvalidFlags() {
        return lnvalidFlags;
    }

    public void setLnvalidFlags(String lnvalidFlags) {
        this.lnvalidFlags = lnvalidFlags == null ? null : lnvalidFlags.trim();
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
    
    public String getStatisType() {
		return statisType;
	}

	public void setStatisType(String statisType) {
		this.statisType = statisType;
	}
}