package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.peg.model.baseCommonVo;

public class AssemblyProductBack extends baseCommonVo{

	private Date startTime;  //开始时间
	
	private Date endTime;     //结束时间
	
    private BigDecimal atrKey;

    private Long siteNum;     

    private String atrName;

    private Long purgeStatus;

    private Date creationTime;   

    private Date creationTimeU;

    private String creationTimeZ;

    private Date lastModifiedTime;

    private Date lastModifiedTimeU;

    private String lastModifiedTimeZ;

    private Long xfrInsertPid;

    private Long xfrUpdatePid;

    private String trxId;

    private BigDecimal parentKey;

    private String checkManS;       //检查人

    private String defectS;         //不良现象

    private BigDecimal defectQtyI;   //不良数量

    private String dutyGroup1S;      //责任班组1

    private String dutyGroup2S;      //责任班组2

    private String dutyGroup3S;      //责任班组3

    private String itemNameS;        //零部件名称

    private String itemNumberS;      //零部件编号

    private String productTypeS;     //机型类别

    private String groupS;           //发生班组

    private String recordManS;       //记录人

    private String areaS;            //车间

    private String factoryS;         //工厂

    private String dutyS;            //责任判定

    private Date dateT;             //发生日期

    private Date dateU;

    private String dateZ;

    private String lineS;           //产品线
    
    private String group;    
    
    private String orderNumbers;     //工单编号
    
    private String checkStep;        //检查工序
    
    private String defectSource;     //不良来源
    
    private String groupLeader;      //班组长
     
    private String checkResult;      //检查结果
    
    private String repariredMan;    //维修人
    
    private String repairedMethod;  //维修方式
    
    private BigDecimal totalQtyI;   //总数
    
    private String facS;
    private String shifS;
    private String timS;
    
    private String marking;//标示，ljy添加（图标标示）
    private String type_s;//标示，ljy添加（图标标示）
    private Integer startnum;
    private Integer endnum;
    private String partTypeListTxt;
    private List<String> list;
    private String bad_Reason;//不良原因
    

	public String getAtrName() {
        return atrName;
    }

    public void setAtrName(String atrName) {
        this.atrName = atrName == null ? null : atrName.trim();
    }

    public Long getPurgeStatus() {
        return purgeStatus;
    }

