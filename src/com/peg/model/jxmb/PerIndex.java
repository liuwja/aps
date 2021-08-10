package com.peg.model.jxmb;

import java.util.Date;

/**
 * 考核指标
 * @author Administrator
 *
 */
public class PerIndex {
    private Long indexKey;

    private Long itemKey;

    private String indexName;

    private String indexCode;

    private String indexDescription;

    private String mainkey;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;
    


    public Long getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(Long indexKey) {
        this.indexKey = indexKey;
    }

    public Long getItemKey() {
        return itemKey;
    }

    public void setItemKey(Long itemKey) {
        this.itemKey = itemKey;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode == null ? null : indexCode.trim();
    }

    public String getIndexDescription() {
        return indexDescription;
    }

    public void setIndexDescription(String indexDescription) {
        this.indexDescription = indexDescription == null ? null : indexDescription.trim();
    }

    public String getMainkey() {
        return mainkey;
    }

    public void setMainkey(String mainkey) {
        this.mainkey = mainkey == null ? null : mainkey.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    
}