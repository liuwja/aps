package com.peg.model.performance;

import java.util.Date;

/**
 * 月度绩效类
 * @author xuanm
 *
 */
public class MonthPerformance {
	private Long id;//主键
	private Long piid;//关联指标，外键
	private Date myMonth;//月份
	private String monTargetValue;//当月目标
	private String monTotalTargetValue;//当月累计目标
	private String monChallengeTargetValue;//当月挑战目标
	private String monRealityTargetValue;//当月实际目标值
	private String monRealityTotalTargetValue;//当月实际累计目标值
	private String monRealityChallengeTargetValue;//当月实际挑战目标
	private String checkMethod;//考核方法
	private String checkResult;//考核结果
	private Date createTime1;//创建目标时间
	private String createUser1;//创建目标人
	private Date createTime2;//创建结果时间
	private String createUser2;//创建结果人
	
	private String column1;//状态，正在进行，延期，终止
	private String column2;//评级 
	
	private String trColor1;//用于页面的颜色显示
	private String trColor2;
	private String trColor3;
	
	private String monthIndex;//月份顺序，用于前台页面横向显示12个月
	
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
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public Date getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(Date createTime1) {
		this.createTime1 = createTime1;
	}
	public String getCreateUser1() {
		return createUser1;
	}
	public void setCreateUser1(String createUser1) {
		this.createUser1 = createUser1;
	}
	public Date getCreateTime2() {
		return createTime2;
	}
	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}
	public String getCreateUser2() {
		return createUser2;
	}
	public void setCreateUser2(String createUser2) {
		this.createUser2 = createUser2;
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
		return "MonthPerformance [id=" + id + ", piid=" + piid + ", myMonth=" + myMonth + ", monTargetValue="
				+ monTargetValue + ", monTotalTargetValue=" + monTotalTargetValue + ", monChallengeTargetValue="
				+ monChallengeTargetValue + ", monRealityTargetValue=" + monRealityTargetValue
				+ ", monRealityTotalTargetValue=" + monRealityTotalTargetValue + ", monRealityChallengeTargetValue="
				+ monRealityChallengeTargetValue + ", checkMethod=" + checkMethod + ", checkResult=" + checkResult
				+ ", createTime1=" + createTime1 + ", createUser1=" + createUser1 + ", createTime2=" + createTime2
				+ ", createUser2=" + createUser2 + ", column1=" + column1 + ", column2=" + column2 + "]";
	}
	public String getTrColor1() {
		return trColor1;
	}
	public void setTrColor1(String trColor1) {
		this.trColor1 = trColor1;
	}
	public String getTrColor2() {
		return trColor2;
	}
	public void setTrColor2(String trColor2) {
		this.trColor2 = trColor2;
	}
	public String getTrColor3() {
		return trColor3;
	}
	public void setTrColor3(String trColor3) {
		this.trColor3 = trColor3;
	}
	public String getMonthIndex() {
		return monthIndex;
	}
	public void setMonthIndex(String monthIndex) {
		this.monthIndex = monthIndex;
	}
}
