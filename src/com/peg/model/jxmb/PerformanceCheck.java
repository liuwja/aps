package com.peg.model.jxmb;

import java.util.Date;
import java.util.List;

/**
 * 类别
 * @author 
 *
 */
public class PerformanceCheck {
    private Long id;

    private Date chekyear;//年度
    
    private String year;

    private String department;//部门
    
    private String departmentNumber;//部门编号

    private String targetclass;//绩效目标大类

    private String indexcontent;//指标内容

    private String performancecontent;//绩效类型

    private String weight;//权重

    private String assessmentmethod;

    private Date recordtime;//记录时间

    private String record;//记录人

    private String company;

    private String targetvalue;//目标值

    private String referencevalue;//基准值

    private String median;//中间值

    private String formula;//公式
    
    private String factoryNumber; //工厂编号
    
    private String factoryName; //工厂名称
    
    private String updateReason;//修改原因
    
    private PerDeparment perdeparment;
    
    private List<PerformanceCheck> monList;
    
 
	public List<PerformanceCheck> getMonList() {
		return monList;
	}

	public void setMonList(List<PerformanceCheck> monList) {
		this.monList = monList;
	}

	public PerDeparment getPerdeparment() {
		return perdeparment;
	}

	public void setPerdeparment(PerDeparment perdeparment) {
		this.perdeparment = perdeparment;
	}

	private List<PerItem>item;
    

	public List<PerItem> getItem() {
		return item;
	}

	public void setItem(List<PerItem> item) {
		this.item = item;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getChekyear() {
        return chekyear;
    }

    public void setChekyear(Date chekyear) {
        this.chekyear = chekyear;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getTargetclass() {
        return targetclass;
    }

    public void setTargetclass(String targetclass) {
        this.targetclass = targetclass == null ? null : targetclass.trim();
    }

    public String getIndexcontent() {
        return indexcontent;
    }

    public void setIndexcontent(String indexcontent) {
        this.indexcontent = indexcontent == null ? null : indexcontent.trim();
    }

    public String getPerformancecontent() {
        return performancecontent;
    }

    public void setPerformancecontent(String performancecontent) {
        this.performancecontent = performancecontent == null ? null : performancecontent.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getAssessmentmethod() {
        return assessmentmethod;
    }

    public void setAssessmentmethod(String assessmentmethod) {
        this.assessmentmethod = assessmentmethod == null ? null : assessmentmethod.trim();
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(String targetvalue) {
        this.targetvalue = targetvalue == null ? null : targetvalue.trim();
    }

    public String getReferencevalue() {
        return referencevalue;
    }

    public void setReferencevalue(String referencevalue) {
        this.referencevalue = referencevalue == null ? null : referencevalue.trim();
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median == null ? null : median.trim();
    }

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula == null ? null : formula.trim();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFactoryNumber() {
		return factoryNumber;
	}

	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}

	public String getFactoryName() {
		return factoryName;
	}
	
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason == null?null:updateReason;
	}
}