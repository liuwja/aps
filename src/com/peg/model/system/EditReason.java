package com.peg.model.system;

import java.util.Date;

/**
 * 绩效管理修日志记录
 * @author xuanm
 * 说明：记录绩效修改人、修改时间、修改内容、修改前后数据。修改前后数据以字符串的形式显示
 */
public class EditReason {
	private Long id; //主键编号
	private String factoryNumber;//工厂编号
	private String factoryName;//工厂名称
	private String departmentNumber;//部门编号
	private String departmentName;//部门名称
	private String targetClass;//绩效目标大类（绩效分类）
	private String indexContent;//指标内容（指标考核的具体方面描述）
	private String performanceContent;//指标类型（望大型、望小型、望目型）
	private String weight;//权重
	private String company;//单位（指标的单位）
	
	private String lastUpdateUser; //修改人
	private String updateReason; //修改原因
	private String updateType; //修改类型
	private String updateContent; //修改内容
	private Date createTime; //创建时间
	private Date lastUpdateTime; //修改时间
	
	private Date startTime;//查询开始时间
	private Date endTime;//查询结束时间
	
	//备用字段
	private String column1; //备用字段1
	private String column2; //备用字段2
	private String column3; //备用字段3
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id == null?null:id;
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
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass == null?null:targetClass;
	}
	public String getIndexContent() {
		return indexContent;
	}
	public void setIndexContent(String indexContent) {
		this.indexContent = indexContent == null?null:indexContent;
	}
	public String getPerformanceContent() {
		return performanceContent;
	}
	public void setPerformanceContent(String performanceContent) {
		this.performanceContent = performanceContent == null?null:performanceContent;
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
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null?null:lastUpdateUser;
	}
	public String getUpdateReason() {
		return updateReason;
	}
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason == null?null:updateReason;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType == null?null:updateType;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent == null?null:updateContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime == null?null:lastUpdateTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime == null?null:startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime == null?null:endTime;
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
}
