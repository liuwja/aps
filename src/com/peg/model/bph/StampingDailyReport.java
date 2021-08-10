package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class StampingDailyReport  extends baseCommonVo{
	
	private Date startTime;
	
	private Date endTime;
	
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

    private String areaS;

    private String checkManS;

    private BigDecimal checkQtyI;

    private Date dateT;

    private Date dateU;

    private String dateZ;

    private String defectS;

    private BigDecimal defectQtyI;

    private String factoryS;

    private String groupS;

    private String groupLeaderS;

    private String lineS;

    private String orderNumberS;

    private String productNameS;

    private String productNumberS;

    private String recordManS;

    private String stepS;

    private BigDecimal totalQtyI;

    private String typeS;

    private String rateS;

    private String locationS;

    private String dutyStepS;

    private String dutyManS;

    private String methodS;

    private String remarkS;
    
    private String defectNameS;
    
    private String facS;
    private String shifS;
    private String timS;
    private String marking;//标示，ljy添加（图标标示）
    private String type_s;//标示，ljy添加（图标标示）
    private Integer badnum;//ljy添加，不良个数
    private Integer badnum2;//ljy添加，不良个数
    

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

    public String getAreaS() {
        return areaS;
    }

    public void setAreaS(String areaS) {
        this.areaS = areaS == null ? null : areaS.trim();
    }

    public String getCheckManS() {
        return checkManS;
    }

    public void setCheckManS(String checkManS) {
        this.checkManS = checkManS == null ? null : checkManS.trim();
    }

    public BigDecimal getCheckQtyI() {
        return checkQtyI;
    }

    public void setCheckQtyI(BigDecimal checkQtyI) {
        this.checkQtyI = checkQtyI;
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

    public String getFactoryS() {
        return factoryS;
    }

    public void setFactoryS(String factoryS) {
        this.factoryS = factoryS == null ? null : factoryS.trim();
    }

    public String getGroupS() {
        return groupS;
    }

    public void setGroupS(String groupS) {
        this.groupS = groupS == null ? null : groupS.trim();
    }

    public String getGroupLeaderS() {
        return groupLeaderS;
    }

    public void setGroupLeaderS(String groupLeaderS) {
        this.groupLeaderS = groupLeaderS == null ? null : groupLeaderS.trim();
    }

    public String getLineS() {
        return lineS;
    }

    public void setLineS(String lineS) {
        this.lineS = lineS == null ? null : lineS.trim();
    }

    public String getOrderNumberS() {
        return orderNumberS;
    }

    public void setOrderNumberS(String orderNumberS) {
        this.orderNumberS = orderNumberS == null ? null : orderNumberS.trim();
    }

    public String getProductNameS() {
        return productNameS;
    }

    public void setProductNameS(String productNameS) {
        this.productNameS = productNameS == null ? null : productNameS.trim();
    }

    public String getProductNumberS() {
        return productNumberS;
    }

    public void setProductNumberS(String productNumberS) {
        this.productNumberS = productNumberS == null ? null : productNumberS.trim();
    }

    public String getRecordManS() {
        return recordManS;
    }

    public void setRecordManS(String recordManS) {
        this.recordManS = recordManS == null ? null : recordManS.trim();
    }

    public String getStepS() {
        return stepS;
    }

    public void setStepS(String stepS) {
        this.stepS = stepS == null ? null : stepS.trim();
    }

    public BigDecimal getTotalQtyI() {
        return totalQtyI;
    }

    public void setTotalQtyI(BigDecimal totalQtyI) {
        this.totalQtyI = totalQtyI;
    }

    public String getTypeS() {
        return typeS;
    }

    public void setTypeS(String typeS) {
        this.typeS = typeS == null ? null : typeS.trim();
    }

    public String getRateS() {
        return rateS;
    }

    public void setRateS(String rateS) {
        this.rateS = rateS == null ? null : rateS.trim();
    }

    public String getLocationS() {
        return locationS;
    }

    public void setLocationS(String locationS) {
        this.locationS = locationS == null ? null : locationS.trim();
    }

    public String getDutyStepS() {
        return dutyStepS;
    }

    public void setDutyStepS(String dutyStepS) {
        this.dutyStepS = dutyStepS == null ? null : dutyStepS.trim();
    }

    public String getDutyManS() {
        return dutyManS;
    }

    public void setDutyManS(String dutyManS) {
        this.dutyManS = dutyManS == null ? null : dutyManS.trim();
    }

    public String getMethodS() {
        return methodS;
    }

    public void setMethodS(String methodS) {
        this.methodS = methodS == null ? null : methodS.trim();
    }

    public String getRemarkS() {
        return remarkS;
    }

    public void setRemarkS(String remarkS) {
        this.remarkS = remarkS == null ? null : remarkS.trim();
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

	public String getDefectNameS() {
		return defectNameS;
	}

	public void setDefectNameS(String defectNameS) {
		this.defectNameS = defectNameS;
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

	public Integer getBadnum() {
		return badnum;
	}

	public void setBadnum(Integer badnum) {
		this.badnum = badnum;
	}

	public Integer getBadnum2() {
		return badnum2;
	}

	public void setBadnum2(Integer badnum2) {
		this.badnum2 = badnum2;
	}

	
    
}