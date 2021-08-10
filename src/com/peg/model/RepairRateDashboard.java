package com.peg.model;

import java.util.Date;

public class RepairRateDashboard {
	
	private Long id;
	
	private String productType;
	
	private String month;
	
    private Double hundredRepairRate = 0d;

    private Double referenctRepairRate = 0d;
    
    private Double targetRepairRate = 0d;
	
    private String createUser;
    
    private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getHundredRepairRate() {
		return hundredRepairRate;
	}

	public void setHundredRepairRate(Double hundredRepairRate) {
		this.hundredRepairRate = hundredRepairRate;
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
