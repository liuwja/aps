package com.peg.model.performance;

import java.util.Date;
import java.util.List;

/**
 * 绩效指标（基础类）：最基本的指标信息，一条记录代表一个指标
 * @author xuanm
 *
 */
public class PerformanceIndex {
    private Long id;//主键

    private Date checkYear;//年度
    private String factoryNumber;//工厂编号
	private String factoryName;//工厂名称
	private String departmentNumber;//部门编号
	private String departmentName;//部门名称
	private String performanceTargetClass;//绩效目标大类（绩效分类）
	private String performanceType;//绩效类型
	private String indexContent;//指标内容（指标考核的具体方面描述）
	private String indexType;//指标类型（望大型、望小型、望目型）
	private String weight;//权重
	private String company;//单位（指标的单位）
	private String referenceValue;//基准值
	private String targetValue;//目标值
    private String middleValue;//中间值
    private String formula;//计算公式
    private String updateReason;//修改原因
    private String assessmentMethod;//评价方法

    private String createUser;//创建人
    private Date createTime;//创建时间
    private String updateUser;//修改人
    private Date updateTime;//修改时间
    
    private String column1;//备用字段1
    private String column2;//备用字段2
    private String column3;//备用字段3
    
    private String year;//年度，用于页面的条件查询
    private String checkYearString;//年度，用于获取指标设置年度，格式为yyyy
    
    private YearPerformance yearPerformance;//年度绩效指标对象
    private List<MonthPerformance> monthList;//月度绩效指标
    private List<MonthPerformance> item;//月度绩效指标（记录月度绩效结果）
    
    
    /**
     * 统计类型，用于页面下拉框。值：所有/当月实际/当月累计实际
     */
    private String statisticsType;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id == null?null:id;
	}
	public Date getCheckYear() {
		return checkYear;
	}
	public void setCheckYear(Date checkYear) {
		this.checkYear = checkYear == null?null:checkYear;
	}
	public String getFactoryNumber() {
		return factoryNumber;
	}
	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber == null?null:factoryNumber;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName == null?null:factoryName;
	}
	public String getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber == null?null:departmentNumber;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName == null?null:departmentName;
	}
	public String getPerformanceTargetClass() {
		return performanceTargetClass;
	}
	public void setPerformanceTargetClass(String performanceTargetClass) {
		this.performanceTargetClass = performanceTargetClass == null?null:performanceTargetClass;
	}
	public String getPerformanceType() {
		return performanceType;
	}
	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType == null?null:performanceType;
	}
	public String getIndexContent() {
		return indexContent;
	}
	public void setIndexContent(String indexContent) {
		this.indexContent = indexContent == null?null:indexContent;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType == null?null:indexType;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight == null?null:weight;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company == null?null:company;
	}
	public String getReferenceValue() {
		return referenceValue;
	}
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue == null?null:referenceValue;
	}
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue == null?null:targetValue;
	}
	public String getMiddleValue() {
		return middleValue;
	}
	public void setMiddleValue(String middleValue) {
		this.middleValue = middleValue == null?null:middleValue;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula == null?null:formula;
	}
	public String getUpdateReason() {
		return updateReason;
	}
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason == null?null:updateReason;
	}
	public String getAssessmentMethod() {
		return assessmentMethod;
	}
	public void setAssessmentMethod(String assessmentMethod) {
		this.assessmentMethod = assessmentMethod == null?null:assessmentMethod;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null?null:createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime == null?null:createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null?null:updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime == null?null:updateTime;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1 == null?null:column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2 == null?null:column2;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3 == null?null:column3;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year == null?null:year;
	}
	public YearPerformance getYearPerformance() {
		return yearPerformance;
	}
	public void setYearPerformance(YearPerformance yearPerformance) {
		this.yearPerformance = yearPerformance;
	}
	public List<MonthPerformance> getMonthList() {
		return monthList;
	}
	public void setMonthList(List<MonthPerformance> monthList) {
		this.monthList = monthList;
	}
	public List<MonthPerformance> getItem() {
		return item;
	}
	public void setItem(List<MonthPerformance> item) {
		this.item = item;
	}
	public String getStatisticsType() {
		return statisticsType;
	}
	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}
	public String getCheckYearString() {
		return checkYearString;
	}
	public void setCheckYearString(String checkYearString) {
		this.checkYearString = checkYearString;
	}
}