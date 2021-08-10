package com.peg.model.system;

import java.math.BigDecimal;
import java.util.Date;

public class SysMesUser {
    private Long userKey;

    private Long siteNum;

    private String userName;

    private String firstName;

    private String lastName;

    private String description;

    private String category;

    private Long passwordModifiable;

    private String status;

    private Date statusChangeTime;

    private Date statusChangeTimeU;

    private String statusChangeTimeZ;

    private String password;
    
    private String newPassword;

    private BigDecimal formKey;

    private BigDecimal imageKey;

    private BigDecimal noteKey;

    private Date userExpiration;

    private Date userExpirationU;

    private String userExpirationZ;

    private Date passwordExpiration;

    private Date passwordExpirationU;

    private String passwordExpirationZ;

    private Long passwordDuration;

    private String passwordHistory;

    private BigDecimal creatorKey;

    private Date creationTime;

    private Date creationTimeU;

    private String creationTimeZ;

    private BigDecimal lastModifierKey;

    private Date lastModifiedTime;

    private Date lastModifiedTimeU;

    private String lastModifiedTimeZ;

    private Long loginCount;

    private String emailAddress;

    private String uda0;

    private String uda1;

    private String uda2;

    private String uda3;

    private String uda4;

    private Long xfrInsertPid;

    private Long xfrUpdatePid;

    private String securityMask;

    private Long failedLoginAttemptCount;

    private String trxId;

    private String securityRealm;

    private Long unusedPrivilege;

    private BigDecimal updatePrivilegeKey;

    private BigDecimal deletePrivilegeKey;

    private BigDecimal shiftKey;

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Long getSiteNum() {
        return siteNum;
    }

    public void setSiteNum(Long siteNum) {
        this.siteNum = siteNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
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

    public Long getPasswordModifiable() {
        return passwordModifiable;
    }

    public void setPasswordModifiable(Long passwordModifiable) {
        this.passwordModifiable = passwordModifiable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Date statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public Date getStatusChangeTimeU() {
        return statusChangeTimeU;
    }

    public void setStatusChangeTimeU(Date statusChangeTimeU) {
        this.statusChangeTimeU = statusChangeTimeU;
    }

    public String getStatusChangeTimeZ() {
        return statusChangeTimeZ;
    }

    public void setStatusChangeTimeZ(String statusChangeTimeZ) {
        this.statusChangeTimeZ = statusChangeTimeZ == null ? null : statusChangeTimeZ.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public BigDecimal getFormKey() {
        return formKey;
    }

    public void setFormKey(BigDecimal formKey) {
        this.formKey = formKey;
    }

    public BigDecimal getImageKey() {
        return imageKey;
    }

    public void setImageKey(BigDecimal imageKey) {
        this.imageKey = imageKey;
    }

    public BigDecimal getNoteKey() {
        return noteKey;
    }

    public void setNoteKey(BigDecimal noteKey) {
        this.noteKey = noteKey;
    }

    public Date getUserExpiration() {
        return userExpiration;
    }

    public void setUserExpiration(Date userExpiration) {
        this.userExpiration = userExpiration;
    }

    public Date getUserExpirationU() {
        return userExpirationU;
    }

    public void setUserExpirationU(Date userExpirationU) {
        this.userExpirationU = userExpirationU;
    }

    public String getUserExpirationZ() {
        return userExpirationZ;
    }

    public void setUserExpirationZ(String userExpirationZ) {
        this.userExpirationZ = userExpirationZ == null ? null : userExpirationZ.trim();
    }

    public Date getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(Date passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
    }

    public Date getPasswordExpirationU() {
        return passwordExpirationU;
    }

    public void setPasswordExpirationU(Date passwordExpirationU) {
        this.passwordExpirationU = passwordExpirationU;
    }

    public String getPasswordExpirationZ() {
        return passwordExpirationZ;
    }

    public void setPasswordExpirationZ(String passwordExpirationZ) {
        this.passwordExpirationZ = passwordExpirationZ == null ? null : passwordExpirationZ.trim();
    }

    public Long getPasswordDuration() {
        return passwordDuration;
    }

    public void setPasswordDuration(Long passwordDuration) {
        this.passwordDuration = passwordDuration;
    }

    public String getPasswordHistory() {
        return passwordHistory;
    }

    public void setPasswordHistory(String passwordHistory) {
        this.passwordHistory = passwordHistory == null ? null : passwordHistory.trim();
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

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
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

    public String getSecurityMask() {
        return securityMask;
    }

    public void setSecurityMask(String securityMask) {
        this.securityMask = securityMask == null ? null : securityMask.trim();
    }

    public Long getFailedLoginAttemptCount() {
        return failedLoginAttemptCount;
    }

    public void setFailedLoginAttemptCount(Long failedLoginAttemptCount) {
        this.failedLoginAttemptCount = failedLoginAttemptCount;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId == null ? null : trxId.trim();
    }

    public String getSecurityRealm() {
        return securityRealm;
    }

    public void setSecurityRealm(String securityRealm) {
        this.securityRealm = securityRealm == null ? null : securityRealm.trim();
    }

    public Long getUnusedPrivilege() {
        return unusedPrivilege;
    }

    public void setUnusedPrivilege(Long unusedPrivilege) {
        this.unusedPrivilege = unusedPrivilege;
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

    public BigDecimal getShiftKey() {
        return shiftKey;
    }

    public void setShiftKey(BigDecimal shiftKey) {
        this.shiftKey = shiftKey;
    }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
}