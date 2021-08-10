package com.peg.model.jxmb;

import java.util.Date;
import java.util.List;

public class PerSetup {
	/**
	 * 月度设定
	 * @author Administrator
	 *
	 */
    private Long indexKey;

    private Long itemKey;

    private String targetvalue;//目标值

    private String basevalue;//基准值

    private String reasonsmodification;

    private String mainkey;

    private String record;//记录人

    private Date recordtime;//记录时间

    private Date lastupdatetime;//修改时间

    private String lastupdateuser;//修改人

    private String formula;//公式
    
    private String month;//月份
    
    private PerItem items;
    
    private List<PerMonth>uigroupCategory;
    
   
    public List<PerMonth> getUigroupCategory() {
		return uigroupCategory;
	}

	public void setUigroupCategory(List<PerMonth> uigroupCategory) {
		this.uigroupCategory = uigroupCategory;
	}
	//private List<PerMonthly>monthAssessments;
    
//	public List<PerMonthly> getMonthAssessments() {
//		return monthAssessments;
//	}
//
//	public void setMonthAssessments(List<PerMonthly> monthAssessments) {
//		this.monthAssessments = monthAssessments;
//	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

    
    public PerItem getItems() {
		return items;
	}

	public void setItems(PerItem items) {
		this.items = items;
	}

	public Long getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(Long indexKey) {
        this.indexKey = indexKey;
    }

    public Long getItemKey() {
        return itemKey;
    }

    public void setItemKey(Long itemKey) {
        this.itemKey = itemKey;
    }

    public String getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(String targetvalue) {
        this.targetvalue = targetvalue == null ? null : targetvalue.trim();
    }

    public String getBasevalue() {
		return basevalue;
	}

	public void setBasevalue(String basevalue) {
		this.basevalue = basevalue;
	}

	public String getReasonsmodification() {
        return reasonsmodification;
    }

    public void setReasonsmodification(String reasonsmodification) {
        this.reasonsmodification = reasonsmodification == null ? null : reasonsmodification.trim();
    }

    public String getMainkey() {
        return mainkey;
    }

    public void setMainkey(String mainkey) {
        this.mainkey = mainkey == null ? null : mainkey.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }
 // 获取月度数量
    public int getMonthNum(){
    	if(uigroupCategory !=null){
    		return uigroupCategory.size();
    	}
    	return 0;
    }
}