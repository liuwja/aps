package com.peg.model;

import java.util.Date;

public class LocationRegion {
    private Long id;

    private String locationCode;

    private String location;

    private String region;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;
    
    private String province;
    
    private String mergeRegion;
    
    private String keys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    
    public String getProvince() {
    	return province;
    }
    
    public void setProvince(String province) {
    	this.province = province;
    }
    
    public String getMergeRegion() {
    	return mergeRegion;
    }
    
    public void setMergeRegion(String mergeRegion) {
    	this.mergeRegion = mergeRegion;
    }
    
    public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
}