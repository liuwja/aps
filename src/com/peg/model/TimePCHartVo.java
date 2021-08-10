package com.peg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间矩阵P控制图对象
 * @author song
 *
 */
public class TimePCHartVo {
	private long totalDownlineCount = 0l;
	private long totalRepairCount= 0l;
	private int count= 0;//用于计数
	private String staticMonth;//统计月份
	private double PNumber = 0.0;
	private double QNumber = 0.0;
	private double repairPercent = 0.0;
	private long downlineCount;
	private List<String> baseMonth = new ArrayList<String>(); //累计有效月份
	private List<String> baseCountArr = new ArrayList<String>(); //每月生产数
	private List<String> repairArr = new ArrayList<String>(); //每月维修数
	private List<String> repairPercentList = new ArrayList<String>(); //每月维修数
	public long getTotalDownlineCount() {
		return totalDownlineCount;
	}
	public void setTotalDownlineCount(long totalDownlineCount) {
		this.totalDownlineCount = totalDownlineCount;
	}
	public long getTotalRepairCount() {
		return totalRepairCount;
	}
	public void setTotalRepairCount(long totalRepairCount) {
		this.totalRepairCount = totalRepairCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStaticMonth() {
		return staticMonth;
	}
	public void setStaticMonth(String staticMonth) {
		this.staticMonth = staticMonth;
	}
	public double getPNumber() {
		return PNumber;
	}
	public void setPNumber(double pNumber) {
		PNumber = pNumber;
	}
	public double getQNumber() {
		return QNumber;
	}
	public void setQNumber(double qNumber) {
		QNumber = qNumber;
	}
	public double getRepairPercent() {
		return repairPercent;
	}
	public void setRepairPercent(double repairPercent) {
		this.repairPercent = repairPercent;
	}
	public void setDownlineCount(long downlineCount) {
		this.downlineCount = downlineCount;
	}
	public long getDownlineCount() {
		return downlineCount;
	}
	public List<String> getBaseMonth() {
		return baseMonth;
	}
	public void setBaseMonth(List<String> baseMonth) {
		this.baseMonth = baseMonth;
	}
	public List<String> getBaseCountArr() {
		return baseCountArr;
	}
	public void setBaseCountArr(List<String> baseCountArr) {
		this.baseCountArr = baseCountArr;
	}
	public List<String> getRepairArr() {
		return repairArr;
	}
	public void setRepairArr(List<String> repairArr) {
		this.repairArr = repairArr;
	}
	public List<String> getRepairPercentList() {
		return repairPercentList;
	}
	public void setRepairPercentList(List<String> repairPercentList) {
		this.repairPercentList = repairPercentList;
	}
}
