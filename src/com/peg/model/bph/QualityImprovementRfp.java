package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class QualityImprovementRfp   extends baseCommonVo{
	
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

    private String changedS;

    private String descriptionS;

    private String dutyGroupS;

    private String factoryS;

    private String finderS;

    private String groupLeaderS;

    private String methodS;

    private String photoAfterS;

    private String photoFrontS;

    private String qcCheckedS;

    private String reasonS;

    private Date recordDateT;

    private Date recordDateU;

    private String recordDateZ;

    private String recordManS;

    private String rfpNameS;

    private BigDecimal rfpScoreI;

    private BigDecimal totalScoreI;
    
    private String groupS;
    
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

    public String getChangedS() {
        return changedS;
    }

    public void setChangedS(String changedS) {
        this.changedS = changedS == null ? null : changedS.trim();
    }

    public String getDescriptionS() {
        return descriptionS;
    }

    public void setDescriptionS(String descriptionS) {
        this.descriptionS = descriptionS == null ? null : descriptionS.trim();
    }

    public String getDutyGroupS() {
        return dutyGroupS;
    }

    public void setDutyGroupS(String dutyGroupS) {
        this.dutyGroupS = dutyGroupS == null ? null : dutyGroupS.trim();
    }

    public String getFactoryS() {
        return factoryS;
    }

    public void setFactoryS(String factoryS) {
        this.factoryS = factoryS == null ? null : factoryS.trim();
    }

    public String getFinderS() {
        return finderS;
    }

    public void setFinderS(String finderS) {
        this.finderS = finderS == null ? null : finderS.trim();
    }

    public String getGroupLeaderS() {
        return groupLeaderS;
    }

    public void setGroupLeaderS(String groupLeaderS) {
        this.groupLeaderS = groupLeaderS == null ? null : groupLeaderS.trim();
    }

    public String getMethodS() {
        return methodS;
    }

    public void setMethodS(String methodS) {
        this.methodS = methodS == null ? null : methodS.trim();
    }

    public String getPhotoAfterS() {
        return photoAfterS;
    }

    public void setPhotoAfterS(String photoAfterS) {
        this.photoAfterS = photoAfterS == null ? null : photoAfterS.trim();
    }

    public String getPhotoFrontS() {
        return photoFrontS;
    }

    public void setPhotoFrontS(String photoFrontS) {
        this.photoFrontS = photoFrontS == null ? null : photoFrontS.trim();
    }

    public String getQcCheckedS() {
        return qcCheckedS;
    }

    public void setQcCheckedS(String qcCheckedS) {
        this.qcCheckedS = qcCheckedS == null ? null : qcCheckedS.trim();
    }

    public String getReasonS() {
        return reasonS;
    }

    public void setReasonS(String reasonS) {
        this.reasonS = reasonS == null ? null : reasonS.trim();
    }

    public Date getRecordDateT() {
        return recordDateT;
    }

    public void setRecordDateT(Date recordDateT) {
        this.recordDateT = recordDateT;
    }

    public Date getRecordDateU() {
        return recordDateU;
    }

    public void setRecordDateU(Date recordDateU) {
        this.recordDateU = recordDateU;
    }

    public String getRecordDateZ() {
        return recordDateZ;
    }

    public void setRecordDateZ(String recordDateZ) {
        this.recordDateZ = recordDateZ == null ? null : recordDateZ.trim();
    }

    public String getRecordManS() {
        return recordManS;
    }

    public void setRecordManS(String recordManS) {
        this.recordManS = recordManS == null ? null : recordManS.trim();
    }

    public String getRfpNameS() {
        return rfpNameS;
    }

    public void setRfpNameS(String rfpNameS) {
        this.rfpNameS = rfpNameS == null ? null : rfpNameS.trim();
    }

    public BigDecimal getRfpScoreI() {
        return rfpScoreI;
    }

    public void setRfpScoreI(BigDecimal rfpScoreI) {
        this.rfpScoreI = rfpScoreI;
    }

    public BigDecimal getTotalScoreI() {
        return totalScoreI;
    }

    public void setTotalScoreI(BigDecimal totalScoreI) {
        this.totalScoreI = totalScoreI;
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

	public String getGroupS() {
		return groupS;
	}

	public void setGroupS(String groupS) {
		this.groupS = groupS;
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