package com.peg.mes.highcharts;

import java.util.List;

import com.peg.web.util.TwoGen;

/**
 * @version V1.0
 * @Description: 公共报表信息
 */
@Deprecated
public class PublicChartsInfo {
	/**
	 * highcharts在页面上显示的宽度
	 */
	private int chartWidth;
	/**
	 * highcharts在页面上显示的高度
	 */
	private int chartHight;
	/**
	 * highcharts名称
	 */
	private String chartText;
	/**
	 * 横坐标名称
	 */
	private String xText;
	/**
	 * 纵坐标名称
	 */
	private String yText_left; //左边Y轴
	private String yText_right;//右边Y轴
	/**
	 * 纵坐标显示单位格式
	 */
	private String yFormat_left; //左边Y轴
	private String yFormat_right;//右边Y轴
	/**
	 * 系列名称：
	 */
	private String seriesName[];
	/**
	 * 数据
	 */
	private List<ChartData> chartDatas;
	
	private List<yseries> yseries;
	
	private TwoGen<List<ChartData>, List<yseries>> TwoGenlist;
	/**
	 * 类别
	 * 如果是单独一种图形，只传chartType就可以了，另外两个传空即可
	 * 如果是折线柱状图的或者其他2种图形的，就需要3个都需要传，chartType1和chartType2是series中的type值,chartType是charts中的type值
	 * 
	 */
	private String chartType; 
	private String chartType1; 
	private String chartType2;


	public List<yseries> getYseries() {
		return yseries;
	}

	public void setYseries(List<yseries> yseries) {
		this.yseries = yseries;
	}

	public TwoGen<List<ChartData>, List<yseries>> getTwoGenlist() {
		return TwoGenlist;
	}

	public void setTwoGenlist(TwoGen<List<ChartData>, List<yseries>> twoGenlist) {
		TwoGenlist = twoGenlist;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public int getChartWidth() {
		return chartWidth;
	}

	public void setChartWidth(int chartWidth) {
		this.chartWidth = chartWidth;
	}

	public int getChartHight() {
		return chartHight;
	}

	public void setChartHight(int chartHight) {
		this.chartHight = chartHight;
	}

	public String getChartText() {
		return chartText;
	}

	public void setChartText(String chartText) {
		this.chartText = chartText;
	}

	public String getxText() {
		return xText;
	}

	public void setxText(String xText) {
		this.xText = xText;
	}



	public String getyText_left() {
		return yText_left;
	}

	public void setyText_left(String yTextLeft) {
		yText_left = yTextLeft;
	}

	public String getyText_right() {
		return yText_right;
	}

	public void setyText_right(String yTextRight) {
		yText_right = yTextRight;
	}

	public String getyFormat_left() {
		return yFormat_left;
	}

	public void setyFormat_left(String yFormatLeft) {
		yFormat_left = yFormatLeft;
	}

	public String getyFormat_right() {
		return yFormat_right;
	}

	public void setyFormat_right(String yFormatRight) {
		yFormat_right = yFormatRight;
	}

	public String[] getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String[] seriesName) {
		this.seriesName = seriesName;
	}

	public String getChartType1() {
		return chartType1;
	}

	public void setChartType1(String chartType1) {
		this.chartType1 = chartType1;
	}

	public String getChartType2() {
		return chartType2;
	}

	public void setChartType2(String chartType2) {
		this.chartType2 = chartType2;
	}

	public List<ChartData> getChartDatas() {
		return chartDatas;
	}

	public void setChartDatas(List<ChartData> list) {
		this.chartDatas = list;
	}
	


}

