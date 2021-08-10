package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class FormerProcessFqcCheck  extends baseCommonVo{
	
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

    private String checkResultS;

    private Date dateT;

    private Date dateU;

    private String dateZ;

    private String defectS;

    private BigDecimal defectQtyI;

    private String factoryS;

    private String group1S;

    private String group2S;

    private String group3S;

    private String groupS;

    private String itemNameS;

    private String itemNumberS;

    private String lineS;

    private String methodS;

    private String orderNumberS;

    private String recordManS;

    private BigDecimal simpleQtyI;

    private BigDecimal totalQtyI;

    private String trackingNumberS;

    private String typeS;
    
    private String group;
    
    private String facS;
    private String shifS;
    private String timS;
    

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

    public String getCheckResultS() {
        return checkResultS;
    }

    public void setCheckResultS(String checkResultS) {
        this.checkResultS = checkResultS == null ? null : checkResultS.trim();
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

    public String getGroup1S() {
        return group1S;
    }

    public void setGroup1S(String group1S) {
        this.group1S = group1S == null ? null : group1S.trim();
    }

    public String getGroup2S() {
        return group2S;
    }

    public void setGroup2S(String group2S) {
        this.group2S = group2S == null ? null : group2S.trim();
    }

    public String getGroup3S() {
        return group3S;
    }

    public void setGroup3S(String group3S) {
        this.group3S = group3S == null ? null : group3S.trim();
    }

    public String getGroupS() {
        return groupS;
    }

    public void setGroupS(String groupS) {
        this.groupS = groupS == null ? null : groupS.trim();
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

    public String getLineS() {
        return lineS;
    }

    public void setLineS(String lineS) {
        this.lineS = lineS == null ? null : lineS.trim();
    }

    public String getMethodS() {
        return methodS;
    }

    public void setMethodS(String methodS) {
        this.methodS = methodS == null ? null : methodS.trim();
    }

    public String getOrderNumberS() {
        return orderNumberS;
    }

    public void setOrderNumberS(String orderNumberS) {
        this.orderNumberS = orderNumberS == null ? null : orderNumberS.trim();
    }

    public String getRecordManS() {
        return recordManS;
    }

    public void setRecordManS(String recordManS) {
        this.recordManS = recordManS == null ? null : recordManS.trim();
    }

    public BigDecimal getSimpleQtyI() {
        return simpleQtyI;
    }

    public void setSimpleQtyI(BigDecimal simpleQtyI) {
        this.simpleQtyI = simpleQtyI;
    }

    public BigDecimal getTotalQtyI() {
        return totalQtyI;
    }

    public void setTotalQtyI(BigDecimal totalQtyI) {
        this.totalQtyI = totalQtyI;
    }

    public String getTrackingNumberS() {
        return trackingNumberS;
    }

    public void setTrackingNumberS(String trackingNumberS) {
        this.trackingNumberS = trackingNumberS == null ? null : trackingNumberS.trim();
    }

    public String getTypeS() {
        return typeS;
    }

    public void setTypeS(String typeS) {
        this.typeS = typeS == null ? null : typeS.trim();
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
    
    
}