package com.peg.model.part;

import java.util.Date;

public class WareHouseProductRef {
    private Long wareRefKey;

    private String wareNumber;

    private String wareName;

    private String prouctNumber;

    private String productName;

    private Date creationTime;

    private Date lastModifyTime;

    private String createUser;

    public Long getWareRefKey() {
        return wareRefKey;
    }

    public void setWareRefKey(Long wareRefKey) {
        this.wareRefKey = wareRefKey;
    }

    public String getWareNumber() {
        return wareNumber;
    }

    public void setWareNumber(String wareNumber) {
        this.wareNumber = wareNumber == null ? null : wareNumber.trim();
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName == null ? null : wareName.trim();
    }

    public String getProuctNumber() {
        return prouctNumber;
    }

    public void setProuctNumber(String prouctNumber) {
        this.prouctNumber = prouctNumber == null ? null : prouctNumber.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }
}