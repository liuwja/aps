package com.peg.model;


public class Location {
    private Long id;

    private String locationCode;

    private String location;
    
    private String province;
    
    private String navId;

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
		this.locationCode = locationCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getNavId() {
		return navId;
	}

	public void setNavId(String navId) {
		this.navId = navId;
	}
	
	
}