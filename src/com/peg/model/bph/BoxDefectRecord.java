package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class BoxDefectRecord  extends baseCommonVo{
	
	private Date startTime;
	
	private Date endTime;
	private String startTimeS;
	
	private String endTimeS;
	
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

    private String factoryS;

    private String recordManS;

    private String defectSourceS;

    private String defectTypeS;

    private String productNumberS;

    private String defectS;

    private String defectReasonS;

    private String group1S;

    private String group2S;

    private String group3S;

    private Date recordDateT;

    private Date recordDateU;

    private String recordDateZ;

    private String processNodeS;

    private String qualityResultS;

    private double qualityScoreI;

    private String lotQtyS;
    
    private String topRistScore;
    
    private String lowRistScore;
    
    private String group;
    
    private String groupS;
    
    private String dateZ;
    
    private String defectCategoryS;
    
    private String defectSpareNameS;
    
    
    private String pictureUrlS;
    private String primaryDutyS;
    private String primaryManS;
    private String ultimateDutyS;
    private String ultimateManS;
    private String specificDutyS;

    
    
    private String facS;
    private String shifS;
    private String timeS;
    private Long sum;
    
    
    

    public String getStartTimeS() {
		return startTimeS;
	}

	public void setStartTimeS(String startTimeS) {
		this.startTimeS = startTimeS;
	}

	public String getEndTimeS() {
		return endTimeS;
	}

	public void setEndTimeS(String endTimeS) {
		this.endTimeS = endTimeS;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

	public String getPictureUrlS() {
		return pictureUrlS;
	}

	public void setPictureUrlS(String pictureUrlS) {
		this.pictureUrlS = pictureUrlS;
	}

	public String getPrimaryDutyS() {
		return primaryDutyS;
	}

	public void setPrimaryDutyS(String primaryDutyS) {
		this.primaryDutyS = primaryDutyS;
	}

	public String getPrimaryManS() {
		return primaryManS;
	}

	public void setPrimaryManS(String primaryManS) {
		this.primaryManS = primaryManS;
	}

	public String getUltimateDutyS() {
		return ultimateDutyS;
	}

	public void setUltimateDutyS(String ultimateDutyS) {
		this.ultimateDutyS = ultimateDutyS;
	}

	public String getUltimateManS() {
		return ultimateManS;
	}

	public void setUltimateManS(String ultimateManS) {
		this.ultimateManS = ultimateManS;
	}

	public String getSpecificDutyS() {
		return specificDutyS;
	}

	public void setSpecificDutyS(String specificDutyS) {
		this.specificDutyS = specificDutyS;
	}

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

    public String getFactoryS() {
        return factoryS;
    }

    public void setFactoryS(String factoryS) {
        this.factoryS = factoryS == null ? null : factoryS.trim();
    }

    public String getRecordManS() {
        return recordManS;
    }

    public void setRecordManS(String recordManS) {
        this.recordManS = recordManS == null ? null : recordManS.trim();
    }

    public String getDefectSourceS() {
        return defectSourceS;
    }

    public void setDefectSourceS(String defectSourceS) {
        this.defectSourceS = defectSourceS == null ? null : defectSourceS.trim();
    }

    public String getDefectTypeS() {
        return defectTypeS;
    }

    public void setDefectTypeS(String defectTypeS) {
        this.defectTypeS = defectTypeS == null ? null : defectTypeS.trim();
    }

    public String getProductNumberS() {
        return productNumberS;
    }

    public void setProductNumberS(String productNumberS) {
        this.productNumberS = productNumberS == null ? null : productNumberS.trim();
    }

    public String getDefectS() {
        return defectS;
    }

    public void setDefectS(String defectS) {
        this.defectS = defectS == null ? null : defectS.trim();
    }

    public String getDefectReasonS() {
        return defectReasonS;
    }

    public void setDefectReasonS(String defectReasonS) {
        this.defectReasonS = defectReasonS == null ? null : defectReasonS.trim();
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

    public String getProcessNodeS() {
        return processNodeS;
    }

    public void setProcessNodeS(String processNodeS) {
        this.processNodeS = processNodeS == null ? null : processNodeS.trim();
    }

    public String getQualityResultS() {
        return qualityResultS;
    }

    public void setQualityResultS(String qualityResultS) {
        this.qualityResultS = qualityResultS == null ? null : qualityResultS.trim();
    }

 
    public double getQualityScoreI() {
		return qualityScoreI;
	}

	public void setQualityScoreI(double qualityScoreI) {
		this.qualityScoreI = qualityScoreI;
	}

	public String getLotQtyS() {
		return lotQtyS;
	}

	public void setLotQtyS(String lotQtyS) {
		this.lotQtyS = lotQtyS;
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

	public String getTopRistScore() {
		return topRistScore;
	}

	public void setTopRistScore(String topRistScore) {
		this.topRistScore = topRistScore;
	}

	public String getLowRistScore() {
		return lowRistScore;
	}

	public void setLowRistScore(String lowRistScore) {
		this.lowRistScore = lowRistScore;
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

	public String getTimeS() {
		return timeS;
	}

	public void setTimeS(String timeS) {
		this.timeS = timeS;
	}

	public String getDefectCategoryS() {
		return defectCategoryS;
	}

	public void setDefectCategoryS(String defectCategoryS) {
		this.defectCategoryS = defectCategoryS;
	}

	public String getDefectSpareNameS() {
		return defectSpareNameS;
	}

	public void setDefectSpareNameS(String defectSpareNameS) {
		this.defectSpareNameS = defectSpareNameS;
	}
    
	
}