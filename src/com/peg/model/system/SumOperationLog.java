package com.peg.model.system;

import java.util.Date;

public class SumOperationLog {
    private Long id;

    private String name;

    private String statisticsTime;

    private Date startTime;

    private Date endTime;

    private String rusult;

    private String operationType;

    private String sumType;

    private String remak;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(String statisticsTime) {
        this.statisticsTime = statisticsTime == null ? null : statisticsTime.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRusult() {
        return rusult;
    }

    public void setRusult(String rusult) {
        this.rusult = rusult == null ? null : rusult.trim();
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public String getSumType() {
        return sumType;
    }

    public void setSumType(String sumType) {
        this.sumType = sumType == null ? null : sumType.trim();
    }

    public String getRemak() {
        return remak;
    }

    public void setRemak(String remak) {
        this.remak = remak == null ? null : remak.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public SumOperationLog(String name, String statisticsTime,
			Date startTime, Date endTime, String rusult, String operationType,
			String sumType, String remak) {
		super();
		this.name = name;
		this.statisticsTime = statisticsTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rusult = rusult;
		this.operationType = operationType;
		this.sumType = sumType;
		this.remak = remak;
	}
    
	public SumOperationLog() {
		super();
	}
    
}