package com.peg.model.jxmb;

import java.util.Date;
import java.util.List;

/**
 * 月份基准目标设定
 * @author
 *
 */
public class PerMonth {
	
    private Long monthKey;

    private Long indexKey;

    private String accumulatedmonth;//上月累计目标值

    private String lastmonthactual;//上月累计实际值

    private String  monthreality;//当月实际值

    private String targetvaluemonth;//当月累计目标值

    private String accumumonth ;//当月累计实际值

    private String record;

    private Date recordtime;//记录时间

    private Date lastupdatetime;//修改时间

    private String lastupdateuser;

    private String monthvalue;//月份值
    
  //  private List<PerGroup> uigroupCategory;
    
    
//   private PerGroup uigroupCategory;
//   
//	public PerGroup getUigroupCategory() {
//		return uigroupCategory;
//	}

//	public void setUigroupCategory(PerGroup uigroupCategory) {
//		this.uigroupCategory = uigroupCategory;
//}

	private List<PerMonth> monList;
    
    private PerSetup setup;
      
    public PerSetup getSetup() {
		return setup;
	}

	public void setSetup(PerSetup setup) {
		this.setup = setup;
	}

	private PerMember group;
    
  
	public PerMember getGroup() {
		return group;
	}

	public void setGroup(PerMember group) {
		this.group = group;
	}

	private Long groupKey;
    
    private String queryMonth;
        
 
	public List<PerMonth> getMonList() {
		return monList;
	}

	public void setMonList(List<PerMonth> monList) {
		this.monList = monList;
	}

	public Long getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(Long groupKey) {
		this.groupKey = groupKey;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public List<PerMonth> getPermonth() {
		return monList;
	}

	public void setPermonth(List<PerMonth> monList) {
		this.monList = monList;
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
		this.monthreality = monthreality;
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
		this.accumumonth = accumumonth;
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
}