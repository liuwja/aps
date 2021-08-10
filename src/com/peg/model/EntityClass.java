package com.peg.model;

public class EntityClass {
	//不良现象排列图
	private String badname;//不良现象名称
	private String product_type_s;//不良型号
	public String getProduct_type_s() {
		return product_type_s;
	}
	public void setProduct_type_s(String product_type_s) {
		this.product_type_s = product_type_s;
	}
	private int badnumber;//不良数
	private int countnumber;//总数
	
	//组装/喷涂退次时序图
	private String month;//月份 2016-05
	private String basevalue;//基准值
	private String targetvalue;//目标值
	private String indexscore;//指标得分
	
	//对比功能的时间序列图
	private String indexactvalue;
	private String sumdate;
	private String shiftgroupname;
	
	public String getIndexscore() {
		return indexscore;
	}
	public void setIndexscore(String indexscore) {
		this.indexscore = indexscore;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getBasevalue() {
		return basevalue;
	}
	public void setBasevalue(String basevalue) {
		this.basevalue = basevalue;
	}
	public String getTargetvalue() {
		return targetvalue;
	}
	public void setTargetvalue(String targetvalue) {
		this.targetvalue = targetvalue;
	}
	public String getBadname() {
		return badname;
	}
	public void setBadname(String badname) {
		this.badname = badname;
	}
	public int getBadnumber() {
		return badnumber;
	}
	public void setBadnumber(int badnumber) {
		this.badnumber = badnumber;
	}
	public int getCountnumber() {
		return countnumber;
	}
	public void setCountnumber(int countnumber) {
		this.countnumber = countnumber;
	}
	public String getIndexactvalue() {
		return indexactvalue;
	}
	public void setIndexactvalue(String indexactvalue) {
		this.indexactvalue = indexactvalue;
	}
	public String getSumdate() {
		return sumdate;
	}
	public void setSumdate(String sumdate) {
		this.sumdate = sumdate;
	}
	public String getShiftgroupname() {
		return shiftgroupname;
	}
	public void setShiftgroupname(String shiftgroupname) {
		this.shiftgroupname = shiftgroupname;
	}
	
	
}
