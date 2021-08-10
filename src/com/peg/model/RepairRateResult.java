/*
 * @(#) RepairRateResult.java 2015-4-10 下午02:40:31
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.model;

/**
 * 维修率结果
 * <p>
 * @author Lin, 2015-4-10 下午02:40:31
 */
public class RepairRateResult
{
	//月份
	private String month;
	//机型类别
	private String productType;
	
	//累计发货数
	private Long totalShipCount = 0L;
	//累计维修数
	private Long totalRepairCount = 0L;
	
	/**
	 * 当月维修数
	 */
	private Long singleRepairCount = 0L;
	
	//累计维修率
	private double totalRepairRate;
	
	/**
	 * 累计维修率(格式为:12.3%)
	 */
	private String totalRepairRatePercent;
	
	//预先设定的标准维修率
	private double baseRepairRate;
	
	/**
	 * 单月维修率
	 */
	private double singleRepairRate;
	private String singleRepairRatePercent;
	
	/**
	 * 维修率超过设定的基准维修率则为true
	 */
	private boolean exceedRate;

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public Long getTotalShipCount()
	{
		return totalShipCount;
	}

	public void setTotalShipCount(Long totalShipCount)
	{
		this.totalShipCount = totalShipCount;
	}

	public Long getTotalRepairCount()
	{
		return totalRepairCount;
	}

	public void setTotalRepairCount(Long totalRepairCount)
	{
		this.totalRepairCount = totalRepairCount;
	}


	public Long getSingleRepairCount()
	{
		return singleRepairCount;
	}

	public void setSingleRepairCount(Long singleRepairCount)
	{
		this.singleRepairCount = singleRepairCount;
	}


	public double getBaseRepairRate()
	{
		return baseRepairRate;
	}

	public void setBaseRepairRate(double baseRepairRate)
	{
		this.baseRepairRate = baseRepairRate;
	}

	public boolean isExceedRate()
	{
		return exceedRate;
	}

	public void setExceedRate(boolean exceedRate)
	{
		this.exceedRate = exceedRate;
	}

	public double getTotalRepairRate()
	{
		return totalRepairRate;
	}

	public void setTotalRepairRate(double totalRepairRate)
	{
		this.totalRepairRate = totalRepairRate;
	}

	public double getSingleRepairRate()
	{
		return singleRepairRate;
	}

	public void setSingleRepairRate(double singleRepairRate)
	{
		this.singleRepairRate = singleRepairRate;
	}

	public String getTotalRepairRatePercent()
	{
		return totalRepairRatePercent;
	}

	public void setTotalRepairRatePercent(String totalRepairRatePercent)
	{
		this.totalRepairRatePercent = totalRepairRatePercent;
	}

	public String getSingleRepairRatePercent()
	{
		return singleRepairRatePercent;
	}

	public void setSingleRepairRatePercent(String singleRepairRatePercent)
	{
		this.singleRepairRatePercent = singleRepairRatePercent;
	}

	@Override
	public String toString()
	{
		return "RepairRateResult [baseRepairRate=" + baseRepairRate + ", exceedRate=" + exceedRate
			+ ", month=" + month + ", productType=" + productType + ", singleRepairCount="
			+ singleRepairCount + ", singleRepairRate=" + singleRepairRate
			+ ", singleRepairRatePercent=" + singleRepairRatePercent + ", totalRepairCount="
			+ totalRepairCount + ", totalRepairRate=" + totalRepairRate
			+ ", totalRepairRatePercent=" + totalRepairRatePercent + ", totalShipCount="
			+ totalShipCount + "]";
	}
	
}
