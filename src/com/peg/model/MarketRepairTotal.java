package com.peg.model;

import java.util.Date;

public class MarketRepairTotal {
	private Long id;

	private String productType;

	private String downlineMonth;

	private String repairedMonth;
	
	private String repairedMonthStart;

	private String partType;

	private String region;

	private String productlineNumber;

	private String faultTypeCode;

	private String faultReasonCode;

	private Long repairedCount;

	private Date createTime;

	private String isOver;
	
	private String productFamily;//产品系列

	private String startTime;
	private String endTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getRepairedMonthStart() {
		return repairedMonthStart;
	}

	public void setRepairedMonthStart(String repairedMonthStart) {
		this.repairedMonthStart = repairedMonthStart;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getDownlineMonth() {
		return downlineMonth;
	}

	public void setDownlineMonth(String downlineMonth) {
		this.downlineMonth = downlineMonth;
	}

	public String getRepairedMonth() {
		return repairedMonth;
	}

	public void setRepairedMonth(String repairedMonth) {
		this.repairedMonth = repairedMonth;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProductlineNumber() {
		return productlineNumber;
	}

	public void setProductlineNumber(String productlineNumber) {
		this.productlineNumber = productlineNumber;
	}

	public String getFaultTypeCode() {
		return faultTypeCode;
	}

	public void setFaultTypeCode(String faultTypeCode) {
		this.faultTypeCode = faultTypeCode;
	}

	public String getFaultReasonCode() {
		return faultReasonCode;
	}

	public void setFaultReasonCode(String faultReasonCode) {
		this.faultReasonCode = faultReasonCode;
	}

	public Long getRepairedCount() {
		return repairedCount;
	}

	public void setRepairedCount(Long repairedCount) {
		this.repairedCount = repairedCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsOver() {
		return isOver;
	}

	public void setIsOver(String isOver) {
		this.isOver = isOver;
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

	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	

}