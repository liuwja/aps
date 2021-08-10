package com.peg.web.menu;

import java.util.List;

public class UiMenu {
	private String name;
	private String rowCode;
	private String parentRowCode;
	
	//1:accordion, 2:folder, 3:menu
	//从ConstantInterface.UIMENU_TYPE取值
	private int type;
	private List<Operation>  optList;
	

	public UiMenu(String name, String rowCode, String parentRowCode, int type) {
		this.name = name;
		this.rowCode = rowCode;
		this.parentRowCode = parentRowCode;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRowCode() {
		return rowCode;
	}
	public void setRowCode(String rowCode) {
		this.rowCode = rowCode;
	}
	public String getParentRowCode() {
		return parentRowCode;
	}
	public void setParentRowCode(String parentRowCode) {
		this.parentRowCode = parentRowCode;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Operation> getOptList() {
		return optList;
	}
	public void setOptList(List<Operation> optList) {
		this.optList = optList;
	}
	
}
