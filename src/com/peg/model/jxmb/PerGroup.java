package com.peg.model.jxmb;

import java.util.Date;
import java.util.List;


/**
 * 分组类别
 * @author Administrator
 *
 */
public class PerGroup {
	

    private Long id;

    private Date chekyear;

    private String department;

    private String targetclass;

    private String indexcontent;

    private String performancecontent;

    private String weight;

    private String assessmentmethod;

    private Date recordtime;

    private String record;

    private String company;

    private String targetvalue;

    private String referencevalue;

    private String median;

    private String formula;
    
    private String year;
    
    private List<PerItem> item;
    
    private long itemKey;
    
    private String factoryNumber;
    
    private String factoryName;
    
    private String departmentName;
    
//    private  PerMonth uigroup;
//	
//	public PerMonth getUigroup() {
//		return uigroup;
//	}
//
//	public void setUigroup(PerMonth uigroup) {
//		this.uigroup = uigroup;
//	}

//    private List<PerMonth>uigroup;
//    
//    
//	public List<PerMonth> getUigroup() {
//		return uigroup;
//	}
//
//	public void setUigroup(List<PerMonth> uigroup) {
//		this.uigroup = uigroup;
//	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
  

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
    
	//获取index数量
	public int getIndexNum()
	{
		int len = 0;
		if(item != null)
		{
			for(PerItem rs : item)
			{
				if(rs.getIndexNum()!= 0){
					len = len + rs.getIndexNum()-1;
				}			
			}
			len = len + item.size();
		}
		return len;
	}
	
	//获取index数量
	public int getMonthNum()
	{
		int len = 0;
		if(item != null)
		{
			for(PerItem rs : item)
			{
				if(rs.getMonthNum()!= 0){
					len = len + rs.getMonthNum()-1;
				}			
			}
			len = len + item.size();
		}
		return len;
	}

	public long getItemKey() {
		return itemKey;
	}

	public void setItemKey(long itemKey) {
		this.itemKey = itemKey;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}