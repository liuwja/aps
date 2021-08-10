package com.peg.model.system;

import java.util.Date;
import java.util.List;

import com.peg.model.UserGroup;

public class SysGroup {
	private Long groupKey;

	private String groupCode;

	private String groupName;

	private Date creationTime;

	private Date lastModifiedTime;

	private List<SysMesUser> userList;

	private List<UserGroup> ugList;

	public Long getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(Long groupKey) {
		this.groupKey = groupKey;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public List<SysMesUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SysMesUser> userList) {
		this.userList = userList;
	}

	public List<UserGroup> getUgList() {
		return ugList;
	}

	public void setUgList(List<UserGroup> ugList) {
		this.ugList = ugList;
	}

}