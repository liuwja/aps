package com.peg.model;

public class BoxdefectrecordNew {
	
	private Long atrKey	;//number(19,0)	yes		1	
	private String backNum	;//varchar2(80 byte)	no		2	反馈单号
	private String billType	;//varchar2(40 byte)	yes		3	单据类别
	private String region	;//varchar2(80 byte)	yes		4	服务大区
	private String regionCore	;//varchar2(40 byte)	yes		5	服务中心
	private String userName	;//varchar2(40 byte)	yes		6	用户姓名
	private String userPhone	;//varchar2(80 byte)	yes		7	用户电话
	private String backType	;//varchar2(60 byte)	yes		8	反馈类型
	private String faultType	;//varchar2(60 byte)	yes		9	故障大类
	private String faultReason	;//varchar2(60 byte)	yes		10	故障小类
	private String billTheme	;//varchar2(100 byte)	yes		11	报告主题
	private String productType	;//varchar2(60 byte)	no		12	产品类型
	private String partType	;//varchar2(60 byte)	no		13	产品型号
	private String productNum	;//varchar2(60 byte)	yes		14	产品编码
	private String useTime	;//number(10,0)	yes		15	使用期限
	private String findPlace	;//varchar2(80 byte)	yes		16	发现场所
	private String describe	;//varchar2(100 byte)	yes		17	问题描述
	private String placeDescribe	;//varchar2(100 byte)	yes		18	操作场景描述
	private String disposalSituation	;//varchar2(100 byte)	yes		19	处置情况
	private String causeAnalysis	;//varchar2(100 byte)	yes		20	初步原因分析
	private String suggest	;//varchar2(100 byte)	yes		21	改善建议
	private String mechanicName	;//varchar2(80 byte)	no		22	技师姓名
	private String mechanicPhone	;//varchar2(80 byte)	no		23	技师电话
	private String point	;//varchar2(80 byte)	no		24	网点
	private String state	;//varchar2(80 byte)	no		25	状态
	private String bactTime	;//date	yes		26	反馈日期
	private String installTime	;//date	yes		27	安装日期
	private String production	;//date	yes		28	生产日期
	private String duty1	;//varchar2(60 byte)	yes		29	责任1
	private String duty2	;//varchar2(60 byte)	yes		30	责任2
	private String duty3	;//varchar2(60 byte)	yes		31	责任3
	private String duty1Name	;//varchar2(60 byte)	yes		32	判定人1
	private String duty2Name	;//varchar2(60 byte)	yes		33	判定人2
	private String engineerConfirm;//总部工程师确认
	//前端选择条件
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String baseFactory;//工厂
	private String baseGroup;//班组
	
	
	public String getEngineerConfirm() {
		return engineerConfirm;
	}
	public void setEngineerConfirm(String engineerConfirm) {
		this.engineerConfirm = engineerConfirm;
	}
	public String getBaseFactory() {
		return baseFactory;
	}
	public void setBaseFactory(String baseFactory) {
		this.baseFactory = baseFactory;
	}
	public String getBaseGroup() {
		return baseGroup;
	}
	public void setBaseGroup(String baseGroup) {
		this.baseGroup = baseGroup;
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
	public Long getAtrKey() {
		return atrKey;
	}
	public void setAtrKey(Long atrKey) {
		this.atrKey = atrKey;
	}
	public String getBackNum() {
		return backNum;
	}
	public void setBackNum(String backNum) {
		this.backNum = backNum;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegionCore() {
		return regionCore;
	}
	public void setRegionCore(String regionCore) {
		this.regionCore = regionCore;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getBackType() {
		return backType;
	}
	public void setBackType(String backType) {
		this.backType = backType;
	}
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public String getFaultReason() {
		return faultReason;
	}
	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	public String getBillTheme() {
		return billTheme;
	}
	public void setBillTheme(String billTheme) {
		this.billTheme = billTheme;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getFindPlace() {
		return findPlace;
	}
	public void setFindPlace(String findPlace) {
		this.findPlace = findPlace;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPlaceDescribe() {
		return placeDescribe;
	}
	public void setPlaceDescribe(String placeDescribe) {
		this.placeDescribe = placeDescribe;
	}
	public String getDisposalSituation() {
		return disposalSituation;
	}
	public void setDisposalSituation(String disposalSituation) {
		this.disposalSituation = disposalSituation;
	}
	public String getCauseAnalysis() {
		return causeAnalysis;
	}
	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getMechanicName() {
		return mechanicName;
	}
	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}
	public String getMechanicPhone() {
		return mechanicPhone;
	}
	public void setMechanicPhone(String mechanicPhone) {
		this.mechanicPhone = mechanicPhone;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBactTime() {
		return bactTime;
	}
	public void setBactTime(String bactTime) {
		this.bactTime = bactTime;
	}
	public String getInstallTime() {
		return installTime;
	}
	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getDuty1() {
		return duty1;
	}
	public void setDuty1(String duty1) {
		this.duty1 = duty1;
	}
	public String getDuty2() {
		return duty2;
	}
	public void setDuty2(String duty2) {
		this.duty2 = duty2;
	}
	public String getDuty3() {
		return duty3;
	}
	public void setDuty3(String duty3) {
		this.duty3 = duty3;
	}
	public String getDuty1Name() {
		return duty1Name;
	}
	public void setDuty1Name(String duty1Name) {
		this.duty1Name = duty1Name;
	}
	public String getDuty2Name() {
		return duty2Name;
	}
	public void setDuty2Name(String duty2Name) {
		this.duty2Name = duty2Name;
	}
	
	

}
