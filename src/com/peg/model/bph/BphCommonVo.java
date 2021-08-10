package com.peg.model.bph;

import java.util.ArrayList;
import java.util.List;

import com.peg.model.baseCommonVo;

public class BphCommonVo extends baseCommonVo{
	private long id;
	//用于图形显示
	private String chartType;
	private int width;
	private int hight;
	
	//仅用于查询条件
	private String startTime;
	private String endTime;
	private String queryMonth;
	private Integer xCount;
	private String factory; //工厂
	private String area;    //车间
	private String region;  //区域

	
	//基准月份
	private String baseMonth;
	//多选

	private String shiftGroupTxt;//班组
	private String[] shiftGroups; //班组列表
	
	//可用于查询、结果返回

	private double shiftGroupScore;//班组得分
	private String shiftGroupCode; //班组代码
	private String category;       //班组类别
	private Long rowId;            //rowId
	private Long defectNum;        //不良數量
	private String indexName;      //指标名称
	private double indexActValue;  //指标得分实绩值
	private String createTime;     //创建时间
	private Double col1;
	private Double col2;
	private Double col3;
	private String sumDate;
	private String indexCode;
	
	
	private List<BphCommonVo> comList = new ArrayList<BphCommonVo>(0);




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public String getQueryMonth() {
		return queryMonth;
	}
	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public String getBaseMonth()
	{
		return baseMonth;
	}
	public void setBaseMonth(String baseMonth)
	{
		this.baseMonth = baseMonth;
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
	
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactory() {
		return factory;
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
	
	public List<BphCommonVo> getComList() {
		return comList;
	}
	public void setComList(List<BphCommonVo> comList) {
		this.comList = comList;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Double getCol1() {
		return col1;
	}
	public void setCol1(Double col1) {
		this.col1 = col1;
	}
	public Double getCol2() {
		return col2;
	}
	public void setCol2(Double col2) {
		this.col2 = col2;
	}
	public Double getCol3() {
		return col3;
	}
	public void setCol3(Double col3) {
		this.col3 = col3;
	}
	public String getSumDate() {
		return sumDate;
	}
	public void setSumDate(String sumDate) {
		this.sumDate = sumDate;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	
}
