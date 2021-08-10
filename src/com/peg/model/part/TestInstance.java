package com.peg.model.part;

/**
 * 全质量进料部分model
 * @author Administrator
 *
 */
public class TestInstance implements Cloneable{

	private Long id;
	private String productType;      //机型类别
	private String supplier;         //供应商
	private String partType;         //物料类别
	private String partName;         //物料名称
	private String dateT;             //日期
	private String isNew;            //是否新品
	private String iscrux;			//是否关键件
	private String lotName;          //批次号
	private String supplierCode;     //供应商编号
	private String supplierBrief;    //供应商简称
	private String dateType;         //时间维度
	private String factory;          //工厂
	private String partClass;        //物料级别
	private String partVersion;      //物料版本
	private String lotTime;          //供应商批次
	private int columnNum;           //排列图数量
	private String analysisType;     //统计类别  
	private String partNumber;       //物料编号
	private int type;                //查询类型,1 供应商，2 零部件，3 不良现象，4 供应商趋势，5 零部件趋势，6 供应商对比，7 零部件对比
	private String defectType;       //不良类型
	private Long week;               //周
	private String wareHouse;        //仓库
	private String resultS;          //判定结果
	private String aspectType;       //外观判断
	private String sizeType;         //尺寸判断
	private String propertyType;     //性能判断
	private String otherType;        //其他判断
	private Long propertyTnum;       //性能不良总数
	private Long propertyDnum;       //性能不良不良数
	private Long sizeTnum;           //尺寸不良总数
	private Long sizeDnum;           //尺寸不良数
	private Long aspectTnum;         //外观不良总数
	private Long aspectDnum;         //外观不良不良数
	private Long otherTnum;          //其他类型总数
	private Long otherDnum;          //其他不良数
	
	private int totalLot;           //总批次
	private int defectLot;          //不良批次
	private int totalQty;           //总数
	private int defectQty;          //不良数量
	private int aspectLot;          //外观不良批次
	private int sizeLot;            //尺寸不良批次
	private int propertyLot;        //性能不良批次
	private int otherLot;           //其他不良批次
	private int aspectQty;          //外观不良数
	private int sizeQty;            //尺寸不良数
	private int propertyQty;        //性能不良数
	private int otherQty;           //其他不良数
	
	
	private String startTime;       //开始时间
	private String endTime;         //结束时间
	private String supplierData;    //查询用
	private String partData;        //查询用
	private int totalS;             //总数（趋势图用）
	private int defectS;            //不良数（趋势图用）
	
	private String lotStartTime;    //供应商批次开始日期
	private String lotEndTime;      //供应商批次结束日期
	
	private String partTypeListText;  //物料类别
	private String dayTime;           //(本月/上月)
	
	private String idNameNumber;
	private String newPartNumber;   //新物料编号
	private String newSupplierNumber;  //新供应商编号
	private String sordType;          //排序方式
	private Long distinction;       //区分跳转查询（1）和手动查询（0）
	
	private String smallBatch;//是否小批量
	private String source;//国内/海外
	private String rohs;//ROHS标识
	private String productionLineName;//产品线
	private String location;//仓库
	private String defect;//缺陷分类
	private String class1;//分类1
	private String class2;//分类2
	
	
	
