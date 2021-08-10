package com.peg.model.bph;

import java.util.Date;

import com.peg.model.baseCommonVo;

public class CheckIndex extends baseCommonVo{
	
	private Long id;
	
	private String factory;
	
	private String area;
	
	/**
	 * 班组类别
	 */
	private String shiftGroupCategory;
	
	private String checkItem;
	
	/**
	 * 指标名称
	 */
	private  String checkIndex;
	
	/**
	 * 比例
	 */
	private double scale;
	
	/**
	 * 是否关键指标
	 */
	private String mykey;
	
	private double baseValue;
	
	private double targetValue;
	
	private String createUser;
	
	private Date createTime;
	
	private String lastUpdateUser;
	
	private Date lastUpdateTime;
	
	/**
	 * 指标代码
	 */
	private String indexCode;
	
	/**
	 * 指标描述
	 */
	private String indexDescription;
	
	private String col1;  //备用字段1
	
	private String col2;  //备用字段2
	
	private String col3;  //备用字段3

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory==null ? null : factory.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area==null ? null : area.trim();
	}

	public String getShiftGroupCategory() {
		return shiftGroupCategory;
	}

	public void setShiftGroupCategory(String shiftGroupCategory) {
		this.shiftGroupCategory = shiftGroupCategory==null ? null : shiftGroupCategory.trim();
	}

	public String getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem==null ? null : checkItem.trim();
	}

	public String getCheckIndex() {
		return checkIndex;
	}

	public void setCheckIndex(String checkIndex) {
		this.checkIndex = checkIndex==null ? null : checkIndex.trim();
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	

	public String getMykey() {
		return mykey;
	}

	public void setMykey(String mykey) {
		this.mykey = mykey;
	}


	public double getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}

	public double getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(double targetValue) {
		this.targetValue = targetValue;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser==null ? null : createUser.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser==null ? null : lastUpdateUser.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public String getIndexDescription() {
		return indexDescription;
	}

	public void setIndexDescription(String indexDescription) {
		this.indexDescription = indexDescription;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}
	
	

}
