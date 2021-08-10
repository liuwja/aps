package com.peg.model;

import java.util.List;

public class NewInstallation {
	private String baseMonth;	//基准月份
	private Long baseCount; //安装数
	private Long allBaseCount;//累计安装数
	private List<Long> reCount; //数据维修数
	private List<Long> repairCount; //数据维修率p
	private List<Double> repair2Count;//数据维修率%
	private int l;//显示控制器
	
	
	public List<Double> getRepair2Count() {
		return repair2Count;
	}
	public void setRepair2Count(List<Double> repair2Count) {
		this.repair2Count = repair2Count;
	}
	public List<Long> getRepairCount() {
		return repairCount;
	}
	public void setRepairCount(List<Long> repairCount) {
		this.repairCount = repairCount;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
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
	public Long getAllBaseCount() {
		return allBaseCount;
	}
	public void setAllBaseCount(Long allBaseCount) {
		this.allBaseCount = allBaseCount;
	}
	public List<Long> getReCount() {
		return reCount;
	}
	public void setReCount(List<Long> reCount) {
		this.reCount = reCount;
	}
	
}
