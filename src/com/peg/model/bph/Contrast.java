package com.peg.model.bph;

public class Contrast {
	private String itemkey;//考核项目key
	private String itemname;//考核项目名称
	private String indexkey;//考核指标key
	private String indexname;//考核项目name
	private String shiftgroupcode;//班组名称code
	private String shiftgroupname;//班组名称
	private Double indexactvalue;//考核指标实际值
	public String getItemkey() {
		return itemkey;
	}
	public void setItemkey(String itemkey) {
		this.itemkey = itemkey;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getIndexkey() {
		return indexkey;
	}
	public void setIndexkey(String indexkey) {
		this.indexkey = indexkey;
	}
	public String getIndexname() {
		return indexname;
	}
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	public String getShiftgroupcode() {
		return shiftgroupcode;
	}
	public void setShiftgroupcode(String shiftgroupcode) {
		this.shiftgroupcode = shiftgroupcode;
	}
	public String getShiftgroupname() {
		return shiftgroupname;
	}
	public void setShiftgroupname(String shiftgroupname) {
		this.shiftgroupname = shiftgroupname;
	}
	public Double getIndexactvalue() {
		return indexactvalue;
	}
	public void setIndexactvalue(Double indexactvalue) {
		this.indexactvalue = indexactvalue;
	}

}
