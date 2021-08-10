package com.peg.model;

import java.util.Map;

public class WarrantyRepairResultVo {
	
	private String baseMonth;	//月份
	private Long baseCount; //安装数
	private Map<String,Long> reCount; //维修数 key:间隔时间,value:这个时间段对应的维修数
	private Map<String,Long> reTotalCount; //累计维修数（24个月份）
	private Map<String,Long> repairPercent; //维修率 key:间隔时间,value:这个时间段对应的维修率
	private Map<String,Long> repairTotalPercent; //累计维修率（24个月份）
	private boolean effective;  //是否有效
	private int preDiff;//倒三角阵前台控制
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
	public Map<String, Long> getReCount() {
		return reCount;
	}
	public void setReCount(Map<String, Long> reCount) {
		this.reCount = reCount;
	}
	public Map<String, Long> getReTotalCount() {
		return reTotalCount;
	}
	public void setReTotalCount(Map<String, Long> reTotalCount) {
		this.reTotalCount = reTotalCount;
	}
	public Map<String, Long> getRepairPercent() {
		return repairPercent;
	}
	public void setRepairPercent(Map<String, Long> repairPercent) {
		this.repairPercent = repairPercent;
	}
	public Map<String, Long> getRepairTotalPercent() {
		return repairTotalPercent;
	}
	public void setRepairTotalPercent(Map<String, Long> repairTotalPercent) {
		this.repairTotalPercent = repairTotalPercent;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public int getPreDiff() {
		return preDiff;
	}
	public void setPreDiff(int preDiff) {
		this.preDiff = preDiff;
	}

	
}
