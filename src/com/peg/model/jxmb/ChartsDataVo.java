package com.peg.model.jxmb;

import java.util.List;

public class ChartsDataVo {
	/**
	 * 参数bean
	 */
	private String smallMonth;//月份
	private String bigMonth;//月份
	private String dape;//部门
	private String bigClass;//目标大类
	private String indexContent;//小类
	private String type;//类型
	private String yearEquaTo;//一个年份
	private String smallYear;//年份
	private String bigYear;//年份
	
	private String title;//头部
	//图形数据
	private String[] legendData;//
	private String[] xAxisData;//x轴
	private List<Double[]> list;
	private double[] seriesData;//值
	private double[] seriesData2;//值
	//table数据
	private String actualReach;//实际达成率
	private String totalReach;//累计达成率
	private String depaName;//部门
	private String monthTime;//时间
	
	public String getSmallMonth() {
		return smallMonth;
	}
	public void setSmallMonth(String smallMonth) {
		this.smallMonth = smallMonth;
	}
	public String getBigMonth() {
		return bigMonth;
	}
	public void setBigMonth(String bigMonth) {
		this.bigMonth = bigMonth;
	}
	public String getDape() {
		return dape;
	}
	public void setDape(String dape) {
		this.dape = dape;
	}
	public String getBigClass() {
		return bigClass;
	}
	public void setBigClass(String bigClass) {
		this.bigClass = bigClass;
	}
	public String getIndexContent() {
		return indexContent;
	}
	public void setIndexContent(String indexContent) {
		this.indexContent = indexContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getLegendData() {
		return legendData;
	}
	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}
	public String[] getxAxisData() {
		return xAxisData;
	}
	public void setxAxisData(String[] xAxisData) {
		this.xAxisData = xAxisData;
	}
	public double[] getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(double[] seriesData) {
		this.seriesData = seriesData;
	}
	public double[] getSeriesData2() {
		return seriesData2;
	}
	public void setSeriesData2(double[] seriesData2) {
		this.seriesData2 = seriesData2;
	}
	public List<Double[]> getList() {
		return list;
	}
	public void setList(List<Double[]> list) {
		this.list = list;
	}
	
	public String getYearEquaTo() {
		return yearEquaTo;
	}
	public void setYearEquaTo(String yearEquaTo) {
		this.yearEquaTo = yearEquaTo;
	}
	public String getSmallYear() {
		return smallYear;
	}
	public void setSmallYear(String smallYear) {
		this.smallYear = smallYear;
	}
	public String getBigYear() {
		return bigYear;
	}
	public void setBigYear(String bigYear) {
		this.bigYear = bigYear;
	}
	public String getActualReach() {
		return actualReach;
	}
	public void setActualReach(String actualReach) {
		this.actualReach = actualReach;
	}
	public String getTotalReach() {
		return totalReach;
	}
	public void setTotalReach(String totalReach) {
		this.totalReach = totalReach;
	}
	public String getDepaName() {
		return depaName;
	}
	public void setDepaName(String depaName) {
		this.depaName = depaName;
	}
	public String getMonthTime() {
		return monthTime;
	}
	public void setMonthTime(String monthTime) {
		this.monthTime = monthTime;
	}
	
}
