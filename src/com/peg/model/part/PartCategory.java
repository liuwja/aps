package com.peg.model.part;

public class PartCategory {
	private Long id; //ID
	private String categoryName;
	private String categoryTwoName;
	private String IdNameNumber;
	

	public String getIdNameNumber() {
		return IdNameNumber;
	}

	public void setIdNameNumber(String idNameNumber) {
		IdNameNumber = idNameNumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryTwoName() {
		return categoryTwoName;
	}

	public void setCategoryTwoName(String categoryTwoName) {
		this.categoryTwoName = categoryTwoName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
