package com.peg.mes.highcharts;

import java.util.List;

/**
 * chart对象
 * @author song
 *
 */
public class ChartObj {

	private String chartType;//图表类型
	private int chartWidth;//宽
	private int chartHight;//高
	private String title;//图标名称
	private String xTitle;//横坐标名称
	private String subtitle;//副标题
	private String yLeftTitle;//纵坐标左标题
	private String yRightTitle;//纵坐标右标题
	private String yLeftUnit;//获取焦点时自定义提示单位左单位
	private String yRightUnit;//获取焦点时自定义提示单位右单位
	private String tipUnit;//获取焦点时自定义提示单位
	private Double defaultValue;//虚线默认值
	List<String> xValue;//x轴的值
	List<Long> yValue;//y轴的值
	String[] seriesNames;//对应系列的名称
	List<List<Double>> yValues;//对应系列的list值
	List<String> tipValues;//获取焦点时自定义提示值
	List<String> tipValues1;//获取焦点时自定义提示值
	List<String> tipValues2;//获取焦点时自定义提示值
	private String tipText;//获取焦点时提示文本
	private String tipText1;//获取焦点时提示文本
	private String tipText2;//获取焦点时提示文本
	private String highCtrLine;//上控制线
	private String lowCtrLine;//下控制线
	private String functionName;
	
	public List<Long> getyValue() {
		return yValue;
	}
	public void setyValue(List<Long> yValue) {
		this.yValue = yValue;
	}
	public String getHighCtrLine() {
		return highCtrLine;
	}
	public void setHighCtrLine(String highCtrLine) {
		this.highCtrLine = highCtrLine;
	}
	public String getLowCtrLine() {
		return lowCtrLine;
	}
	public void setLowCtrLine(String lowCtrLine) {
		this.lowCtrLine = lowCtrLine;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getxTitle() {
		return xTitle;
	}
	public void setxTitle(String xTitle) {
		this.xTitle = xTitle;
	}
	public String getyLeftTitle() {
		return yLeftTitle;
	}
	public void setyLeftTitle(String yLeftTitle) {
		this.yLeftTitle = yLeftTitle;
	}
	public String getyRightTitle() {
		return yRightTitle;
	}
	public void setyRightTitle(String yRightTitle) {
		this.yRightTitle = yRightTitle;
	}
	public String getyLeftUnit() {
		return yLeftUnit;
	}
	public void setyLeftUnit(String yLeftUnit) {
		this.yLeftUnit = yLeftUnit;
	}
	public String getyRightUnit() {
		return yRightUnit;
	}
	public void setyRightUnit(String yRightUnit) {
		this.yRightUnit = yRightUnit;
	}
	public Double getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Double defaultValue) {
		this.defaultValue = defaultValue;
	}
	public List<String> getxValue() {
		return xValue;
	}
	public void setxValue(List<String> xValue) {
		this.xValue = xValue;
	}
	public String[] getSeriesNames() {
		return seriesNames;
	}
	public void setSeriesNames(String[] seriesNames) {
		this.seriesNames = seriesNames;
	}
	public List<List<Double>> getyValues() {
		return yValues;
	}
	public void setyValues(List<List<Double>> yValues) {
		this.yValues = yValues;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getTipUnit() {
		return tipUnit;
	}
	public void setTipUnit(String tipUnit) {
		this.tipUnit = tipUnit;
	}
	public List<String> getTipValues() {
		return tipValues;
	}
	public void setTipValues(List<String> tipValues) {
		this.tipValues = tipValues;
	}
	public String getTipText() {
		return tipText;
	}
	public void setTipText(String tipText) {
		this.tipText = tipText;
	}
	public List<String> getTipValues1() {
		return tipValues1;
	}
	public void setTipValues1(List<String> tipValues1) {
		this.tipValues1 = tipValues1;
	}
	public String getTipText1() {
		return tipText1;
	}
	public void setTipText1(String tipText1) {
		this.tipText1 = tipText1;
	}
	public List<String> getTipValues2() {
		return tipValues2;
	}
	public void setTipValues2(List<String> tipValues2) {
		this.tipValues2 = tipValues2;
	}
	public String getTipText2() {
		return tipText2;
	}
	public void setTipText2(String tipText2) {
		this.tipText2 = tipText2;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
