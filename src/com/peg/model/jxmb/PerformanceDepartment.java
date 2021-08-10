package com.peg.model.jxmb;

import java.util.Date;

/**
 * 绩效管理部门类
 * @author xuanm
 *
 */
public class PerformanceDepartment {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 工厂编号
	 */
    private String factoryNumber;

    /**
     * 工厂名称
     */
    private String factoryName;

    /**
     * 部门编号
     */
    private String departmentNumber;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 创建人
     */
    private String creationName;

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

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime == null?null:creationTime;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName == null?null:creationName;
	}
}
