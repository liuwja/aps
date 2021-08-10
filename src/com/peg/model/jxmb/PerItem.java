package com.peg.model.jxmb;

import java.util.Date;
import java.util.List;

/**
 * 考核项目类
 * @author Administrator
 *
 */
public class PerItem {
    private Long itemKey;

    private Long id;

    private String upperactualvalue;//上年实际值

    private String upperhalftargetvalue;//上半年

    private String secondhalftargetvalue;//下半年

    private String yeartargetvalue;//本年目标值

    private String median;//中间值

    private String referencevalue;//基准值

    private String maximumvalue;//最大目标值

    private String targetvalue;//

    private Date lastupdatetime;//修改时间

    private String lastupdateuser;//修改人

    private Date recordtime;//记录时间

    private String record;//记录人

    private String formula;//公式
    
    private List<PerSetup> uiindexs;
    
    private PerGroup perGroup;
    
    private String month2;//月份
    
    private String values;
   
         
    public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}
    
	public List<PerSetup> getUiindexs() {
		return uiindexs;
	}

	public void setUiindexs(List<PerSetup> uiindexs) {
		this.uiindexs = uiindexs;
	}

	public PerGroup getPerGroup() {
		return perGroup;
	}

	public void setPerGroup(PerGroup perGroup) {
		this.perGroup = perGroup;
	}

	public Long getItemKey() {
        return itemKey;
    }

    public void setItemKey(Long itemKey) {
        this.itemKey = itemKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpperactualvalue() {
        return upperactualvalue;
    }

    public void setUpperactualvalue(String upperactualvalue) {
        this.upperactualvalue = upperactualvalue == null ? null : upperactualvalue.trim();
    }

    public String getUpperhalftargetvalue() {
        return upperhalftargetvalue;
    }

    public void setUpperhalftargetvalue(String upperhalftargetvalue) {
        this.upperhalftargetvalue = upperhalftargetvalue == null ? null : upperhalftargetvalue.trim();
    }

    public String getSecondhalftargetvalue() {
        return secondhalftargetvalue;
    }

    public void setSecondhalftargetvalue(String secondhalftargetvalue) {
        this.secondhalftargetvalue = secondhalftargetvalue == null ? null : secondhalftargetvalue.trim();
    }

    public String getYeartargetvalue() {
        return yeartargetvalue;
    }

    public void setYeartargetvalue(String yeartargetvalue) {
        this.yeartargetvalue = yeartargetvalue == null ? null : yeartargetvalue.trim();
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median == null ? null : median.trim();
    }

    public String getReferencevalue() {
        return referencevalue;
    }

    public void setReferencevalue(String referencevalue) {
        this.referencevalue = referencevalue == null ? null : referencevalue.trim();
    }

    public String getMaximumvalue() {
        return maximumvalue;
    }

    public void setMaximumvalue(String maximumvalue) {
        this.maximumvalue = maximumvalue == null ? null : maximumvalue.trim();
    }

    public String getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(String targetvalue) {
        this.targetvalue = targetvalue == null ? null : targetvalue.trim();
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser == null ? null : lastupdateuser.trim();
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }
    //获取考核指标数量
    public int getIndexNum(){
    	if(uiindexs != null){
    		return uiindexs.size();
    	}
    	return 0;
    }
  //获取index数量
  	public int getMonthNum()
  	{
  		int len = 0;
  		if(uiindexs != null)
  		{
  			for(PerSetup rs : uiindexs){
  				if(rs.getMonthNum()!=0){
  					len =len+rs.getMonthNum()-1;
  				}
  			}
  			len = len + uiindexs.size();
  		}
  		return len;
  	}
}