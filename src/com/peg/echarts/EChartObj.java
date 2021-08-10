package com.peg.echarts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.peg.model.part.OnlineModel;

/**
 * chart对象
 * @author song
 *
 */
public class EChartObj {

	private String chartType;//图表类型
	private int chartWidth;//宽
	private int chartHight;//高
	private String title;//图标名称
	private String xTitle;//横坐标名称
	private String yLeftTitle;//纵坐标左标题
	private String yRightTitle;//纵坐标右标题
	private String yLeftUnit;//左单位
	private String yRightUnit;//右单位
	private Double defaultValue;//虚线默认值
	private List<String> xValue;//x轴的值
	private String[] seriesNames;//对应系列的名称
	private List<List<Double>> yValues;//对应系列的list值
	private String[] stack ;   //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	private List<EchartYAxis> yAxiss; //y轴
	private String[] yAxisIndex; //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	private String[] colors;     //颜色配置
	private String subtext;      //副标题  
	
	private List<String> value;//隐藏值
	/**
	 * 联动图配置
	 */
	List<EchartChild> childList;   //联动
	private String childSeriesName;  //联动图的系列名
	private String barWidth;       //柱状图柱子的宽
	
	
	
	//legend 图例组件。
	private String axisPointerType; //坐标轴指示器，坐标轴触发有效 ,选项有 'line' | 'shadow'
	
	//tooltip
	private String tooltipTrigger; //触发类型。'item'|'axis','item' 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。,'axis' 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
	
	//toolbox工具配置项
	private boolean toolboxShow; //工具配置项。 'true' | 'false' 默认'false'
	private boolean toolboxFeatureSaveAsImageShow; //工具配置项-保存为图片。 'true' | 'false'  默认'false'
	private boolean toolboxFeatureRestoreShow; //工具配置项-配置项还原。 'true' | 'false'      默认'false'
	private boolean toolboxFeatureDataViewShow; //工具配置项-数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。'true' | 'false'  默认'true'
	private boolean toolboxFeatureMagicTypeShow; //工具配置项-动态类型切换 'true' | 'false'。       默认'false'
	private String[] toolboxFeatureMagicTypeType; //工具配置项-动态类型切换 示例：['line', 'bar', 'stack', 'tiled']。
	
	//xAxis 直角坐标系 grid 中的 x 轴
	private String xAxisType; //坐标轴类型。'value'|'category'|'time'|'log', 'value' 数值轴，适用于连续数据。'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
	                          //'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。'log' 对数轴。适用于对数数据。
	
	//yAxis 直角坐标系 grid 中的 y 轴
	private String yAxisType; //坐标轴类型。'value'|'category'|'time'|'log', 'value' 数值轴，适用于连续数据。'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
                              //'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。'log' 对数轴。适用于对数数据。
	private String yAxisAxisLabelFormatter;//刻度标签的内容格式器，支持字符串模板和回调函数两种形式。'{value} kg'
	
	//series 系列列表。
	private String[] seriesType; //类型 'line'|'bar'|'pie'
	private String seriesMarkPointDataType;//图表标注。特殊的标注类型，用于标注最大值最小值等。'min' 最大值。'max' 最大值。'average' 平均值。
	private String seriesMarkPointDataName;//图表标注名称
	private String seriesMarkLineDataType; //图表标线。起点的数据。'min' 最大值。'max' 最大值。'average' 平均值。
	private String seriesMarkLineDataName;//图表标线名称
	
	private Map<String,Long> map = new HashMap<String, Long>();  //用于存数据
	
	private OnlineModel online ;       //在线对象
	
	private List<OnlineModel> onlineList ;//在线list
	
	private Map<String,String> numberMap = new HashMap<String, String>();  //编号map
	
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
	public String getAxisPointerType() {
		return axisPointerType;
	}
	public void setAxisPointerType(String axisPointerType) {
		this.axisPointerType = axisPointerType;
	}
	public String getTooltipTrigger() {
		return tooltipTrigger;
	}
	public void setTooltipTrigger(String tooltipTrigger) {
		this.tooltipTrigger = tooltipTrigger;
	}
	
