package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

public class ItemScore extends ItemScoreKey {
	
	
    public ItemScore()
	{
	}

	/**
	 * @param factory
	 * @param area
	 * @param shiftGroupCode
	 * @param sumDate
	 * @param itemId
	 */
	public ItemScore(String factory, String area, String shiftGroupCode,String shiftGroupName, Date sumDate, Long itemId)
	{
		super(factory, area, shiftGroupCode,shiftGroupName, sumDate, itemId);
	}


    private BigDecimal itemScore;

    private Date insertTime;

    private Date lastUpdateTime;
    
    private String startMonth;
    
    private String endMonth;


    public BigDecimal getItemScore() {
        return itemScore;
    }

    public void setItemScore(BigDecimal itemScore) {
        this.itemScore = itemScore;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
    
}