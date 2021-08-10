package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class BatchDefectRecord  extends baseCommonVo{
	
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

    private String areaS;//AREA_S，车间

    private String batchS;//BATCH_S，批量大小

    private String defectS;//DEFECT_S，不良现象

    private BigDecimal defectQtyI;//DEFECT_QTY_I，不良数

    private String factoryS;//FACTORY_S，工厂

    private String finderS;//FINDER_S，发生班组

    private String group1S;//GROUP1_S，责任班组1

    private String group2S;//GROUP2_S，责任班组2

    private String group3S;//GROUP3_S，责任班组3

    private String lineS;//LINE_S，产线

    private String orderNumberS;//ORDER_NUMBER_S，发现方

    private String processNodeS;//PROCESS_NODE_S，发生流程节点

    private String productNameS;//PRODUCT_NAME_S，产品名称

    private String productNumberS;//PRODUCT_NUMBER_S，不良部件

    private Date recordDateT;//RECORD_DATE_T，发生日期

    private Date recordDateU;

    private String recordDateZ;

    private String recordManS;

    private String resultS;//RESULT_S，质量后果

    private double riskScoreI;//RISK_SCORE_F，质量风险系数

    private BigDecimal totalQtyI;//TOTAL_QTY_I，不良总数

    private String typeS;//TYPE_S，产品型号

    private String rateS;//RATE_S，不良率
    
    private String topRiskScore;
    
    private String lowRistScore;
    
    private String group;
    
    private String groupS;
    
    private String dateZ;
    
    private String facS;
    private String shifS;
    private String timS;
    
    private String checkStep;//CHECK_STEP_S，检查工序
    private String dutyMan;//DUTY_MAN_S，责任人
    private String groupLeader;//GROUP_LEADER_S，班组长
    private String meNode;//ME_S，5M1E
    private String method;//METHOD_S，临时措施
    private String workMan;//WORK_MAN_S，处理人
    

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


	public String getBatchS() {
		return batchS;
	}

	public void setBatchS(String batchS) {
		this.batchS = batchS;
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

    public String getFinderS() {
        return finderS;
    }

    public void setFinderS(String finderS) {
        this.finderS = finderS == null ? null : finderS.trim();
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

    public String getProcessNodeS() {
        return processNodeS;
    }

    public void setProcessNodeS(String processNodeS) {
        this.processNodeS = processNodeS == null ? null : processNodeS.trim();
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

    public String getResultS() {
        return resultS;
    }

    public void setResultS(String resultS) {
        this.resultS = resultS == null ? null : resultS.trim();
    }
   

    public double getRiskScoreI() {
		return riskScoreI;
	}

	public void setRiskScoreI(double riskScoreI) {
		this.riskScoreI = riskScoreI;
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

	public String getTopRiskScore() {
		return topRiskScore;
	}

	public void setTopRiskScore(String topRiskScore) {
		this.topRiskScore = topRiskScore;
	}

	public String getLowRistScore() {
		return lowRistScore;
	}

	public void setLowRistScore(String lowRistScore) {
		this.lowRistScore = lowRistScore;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public String getCheckStep() {
		return checkStep;
	}

	public void setCheckStep(String checkStep) {
		this.checkStep = checkStep;
	}

	public String getDutyMan() {
		return dutyMan;
	}

	public void setDutyMan(String dutyMan) {
		this.dutyMan = dutyMan;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getMeNode() {
		return meNode;
	}

	public void setMeNode(String meNode) {
		this.meNode = meNode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getWorkMan() {
		return workMan;
	}

	public void setWorkMan(String workMan) {
		this.workMan = workMan;
	}
    
    
}