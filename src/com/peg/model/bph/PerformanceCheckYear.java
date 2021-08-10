package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

public class PerformanceCheckYear {
    private Long id;

    private Date checkYear;

    private String factory;

    private String checkIndexName;

    private BigDecimal baseValueYear;

    private BigDecimal targetValueYear;

    private BigDecimal targetValueHalfyear;

    private BigDecimal depressRateYear;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;
    
    private String queryYear;
    
    private String queryMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckYear() {
        return checkYear;
    }

    public void setCheckYear(Date checkYear) {
        this.checkYear = checkYear;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getCheckIndexName() {
        return checkIndexName;
    }

    public void setCheckIndexName(String checkIndexName) {
        this.checkIndexName = checkIndexName == null ? null : checkIndexName.trim();
    }

    public BigDecimal getBaseValueYear() {
        return baseValueYear;
    }

    public void setBaseValueYear(BigDecimal baseValueYear) {
        this.baseValueYear = baseValueYear;
    }

    public BigDecimal getTargetValueYear() {
        return targetValueYear;
    }

    public void setTargetValueYear(BigDecimal targetValueYear) {
        this.targetValueYear = targetValueYear;
    }

    public BigDecimal getTargetValueHalfyear() {
        return targetValueHalfyear;
    }

    public void setTargetValueHalfyear(BigDecimal targetValueHalfyear) {
        this.targetValueHalfyear = targetValueHalfyear;
    }

    public BigDecimal getDepressRateYear() {
        return depressRateYear;
    }

    public void setDepressRateYear(BigDecimal depressRateYear) {
        this.depressRateYear = depressRateYear;
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

	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}
    
    
}