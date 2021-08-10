package com.peg.model;

public class ProductionLine {
	private String factory;
	private String factoryNumber;
	private String area;
	private String productionLineNumber;
	private String productionLineName;
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactoryNumber() {
		return factoryNumber;
	}
	public void setFactory_number(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getProductionLineNumber() {
		return productionLineNumber;
	}
	public void setProductionLineNumber(String productionLineNumber) {
		this.productionLineNumber = productionLineNumber;
	}
	public String getProductionLineName() {
		return productionLineName;
	}
	public void setProductionLineName(String productionLineName) {
		this.productionLineName = productionLineName;
	}
	@Override
	public String toString() {
		return "ProductionLine [factory=" + factory + ", factoryNumber=" + factoryNumber + ", area=" + area
				+ ", productionLineNumber=" + productionLineNumber + ", productionLineName=" + productionLineName + "]";
	}
}
