package com.peg.model;

import java.util.List;


public class CommonVo extends baseCommonVo{
	private long id;
	//用于图形显示
	private String chartType;
	private int width;
	private int hight;
	
	//仅用于查询条件
	private String startTime;
	private String endTime;
	private String repairStartTime;
	private String repairEndTime;
	private String insStartTime;//安装开始时间
	private String insEndTime;//安装结束时间
	private String dwStartTime;
	private String dwEndTime;
	//BY JiangFeng
	private String dlStartTime;
	private String dlEndTime;
	//
	private String queryMonth;
	private Integer xCount;
	private int typeNum;
	private String factory; //工厂
	private String area;    //车间
	private String statisType;//统计方式：如（月/年/季度）
	private String statisData;//统计数据：维修数/维修率
	private String matrixTableType; //三角阵类型（正/倒）
	
	//基准月份
	private String baseMonth;
	//多选
	private String productFamilyTxt;
	private String[] productFamilys;
	private String voiceCategoryTxt;
	private String[] voiceCategorys;
	private String gasCategoryTxt;
	private String[] gasCategorys;
	private String partTypeListTxt;
	private String[] partTypes;
	private String regionListTxt;
	private String[] regions;
	private String plineListTxt;
	private String[] plines;
	private String faultTypeTxt;
	private String[] faultTypes;
	private String faultReasonTxt;
	private String[] faultReasons;
	private String[] faultTypeCodes;
	private String supplierId;
	private String supplierListTxt; //供应商
	private String[] suppliers;
	private String supplierNumber;
	private String[] supplierNumbers;
	
	private String mergeRegionListTxt;
	private String[] mergeRegions;
	
	private String shiftGroupTxt;//班组
	private String[] shiftGroups; //班组列表
	private String meshFaultName;//合并故障小类名称
	private String[] meshFaultReasons;//合并故障小类名称
	
	//可用于查询、结果返回
	private String title; //标题
	private String region;//区域
	private String province;//省份
	
	private String serialNumber; //主机条码
	private String partNumber; //物料编码
	
	private String productType;//机型类别
	private String productFamily;//产品系列
	private String partType;//型号
	private String voiceCategory;//客户之声二级分类
	
	private String productLineNumber;//产线编号
	private String productLineName;//产线名称
	private String faultTypeCode;//故障大类代码
	private String faultTypeName;//故障大类名称
	private String faultReasonCode;//故障小类代码
	private String meshFaultReasonCode;//合并故障小类代码
	private String faultReasonName;//故障小类名称
	private String faultReasonValid;//故障小类是否有效
	private String repairMonth;//维修月份
	private String shipMonth;//发货月份
	private String downLineMonth;//下线月份
	private String installMonth;//安装月份
	private Long installCount;//安装量
	private String createTime;
	private String isOver; //是否百台
	private String underWarranty; //是否保修期内
	private String faultTypeID;
	private String faultReasonID;
	private String meshFaultReasonID;
	private Long intervalTime;//维修间隔时间,30的倍数,取整不四舍五入
	
	private Long downlineCount = 0L;//下线数
	private Long shipCount = 0L;//发货数
	private Long repairCount = 0L;//维修数
	
	private String repairPercent;//维修率
	private double repairRate;//维修率
	
	private double shiftGroupScore;//班组得分
	private String shiftGroupCode; //班组代码
	private String category;       //班组类别
	private Long rowId;            //rowId
	private Long defectNum;        //不良數量
	private String indexName;      //指标名称
	private double indexActValue;  //指标得分实绩值
	
	//筛选条件
	private Integer startI;
	private Integer endI;
	private Integer insStartI;//安装
	private Integer insEndI;//安装
	
	private String gas;//气源
	private String billType;//不良来源
	private String productNum	;//产品编码
	private String timeType;//时间类型(年月日)
	private String vocTypeTxt;
	private String vocTypeID;
	private String[] vocTypeIDs;
	private String vocTypeCode;
	private String keys;
	
	private String isConsumedPart; //是否消耗配件
	
	public String getIsConsumedPart() {
		return isConsumedPart;
	}

	public void setIsConsumedPart(String isConsumedPart) {
		this.isConsumedPart = isConsumedPart;
	}

