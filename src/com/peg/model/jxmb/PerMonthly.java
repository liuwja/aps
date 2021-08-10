package com.peg.model.jxmb;

import java.util.Date;

public class PerMonthly {
	
    private Long monthKey;

    private Long indexKey;

    private String accumulatedmonth;

    private String lastmonthactual;

    private String monthreality;

    private String targetvaluemonth;

    private String accumumonth;

    private String record;

    private Date recordtime;

    private Date lastupdatetime;

    private String lastupdateuser;

    private String monthvalue;

    private Long groupKey;
    
//    private List<PerMonthly> monList;
//      
//    public List<PerMonthly> getMonList() {
//		return monList;
//	}
//
//	public void setMonList(List<PerMonthly> monList) {
//		this.monList = monList;
//	}

	private PerSetup setup;
    
	private PerGroup group;
	
    public PerGroup getGroup() {
		return group;
	}

	public void setGroup(PerGroup group) {
		this.group = group;
	}

	public PerSetup getSetup() {
		return setup;
	}

	public void setSetup(PerSetup setup) {
		this.setup = setup;
	}

	public Long getMonthKey() {
        return monthKey;
    }

    public void setMonthKey(Long monthKey) {
        this.monthKey = monthKey;
    }

    public Long getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(Long indexKey) {
        this.indexKey = indexKey;
    }

    public String getAccumulatedmonth() {
        return accumulatedmonth;
    }

    public void setAccumulatedmonth(String accumulatedmonth) {
        this.accumulatedmonth = accumulatedmonth == null ? null : accumulatedmonth.trim();
    }

    public String getLastmonthactual() {
        return lastmonthactual;
    }

    public void setLastmonthactual(String lastmonthactual) {
        this.lastmonthactual = lastmonthactual == null ? null : lastmonthactual.trim();
    }

    public String getMonthreality() {
        return monthreality;
    }

    public void setMonthreality(String monthreality) {
        this.monthreality = monthreality == null ? null : monthreality.trim();
    }

    public String getTargetvaluemonth() {
        return targetvaluemonth;
    }

    public void setTargetvaluemonth(String targetvaluemonth) {
        this.targetvaluemonth = targetvaluemonth == null ? null : targetvaluemonth.trim();
    }

    public String getAccumumonth() {
        return accumumonth;
    }

    public void setAccumumonth(String accumumonth) {
        this.accumumonth = accumumonth == null ? null : accumumonth.trim();
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

    public String getMonthvalue() {
        return monthvalue;
    }

    public void setMonthvalue(String monthvalue) {
        this.monthvalue = monthvalue == null ? null : monthvalue.trim();
    }

    public Long getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(Long groupKey) {
        this.groupKey = groupKey;
    }
}