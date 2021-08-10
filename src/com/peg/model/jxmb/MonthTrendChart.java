package com.peg.model.jxmb;

public class MonthTrendChart {
	private String baseValue;//月度目标
	private String monthReality;//月度实际
	private String targetValue;//月度累计目标
	private String accumuMonth;//月度累计实际
	private String month;//月份
	private String oldMonthreality;//上一年当月实际
	private String oldAccumumonth;//上一年当月累计实际
	private String depaNumber;//部门编号
	private String depaName;//部门名称
	private String content;//考核指标类型
	
	private String year;//年份
	private String baseValueByYear;//年度基准
	private String targetValueByYear;//年度目标
	private String oldTargetValueByYear;//累计目标
	private String moncolumn1;//望目型判断字段
	private String performanceType;//
	
	
	public String getPerformanceType() {
		return performanceType;
	}
	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}
	public String getMoncolumn1() {
		return moncolumn1;
	}
	public void setMoncolumn1(String moncolumn1) {
		this.moncolumn1 = moncolumn1;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}
	public String getMonthReality() {
		return monthReality;
	}
	public void setMonthReality(String monthReality) {
		this.monthReality = monthReality;
	}
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	public String getAccumuMonth() {
		return accumuMonth;
	}
	public void setAccumuMonth(String accumuMonth) {
		this.accumuMonth = accumuMonth;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getOldMonthreality() {
		return oldMonthreality;
	}
	public void setOldMonthreality(String oldMonthreality) {
		this.oldMonthreality = oldMonthreality;
	}
	public String getOldAccumumonth() {
		return oldAccumumonth;
	}
	public void setOldAccumumonth(String oldAccumumonth) {
		this.oldAccumumonth = oldAccumumonth;
	}
	public String getDepaNumber() {
		return depaNumber;
	}
	public void setDepaNumber(String depaNumber) {
		this.depaNumber = depaNumber;
	}
	public String getDepaName() {
		return depaName;
	}
	public void setDepaName(String depaName) {
		this.depaName = depaName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBaseValueByYear() {
		return baseValueByYear;
	}
	public void setBaseValueByYear(String baseValueByYear) {
		this.baseValueByYear = baseValueByYear;
	}
	public String getTargetValueByYear() {
		return targetValueByYear;
	}
	public void setTargetValueByYear(String targetValueByYear) {
		this.targetValueByYear = targetValueByYear;
	}
	public String getOldTargetValueByYear() {
		return oldTargetValueByYear;
	}
	public void setOldTargetValueByYear(String oldTargetValueByYear) {
		this.oldTargetValueByYear = oldTargetValueByYear;
	}

}
