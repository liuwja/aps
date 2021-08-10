package com.peg.model.system;

import java.util.Date;

public class SysPrivilege {
	private Long privilegeKey;

    private String menuName;

    private String menuCode;

    private String operationName;

    private String operationCode;

    private Date creationTime;

    private Date lastModifiedTime;

    private String uda0;

    private String uda1;

    private String uda2;

    public Long getPrivilegeKey() {
        return privilegeKey;
    }

    public void setPrivilegeKey(Long privilegeKey) {
        this.privilegeKey = privilegeKey;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode == null ? null : operationCode.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
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
}