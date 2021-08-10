package com.peg.model.bph;

import java.util.Date;

import com.peg.model.baseCommonVo;

public class CheckItem extends baseCommonVo{ 
	
	private Long id;  //id
	
	private String factory; //工厂
	
	private String shiftGroupCategory; //班组类别
	
	private String item; //考核项目
	
	private String itemCode; //项目代码
	
	private double scale; //分数
	
	private String createUser;//创建人
	
	private Date createTime;//创建时间
	
	private String lastUpdateUser;//更新人
	
	private Date lastUpdateTime;//更新时间



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

	public String getShiftGroupCategory() {
		return shiftGroupCategory;
	}

	public void setShiftGroupCategory(String shiftGroupCategory) {
		this.shiftGroupCategory = shiftGroupCategory==null ? null : shiftGroupCategory.trim();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item==null ? null : item.trim();
	}
	
	

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
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

	

}
