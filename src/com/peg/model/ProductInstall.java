package com.peg.model;
/**
 * 产品安装信息
 * @author Administrator
 *
 */
public class ProductInstall {

	private String partCode; // 主机条码
	private String intallDate;// 安装时间
	private String serivceOrder;// 服务工单
	private String startTime; // 开始时间(查询条件)
	private String decription;// 产品描述
	private String endTime; // 结束时间（查询条件）
	private String productType; // 产品类型
	private String nProductType;
	private String partNumber;
	private String partFamily;
	private String partType;
	private String isOut;
	private String partTypeListTxt;
	private String[] partTypes;
	private String region;
	private String[] regions;
	private String regionListTxt;
	
	public String[] getPartTypes() {
		return partTypes;
	}
	public void setPartTypes(String[] partTypes) {
		this.partTypes = partTypes;
	}
	public String getPartTypeListTxt() {
		return partTypeListTxt;
	}
	public void setPartTypeListTxt(String partTypeListTxt) {
		this.partTypeListTxt = partTypeListTxt;
	}
	public String getnProductType() {
		return nProductType;
	}
	public void setnProductType(String nProductType) {
		this.nProductType = nProductType;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getIntallDate() {
		return intallDate;
	}
	public void setIntallDate(String intallDate) {
		this.intallDate = intallDate;
	}
	public String getSerivceOrder() {
		return serivceOrder;
	}
	public void setSerivceOrder(String serivceOrder) {
		this.serivceOrder = serivceOrder;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
//	public String getNProductType() {
//		return nProductType;
//	}
//	public void setNProductType(String nProductType) {
//		this.nProductType = nProductType;
//	}
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
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getIsOut() {
		return isOut;
	}
	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
}
