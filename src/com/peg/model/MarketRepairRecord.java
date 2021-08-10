package com.peg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MarketRepairRecord implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String serviceCenter; //加工后合并服务中心
    
    private String rawServiceCenter;//原始服务中心从crm获取

    private String orderNumber; //工单编号（维修）

    private String partCode; // 主机条码

    private String partName; //物料名称

    private String partNumber; //物料编号

    private Date invoiceDate;  //开票日期

    private Date finishDate;  //实际完成时间

    private String serviceSite; //服务网点

    private String serviceEngineer; //服务工程师

    private String settlementDesc;  //结算说明

    private String homePhone; //家庭电话

    private String cellphone; //手机

    private Date recordTime; //录入时间（crm创建时间）
    
    private String recordTimeStart;	//录入时间开始（查询条件）
    
    private String recordTimeEnd;		//录入时间结束（查询条件）

    private String buyType; //购买方式

    private String faultTypeCode; //故障大类编号

    private String faultTypeName; //故障大类名称

    private String faultReasonCode; //故障小类编号

    private String faultReasonName; //故障小类名称

    private String faultDesc; //故障描述现象

    private String faultCurrentDesc; //故障现场现象

    private String infoDesc; //详细信息

    private Date createTime;
    
    private String insStartTime; //安装开始时间
    
    private String insEndTime; //安装结束时间
    
    private String createStartTime; //生产开始时间
    
    private String createEndTime; //生产结束时间
    
	private String startTime;  //开始时间用于查询
    
    private String endTime;  //结束时间用于查询
    
    private String productType; // 产品型号
    
    private String productFamily; // 产品系列
    
    private String partType; // 机型类别
    
    private String isOver; //是否百台
    
    private String faultReasonValid;//故障小类是否有效
    
    private String isSettlement;//是否结算
    
    private Date intallDate;  //安装日期
    
    private String downlineDate;  //下线日期
    
    private Integer codeRepeat;//条码是否重复
    
    private String meshFaultReasonName;//合并故障小类
    
    private String gas;//气源
    
    private String isConsumed; //是否消耗配件
    
    
    //多项
    private List<String> faultReasonCodeList;
    public String getIsConsumed() {
		return isConsumed;
	}
	public void setIsConsumed(String isConsumed) {
		this.isConsumed = isConsumed;
	}
	private List<String> faultTypeCodeList;
    private List<String> regionList;
    private List<String> mergeRegionList;
    private List<String> partTypeList;
    private List<String> productFamilyList;
    private List<String> meshfaultReasonCodeList;
    private List<String> vocTypeList;//客户之声分类
   
    
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public List<String> getVocTypeList() {
		return vocTypeList;
	}
	public void setVocTypeList(List<String> vocTypeList) {
		this.vocTypeList = vocTypeList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getServiceSite() {
		return serviceSite;
	}
	public void setServiceSite(String serviceSite) {
		this.serviceSite = serviceSite;
	}
	public String getServiceEngineer() {
		return serviceEngineer;
	}
	public void setServiceEngineer(String serviceEngineer) {
		this.serviceEngineer = serviceEngineer;
	}
	public String getSettlementDesc() {
		return settlementDesc;
	}
	public void setSettlementDesc(String settlementDesc) {
		this.settlementDesc = settlementDesc;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getRecordTimeStart() {
		return recordTimeStart;
	}
	public void setRecordTimeStart(String recordTimeStart) {
		this.recordTimeStart = recordTimeStart;
	}
	public String getRecordTimeEnd() {
		return recordTimeEnd;
	}
	public void setRecordTimeEnd(String recordTimeEnd) {
		this.recordTimeEnd = recordTimeEnd;
	}
	public String getBuyType() {
		return buyType;
	}
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
	public String getFaultTypeCode() {
		return faultTypeCode;
	}
	public void setFaultTypeCode(String faultTypeCode) {
		this.faultTypeCode = faultTypeCode;
	}
	public String getFaultTypeName() {
		return faultTypeName;
	}
	public void setFaultTypeName(String faultTypeName) {
		this.faultTypeName = faultTypeName;
	}
	public String getFaultReasonCode() {
		return faultReasonCode;
	}
	public void setFaultReasonCode(String faultReasonCode) {
		this.faultReasonCode = faultReasonCode;
	}
	public String getFaultReasonName() {
		return faultReasonName;
	}
	public void setFaultReasonName(String faultReasonName) {
		this.faultReasonName = faultReasonName;
	}
	public String getFaultDesc() {
		return faultDesc;
	}
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}
	public String getFaultCurrentDesc() {
		return faultCurrentDesc;
	}
	public void setFaultCurrentDesc(String faultCurrentDesc) {
		this.faultCurrentDesc = faultCurrentDesc;
	}
	public String getInfoDesc() {
		return infoDesc;
	}
	public void setInfoDesc(String infoDesc) {
		this.infoDesc = infoDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getInsStartTime() {
		return insStartTime;
	}
	public void setInsStartTime(String insStartTime) {
		this.insStartTime = insStartTime;
	}
	public String getInsEndTime() {
		return insEndTime;
	}
	public void setInsEndTime(String insEndTime) {
		this.insEndTime = insEndTime;
	}
	public String getCreateStartTime() {
		return createStartTime;
	}
	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}
	public String getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getIsOver() {
		return isOver;
	}
	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}
	public String getFaultReasonValid() {
		return faultReasonValid;
	}
	public void setFaultReasonValid(String faultReasonValid) {
		this.faultReasonValid = faultReasonValid;
	}
	public String getIsSettlement() {
		return isSettlement;
	}
	public void setIsSettlement(String isSettlement) {
		this.isSettlement = isSettlement;
	}
	public Date getIntallDate() {
		return intallDate;
	}
	public void setIntallDate(Date intallDate) {
		this.intallDate = intallDate;
	}
	public String getDownlineDate() {
		return downlineDate;
	}
	public void setDownlineDate(String downlineDate) {
		this.downlineDate = downlineDate;
	}
	public List<String> getFaultReasonCodeList() {
		return faultReasonCodeList;
	}
	public void setFaultReasonCodeList(List<String> faultReasonCodeList) {
		this.faultReasonCodeList = faultReasonCodeList;
	}
	public List<String> getFaultTypeCodeList() {
		return faultTypeCodeList;
	}
	public void setFaultTypeCodeList(List<String> faultTypeCodeList) {
		this.faultTypeCodeList = faultTypeCodeList;
	}
	public List<String> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<String> regionList) {
		this.regionList = regionList;
	}
	public List<String> getPartTypeList() {
		return partTypeList;
	}
	public void setPartTypeList(List<String> partTypeList) {
		this.partTypeList = partTypeList;
	}
	
	public List<String> getMeshfaultReasonCodeList() {
		return meshfaultReasonCodeList;
	}
	public void setMeshfaultReasonCodeList(List<String> meshfaultReasonCodeList) {
		this.meshfaultReasonCodeList = meshfaultReasonCodeList;
	}
	public Integer getCodeRepeat() {
		return codeRepeat;
	}
	public void setCodeRepeat(Integer codeRepeat) {
		this.codeRepeat = codeRepeat;
	}
	public String getMeshFaultReasonName() {
		return meshFaultReasonName;
	}
	public void setMeshFaultReasonName(String meshFaultReasonName) {
		this.meshFaultReasonName = meshFaultReasonName;
	}
	public String getRawServiceCenter() {
		return rawServiceCenter;
	}
	public void setRawServiceCenter(String rawServiceCenter) {
		this.rawServiceCenter = rawServiceCenter;
	}
	public List<String> getMergeRegionList() {
		return mergeRegionList;
	}
	public void setMergeRegionList(List<String> mergeRegionList) {
		this.mergeRegionList = mergeRegionList;
	}
	public List<String> getProductFamilyList() {
		return productFamilyList;
	}
	public void setProductFamilyList(List<String> productFamilyList) {
		this.productFamilyList = productFamilyList;
	}
}