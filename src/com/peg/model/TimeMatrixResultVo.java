package com.peg.model;

import java.util.List;

/**
 * 时间序列矩阵报表对象
 * @author song
 *
 */
public class TimeMatrixResultVo {
	
	private String baseMonth;	//基准月份
	private Long baseCount; //基准数
	private List<Long> reCount; //维修数（24个月份）月
	private List<Long> reTotalCount; //累计维修数（24个月份） 年
	private List<Long> repairPercent; //单月维修率（24个月份）
	private List<Long> repairTotalPercent; //累计维修率（24个月份）
	private List<Double> rePercent;//单月维修率
	private List<Double> reTotalPercent;//年维修率
	private boolean effective;  //是否有效
	private int preDiff;//倒三角阵前台控制
	
	public List<Double> getRePercent() {
		return rePercent;
	}
	public void setRePercent(List<Double> rePercent) {
		this.rePercent = rePercent;
	}
	public List<Double> getReTotalPercent() {
		return reTotalPercent;
	}
	public void setReTotalPercent(List<Double> reTotalPercent) {
		this.reTotalPercent = reTotalPercent;
	}
	public String getBaseMonth() {
		return baseMonth;
	}
	public void setBaseMonth(String baseMonth) {
		this.baseMonth = baseMonth;
	}
	public Long getBaseCount() {
		return baseCount;
	}
	public void setBaseCount(Long baseCount) {
		this.baseCount = baseCount;
	}
	public List<Long> getReCount() {
		return reCount;
	}
	public void setReCount(List<Long> reCount) {
		this.reCount = reCount;
	}
	public void setRepairPercent(List<Long> repairPercent) {
		this.repairPercent = repairPercent;
	}
	public List<Long> getRepairPercent() {
		return repairPercent;
	}
	public void setReTotalCount(List<Long> reTotalCount) {
		this.reTotalCount = reTotalCount;
	}
	public List<Long> getReTotalCount() {
		return reTotalCount;
	}
	public void setRepairTotalPercent(List<Long> repairTotalPercent) {
		this.repairTotalPercent = repairTotalPercent;
	}
	public List<Long> getRepairTotalPercent() {
		return repairTotalPercent;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public boolean isEffective() {
		return effective;
	}
	public int getPreDiff() {
		return preDiff;
	}
	public void setPreDiff(int preDiff) {
		this.preDiff = preDiff;
	}
}
