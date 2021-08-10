package com.peg.model.jxmb;

import java.util.Date;
import java.util.List;

public class PerDeparment {
    private Long id;

    private String factoryNumber;

    private String factoryName;

    private String departmentNumber;

    private String departmentName;

    private Date creationTime;

    private String creationName;

    private List<PerformanceCheck> dtpartment;
       
    public List<PerformanceCheck> getDtpartment() {
		return dtpartment;
	}

	public void setDtpartment(List<PerformanceCheck> dtpartment) {
		this.dtpartment = dtpartment;
	}


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber == null ? null : factoryNumber.trim();
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber == null ? null : departmentNumber.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName == null ? null : creationName.trim();
    }

	@Override
	public String toString() {
		return "PerDeparment [id=" + id + ", factoryNumber=" + factoryNumber + ", factoryName=" + factoryName
				+ ", departmentNumber=" + departmentNumber + ", departmentName=" + departmentName + ", creationTime="
				+ creationTime + ", creationName=" + creationName + "]";
	}
}