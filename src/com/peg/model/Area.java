package com.peg.model;

import java.math.BigDecimal;
import java.util.Date;

public class Area {
	private BigDecimal areaKey;

    private Long siteNum;
	
    private String areaName;

    private String description;

    private String category;

    private BigDecimal creatorKey;

    private Date creationTime;

    private Date creationTimeU;

    private String creationTimeZ;

    private BigDecimal lastModifierKey;

    private Date lastModifiedTime;

    private Date lastModifiedTimeU;

    private String lastModifiedTimeZ;

    private Long xfrInsertPid;

    private Long xfrUpdatePid;

    private String trxId;

    private BigDecimal updatePrivilegeKey;

    private BigDecimal deletePrivilegeKey;

    private BigDecimal locationKey;

    private BigDecimal instListKey;

    private BigDecimal workScheduleKey;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public BigDecimal getCreatorKey() {
        return creatorKey;
    }

    public void setCreatorKey(BigDecimal creatorKey) {
        this.creatorKey = creatorKey;
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

    public BigDecimal getLastModifierKey() {
        return lastModifierKey;
    }

    public void setLastModifierKey(BigDecimal lastModifierKey) {
        this.lastModifierKey = lastModifierKey;
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

    public BigDecimal getUpdatePrivilegeKey() {
        return updatePrivilegeKey;
    }

    public void setUpdatePrivilegeKey(BigDecimal updatePrivilegeKey) {
        this.updatePrivilegeKey = updatePrivilegeKey;
    }

    public BigDecimal getDeletePrivilegeKey() {
        return deletePrivilegeKey;
    }

    public void setDeletePrivilegeKey(BigDecimal deletePrivilegeKey) {
        this.deletePrivilegeKey = deletePrivilegeKey;
    }

    public BigDecimal getLocationKey() {
        return locationKey;
    }

    public void setLocationKey(BigDecimal locationKey) {
        this.locationKey = locationKey;
    }

    public BigDecimal getInstListKey() {
        return instListKey;
    }

    public void setInstListKey(BigDecimal instListKey) {
        this.instListKey = instListKey;
    }

    public BigDecimal getWorkScheduleKey() {
        return workScheduleKey;
    }

    public void setWorkScheduleKey(BigDecimal workScheduleKey) {
        this.workScheduleKey = workScheduleKey;
    }
	
	public BigDecimal getAreaKey() {
        return areaKey;
    }

    public void setAreaKey(BigDecimal areaKey) {
        this.areaKey = areaKey;
    }

    public Long getSiteNum() {
        return siteNum;
    }

    public void setSiteNum(Long siteNum) {
        this.siteNum = siteNum;
    }
}