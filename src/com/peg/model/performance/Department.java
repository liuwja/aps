package com.peg.model.performance;

import java.util.Date;

/**
 * 部门类
 * @author xuanm
 *
 */
public class Department {
	private Long id; //主键
	private String factoryNumber;//工厂编号
	private String factoryName;//工厂名称
	private String departmentNumber;//部门编号
	private String departmentName;//部门名称
	private Date createTime;//创建时间
	private String createUser;//创建人
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id == null?null:id;
	}
	public String getFactoryNumber() {
		return factoryNumber;
	}
	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber == null?null:factoryNumber;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName == null?null:factoryName;
	}
	public String getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber == null?null:departmentNumber;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName == null?null:departmentName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime == null?null:createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null?null:createUser;
	}
}
