package com.peg.model.part;

import java.io.Serializable;
import java.util.List;

public class MarketPart implements Comparable<MarketPart>,Serializable,Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id; //ID
	
	private String serialNumber;
	private String orderNumber;
	
	private String productType; //机型类别
	private String partFamily;	//
	private String qmsType;
	private String partType;	//产品型号
	
	private String supplierId;
	private String supplierListTxt; //供应商
	private String[] suppliers;
	private String supplier;
	private String supplierShortName;
	private String supplierNumber;
	private String consumptionType;//关键件/非关键件/附件
	private String[] supplierNumbers;
	

	
	private Long quantity = 0L; //供应商入库数
	private Long repairCount; //不良数
	private Long shipCount = 0L; //发货数
	private Double repairRate; //不良率
	
	private String faultTypeID;
	private String faultTypeCode;//故障大类代码
	private String faultTypeName;//故障大类名称
	private String faultTypeTxt;
	private String[] faultTypes;
	private String faultReasonID;
	private String faultReasonCode; //故障小类代码
	private String meshFaultReasonCode; //合并故障小类代码
	private String faultReasonValid; //故障原因是否有效
	private String faultReasonTxt;
	private String[] faultReasons;
	
	private String sortType; //排序方式
	private Integer xCount = 9; //X轴长度
	
	private String regionListTxt; //区域
	private String[] regions;
	private String region;
	
	private String selectDate; //时间维度
	private String queryMonth;
	private String startTime;
	private String endTime;
	private String downStartTime;
	private String downEndTime;
	
	private String partName; //物料
	private String partNumber;
	private String partDescription;
	private String[] partNumbers;
	private String partSerial; //零部件条码
	private String isConsumed; //是否关键件
	private String hasVersion = "1"; //是否带版本
	private String partMaturity; //产品成熟度
	private String partLevel; //物料级别
	private String[] partTypes; //物料类型
	private String partTypesListTxt;
	private String partId;
	
	private String mesPartNumber; //MES物料号
	private String[] mesPartNumbers;
	private List<String> mesPartNumberList;
	private String mesPartName;
	private String mesPartDescription;
	private String mesPartId;
	
	private Integer title; //查询时使用
	private String titleContext;

	private String commonName;
	private String commonNumber;
	private String type;
	
	private String repairDate;//维修时间
	private String matrixType;//三角阵类型
	
	private Long maxValue;//三角阵最大值
	
	private List<String> supplierNumberList;
	private List<String> partNumberList;
	
	private String isOver; //百台内
	
	private String downlineTime;
	private String shipDate;

	private String detailDescription; //详细信息
	private String defectLocalDescription; //故障现场信息
	private String servicePoint; //服务网点
	private String servicePerson; //服务工程师
	private String telephont; //电话号码
	private String mobilePhone; //手机号码
	private String buyTheWay; //购买途径
	
	private Double p;
	private Double q;
	private Double topCtrLineValue = 0d; //上控制线值
	private Double lowerCtrLineValue = 0d; //下控制线
	
	private String isPage;
	
	private String crmPartCategoryId;
	private String crmPartCategoryName;//crm物料一级分类前端搜索条件
	private List<String> crmPartCategoryList;
	private String crmPartCategoryTwoName; //crm物料二级分类前端搜索条件
	private List<String> crmPartCategoryTwoList; 
	
	private String mesPartCategoryId;
	private String mesPartCategoryName;//mes物料一级分类前端搜索条件
	private List<String> mesPartCategoryList;
	private String mesPartCategoryTwoName; //mes物料二级分类前端搜索条件
	private List<String> mesPartCategoryTwoList; 
	
	
	private List<String> productFamilyList;//产品系列
	private List<String> partTypeList;//产品型号
	private String isPorB;//百分比或者PPM
	
	private String productFamily;	//产品系列
	
	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	public String getIsPorB() {
		return isPorB;
	}

	public void setIsPorB(String isPorB) {
		this.isPorB = isPorB;
	}

	public String getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(String consumptionType) {
		this.consumptionType = consumptionType;
	}

	public List<String> getProductFamilyList() {
		return productFamilyList;
	}

	public void setProductFamilyList(List<String> productFamilyList) {
		this.productFamilyList = productFamilyList;
	}

	public List<String> getPartTypeList() {
		return partTypeList;
	}

	public void setPartTypeList(List<String> partTypeList) {
		this.partTypeList = partTypeList;
	}

	public String getMesPartCategoryId() {
		return mesPartCategoryId;
	}

	public void setMesPartCategoryId(String mesPartCategoryId) {
		this.mesPartCategoryId = mesPartCategoryId;
	}

	public String getCrmPartCategoryId() {
		return crmPartCategoryId;
	}

	public void setCrmPartCategoryId(String crmPartCategoryId) {
		this.crmPartCategoryId = crmPartCategoryId;
	}

	public String getCrmPartCategoryName() {
		return crmPartCategoryName;
	}

	public void setCrmPartCategoryName(String crmPartCategoryName) {
		this.crmPartCategoryName = crmPartCategoryName;
	}

	public List<String> getCrmPartCategoryList() {
		return crmPartCategoryList;
	}

	public void setCrmPartCategoryList(List<String> crmPartCategoryList) {
		this.crmPartCategoryList = crmPartCategoryList;
	}

	public String getCrmPartCategoryTwoName() {
		return crmPartCategoryTwoName;
	}

	public void setCrmPartCategoryTwoName(String crmPartCategoryTwoName) {
		this.crmPartCategoryTwoName = crmPartCategoryTwoName;
	}

	public List<String> getCrmPartCategoryTwoList() {
		return crmPartCategoryTwoList;
	}

	public void setCrmPartCategoryTwoList(List<String> crmPartCategoryTwoList) {
		this.crmPartCategoryTwoList = crmPartCategoryTwoList;
	}

	public String getMesPartCategoryName() {
		return mesPartCategoryName;
	}

	public void setMesPartCategoryName(String mesPartCategoryName) {
		this.mesPartCategoryName = mesPartCategoryName;
	}

	public List<String> getMesPartCategoryList() {
		return mesPartCategoryList;
	}

	public void setMesPartCategoryList(List<String> mesPartCategoryList) {
		this.mesPartCategoryList = mesPartCategoryList;
	}

	public String getMesPartCategoryTwoName() {
		return mesPartCategoryTwoName;
	}

	public void setMesPartCategoryTwoName(String mesPartCategoryTwoName) {
		this.mesPartCategoryTwoName = mesPartCategoryTwoName;
	}

	public List<String> getMesPartCategoryTwoList() {
		return mesPartCategoryTwoList;
	}

	public void setMesPartCategoryTwoList(List<String> mesPartCategoryTwoList) {
		this.mesPartCategoryTwoList = mesPartCategoryTwoList;
	}

	/** 数据库日期格式（date为1，varchar为2）*/
	private String dateType = "1";
	
	public String getFaultReasonValid() {
		return faultReasonValid;
	}
	
	public void setFaultReasonValid(String faultReasonValid) {
		this.faultReasonValid = faultReasonValid;
	}
	
	public String getPartLevel() {
		return partLevel;
	}
	
	public void setPartLevel(String partLevel) {
		this.partLevel = partLevel;
	}
	
	public String[] getPartTypes() {
		return partTypes;
	}
	
	public void setPartTypes(String partTypes[]) {
		this.partTypes = partTypes;
	}
	
	public String getPartTypesListTxt() {
		return partTypesListTxt;
	}
	
	public void setPartTypesListTxt(String partTypesListTxt) {
		this.partTypesListTxt = partTypesListTxt;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getPartFamily() {
		return partFamily;
	}
	
	public void setPartFamily(String partFamily) {
		this.partFamily = partFamily;
	}
	
	public String getQmsType() {
		return qmsType;
	}
	
	public void setQmsType(String qmsType) {
		this.qmsType = qmsType;
	}
	
	public String getPartType() {
		return partType;
	}
	
	public void setPartType(String partType) {
		this.partType = partType;
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
	
	public String getSupplier() {
		return supplier;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
	
	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Long getRepairCount() {
		return repairCount;
	}
	
	public void setRepairCount(Long repairCount) {
		this.repairCount = repairCount;
	}
	
	public Long getShipCount() {
		return shipCount;
	}
	
	public void setShipCount(Long shipCount) {
		this.shipCount = shipCount;
	}
	
	public Double getRepairRate() {
		return repairRate;
	}
	
	public void setRepairRate(Double repairRate) {
		this.repairRate = repairRate;
	}
	
	public String getFaultTypeID() {
		return faultTypeID;
	}
	
	public void setFaultTypeID(String faultTypeID) {
		this.faultTypeID = faultTypeID;
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
	
	public String getFaultReasonID() {
		return faultReasonID;
	}
	
	public void setFaultReasonID(String faultReasonID) {
		this.faultReasonID = faultReasonID;
	}
	
	public String getFaultReasonCode() {
		return faultReasonCode;
	}
	
	public void setFaultReasonCode(String faultReasonCode) {
		this.faultReasonCode = faultReasonCode;
	}
	
	public String getMeshFaultReasonCode() {
		return meshFaultReasonCode;
	}
	
	public void setMeshFaultReasonCode(String meshFaultReasonCode) {
		this.meshFaultReasonCode = meshFaultReasonCode;
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
	
	/**
	 * 是否关键件
	 * @return 返回1时为关键件，返回2时为非关键件
	 */
	public String getIsConsumed() {
		return isConsumed;
	}
	
	/**
	 * 关键件为1，非关键件为2
	 * @param isConsumed 是否关键件
	 */
	public void setIsConsumed(String isConsumed) {
		this.isConsumed = isConsumed;
	}
	
	public String getHasVersion() {
		return hasVersion;
	}
	
	public void setHasVersion(String hasVersion) {
		this.hasVersion = hasVersion;
	}
	
	public String getPartMaturity() {
		return partMaturity;
	}
	
	public void setPartMaturity(String partMaturity) {
		this.partMaturity = partMaturity;
	}
	
	public String getSortType() {
		return sortType;
	}
	
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	public Integer getxCount() {
		return xCount;
	}
	
	public void setxCount(Integer xCount) {
		this.xCount = xCount;
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
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getSelectDate() {
		return selectDate;
	}
	
	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	
	public String getQueryMonth() {
		return queryMonth;
	}
	
	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
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
	
	public String getDownStartTime() {
		return downStartTime;
	}
	
	public void setDownStartTime(String downStartTime) {
		this.downStartTime = downStartTime;
	}
	
	public String getDownEndTime() {
		return downEndTime;
	}
	
	public void setDownEndTime(String downEndTime) {
		this.downEndTime = downEndTime;
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
	
	public String getMesPartNumber() {
		return mesPartNumber;
	}
	
	public void setMesPartNumber(String mesPartNumber) {
		this.mesPartNumber = mesPartNumber;
	}
	
	public String[] getMesPartNumbers() {
		return mesPartNumbers;
	}
	
	public void setMesPartNumbers(String[] mesPartNumbers) {
		this.mesPartNumbers = mesPartNumbers;
	}
	
	public List<String> getMesPartNumberList() {
		return mesPartNumberList;
	}
	
	public void setMesPartNumberList(List<String> mesPartNumberList) {
		this.mesPartNumberList = mesPartNumberList;
	}
	
	public String getMesPartName() {
		return mesPartName;
	}
	
	public void setMesPartName(String mesPartName) {
		this.mesPartName = mesPartName;
	}
	
	public String getMesPartDescription() {
		return mesPartDescription;
	}
	
	public void setMesPartDescription(String mesPartDescription) {
		this.mesPartDescription = mesPartDescription;
	}
	
	public String getMesPartId() {
		return mesPartId;
	}
	
	public void setMesPartId(String mesPartId) {
		this.mesPartId = mesPartId;
	}
	
	public String getPartDescription() {
		return partDescription;
	}
	
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	
	public String[] getPartNumbers() {
		return partNumbers;
	}
	
	public void setPartNumbers(String[] partNumbers) {
		this.partNumbers = partNumbers;
	}
	
	public String getPartSerial() {
		return partSerial;
	}
	
	public void setPartSerial(String partSerial) {
		this.partSerial = partSerial;
	}
	
	public Integer getTitle() {
		return title;
	}
	
	public void setTitle(Integer title) {
		this.title = title;
	}
	
	public String getTitleContext() {
		return titleContext;
	}
	
	public void setTitleContext(String titleContext) {
		this.titleContext = titleContext;
	}
	
	public String getCommonName() {
		return commonName;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	public String getCommonNumber() {
		return commonNumber;
	}
	
	public void setCommonNumber(String commonNumber) {
		this.commonNumber = commonNumber;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getRepairDate() {
		return repairDate;
	}
	
	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
	
	public String getMatrixType() {
		return matrixType;
	}
	
	public void setMatrixType(String matrixType) {
		this.matrixType = matrixType;
	}
	
	public Long getMaxValue() {
		return maxValue;
	}
	
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getPartId() {
		return partId;
	}
	
	public void setPartId(String partId) {
		this.partId = partId;
	}
	
	public List<String> getSupplierNumberList() {
		return supplierNumberList;
	}
	
	public void setSupplierNumberList(List<String> supplierNumberList) {
		this.supplierNumberList = supplierNumberList;
	}
	
	public List<String> getPartNumberList() {
		return partNumberList;
	}
	
	public void setPartNumberList(List<String> partNumberList) {
		this.partNumberList = partNumberList;
	}
	
	public String getIsOver() {
		return isOver;
	}
	
	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}
	
	public Double getP() {
		return p;
	}
	
	public void setP(Double p) {
		this.p = p;
	}
	
	public Double getQ() {
		return q;
	}
	
	public void setQ(Double q) {
		this.q = q;
	}
	
	public Double getTopCtrLineValue() {
		return topCtrLineValue;
	}
	
	public void setTopCtrLineValue(Double topCtrLineValue) {
		this.topCtrLineValue = topCtrLineValue;
	}
	
	public Double getLowerCtrLineValue() {
		return lowerCtrLineValue;
	}
	
	public void setLowerCtrLineValue(Double lowerCtrLineValue) {
		this.lowerCtrLineValue = lowerCtrLineValue;
	}
	
	public String getSupplierShortName() {
		return supplierShortName;
	}
	
	public void setSupplierShortName(String supplierShortName) {
		this.supplierShortName = supplierShortName;
	}
	
	public String getShipDate() {
		return shipDate;
	}
	
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	
	public String getDownlineTime() {
		return downlineTime;
	}
	
	public void setDownlineTime(String downlineTime) {
		this.downlineTime = downlineTime;
	}
	
	public String getIsPage() {
		return isPage;
	}
	
	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}
	
	public String getDetailDescription() {
		return detailDescription;
	}
	
	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}
	
	public String getDefectLocalDescription() {
		return defectLocalDescription;
	}
	
	public void setDefectLocalDescription(String defectLocalDescription) {
		this.defectLocalDescription = defectLocalDescription;
	}
	
	public String getServicePoint() {
		return servicePoint;
	}
	
	public void setServicePoint(String servicePoint) {
		this.servicePoint = servicePoint;
	}
	
	public String getServicePerson() {
		return servicePerson;
	}
	
	public void setServicePerson(String servicePerson) {
		this.servicePerson = servicePerson;
	}
	
	public String getTelephont() {
		return telephont;
	}
	
	public void setTelephont(String telephont) {
		this.telephont = telephont;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getBuyTheWay() {
		return buyTheWay;
	}
	
	public void setBuyTheWay(String buyTheWay) {
		this.buyTheWay = buyTheWay;
	}
	
	/**
	 * @return 数据库日期格式（date为1，varchar为2）
	 */
	public String getDateType() {
		return dateType;
	}

	/**
	 * @param dateType 数据库日期格式（date为1，varchar为2）
	 */
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	@Override
	public int compareTo(MarketPart o) {
		if (this.getRepairCount().compareTo(o.getRepairCount()) == 0) {
			return this.getQuantity().compareTo(o.getQuantity());
		}
		return this.getRepairCount().compareTo(o.getRepairCount());
	}
	
	public MarketPart clone() {
		MarketPart m = null;
		try {
			m = (MarketPart)super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
}
