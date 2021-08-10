package com.peg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstallTotal {
	private Long id;

	private Long installCount;

	private Long repairCount;

	private Date createTime;

	private String productType;

	private String installMonth;
	
	private String serialNumber;
	private String serviceOrder;
	private String partNumber;
	private String partFamily;
	
	private String repairMonth;
	private String partType;
	private String region;
	private String faultTypeCode;
	private String faultReasonCode;
	private String isOver;
	private String valid;

	private String col1;

	private String col2;

	private String col3;

	private String col4;

	private List<InstallTotal> insList = new ArrayList<InstallTotal>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInstallCount() {
		return installCount;
	}

	public void setInstallCount(Long installCount) {
		this.installCount = installCount;
	}

	public Long getRepairCount() {
		return repairCount;
	}

	public void setRepairCount(Long repairCount) {
		this.repairCount = repairCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getInstallMonth() {
		return installMonth;
	}

	public void setInstallMonth(String installMonth) {
		this.installMonth = installMonth;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getServiceOrder() {
		return serviceOrder;
	}
	
	public void setServiceOrder(String serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartFamily() {
		return partFamily;
	}

	public void setPartFamily(String partFamily) {
		this.partFamily = partFamily;
	}
	
	public String getRepairMonth() {
		return repairMonth;
	}

	public void setRepairMonth(String repairMonth) {
		this.repairMonth = repairMonth;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public List<InstallTotal> getInsList() {
		return insList;
	}

	public void setInsList(List<InstallTotal> insList) {
		this.insList = insList;
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

	public String getIsOver() {
		return isOver;
	}

	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}