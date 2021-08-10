package com.peg.model.performance;

import java.util.Date;

/**
 * 年度绩效指标设定
 * @author xuanm
 *
 */
public class YearPerformance {
	private Long id;//主键
	private Long piid;//关联指标表主键
	private String lastYearRealityValue;//上年度实际值
	private String firstYearReferenceValue;//上半年基准值
	private String referenceValue;//本年基准值
	private String targetValue;//本年目标值
	private String firstYearTargetValue;//上半年目标值
	private String secondYearTargetValue;//下半年目标值
	private Date createTime;//创建时间
	private String createUser;//创建人
	private Date updateTime;//修改时间
	private String updateUser;//修改人
	
	private String column1;//备用字段1
	private String column2;//备用字段2
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id == null?null:id;
	}
	public Long getPiid() {
		return piid;
	}
	public void setPiid(Long piid) {
		this.piid = piid == null?null:piid;
	}
	public String getLastYearRealityValue() {
		return lastYearRealityValue;
	}
	public void setLastYearRealityValue(String lastYearRealityValue) {
		this.lastYearRealityValue = lastYearRealityValue == null?null:lastYearRealityValue;
	}
	public String getFirstYearReferenceValue() {
		return firstYearReferenceValue;
	}
	public void setFirstYearReferenceValue(String firstYearReferenceValue) {
		this.firstYearReferenceValue = firstYearReferenceValue;
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
	public String getFirstYearTargetValue() {
		return firstYearTargetValue;
	}
	public void setFirstYearTargetValue(String firstYearTargetValue) {
		this.firstYearTargetValue = firstYearTargetValue == null?null:firstYearTargetValue;
	}
	public String getSecondYearTargetValue() {
		return secondYearTargetValue;
	}
	public void setSecondYearTargetValue(String secondYearTargetValue) {
		this.secondYearTargetValue = secondYearTargetValue == null?null:secondYearTargetValue;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime == null?null:createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null?null:createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime == null?null:updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null?null:updateUser;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	@Override
	public String toString() {
		return "YearPerformance [id=" + id + ", piid=" + piid + ", lastYearRealityValue=" + lastYearRealityValue
				+ ", firstYearReferenceValue=" + firstYearReferenceValue + ", referenceValue=" + referenceValue
				+ ", targetValue=" + targetValue + ", firstYearTargetValue=" + firstYearTargetValue
				+ ", secondYearTargetValue=" + secondYearTargetValue + ", createTime=" + createTime + ", createUser="
				+ createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", column1=" + column1
				+ ", column2=" + column2 + "]";
	}

}
