package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

public class QprnSetting {
    private Long id;

    private String qprn;

    private Short scoreType;

    private BigDecimal weight;

    private BigDecimal score;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQprn() {
        return qprn;
    }

    public void setQprn(String qprn) {
        this.qprn = qprn == null ? null : qprn.trim();
    }

    public Short getScoreType() {
        return scoreType;
    }

    public void setScoreType(Short scoreType) {
        this.scoreType = scoreType;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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