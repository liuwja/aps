package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class IpqcInspects  extends baseCommonVo{
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

    private String dutyManS;

    private String measureS;

    private String inspectManS;

    private String finderS;

    private String findTimeS;

    private String imageS;

    private String noticeMenS;

    private BigDecimal feedingQtyI;

    private BigDecimal defectQtyI;

    private String defectRateS;

    private String equipmentNoS;

    private String stepNameS;

    private String defectTypeS;

    private String unitBarcodeS;

    private String inspectResultS;

    private String defectLevelS;

    private String defectMaterialS;

    private String unqualifiedAppearanceS;

    private String inspectTypeS;

    private String workcenterS;

    private Date inspectDateT;

    private Date inspectDateU;

    private String inspectDateZ;

    private String factoryS;

    private String orderNumberS;

    private String productionLineS;

    private String groupNameS;

    private String shiftNameS;

    private String productNameS;

    private String standardSizeS;

    private String trulySizeS;

    private String orderItemNumberS;

    private BigDecimal unquantityQtyI;

    private BigDecimal traceQtyI;
    
    private BigDecimal  checkQtyI;
    
    private BigDecimal defectNum;
    
    private String group1S;
    
    private String group2S;
    
    private String group3S;
    
    private String dutyGroup;
    
    private Date startTime;
	
	private Date endTime;
	
	private String group;
	private String processNode;
	private String batch;
	private String result;
	private Float riskScore;
	
	private String facS;
    private String shifS;
    private String timS;
    
    private String partName;
	
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

    public String getDutyManS() {
        return dutyManS;
    }

    public void setDutyManS(String dutyManS) {
        this.dutyManS = dutyManS == null ? null : dutyManS.trim();
    }

    public String getMeasureS() {
        return measureS;
    }

    public void setMeasureS(String measureS) {
        this.measureS = measureS == null ? null : measureS.trim();
    }

    public String getInspectManS() {
        return inspectManS;
    }

    public void setInspectManS(String inspectManS) {
        this.inspectManS = inspectManS == null ? null : inspectManS.trim();
    }

    public String getFinderS() {
        return finderS;
    }

    public void setFinderS(String finderS) {
        this.finderS = finderS == null ? null : finderS.trim();
    }

    public String getFindTimeS() {
        return findTimeS;
    }

    public void setFindTimeS(String findTimeS) {
        this.findTimeS = findTimeS == null ? null : findTimeS.trim();
    }

    public String getImageS() {
        return imageS;
    }

    public void setImageS(String imageS) {
        this.imageS = imageS == null ? null : imageS.trim();
    }

    public String getNoticeMenS() {
        return noticeMenS;
    }

    public void setNoticeMenS(String noticeMenS) {
        this.noticeMenS = noticeMenS == null ? null : noticeMenS.trim();
    }

    public BigDecimal getFeedingQtyI() {
        return feedingQtyI;
    }

    public void setFeedingQtyI(BigDecimal feedingQtyI) {
        this.feedingQtyI = feedingQtyI;
    }

    public BigDecimal getDefectQtyI() {
        return defectQtyI;
    }

    public void setDefectQtyI(BigDecimal defectQtyI) {
        this.defectQtyI = defectQtyI;
    }

    public String getDefectRateS() {
        return defectRateS;
    }

    public void setDefectRateS(String defectRateS) {
        this.defectRateS = defectRateS == null ? null : defectRateS.trim();
    }

    public String getEquipmentNoS() {
        return equipmentNoS;
    }

    public void setEquipmentNoS(String equipmentNoS) {
        this.equipmentNoS = equipmentNoS == null ? null : equipmentNoS.trim();
    }

    public String getStepNameS() {
        return stepNameS;
    }

    public void setStepNameS(String stepNameS) {
        this.stepNameS = stepNameS == null ? null : stepNameS.trim();
    }

    public String getDefectTypeS() {
        return defectTypeS;
    }

    public void setDefectTypeS(String defectTypeS) {
        this.defectTypeS = defectTypeS == null ? null : defectTypeS.trim();
    }

    public String getUnitBarcodeS() {
        return unitBarcodeS;
    }

    public void setUnitBarcodeS(String unitBarcodeS) {
        this.unitBarcodeS = unitBarcodeS == null ? null : unitBarcodeS.trim();
    }

    public String getInspectResultS() {
        return inspectResultS;
    }

    public void setInspectResultS(String inspectResultS) {
        this.inspectResultS = inspectResultS == null ? null : inspectResultS.trim();
    }

    public String getDefectLevelS() {
        return defectLevelS;
    }

    public void setDefectLevelS(String defectLevelS) {
        this.defectLevelS = defectLevelS == null ? null : defectLevelS.trim();
    }

    public String getDefectMaterialS() {
        return defectMaterialS;
    }

    public void setDefectMaterialS(String defectMaterialS) {
        this.defectMaterialS = defectMaterialS == null ? null : defectMaterialS.trim();
    }

    public String getUnqualifiedAppearanceS() {
        return unqualifiedAppearanceS;
    }

    public void setUnqualifiedAppearanceS(String unqualifiedAppearanceS) {
        this.unqualifiedAppearanceS = unqualifiedAppearanceS == null ? null : unqualifiedAppearanceS.trim();
    }

    public String getInspectTypeS() {
        return inspectTypeS;
    }

    public void setInspectTypeS(String inspectTypeS) {
        this.inspectTypeS = inspectTypeS == null ? null : inspectTypeS.trim();
    }

    public String getWorkcenterS() {
        return workcenterS;
    }

    public void setWorkcenterS(String workcenterS) {
        this.workcenterS = workcenterS == null ? null : workcenterS.trim();
    }

    public Date getInspectDateT() {
        return inspectDateT;
    }

    public void setInspectDateT(Date inspectDateT) {
        this.inspectDateT = inspectDateT;
    }

    public Date getInspectDateU() {
        return inspectDateU;
    }

    public void setInspectDateU(Date inspectDateU) {
        this.inspectDateU = inspectDateU;
    }

    public String getInspectDateZ() {
        return inspectDateZ;
    }

    public void setInspectDateZ(String inspectDateZ) {
        this.inspectDateZ = inspectDateZ == null ? null : inspectDateZ.trim();
    }

    public String getFactoryS() {
        return factoryS;
    }

    public void setFactoryS(String factoryS) {
        this.factoryS = factoryS == null ? null : factoryS.trim();
    }

    public String getOrderNumberS() {
        return orderNumberS;
    }

    public void setOrderNumberS(String orderNumberS) {
        this.orderNumberS = orderNumberS == null ? null : orderNumberS.trim();
    }

    public String getProductionLineS() {
        return productionLineS;
    }

    public void setProductionLineS(String productionLineS) {
        this.productionLineS = productionLineS == null ? null : productionLineS.trim();
    }

    public String getGroupNameS() {
        return groupNameS;
    }

    public void setGroupNameS(String groupNameS) {
        this.groupNameS = groupNameS == null ? null : groupNameS.trim();
    }

    public String getShiftNameS() {
        return shiftNameS;
    }

    public void setShiftNameS(String shiftNameS) {
        this.shiftNameS = shiftNameS == null ? null : shiftNameS.trim();
    }

    public String getProductNameS() {
        return productNameS;
    }

    public void setProductNameS(String productNameS) {
        this.productNameS = productNameS == null ? null : productNameS.trim();
    }

    public String getStandardSizeS() {
        return standardSizeS;
    }

    public void setStandardSizeS(String standardSizeS) {
        this.standardSizeS = standardSizeS == null ? null : standardSizeS.trim();
    }

    public String getTrulySizeS() {
        return trulySizeS;
    }

    public void setTrulySizeS(String trulySizeS) {
        this.trulySizeS = trulySizeS == null ? null : trulySizeS.trim();
    }

    public String getOrderItemNumberS() {
        return orderItemNumberS;
    }

    public void setOrderItemNumberS(String orderItemNumberS) {
        this.orderItemNumberS = orderItemNumberS == null ? null : orderItemNumberS.trim();
    }

    public BigDecimal getUnquantityQtyI() {
        return unquantityQtyI;
    }

    public void setUnquantityQtyI(BigDecimal unquantityQtyI) {
        this.unquantityQtyI = unquantityQtyI;
    }

    public BigDecimal getTraceQtyI() {
        return traceQtyI;
    }

    public void setTraceQtyI(BigDecimal traceQtyI) {
        this.traceQtyI = traceQtyI;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public BigDecimal getCheckQtyI() {
		return checkQtyI;
	}

	public void setCheckQtyI(BigDecimal checkQtyI) {
		this.checkQtyI = checkQtyI;
	}

	public BigDecimal getDefectNum() {
		return defectNum;
	}

	public void setDefectNum(BigDecimal defectNum) {
		this.defectNum = defectNum;
	}

	public String getGroup1S() {
		return group1S;
	}

	public void setGroup1S(String group1s) {
		group1S = group1s;
	}

	public String getGroup2S() {
		return group2S;
	}

	public void setGroup2S(String group2s) {
		group2S = group2s;
	}

	public String getGroup3S() {
		return group3S;
	}

	public void setGroup3S(String group3s) {
		group3S = group3s;
	}

	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}

	public String getProcessNode() {
		return processNode;
	}

	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Float getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(Float riskScore) {
		this.riskScore = riskScore;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}
	
 
    
}