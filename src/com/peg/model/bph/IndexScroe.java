package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

public class IndexScroe extends IndexScroeKey{
	
	
	
    public IndexScroe()
	{
	}

	/**
	 * @param factory
	 * @param area
	 * @param shiftGroupCode
	 * @param sumDate
	 * @param itemId
	 * @param indexId
	 */
	public IndexScroe(String factory, String area, String shiftGroupCode,String shiftGroupName, Date sumDate,
		Long itemId, Long indexId)
	{
		super(factory, area, shiftGroupCode,shiftGroupName, sumDate, itemId, indexId);
	}


    private BigDecimal indexActValue;

    private BigDecimal indexScore;

    private Date insertTime;

    private Date lastUpdateTime;
    
    private String startTime;
    
    private String endTime;
    
    private String checkItem;
    
    private String checkIndex;
    
    private String indexCode;
    
    private double scale;
    
    private String mykey;
    
    private double baseValue;
    
    private double targetValue;
    
    private String group;
    
    private String date;
    
    private String homeScore;  //总分
    
    private String homeRank;   //排名


    public BigDecimal getIndexActValue() {
        return indexActValue;
    }

    public void setIndexActValue(BigDecimal indexActValue) {
        this.indexActValue = indexActValue;
    }

    public BigDecimal getIndexScore() {
        return indexScore;
    }

    public void setIndexScore(BigDecimal indexScore) {
        this.indexScore = indexScore;
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

	public String getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}

	public String getCheckIndex() {
		return checkIndex;
	}

	public void setCheckIndex(String checkIndex) {
		this.checkIndex = checkIndex;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public String getMykey() {
		return mykey;
	}

	public void setMykey(String mykey) {
		this.mykey = mykey;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}

	public double getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(double targetValue) {
		this.targetValue = targetValue;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}

	public String getHomeRank() {
		return homeRank;
	}

	public void setHomeRank(String homeRank) {
		this.homeRank = homeRank;
	}

 
}