	public boolean isToolboxShow() {
		return toolboxShow;
	}
	public void setToolboxShow(boolean toolboxShow) {
		this.toolboxShow = toolboxShow;
	}
	public boolean isToolboxFeatureSaveAsImageShow() {
		return toolboxFeatureSaveAsImageShow;
	}
	public void setToolboxFeatureSaveAsImageShow(
			boolean toolboxFeatureSaveAsImageShow) {
		this.toolboxFeatureSaveAsImageShow = toolboxFeatureSaveAsImageShow;
	}
	public boolean isToolboxFeatureRestoreShow() {
		return toolboxFeatureRestoreShow;
	}
	public void setToolboxFeatureRestoreShow(boolean toolboxFeatureRestoreShow) {
		this.toolboxFeatureRestoreShow = toolboxFeatureRestoreShow;
	}
	public boolean isToolboxFeatureDataViewShow() {
		return toolboxFeatureDataViewShow;
	}
	public void setToolboxFeatureDataViewShow(boolean toolboxFeatureDataViewShow) {
		this.toolboxFeatureDataViewShow = toolboxFeatureDataViewShow;
	}
	public boolean isToolboxFeatureMagicTypeShow() {
		return toolboxFeatureMagicTypeShow;
	}
	public void setToolboxFeatureMagicTypeShow(boolean toolboxFeatureMagicTypeShow) {
		this.toolboxFeatureMagicTypeShow = toolboxFeatureMagicTypeShow;
	}
	public String[] getToolboxFeatureMagicTypeType() {
		return toolboxFeatureMagicTypeType;
	}
	public void setToolboxFeatureMagicTypeType(String[] toolboxFeatureMagicTypeType) {
		this.toolboxFeatureMagicTypeType = toolboxFeatureMagicTypeType;
	}
	public String getxAxisType() {
		return xAxisType;
	}
	public void setxAxisType(String xAxisType) {
		this.xAxisType = xAxisType;
	}
	public String getyAxisType() {
		return yAxisType;
	}
	public void setyAxisType(String yAxisType) {
		this.yAxisType = yAxisType;
	}
	public String getyAxisAxisLabelFormatter() {
		return yAxisAxisLabelFormatter;
	}
	public void setyAxisAxisLabelFormatter(String yAxisAxisLabelFormatter) {
		this.yAxisAxisLabelFormatter = yAxisAxisLabelFormatter;
	}
	
	public String[] getSeriesType() {
		return seriesType;
	}
	public void setSeriesType(String[] seriesType) {
		this.seriesType = seriesType;
	}
	public String getSeriesMarkPointDataType() {
		return seriesMarkPointDataType;
	}
	public void setSeriesMarkPointDataType(String seriesMarkPointDataType) {
		this.seriesMarkPointDataType = seriesMarkPointDataType;
	}
	public String getSeriesMarkPointDataName() {
		return seriesMarkPointDataName;
	}
	public void setSeriesMarkPointDataName(String seriesMarkPointDataName) {
		this.seriesMarkPointDataName = seriesMarkPointDataName;
	}
	public String getSeriesMarkLineDataType() {
		return seriesMarkLineDataType;
	}
	public void setSeriesMarkLineDataType(String seriesMarkLineDataType) {
		this.seriesMarkLineDataType = seriesMarkLineDataType;
	}
	public String getSeriesMarkLineDataName() {
		return seriesMarkLineDataName;
	}
	public void setSeriesMarkLineDataName(String seriesMarkLineDataName) {
		this.seriesMarkLineDataName = seriesMarkLineDataName;
	}
	public List<EchartChild> getChildList() {
		return childList;
	}
	public void setChildList(List<EchartChild> childList) {
		this.childList = childList;
	}
	public String getChildSeriesName() {
		return childSeriesName;
	}
	public void setChildSeriesName(String childSeriesName) {
		this.childSeriesName = childSeriesName;
	}
	public String getBarWidth() {
		return barWidth;
	}
	public void setBarWidth(String barWidth) {
		this.barWidth = barWidth;
	}
	public String[] getStack() {
		return stack;
	}
	public void setStack(String[] stack) {
		this.stack = stack;
	}
	public List<EchartYAxis> getyAxiss() {
		return yAxiss;
	}
	public void setyAxiss(List<EchartYAxis> yAxiss) {
		this.yAxiss = yAxiss;
	}
	public String[] getyAxisIndex() {
		return yAxisIndex;
	}
	public void setyAxisIndex(String[] yAxisIndex) {
		this.yAxisIndex = yAxisIndex;
	}
	public String[] getColors() {
		return colors;
	}
	public void setColors(String[] colors) {
		this.colors = colors;
	}
	public String getSubtext() {
		return subtext;
	}
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	public Map<String, Long> getMap() {
		return map;
	}
	public void setMap(Map<String, Long> map) {
		this.map = map;
	}
	public OnlineModel getOnline() {
		return online;
	}
	public void setOnline(OnlineModel online) {
		this.online = online;
	}
	public List<OnlineModel> getOnlineList() {
		return onlineList;
	}
	public void setOnlineList(List<OnlineModel> onlineList) {
		this.onlineList = onlineList;
	}
	public Map<String, String> getNumberMap() {
		return numberMap;
	}
	public void setNumberMap(Map<String, String> numberMap) {
		this.numberMap = numberMap;
	}
	
}
