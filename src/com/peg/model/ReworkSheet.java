package com.peg.model;


/**
 * 
 * <p>Title: ReworkSheet</p>
 * <p>Description: 返工停线单</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-9 上午9:55:15
 */
public class ReworkSheet {
    private String id;//序号
    private String factory;//工厂
    private String rework_number;//返工/停线单号
    private String workhour;//工时
    private String supplies_expense;//耗材费用
    private String money;//金额
    private String status;//状态
    private String creation_time;//开单日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getRework_number() {
		return rework_number;
	}
	public void setRework_number(String rework_number) {
		this.rework_number = rework_number;
	}
	public String getWorkhour() {
		return workhour;
	}
	public void setWorkhour(String workhour) {
		this.workhour = workhour;
	}
	public String getSupplies_expense() {
		return supplies_expense;
	}
	public void setSupplies_expense(String supplies_expense) {
		this.supplies_expense = supplies_expense;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
    
}