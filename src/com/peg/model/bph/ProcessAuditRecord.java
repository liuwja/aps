package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class ProcessAuditRecord  extends baseCommonVo{
	
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

    private String auditBasisS;

    private String auditFindS;

    private String auditManS;

    private String auditResultS;

    private String auditTermsS;

    private Date checkDateT;

    private Date checkDateU;

    private String checkDateZ;

    private String checkTypeS;

    private String defectS;

    private String defectNumberS;

    private String dutyS;

    private String factoryS;

    private String followManS;

    private String followStateS;

    private String groupS;

    private String groupLeaderS;

    private String isCloseS;

    private String planS;

    private String recordManS;
    
    private String dateZ;
    
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

    public String getAuditBasisS() {
        return auditBasisS;
    }

    public void setAuditBasisS(String auditBasisS) {
        this.auditBasisS = auditBasisS == null ? null : auditBasisS.trim();
    }

    public String getAuditFindS() {
        return auditFindS;
    }

    public void setAuditFindS(String auditFindS) {
        this.auditFindS = auditFindS == null ? null : auditFindS.trim();
    }

    public String getAuditManS() {
        return auditManS;
    }

    public void setAuditManS(String auditManS) {
        this.auditManS = auditManS == null ? null : auditManS.trim();
    }

    public String getAuditResultS() {
        return auditResultS;
    }

    public void setAuditResultS(String auditResultS) {
        this.auditResultS = auditResultS == null ? null : auditResultS.trim();
    }

    public String getAuditTermsS() {
        return auditTermsS;
    }

    public void setAuditTermsS(String auditTermsS) {
        this.auditTermsS = auditTermsS == null ? null : auditTermsS.trim();
    }

    public Date getCheckDateT() {
        return checkDateT;
    }

    public void setCheckDateT(Date checkDateT) {
        this.checkDateT = checkDateT;
    }

    public Date getCheckDateU() {
        return checkDateU;
    }

    public void setCheckDateU(Date checkDateU) {
        this.checkDateU = checkDateU;
    }

    public String getCheckDateZ() {
        return checkDateZ;
    }

    public void setCheckDateZ(String checkDateZ) {
        this.checkDateZ = checkDateZ == null ? null : checkDateZ.trim();
    }

    public String getCheckTypeS() {
        return checkTypeS;
    }

    public void setCheckTypeS(String checkTypeS) {
        this.checkTypeS = checkTypeS == null ? null : checkTypeS.trim();
    }

    public String getDefectS() {
        return defectS;
    }

    public void setDefectS(String defectS) {
        this.defectS = defectS == null ? null : defectS.trim();
    }

    public String getDefectNumberS() {
        return defectNumberS;
    }

    public void setDefectNumberS(String defectNumberS) {
        this.defectNumberS = defectNumberS == null ? null : defectNumberS.trim();
    }

    public String getDutyS() {
        return dutyS;
    }

    public void setDutyS(String dutyS) {
        this.dutyS = dutyS == null ? null : dutyS.trim();
    }

    public String getFactoryS() {
        return factoryS;
    }

    public void setFactoryS(String factoryS) {
        this.factoryS = factoryS == null ? null : factoryS.trim();
    }

    public String getFollowManS() {
        return followManS;
    }

    public void setFollowManS(String followManS) {
        this.followManS = followManS == null ? null : followManS.trim();
    }

    public String getFollowStateS() {
        return followStateS;
    }

    public void setFollowStateS(String followStateS) {
        this.followStateS = followStateS == null ? null : followStateS.trim();
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

    public String getIsCloseS() {
        return isCloseS;
    }

    public void setIsCloseS(String isCloseS) {
        this.isCloseS = isCloseS == null ? null : isCloseS.trim();
    }

    public String getPlanS() {
        return planS;
    }

    public void setPlanS(String planS) {
        this.planS = planS == null ? null : planS.trim();
    }

    public String getRecordManS() {
        return recordManS;
    }

    public void setRecordManS(String recordManS) {
        this.recordManS = recordManS == null ? null : recordManS.trim();
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

	public String getDateZ() {
		return dateZ;
	}

	public void setDateZ(String dateZ) {
		this.dateZ = dateZ;
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