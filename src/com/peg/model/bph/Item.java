package com.peg.model.bph;

import java.util.Date;
import java.util.List;

import com.peg.model.baseCommonVo;

/**
 * 考核项目类
 * @author Administrator
 *
 */
public class Item extends baseCommonVo{

	private Long itemKey;
	private Long gcKey;
	private String itemCode;
	private String itemName;
	private String createUser;
    private Date createTime;
    private Date lastUpdateTime;
    private String lastUpdateUser;
    private List<Index> uiindexs;
    private GroupCategory groupCategory;
    
    private Float itemScore;
    private double itemScale;
    
	public Long getItemKey() {
		return itemKey;
	}
	public void setItemKey(Long itemKey) {
		this.itemKey = itemKey;
	}
	public Long getGcKey() {
		return gcKey;
	}
	public void setGcKey(Long gcKey) {
		this.gcKey = gcKey;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	
	public GroupCategory getGroupCategory() {
		return groupCategory;
	}
	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}
	public List<Index> getUiindexs() {
		return uiindexs;
	}
	public void setUiindexs(List<Index> uiindexs) {
		this.uiindexs = uiindexs;
	}
	
	public Float getItemScore() {
		return itemScore;
	}
	public void setItemScore(Float itemScore) {
		this.itemScore = itemScore;
	}
	
	public double getItemScale() {
		return itemScale;
	}
	public void setItemScale(double itemScale) {
		this.itemScale = itemScale;
	}
	// 获取考核指标数量
	public int getIndexNum()
	{
		if (uiindexs != null)
		{
			return uiindexs.size();
		}
		return 0;
	}
    
	
	//获取index数量
	public int getMonthNum()
	{
		int len = 0;
		if(uiindexs != null)
		{
			for(Index rs : uiindexs)
			{
				if(rs.getMonthNum()!= 0){
					len = len + rs.getMonthNum()-1;
				}			
			}
			len = len + uiindexs.size();
		}
		return len;
	}
}
