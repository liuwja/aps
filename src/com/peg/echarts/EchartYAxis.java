package com.peg.echarts;
/**
 * Echarts y轴配置
 * @author Administrator
 *
 */
public class EchartYAxis {

	private String type;      //坐标轴类型。
	private String name;      //坐标轴名称。
	private EchartLable axisLabel;  //刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EchartLable getAxisLabel() {
		return axisLabel;
	}
	public void setAxisLabel(EchartLable axisLabel) {
		this.axisLabel = axisLabel;
	}
	
	
	
}