	public String getProductionLineName() {
		return productionLineName;
	}
	public void setProductionLineName(String productionLineName) {
		this.productionLineName = productionLineName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDefect() {
		return defect;
	}
	public void setDefect(String defect) {
		this.defect = defect;
	}
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public String getClass2() {
		return class2;
	}
	public void setClass2(String class2) {
		this.class2 = class2;
	}
	public String getSmallBatch() {
		return smallBatch;
	}
	public void setSmallBatch(String smallBatch) {
		this.smallBatch = smallBatch;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRohs() {
		return rohs;
	}
	public void setRohs(String rohs) {
		this.rohs = rohs;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	public String getDateT() {
		return dateT;
	}
	public void setDateT(String dateT) {
		this.dateT = dateT;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType == null ? null : dateType.trim();
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory == null ? null : factory.trim();
	}
	public String getPartClass() {
		return partClass;
	}
	public void setPartClass(String partClass) {
		this.partClass = partClass ==null ? null : partClass.trim();
	}
	public String getPartVersion() {
		return partVersion;
	}
	public void setPartVersion(String partVersion) {
		this.partVersion = partVersion == null ? null : partVersion.trim();
	}
	public String getLotTime() {
		return lotTime;
	}
	public void setLotTime(String lotTime) {
		this.lotTime = lotTime;
	}
	public int getColumnNum() {
		return columnNum;
	}
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}
	public String getAnalysisType() {
		return analysisType;
	}
	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTotalLot() {
		return totalLot;
	}
	public void setTotalLot(int totalLot) {
		this.totalLot = totalLot;
	}
	public int getDefectLot() {
		return defectLot;
	}
	public void setDefectLot(int defectLot) {
		this.defectLot = defectLot;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public int getDefectQty() {
		return defectQty;
	}
	public void setDefectQty(int defectQty) {
		this.defectQty = defectQty;
	}
	public int getAspectLot() {
		return aspectLot;
	}
	public void setAspectLot(int aspectLot) {
		this.aspectLot = aspectLot;
	}
	public int getSizeLot() {
		return sizeLot;
	}
	public void setSizeLot(int sizeLot) {
		this.sizeLot = sizeLot;
	}
	public int getPropertyLot() {
		return propertyLot;
	}
	public void setPropertyLot(int propertyLot) {
		this.propertyLot = propertyLot;
	}
	public int getOtherLot() {
		return otherLot;
	}
	public void setOtherLot(int otherLot) {
		this.otherLot = otherLot;
	}
	public int getAspectQty() {
		return aspectQty;
	}
	public void setAspectQty(int aspectQty) {
		this.aspectQty = aspectQty;
	}
	public int getSizeQty() {
		return sizeQty;
	}
	public void setSizeQty(int sizeQty) {
		this.sizeQty = sizeQty;
	}
	public int getPropertyQty() {
		return propertyQty;
	}
	public void setPropertyQty(int propertyQty) {
		this.propertyQty = propertyQty;
	}
	public int getOtherQty() {
		return otherQty;
	}
	public void setOtherQty(int otherQty) {
		this.otherQty = otherQty;
	}
	public String getDefectType() {
		return defectType;
	}
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}
	public Long getWeek() {
		return week;
	}
	public void setWeek(Long week) {
		this.week = week;
	}
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getResultS() {
		return resultS;
	}
	public void setResultS(String resultS) {
		this.resultS = resultS;
	}
	public String getAspectType() {
		return aspectType;
	}
	public void setAspectType(String aspectType) {
		this.aspectType = aspectType;
	}
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getOtherType() {
		return otherType;
	}
	public void setOtherType(String otherType) {
		this.otherType = otherType;
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
	public String getSupplierData() {
		return supplierData;
	}
	public void setSupplierData(String supplierData) {
		this.supplierData = supplierData;
	}
	public String getPartData() {
		return partData;
	}
	public void setPartData(String partData) {
		this.partData = partData;
	}
	public Long getPropertyTnum() {
		return propertyTnum;
	}
	public void setPropertyTnum(Long propertyTnum) {
		this.propertyTnum = propertyTnum;
	}
	public Long getPropertyDnum() {
		return propertyDnum;
	}
	public void setPropertyDnum(Long propertyDnum) {
		this.propertyDnum = propertyDnum;
	}
	public Long getSizeTnum() {
		return sizeTnum;
	}
	public void setSizeTnum(Long sizeTnum) {
		this.sizeTnum = sizeTnum;
	}
	public Long getSizeDnum() {
		return sizeDnum;
	}
	public void setSizeDnum(Long sizeDnum) {
		this.sizeDnum = sizeDnum;
	}
	public Long getAspectTnum() {
		return aspectTnum;
	}
	public void setAspectTnum(Long aspectTnum) {
		this.aspectTnum = aspectTnum;
	}
	public Long getAspectDnum() {
		return aspectDnum;
	}
	public void setAspectDnum(Long aspectDnum) {
		this.aspectDnum = aspectDnum;
	}
	public Long getOtherTnum() {
		return otherTnum;
	}
	public void setOtherTnum(Long otherTnum) {
		this.otherTnum = otherTnum;
	}
	public Long getOtherDnum() {
		return otherDnum;
	}
	public void setOtherDnum(Long otherDnum) {
		this.otherDnum = otherDnum;
	}
	public int getTotalS() {
		return totalS;
	}
	public void setTotalS(int totalS) {
		this.totalS = totalS;
	}
	public int getDefectS() {
		return defectS;
	}
	public void setDefectS(int defectS) {
		this.defectS = defectS;
	}
	public String getLotStartTime() {
		return lotStartTime;
	}
	public void setLotStartTime(String lotStartTime) {
		this.lotStartTime = lotStartTime;
	}
	public String getLotEndTime() {
		return lotEndTime;
	}
	public void setLotEndTime(String lotEndTime) {
		this.lotEndTime = lotEndTime;
	}
	public String getPartTypeListText() {
		return partTypeListText;
	}
	public void setPartTypeListText(String partTypeListText) {
		this.partTypeListText = partTypeListText;
	}
	public String getSupplierBrief() {
		return supplierBrief;
	}
	public void setSupplierBrief(String supplierBrief) {
		this.supplierBrief = supplierBrief;
	}
	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime ==null ? null : dayTime.trim();
	}
	public String getIscrux() {
		if(iscrux==null){
			return "0";
		}
		return iscrux;
	}
	public void setIscrux(String iscrux) {
		this.iscrux = iscrux;
	}
	public String getIdNameNumber() {
		return idNameNumber;
	}
	public void setIdNameNumber(String idNameNumber) {
		this.idNameNumber = idNameNumber;
	}
	public String getNewPartNumber() {
		return newPartNumber;
	}
	public void setNewPartNumber(String newPartNumber) {
		this.newPartNumber = newPartNumber;
	}
	public String getNewSupplierNumber() {
		return newSupplierNumber;
	}
	public void setNewSupplierNumber(String newSupplierNumber) {
		this.newSupplierNumber = newSupplierNumber;
	}
	
	public String getSordType() {
		return sordType;
	}
	public void setSordType(String sordType) {
		this.sordType = sordType;
	}
	public Long getDistinction() {
		return distinction;
	}
	public void setDistinction(Long distinction) {
		this.distinction = distinction;
	}
	@Override
	public TestInstance clone() {
		try {
			return (TestInstance) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	
}
