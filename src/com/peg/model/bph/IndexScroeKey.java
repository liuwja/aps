package com.peg.model.bph;

import java.util.Date;

import com.peg.model.baseCommonVo;

public class IndexScroeKey extends baseCommonVo{
    private String factory;

    private String area;

    private String shiftGroupCode;
    
    private String shiftGroupName;

    private Date sumDate;

    private Long itemId;

    private Long indexId;

    
    
    public IndexScroeKey()
	{
	}

	public IndexScroeKey(String factory, String area, String shiftGroupCode,String shiftGroupName, Date sumDate,
		Long itemId, Long indexId)
	{
		this.factory = factory;
		this.area = area;
		this.shiftGroupCode = shiftGroupCode;
		this.shiftGroupName = shiftGroupName;
		this.sumDate = sumDate;
		this.itemId = itemId;
		this.indexId = indexId;
	}

	public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getShiftGroupCode() {
        return shiftGroupCode;
    }

    public void setShiftGroupCode(String shiftGroupCode) {
        this.shiftGroupCode = shiftGroupCode == null ? null : shiftGroupCode.trim();
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

	public String getShiftGroupName() {
		return shiftGroupName;
	}

	public void setShiftGroupName(String shiftGroupName) {
		this.shiftGroupName = shiftGroupName;
	}
    
    
}