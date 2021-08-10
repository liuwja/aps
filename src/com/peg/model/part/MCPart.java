package com.peg.model.part;

public class MCPart {
	
	private Long id;
	
	private String mesPartNumber;
	
	private String mesPartName;
	
	private String crmPartNumber;
	
	private String crmPartName;
	
	private String user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMesPartNumber() {
		return mesPartNumber;
	}

	public void setMesPartNumber(String mesPartNumber) {
		this.mesPartNumber = mesPartNumber;
	}

	public String getMesPartName() {
		return mesPartName;
	}

	public void setMesPartName(String mesPartName) {
		this.mesPartName = mesPartName;
	}

	public String getCrmPartNumber() {
		return crmPartNumber;
	}

	public void setCrmPartNumber(String crmPartNumber) {
		this.crmPartNumber = crmPartNumber;
	}

	public String getCrmPartName() {
		return crmPartName;
	}

	public void setCrmPartName(String crmPartName) {
		this.crmPartName = crmPartName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
