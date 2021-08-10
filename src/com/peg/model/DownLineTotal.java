package com.peg.model;

import java.util.Date;

public class DownLineTotal {
    private Long id;

    private String productType;

    private String statisticsMonth;

    private String partType;

    private String region;

    private String productlineNumber;

    private Long downlineCount;

    private Date createTime;
    
    private String productFamily;//产品系列
    //用于调节查询
    private String startTime;
    private String endTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(String statisticsMonth) {
        this.statisticsMonth = statisticsMonth == null ? null : statisticsMonth.trim();
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType == null ? null : partType.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getProductlineNumber() {
        return productlineNumber;
    }

    public void setProductlineNumber(String productlineNumber) {
        this.productlineNumber = productlineNumber == null ? null : productlineNumber.trim();
    }

    public Long getDownlineCount() {
        return downlineCount;
    }

    public void setDownlineCount(Long downlineCount) {
        this.downlineCount = downlineCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	
}