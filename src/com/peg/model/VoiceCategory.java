package com.peg.model;

import java.util.Date;

/**
 * 客户之声一二级分类
 * @author by_jzp
 *
 */
public class VoiceCategory {
	
	private Integer number;//分类编号
	private String name;//分类名称
	private Integer paterNumber;//父类编号
	private Date insertTime; //插入时间
	private Date updateTime; //修改时间
	private Integer state;//状态码
	
	private String paterName;//父类名称
	private String groupName;//
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPaterName() {
		return paterName;
	}
	public void setPaterName(String paterName) {
		this.paterName = paterName;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPaterNumber() {
		return paterNumber;
	}
	public void setPaterNumber(Integer paterNumber) {
		this.paterNumber = paterNumber;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

}
