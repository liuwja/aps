package com.peg.model.part;

import java.util.Date;

public class NewPartRef {
    private Long partRefKey;

    private String oldPartNumber;

    private String newPartNumber;

    private String partName;

    private Date creationTime;

    private Date lastModifyTime;

    private String createUser;

    public Long getPartRefKey() {
        return partRefKey;
    }

    public void setPartRefKey(Long partRefKey) {
        this.partRefKey = partRefKey;
    }

    public String getOldPartNumber() {
        return oldPartNumber;
    }

    public void setOldPartNumber(String oldPartNumber) {
        this.oldPartNumber = oldPartNumber == null ? null : oldPartNumber.trim();
    }

    public String getNewPartNumber() {
        return newPartNumber;
    }

    public void setNewPartNumber(String newPartNumber) {
        this.newPartNumber = newPartNumber == null ? null : newPartNumber.trim();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
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