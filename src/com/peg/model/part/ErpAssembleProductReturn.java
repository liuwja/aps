package com.peg.model.part;

import java.util.Date;

public class ErpAssembleProductReturn {
    private Long returnKey;

    private String partType;

    private String partClass;

    private String partNumber;

    private String partName;

    private String supplierNumber;

    private String supplierName;

    private Date returnDate;

    private Long returnNumber;

    private String wareHouse;

    private String productMaturity;

    private String productionType;

    private Date creationTime;

    private String productName;

    private String productNumber;

    private String lotNumber;

    private Long totalQty;
    
    private String part_key; 
    private String consumption_type; 
    private String account_key; 


    public Long getReturnKey() {
        return returnKey;
    }

    public void setReturnKey(Long returnKey) {
        this.returnKey = returnKey;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType == null ? null : partType.trim();
    }

    public String getPartClass() {
        return partClass;
    }

    public void setPartClass(String partClass) {
        this.partClass = partClass == null ? null : partClass.trim();
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber == null ? null : partNumber.trim();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber == null ? null : supplierNumber.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(Long returnNumber) {
        this.returnNumber = returnNumber;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse == null ? null : wareHouse.trim();
    }

    public String getProductMaturity() {
        return productMaturity;
    }

    public void setProductMaturity(String productMaturity) {
        this.productMaturity = productMaturity == null ? null : productMaturity.trim();
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType == null ? null : productionType.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber == null ? null : productNumber.trim();
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    public Long getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Long totalQty) {
        this.totalQty = totalQty;
    }

	public String getPart_key() {
		return part_key;
	}

	public void setPart_key(String part_key) {
		this.part_key = part_key;
	}

	public String getConsumption_type() {
		return consumption_type;
	}

	public void setConsumption_type(String consumption_type) {
		this.consumption_type = consumption_type;
	}

	public String getAccount_key() {
		return account_key;
	}

	public void setAccount_key(String account_key) {
		this.account_key = account_key;
	}
    
    
}