package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class OqcCheck  extends baseCommonVo{
	
	private BigDecimal testInstanceKey;

    private Long siteNum;
	
    private BigDecimal testDefinitionKey;

    private BigDecimal objectKey;

    private String objectName;

    private String objectType;

    private String dscomment;

    private String userName;

    private String routeName;

    private BigDecimal routeKey;

    private String routeStepName;

    private String opName;

    private BigDecimal tobjHistoryKey;

    private Long completeCount;

    private String pLineName;

    private String location;

    private BigDecimal testEquipKey;

    private Long testPassed;

    private Long testValid;

    private Date testStartTime;

    private Date testStartTimeU;

    private String testStartTimeZ;

    private Date testEndTime;

    private Date testEndTimeU;

    private String testEndTimeZ;

    private Date creationTime;

    private Date creationTimeU;

    private String creationTimeZ;

    private Date lastModifiedTime;

    private Date lastModifiedTimeU;

    private String lastModifiedTimeZ;

    private Long xfrInsertPid;

    private Long pdXfrUpdatePid;

    private Long srcXfrUpdatePid;

    private Long xfrUpdatePid;

    private String trxId;

    private String uda0;

    private String uda1;

    private String uda2;

    private String uda3;

    private String uda4;

    private String uda5;

    private String uda6;

    private String uda7;

    private String uda8;

    private String uda9;

    private Date udt0;

    private Date udt0U;

    private String udt0Z;

    private Date udt1;

    private Date udt1U;

    private String udt1Z;

    private Date udt2;

    private Date udt2U;

    private String udt2Z;

    private BigDecimal creatorKey;

    private BigDecimal lastModifierKey;

    private String category;

    private String description;

    private Long passCount;

    private String testInstanceName;
    
    private String factoryS;
    
    private Date startTime;
	
	private Date endTime;
	
	private String group;
	private String[] group1;

	
	private String facS;
    private String shifS;
    private String timS; 
    private String plineNames;
    private String productoinLine;
    private String orderNumber;
    private String defectCode;
    private String ruda1;
    private String defectComment;
	
	 public BigDecimal getTestInstanceKey() {
        return testInstanceKey;
    }

    public void setTestInstanceKey(BigDecimal testInstanceKey) {
        this.testInstanceKey = testInstanceKey;
    }

    public Long getSiteNum() {
        return siteNum;
    }

    public void setSiteNum(Long siteNum) {
        this.siteNum = siteNum;
    }

    public BigDecimal getTestDefinitionKey() {
        return testDefinitionKey;
    }

    public void setTestDefinitionKey(BigDecimal testDefinitionKey) {
        this.testDefinitionKey = testDefinitionKey;
    }

    public BigDecimal getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(BigDecimal objectKey) {
        this.objectKey = objectKey;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getDscomment() {
        return dscomment;
    }

    public void setDscomment(String dscomment) {
        this.dscomment = dscomment == null ? null : dscomment.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public BigDecimal getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(BigDecimal routeKey) {
        this.routeKey = routeKey;
    }

    public String getRouteStepName() {
        return routeStepName;
    }

    public void setRouteStepName(String routeStepName) {
        this.routeStepName = routeStepName == null ? null : routeStepName.trim();
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public BigDecimal getTobjHistoryKey() {
        return tobjHistoryKey;
    }

    public void setTobjHistoryKey(BigDecimal tobjHistoryKey) {
        this.tobjHistoryKey = tobjHistoryKey;
    }

    public Long getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(Long completeCount) {
        this.completeCount = completeCount;
    }

    public String getpLineName() {
        return pLineName;
    }

    public void setpLineName(String pLineName) {
        this.pLineName = pLineName == null ? null : pLineName.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public BigDecimal getTestEquipKey() {
        return testEquipKey;
    }

    public void setTestEquipKey(BigDecimal testEquipKey) {
        this.testEquipKey = testEquipKey;
    }

    public Long getTestPassed() {
        return testPassed;
    }

    public void setTestPassed(Long testPassed) {
        this.testPassed = testPassed;
    }

    public Long getTestValid() {
        return testValid;
    }

    public void setTestValid(Long testValid) {
        this.testValid = testValid;
    }

    public Date getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(Date testStartTime) {
        this.testStartTime = testStartTime;
    }

    public Date getTestStartTimeU() {
        return testStartTimeU;
    }

    public void setTestStartTimeU(Date testStartTimeU) {
        this.testStartTimeU = testStartTimeU;
    }

    public String getTestStartTimeZ() {
        return testStartTimeZ;
    }

    public void setTestStartTimeZ(String testStartTimeZ) {
        this.testStartTimeZ = testStartTimeZ == null ? null : testStartTimeZ.trim();
    }

    public Date getTestEndTime() {
        return testEndTime;
    }

    public void setTestEndTime(Date testEndTime) {
        this.testEndTime = testEndTime;
    }

    public Date getTestEndTimeU() {
        return testEndTimeU;
    }

    public void setTestEndTimeU(Date testEndTimeU) {
        this.testEndTimeU = testEndTimeU;
    }

    public String getTestEndTimeZ() {
        return testEndTimeZ;
    }

    public void setTestEndTimeZ(String testEndTimeZ) {
        this.testEndTimeZ = testEndTimeZ == null ? null : testEndTimeZ.trim();
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

    public Long getPdXfrUpdatePid() {
        return pdXfrUpdatePid;
    }

    public void setPdXfrUpdatePid(Long pdXfrUpdatePid) {
        this.pdXfrUpdatePid = pdXfrUpdatePid;
    }

    public Long getSrcXfrUpdatePid() {
        return srcXfrUpdatePid;
    }

    public void setSrcXfrUpdatePid(Long srcXfrUpdatePid) {
        this.srcXfrUpdatePid = srcXfrUpdatePid;
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

    public String getUda0() {
        return uda0;
    }

    public void setUda0(String uda0) {
        this.uda0 = uda0 == null ? null : uda0.trim();
    }

    public String getUda1() {
        return uda1;
    }

    public void setUda1(String uda1) {
        this.uda1 = uda1 == null ? null : uda1.trim();
    }

    public String getUda2() {
        return uda2;
    }

    public void setUda2(String uda2) {
        this.uda2 = uda2 == null ? null : uda2.trim();
    }

    public String getUda3() {
        return uda3;
    }

    public void setUda3(String uda3) {
        this.uda3 = uda3 == null ? null : uda3.trim();
    }

    public String getUda4() {
        return uda4;
    }

    public void setUda4(String uda4) {
        this.uda4 = uda4 == null ? null : uda4.trim();
    }

    public String getUda5() {
        return uda5;
    }

    public void setUda5(String uda5) {
        this.uda5 = uda5 == null ? null : uda5.trim();
    }

    public String getUda6() {
        return uda6;
    }

    public void setUda6(String uda6) {
        this.uda6 = uda6 == null ? null : uda6.trim();
    }

    public String getUda7() {
        return uda7;
    }

    public void setUda7(String uda7) {
        this.uda7 = uda7 == null ? null : uda7.trim();
    }

    public String getUda8() {
        return uda8;
    }

    public void setUda8(String uda8) {
        this.uda8 = uda8 == null ? null : uda8.trim();
    }

    public String getUda9() {
        return uda9;
    }

    public void setUda9(String uda9) {
        this.uda9 = uda9 == null ? null : uda9.trim();
    }

    public Date getUdt0() {
        return udt0;
    }

    public void setUdt0(Date udt0) {
        this.udt0 = udt0;
    }

    public Date getUdt0U() {
        return udt0U;
    }

    public void setUdt0U(Date udt0U) {
        this.udt0U = udt0U;
    }

    public String getUdt0Z() {
        return udt0Z;
    }

    public void setUdt0Z(String udt0Z) {
        this.udt0Z = udt0Z == null ? null : udt0Z.trim();
    }

    public Date getUdt1() {
        return udt1;
    }

    public void setUdt1(Date udt1) {
        this.udt1 = udt1;
    }

    public Date getUdt1U() {
        return udt1U;
    }

    public void setUdt1U(Date udt1U) {
        this.udt1U = udt1U;
    }

    public String getUdt1Z() {
        return udt1Z;
    }

    public void setUdt1Z(String udt1Z) {
        this.udt1Z = udt1Z == null ? null : udt1Z.trim();
    }

    public Date getUdt2() {
        return udt2;
    }

    public void setUdt2(Date udt2) {
        this.udt2 = udt2;
    }

    public Date getUdt2U() {
        return udt2U;
    }

    public void setUdt2U(Date udt2U) {
        this.udt2U = udt2U;
    }

    public String getUdt2Z() {
        return udt2Z;
    }

    public void setUdt2Z(String udt2Z) {
        this.udt2Z = udt2Z == null ? null : udt2Z.trim();
    }

    public BigDecimal getCreatorKey() {
        return creatorKey;
    }

    public void setCreatorKey(BigDecimal creatorKey) {
        this.creatorKey = creatorKey;
    }

    public BigDecimal getLastModifierKey() {
        return lastModifierKey;
    }

    public void setLastModifierKey(BigDecimal lastModifierKey) {
        this.lastModifierKey = lastModifierKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getPassCount() {
        return passCount;
    }

    public void setPassCount(Long passCount) {
        this.passCount = passCount;
    }

    public String getTestInstanceName() {
        return testInstanceName;
    }

    public void setTestInstanceName(String testInstanceName) {
        this.testInstanceName = testInstanceName == null ? null : testInstanceName.trim();
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

	public String getFactoryS() {
		return factoryS;
	}

	public void setFactoryS(String factoryS) {
		this.factoryS = factoryS;
	}

	public String[] getGroup1() {
		return group1;
	}

	public void setGroup1(String[] group1) {
		this.group1 = group1;
	}

	public String getPlineNames() {
		return plineNames;
	}

	public void setPlineNames(String plineNames) {
		this.plineNames = plineNames;
	}

	public String getProductoinLine() {
		return productoinLine;
	}

	public void setProductoinLine(String productoinLine) {
		this.productoinLine = productoinLine;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDefectCode() {
		return defectCode;
	}

	public void setDefectCode(String defectCode) {
		this.defectCode = defectCode;
	}

	public String getDefectComment() {
		return defectComment;
	}

	public void setDefectComment(String defectComment) {
		this.defectComment = defectComment;
	}

	/**
	 * @param ruda1 the ruda1 to set
	 */
	public void setRuda1(String ruda1) {
		this.ruda1 = ruda1;
	}

	/**
	 * @return the ruda1
	 */
	public String getRuda1() {
		return ruda1;
	}

	
  
}