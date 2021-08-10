package com.peg.model;

import java.math.BigDecimal;
import java.util.Date;

public class RepairTotalrateInput {
    private Long id;

    private String typeCategory;

    private BigDecimal repairRate;

    private String createUser;

    private Date createTime;
    
    private String insertMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory == null ? null : typeCategory.trim();
    }

    public BigDecimal getRepairRate() {
        return repairRate;
    }

    public void setRepairRate(BigDecimal repairRate) {
        this.repairRate = repairRate;
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

	public String getInsertMonth() {
		return insertMonth;
	}

	public void setInsertMonth(String insertMonth) {
		this.insertMonth = insertMonth;
	}
    
    
}