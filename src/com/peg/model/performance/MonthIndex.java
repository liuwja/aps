package com.peg.model.performance;

import java.util.Date;

public class MonthIndex {
	
	private Long id;//月度指标主键
	private Long piid;//关联指标主键
	private Date myMonth;//月份
	private String checkMethod;//考核方法
	private String monTargetValue;//当月目标
	private String monTotalTargetValue;//当月累计目标
	private String monChallengeTargetValue;//当月挑战目标
	private String monRealityTargetValue;//当月实际目标值
	private String monRealityTotalTargetValue;//当月实际累计目标值
	private String monRealityChallengeTargetValue;//当月实际挑战目标
	private String checkResult;//考核结果
	private String state;//状态
	private String grade;//评级
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPiid() {
		return piid;
	}
	public void setPiid(Long piid) {
		this.piid = piid;
	}
	public Date getMyMonth() {
		return myMonth;
	}
	public void setMyMonth(Date myMonth) {
		this.myMonth = myMonth;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public String getMonTargetValue() {
		return monTargetValue;
	}
	public void setMonTargetValue(String monTargetValue) {
		this.monTargetValue = monTargetValue;
	}
	public String getMonTotalTargetValue() {
		return monTotalTargetValue;
	}
	public void setMonTotalTargetValue(String monTotalTargetValue) {
		this.monTotalTargetValue = monTotalTargetValue;
	}
	public String getMonChallengeTargetValue() {
		return monChallengeTargetValue;
	}
	public void setMonChallengeTargetValue(String monChallengeTargetValue) {
		this.monChallengeTargetValue = monChallengeTargetValue;
	}
	public String getMonRealityTargetValue() {
		return monRealityTargetValue;
	}
	public void setMonRealityTargetValue(String monRealityTargetValue) {
		this.monRealityTargetValue = monRealityTargetValue;
	}
	public String getMonRealityTotalTargetValue() {
		return monRealityTotalTargetValue;
	}
	public void setMonRealityTotalTargetValue(String monRealityTotalTargetValue) {
		this.monRealityTotalTargetValue = monRealityTotalTargetValue;
	}
	public String getMonRealityChallengeTargetValue() {
		return monRealityChallengeTargetValue;
	}
	public void setMonRealityChallengeTargetValue(String monRealityChallengeTargetValue) {
		this.monRealityChallengeTargetValue = monRealityChallengeTargetValue;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