    public void setPurgeStatus(Long purgeStatus) {
        this.purgeStatus = purgeStatus;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationTimeU() {
        return creationTimeU;
    }

    public void setCreationTimeU(Date creationTimeU) {
        this.creationTimeU = creationTimeU;
    }

    public String getCreationTimeZ() {
        return creationTimeZ;
    }

    public void setCreationTimeZ(String creationTimeZ) {
        this.creationTimeZ = creationTimeZ == null ? null : creationTimeZ.trim();
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Date getLastModifiedTimeU() {
        return lastModifiedTimeU;
    }

    public void setLastModifiedTimeU(Date lastModifiedTimeU) {
        this.lastModifiedTimeU = lastModifiedTimeU;
    }

    public String getLastModifiedTimeZ() {
        return lastModifiedTimeZ;
    }

    public void setLastModifiedTimeZ(String lastModifiedTimeZ) {
        this.lastModifiedTimeZ = lastModifiedTimeZ == null ? null : lastModifiedTimeZ.trim();
    }

    public Long getXfrInsertPid() {
        return xfrInsertPid;
    }

    public void setXfrInsertPid(Long xfrInsertPid) {
        this.xfrInsertPid = xfrInsertPid;
    }

    public Long getXfrUpdatePid() {
        return xfrUpdatePid;
    }

    public void setXfrUpdatePid(Long xfrUpdatePid) {
        this.xfrUpdatePid = xfrUpdatePid;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId == null ? null : trxId.trim();
    }

    public BigDecimal getParentKey() {
        return parentKey;
    }

    public void setParentKey(BigDecimal parentKey) {
        this.parentKey = parentKey;
    }

    public String getCheckManS() {
        return checkManS;
    }

    public void setCheckManS(String checkManS) {
        this.checkManS = checkManS == null ? null : checkManS.trim();
    }

    public String getDefectS() {
        return defectS;
    }

    public void setDefectS(String defectS) {
        this.defectS = defectS == null ? null : defectS.trim();
    }

    public BigDecimal getDefectQtyI() {
        return defectQtyI;
    }

    public void setDefectQtyI(BigDecimal defectQtyI) {
        this.defectQtyI = defectQtyI;
    }

    public String getDutyGroup1S() {
        return dutyGroup1S;
    }

    public void setDutyGroup1S(String dutyGroup1S) {
        this.dutyGroup1S = dutyGroup1S == null ? null : dutyGroup1S.trim();
    }

    public String getDutyGroup2S() {
        return dutyGroup2S;
    }

    public void setDutyGroup2S(String dutyGroup2S) {
        this.dutyGroup2S = dutyGroup2S == null ? null : dutyGroup2S.trim();
    }

    public String getDutyGroup3S() {
        return dutyGroup3S;
    }

    public void setDutyGroup3S(String dutyGroup3S) {
        this.dutyGroup3S = dutyGroup3S == null ? null : dutyGroup3S.trim();
    }

    public String getItemNameS() {
        return itemNameS;
    }

    public void setItemNameS(String itemNameS) {
        this.itemNameS = itemNameS == null ? null : itemNameS.trim();
    }

    public String getItemNumberS() {
        return itemNumberS;
    }

    public void setItemNumberS(String itemNumberS) {
        this.itemNumberS = itemNumberS == null ? null : itemNumberS.trim();
    }

    public String getProductTypeS() {
        return productTypeS;
    }

    public void setProductTypeS(String productTypeS) {
        this.productTypeS = productTypeS == null ? null : productTypeS.trim();
    }

    public String getGroupS() {
        return groupS;
    }

    public void setGroupS(String groupS) {
        this.groupS = groupS == null ? null : groupS.trim();
    }

    public String getRecordManS() {
        return recordManS;
    }

    public void setRecordManS(String recordManS) {
        this.recordManS = recordManS == null ? null : recordManS.trim();
    }

    public String getAreaS() {
        return areaS;
    }

    public void setAreaS(String areaS) {
        this.areaS = areaS == null ? null : areaS.trim();
    }

    public String getFactoryS() {
        return factoryS;
    }

    public void setFactoryS(String factoryS) {
        this.factoryS = factoryS == null ? null : factoryS.trim();
    }

    public String getDutyS() {
        return dutyS;
    }

    public void setDutyS(String dutyS) {
        this.dutyS = dutyS == null ? null : dutyS.trim();
    }

    public Date getDateT() {
        return dateT;
    }

    public void setDateT(Date dateT) {
        this.dateT = dateT;
    }

    public Date getDateU() {
        return dateU;
    }

    public void setDateU(Date dateU) {
        this.dateU = dateU;
    }

    public String getDateZ() {
        return dateZ;
    }

    public void setDateZ(String dateZ) {
        this.dateZ = dateZ == null ? null : dateZ.trim();
    }

    public String getLineS() {
        return lineS;
    }

    public void setLineS(String lineS) {
        this.lineS = lineS == null ? null : lineS.trim();
    }
     public BigDecimal getAtrKey() {
        return atrKey;
    }

    public void setAtrKey(BigDecimal atrKey) {
        this.atrKey = atrKey;
    }

    public Long getSiteNum() {
        return siteNum;
    }

    public void setSiteNum(Long siteNum) {
        this.siteNum = siteNum;
    }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	public String getFacS() {
		return facS;
	}

	public void setFacS(String facS) {
		this.facS = facS;
	}

	public String getShifS() {
		return shifS;
	}

	public void setShifS(String shifS) {
		this.shifS = shifS;
	}

	public String getTimS() {
		return timS;
	}

	public void setTimS(String timS) {
		this.timS = timS;
	}

	
	public String getCheckStep() {
		return checkStep;
	}

	public void setCheckStep(String checkStep) {
		this.checkStep = checkStep;
	}

	public String getDefectSource() {
		return defectSource;
	}

	public void setDefectSource(String defectSource) {
		this.defectSource = defectSource;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getRepariredMan() {
		return repariredMan;
	}

	public void setRepariredMan(String repariredMan) {
		this.repariredMan = repariredMan;
	}

	public String getRepairedMethod() {
		return repairedMethod;
	}

	public void setRepairedMethod(String repairedMethod) {
		this.repairedMethod = repairedMethod;
	}

	public BigDecimal getTotalQtyI() {
		return totalQtyI;
	}

	public void setTotalQtyI(BigDecimal totalQtyI) {
		this.totalQtyI = totalQtyI;
	}
	public String getMarking() {
		return marking;
	}
	public void setMarking(String marking) {
		this.marking = marking;
	}

	public String getType_s() {
		return type_s;
	}

	public void setType_s(String type_s) {
		this.type_s = type_s;
	}

	public Integer getStartnum() {
		return startnum;
	}

	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}

	public Integer getEndnum() {
		return endnum;
	}

	public void setEndnum(Integer endnum) {
		this.endnum = endnum;
	}

	public String getPartTypeListTxt() {
		return partTypeListTxt;
	}

	public void setPartTypeListTxt(String partTypeListTxt) {
		this.partTypeListTxt = partTypeListTxt;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getBad_Reason() {
		return bad_Reason;
	}

	public void setBad_Reason(String bad_Reason) {
		this.bad_Reason = bad_Reason;
	}
	
}