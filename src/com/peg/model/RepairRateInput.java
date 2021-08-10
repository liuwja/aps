package com.peg.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RepairRateInput {
    private Long id;

    private String typeCategory;//机型类别

    private String repairMonth;//维修月份
    
    private Long repairedCount;//维修数
    
    private Long totalRepairedCount;//累计维修数
    
    private Long shipCount;//累计发货数
    
    private Long totalShipCount;//累计发货数

    private BigDecimal repairRate;//单月维修率
    
    private BigDecimal repairTotalRate;//累计维修率
    
    //预先设定的标准维修率
  	private Double baseRepairRate;
    
    private String createUser;

    private Date createTime;
    
    private List<RepairRateInput> rateResults;//过去一年的累计维修率
    
    
    
    
    private List<RepairRateInput> type;
    
    private List<RepairTotalrateInput> total;
    
    private int flag;
    
    private Long totalId;

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

    public String getRepairMonth() {
        return repairMonth;
    }

    public void setRepairMonth(String repairMonth) {
        this.repairMonth = repairMonth == null ? null : repairMonth.trim();
    }

    public BigDecimal getRepairRate() {
        return repairRate = repairRate == null ? new BigDecimal(0) : repairRate;
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Long getRepairedCount() {
		return repairedCount;
	}

	public void setRepairedCount(Long repairedCount) {
		this.repairedCount = repairedCount;
	}

	public Long getShipCount() {
		return shipCount;
	}

	public void setShipCount(Long shipCount) {
		this.shipCount = shipCount;
	}

	public BigDecimal getRepairTotalRate() {
		return repairTotalRate;
	}

	public void setRepairTotalRate(BigDecimal repairTotalRate) {
		this.repairTotalRate = repairTotalRate;
	}

	public List<RepairRateInput> getRateResults() {
		return rateResults;
	}

	public void setRateResults(List<RepairRateInput> rateResults) {
		this.rateResults = rateResults;
	}

	public List<RepairRateInput> getType() {
		return type;
	}

	public void setType(List<RepairRateInput> type) {
		this.type = type;
	}

	public List<RepairTotalrateInput> getTotal() {
		return total;
	}

	public void setTotal(List<RepairTotalrateInput> total) {
		this.total = total;
	}

	public Long getTotalId() {
		return totalId;
	}

	public void setTotalId(Long totalId) {
		this.totalId = totalId;
	}

	public Long getTotalRepairedCount() {
		return totalRepairedCount;
	}

	public void setTotalRepairedCount(Long totalRepairedCount) {
		this.totalRepairedCount = totalRepairedCount;
	}

	public Long getTotalShipCount() {
		return totalShipCount;
	}

	public void setTotalShipCount(Long totalShipCount) {
		this.totalShipCount = totalShipCount;
	}

	public Double getBaseRepairRate() {
		return baseRepairRate;
	}

	public void setBaseRepairRate(Double baseRepairRate) {
		this.baseRepairRate = baseRepairRate;
	}
}