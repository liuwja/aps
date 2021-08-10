package com.peg.model.part;

import java.util.Date;

public class ErpPartReturn {
    private Long returnKey;

    private String partType;

    private String partClass;

    private String partNumber;

    private String partName;

    private String supplierNumber;

    private String supplierName;

    private String returnDate;

    private Long returnNumber;

    private String wareHouse;

    private String productMaturity;

    private String productionType;

    private Date creationTime;

    private String lotNumber;

    private Long totalQty;

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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate == null ? null : returnDate.trim();
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
}