	public String getGasCategoryTxt() {
		return gasCategoryTxt;
	}

	public void setGasCategoryTxt(String gasCategoryTxt) {
		this.gasCategoryTxt = gasCategoryTxt;
	}

	public String[] getGasCategorys() {
		return gasCategorys;
	}

	public void setGasCategorys(String[] gasCategorys) {
		this.gasCategorys = gasCategorys;
	}

	public Integer getInsStartI() {
		return insStartI;
	}

	public void setInsStartI(Integer insStartI) {
		this.insStartI = insStartI;
	}

	public Integer getInsEndI() {
		return insEndI;
	}

	public void setInsEndI(Integer insEndI) {
		this.insEndI = insEndI;
	}

	public String[] getVocTypeIDs() {
		return vocTypeIDs;
	}

	public void setVocTypeIDs(String[] vocTypeIDs) {
		this.vocTypeIDs = vocTypeIDs;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getVocTypeTxt() {
		return vocTypeTxt;
	}

	public void setVocTypeTxt(String vocTypeTxt) {
		this.vocTypeTxt = vocTypeTxt;
	}

	public String getVocTypeID() {
		return vocTypeID;
	}

	public void setVocTypeID(String vocTypeID) {
		this.vocTypeID = vocTypeID;
	}

	public String getVocTypeCode() {
		return vocTypeCode;
	}

	public void setVocTypeCode(String vocTypeCode) {
		this.vocTypeCode = vocTypeCode;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierListTxt() {
		return supplierListTxt;
	}

	public void setSupplierListTxt(String supplierListTxt) {
		this.supplierListTxt = supplierListTxt;
	}

	public String[] getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String[] suppliers) {
		this.suppliers = suppliers;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String[] getSupplierNumbers() {
		return supplierNumbers;
	}

	public void setSupplierNumbers(String[] supplierNumbers) {
		this.supplierNumbers = supplierNumbers;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	private String maxCount;
	
	private List<String> productFamilyList;
	
	private String funcName;
	
	
	public Integer getStartI() {
		return startI;
	}

	public void setStartI(Integer startI) {
		this.startI = startI;
	}

	public Integer getEndI() {
		return endI;
	}

	public void setEndI(Integer endI) {
		this.endI = endI;
	}

	public List<String> getProductFamilyList() {
		return productFamilyList;
	}
	
	public void setProductFamilyList(List<String> productFamilyList) {
		this.productFamilyList = productFamilyList;
	}
	
	public String getFaultTypeID() {
		return faultTypeID;
	}
	public void setFaultTypeID(String faultTypeID) {
		this.faultTypeID = faultTypeID;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRegionListTxt() {
		return regionListTxt;
	}
	public void setRegionListTxt(String regionListTxt) {
		this.regionListTxt = regionListTxt;
	}
	public String[] getRegions() {
		return regions;
	}
	public void setRegions(String[] regions) {
		this.regions = regions;
	}
	public String getPlineListTxt() {
		return plineListTxt;
	}
	public void setPlineListTxt(String plineListTxt) {
		this.plineListTxt = plineListTxt;
	}
	public String[] getPlines() {
		return plines;
	}
	public void setPlines(String[] plines) {
		this.plines = plines;
	}
	public String getFaultTypeTxt() {
		return faultTypeTxt;
	}
	public void setFaultTypeTxt(String faultTypeTxt) {
		this.faultTypeTxt = faultTypeTxt;
	}
	public String[] getFaultTypes() {
		return faultTypes;
	}
	public void setFaultTypes(String[] faultTypes) {
		this.faultTypes = faultTypes;
	}
	public String getFaultReasonTxt() {
		return faultReasonTxt;
	}
	public void setFaultReasonTxt(String faultReasonTxt) {
		this.faultReasonTxt = faultReasonTxt;
	}
	public String[] getFaultReasons() {
		return faultReasons;
	}
	public void setFaultReasons(String[] faultReasons) {
		this.faultReasons = faultReasons;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getProductLineNumber() {
		return productLineNumber;
	}
	public void setProductLineNumber(String productLineNumber) {
		this.productLineNumber = productLineNumber;
	}
	public String getProductLineName() {
		return productLineName;
	}
	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	public String getRepairMonth() {
		return repairMonth;
	}
	public void setRepairMonth(String repairMonth) {
		this.repairMonth = repairMonth;
	}
	public String getShipMonth() {
		return shipMonth;
	}
	public void setShipMonth(String shipMonth) {
		this.shipMonth = shipMonth;
	}
	public String getDownLineMonth() {
		return downLineMonth;
	}
	public void setDownLineMonth(String downLineMonth) {
		this.downLineMonth = downLineMonth;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDwStartTime() {
		return dwStartTime;
	}

	public void setDwStartTime(String dwStartTime) {
		this.dwStartTime = dwStartTime;
	}

	public String getDwEndTime() {
		return dwEndTime;
	}
	public String getRepairStartTime() {
		return repairStartTime;
	}

	public void setRepairStartTime(String repairStartTime) {
		this.repairStartTime = repairStartTime;
	}

	public String getRepairEndTime() {
		return repairEndTime;
	}

	public void setRepairEndTime(String repairEndTime) {
		this.repairEndTime = repairEndTime;
	}
	public void setDwEndTime(String dwEndTime) {
		this.dwEndTime = dwEndTime;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getDownlineCount() {
		return downlineCount;
	}
	public void setDownlineCount(Long downlineCount) {
		this.downlineCount = downlineCount;
	}
	public String getMatrixTableType() {
		return matrixTableType;
	}

	public void setMatrixTableType(String matrixTableType) {
		this.matrixTableType = matrixTableType;
	}
	public Long getShipCount() {
		return shipCount;
	}
	public void setShipCount(Long shipCount) {
		this.shipCount = shipCount;
	}
	public Long getRepairCount() {
		return repairCount;
	}
	public void setRepairCount(Long repairCount) {
		this.repairCount = repairCount;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductType() {
		return productType;
	}
	public void setRepairPercent(String repairPercent) {
		this.repairPercent = repairPercent;
	}
	public String getRepairPercent() {
		return repairPercent;
	}
	public String getQueryMonth() {
		return queryMonth;
	}
	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
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
	public String getFaultTypeName() {
		return faultTypeName;
	}
	public void setFaultTypeName(String faultTypeName) {
		this.faultTypeName = faultTypeName;
	}
	public String getFaultReasonName() {
		return faultReasonName;
	}
	public void setFaultReasonName(String faultReasonName) {
		this.faultReasonName = faultReasonName;
	}
	public String getBaseMonth()
	{
		return baseMonth;
	}
	public void setBaseMonth(String baseMonth)
	{
		this.baseMonth = baseMonth;
	}
	public double getRepairRate()
	{
		return repairRate;
	}
	public void setRepairRate(double repairRate)
	{
		this.repairRate = repairRate;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getChartType() {
		return chartType;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	public Integer getxCount() {
		return xCount;
	}
	public void setxCount(Integer xCount) {
		this.xCount = xCount;
	}
	public void setPartTypeListTxt(String partTypeListTxt) {
		this.partTypeListTxt = partTypeListTxt;
	}
	public String getPartTypeListTxt() {
		return partTypeListTxt;
	}
	public void setPartTypes(String[] partTypes) {
		this.partTypes = partTypes;
	}
	public String[] getPartTypes() {
		return partTypes;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactory() {
		return factory;
	}
	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}
	public int getTypeNum() {
		return typeNum;
	}

	public double getShiftGroupScore() {
		return shiftGroupScore;
	}
	public void setShiftGroupScore(double shiftGroupScore) {
		this.shiftGroupScore = shiftGroupScore;
	}
	public String getShiftGroupTxt() {
		return shiftGroupTxt;
	}
	public void setShiftGroupTxt(String shiftGroupTxt) {
		this.shiftGroupTxt = shiftGroupTxt ==null ? null : shiftGroupTxt.trim();
	}
	public String[] getShiftGroups() {
		return shiftGroups;
	}
	public void setShiftGroups(String[] shiftGroups) {
		this.shiftGroups = shiftGroups;
	}
	public String getShiftGroupCode() {
		return shiftGroupCode;
	}
	public void setShiftGroupCode(String shiftGroupCode) {
		this.shiftGroupCode = shiftGroupCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStatisType() {
		return statisType;
	}
	public void setStatisType(String statisType) {
		this.statisType = statisType;
	}
	public String getStatisData() {
		return statisData;
	}
	public void setStatisData(String statisData) {
		this.statisData = statisData;
	}
	public String getInstallMonth() {
		return installMonth;
	}
	public void setInstallMonth(String installMonth) {
		this.installMonth = installMonth;
	}
	
	public Long getInstallCount() {
		return installCount;
	}
	public void setInstallCount(Long installCount) {
		this.installCount = installCount;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getFaultReasonValid() {
		return faultReasonValid;
	}
	public void setFaultReasonValid(String faultReasonValid) {
		this.faultReasonValid = faultReasonValid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	public String getIsOver() {
		return isOver;
	}
	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}
	public String getUnderWarranty() {
		return underWarranty;
	}
	public void setUnderWarranty(String underWarranty) {
		this.underWarranty = underWarranty;
	}
	public Long getDefectNum() {
		return defectNum;
	}
	public void setDefectNum(Long defectNum) {
		this.defectNum = defectNum;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public double getIndexActValue() {
		return indexActValue;
	}
	public void setIndexActValue(double indexActValue) {
		this.indexActValue = indexActValue;
	}
	public String getMeshFaultName() {
		return meshFaultName;
	}
	public void setMeshFaultName(String meshFaultName) {
		this.meshFaultName = meshFaultName;
	}
	
	public String[] getMeshFaultReasons() {
		return meshFaultReasons;
	}
	public void setMeshFaultReasons(String[] meshFaultReasons) {
		this.meshFaultReasons = meshFaultReasons;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductFamilyTxt() {
		return productFamilyTxt;
	}
	public void setProductFamilyTxt(String productFamilyTxt) {
		this.productFamilyTxt = productFamilyTxt;
	}
	public String[] getProductFamilys() {
		return productFamilys;
	}
	public void setProductFamilys(String[] productFamilys) {
		this.productFamilys = productFamilys;
	}
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	public String getMeshFaultReasonCode() {
		return meshFaultReasonCode;
	}
	public void setMeshFaultReasonCode(String meshFaultReasonCode) {
		this.meshFaultReasonCode = meshFaultReasonCode;
	}
	public String getFaultReasonID() {
		return faultReasonID;
	}
	public void setFaultReasonID(String faultReasonID) {
		this.faultReasonID = faultReasonID;
	}
	public String getMeshFaultReasonID() {
		return meshFaultReasonID;
	}
	public void setMeshFaultReasonID(String meshFaultReasonID) {
		this.meshFaultReasonID = meshFaultReasonID;
	}
	public String getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String[] getFaultTypeCodes() {
		return faultTypeCodes;
	}

	public void setFaultTypeCodes(String[] faultTypeCodes) {
		this.faultTypeCodes = faultTypeCodes;
	}

	public String getDlStartTime() {
		return dlStartTime;
	}

	public void setDlStartTime(String dlStartTime) {
		this.dlStartTime = dlStartTime;
	}

	public String getDlEndTime() {
		return dlEndTime;
	}

	public void setDlEndTime(String dlEndTime) {
		this.dlEndTime = dlEndTime;
	}

	public String getMergeRegionListTxt() {
		return mergeRegionListTxt;
	}

	public void setMergeRegionListTxt(String mergeRegionListTxt) {
		this.mergeRegionListTxt = mergeRegionListTxt;
	}

	public String[] getMergeRegions() {
		return mergeRegions;
	}

	public void setMergeRegions(String[] mergeRegions) {
		this.mergeRegions = mergeRegions;
	}

	public Long getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(Long intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getVoiceCategoryTxt() {
		return voiceCategoryTxt;
	}

	public void setVoiceCategoryTxt(String voiceCategoryTxt) {
		this.voiceCategoryTxt = voiceCategoryTxt;
	}

	public String[] getVoiceCategorys() {
		return voiceCategorys;
	}

	public void setVoiceCategorys(String[] voiceCategorys) {
		this.voiceCategorys = voiceCategorys;
	}

	public String getVoiceCategory() {
		return voiceCategory;
	}

	public void setVoiceCategory(String voiceCategory) {
		this.voiceCategory = voiceCategory;
	}


	
	
}
