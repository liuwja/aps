package com.peg.model.system;

import java.util.Date;

public class SysLoginHistory {
	
	private Long rowId;
	
    private Long id;

    private String sessionid;

    private Long userKey;

    private String userName;

    private String userDescription;

    private String loginIp;

    private Short logoutType;

    private Date loginTime;

    private Date logoutTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription == null ? null : userDescription.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Short getLogoutType() {
        return logoutType;
    }

    public void setLogoutType(Short logoutType) {
        this.logoutType = logoutType;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
    
    
}