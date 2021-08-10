package com.peg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaultReason {
	private Long id;

	private String code;

	private String name;

	private String valid;

	private Date createTime;

	private String createUser;

	private Date lastUpdateTime;

	private String lastUpdateUser;

	private String meshFaultName;
	
	private String meshFaultNameArr[];

	private String meshFaultCode;
	
	private String groupName;
	
	private String keys;
	
	private String productType;
	
	private String count;
	
	private String lastUpdateType;
	
	private List<FaultReason> faultList = new ArrayList<FaultReason>(0);

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMeshFaultName() {
		return meshFaultName;
	}

	public void setMeshFaultName(String meshFaultName) {
		this.meshFaultName = meshFaultName;
	}

	public String[] getMeshFaultNameArr() {
		return meshFaultNameArr;
	}

	public void setMeshFaultNameArr(String[] meshFaultNameArr) {
		this.meshFaultNameArr = meshFaultNameArr;
	}
	
	public String getMeshFaultCode() {
		return meshFaultCode;
	}

	public void setMeshFaultCode(String meshFaultCode) {
		this.meshFaultCode = meshFaultCode;
	}

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
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid == null ? null : valid.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}
	
	public String getLastUpdateType() {
		return lastUpdateType;
	}

	public void setLastUpdateType(String lastUpdateType) {
		this.lastUpdateType = lastUpdateType;
	}

	public List<FaultReason> getFaultList() {
		return faultList;
	}

	public void setFaultList(List<FaultReason> faultList) {
		this.faultList = faultList;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}