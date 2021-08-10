package com.peg.model;
/**
 * CRM安装中间表
 * @author Administrator
 *
 */
public class CrmInstall {
	private String serialNumber; //主机条码
	private String installDate; //安装日期
    private String serviceOrder; //安装工单
	private String productType;//机型类别
	private String description;//产品名称
	private String region; //服务中心
	private String creationTime; //创建日期
	private String installStartTime; //安装开始日期 （查询）
	private String installEndTime;//安装结束日期（查询）
	private String startTime;   //创建日期（查询）
	private String endTime;  //创建日期（查询）
	private String[] regions;
	private String regionListTxt;
	private String intallDate;// 安装时间
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public String getServiceOrder() {
		return serviceOrder;
	}
	public void setServiceOrder(String serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getInstallStartTime() {
		return installStartTime;
	}
	public void setInstallStartTime(String installStartTime) {
		this.installStartTime = installStartTime;
	}
	public String getInstallEndTime() {
		return installEndTime;
	}
	public void setInstallEndTime(String installEndTime) {
		this.installEndTime = installEndTime;
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
	public String[] getRegions() {
		return regions;
	}
	public void setRegions(String[] regions) {
		this.regions = regions;
	}
	public String getRegionListTxt() {
		return regionListTxt;
	}
	public void setRegionListTxt(String regionListTxt) {
		this.regionListTxt = regionListTxt;
	}
	public String getIntallDate() {
		return intallDate;
	}
	public void setIntallDate(String intallDate) {
		this.intallDate = intallDate;
	}
	
	
}
