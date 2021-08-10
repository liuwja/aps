package com.peg.model;

import java.util.Date;

public class RepairRate {
    private Long id;

    private String machineType;

    private String yearMon;

    private Double hundredRepairRate = 0d;

    private Double referenctRepairRate = 0d;
    
    private Double targetRepairRate = 0d;
    
    private String userName;

    private Date entryTime;

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


    public String getYearMon()
	{
		return yearMon;
	}

	public void setYearMon(String yearMon)
	{
		this.yearMon = yearMon;
	}

	public Double getHundredRepairRate() {
        return hundredRepairRate;
    }

    public void setHundredRepairRate(Double hundredRepairRate) {
        this.hundredRepairRate = hundredRepairRate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

	public Double getReferenctRepairRate() {
		return referenctRepairRate;
	}

	public void setReferenctRepairRate(Double referenctRepairRate) {
		this.referenctRepairRate = referenctRepairRate;
	}

	public Double getTargetRepairRate() {
		return targetRepairRate;
	}

	public void setTargetRepairRate(Double targetRepairRate) {
		this.targetRepairRate = targetRepairRate;
	}
}