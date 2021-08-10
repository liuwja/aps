package com.peg.model.system;

import java.util.Date;

public class GroupPrivilege {
    private Long groupPrivilegeKey;

    private Long groupKey;

    private Long privilegeKey;

    private Date creationTime;

    private Date lastModifiedTime;

    
    public GroupPrivilege() {
		super();
	}

	public GroupPrivilege(Long groupKey, Long privilegeKey) {
		super();
		this.groupKey = groupKey;
		this.privilegeKey = privilegeKey;
	}

	public Long getGroupPrivilegeKey() {
        return groupPrivilegeKey;
    }

    public void setGroupPrivilegeKey(Long groupPrivilegeKey) {
        this.groupPrivilegeKey = groupPrivilegeKey;
    }

    public Long getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(Long groupKey) {
        this.groupKey = groupKey;
    }

    public Long getPrivilegeKey() {
        return privilegeKey;
    }

    public void setPrivilegeKey(Long privilegeKey) {
        this.privilegeKey = privilegeKey;
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
}