package com.peg.model;


public class Manage {
	private Long id;
	private String code;
	
	private String codeName;
	private String codeValue;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code== null ? null : code.trim();
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName== null ? null : codeName.trim();
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue== null ? null : codeValue.trim();
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime== null ? null : createTime.trim();
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser== null ? null : createUser.trim();
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime== null ? null : updateTime.trim();
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser== null ? null : updateUser.trim();
	}
	
}
	
