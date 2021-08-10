package com.peg.model.performance;

public class PerformanceEcharVo {
	
	private String startTime;
	private String endTime;
	private String departmentName;//部门名称
	private String performanceTargetClass;//绩效目标大类（绩效分类）
	private String indexContent;//指标内容（指标考核的具体方面描述）
	private Integer type;//报表类型
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPerformanceTargetClass() {
		return performanceTargetClass;
	}
	public void setPerformanceTargetClass(String performanceTargetClass) {
		this.performanceTargetClass = performanceTargetClass;
	}
	public String getIndexContent() {
		return indexContent;
	}
	public void setIndexContent(String indexContent) {
		this.indexContent = indexContent;
	}
	
	
	

}
