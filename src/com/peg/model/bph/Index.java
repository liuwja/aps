package com.peg.model.bph;

import java.util.Date;
import java.util.List;

/**
 * 考核指标
 * @author Administrator
 *
 */
public class Index {

	private Long indexKey;
	private Long itemKey;
	private String indexName;
	private String indexCode;
	private String indexDescription;
	private String mainKey;
	private String createUser;
    private Date createTime;
    private Date lastUpdateTime;
    private String lastUpdateUser;
    
    private Item items;
    private List<MonthAssessment> monthAssessments;
    
    
	public Long getIndexKey() {
		return indexKey;
	}
	public void setIndexKey(Long indexKey) {
		this.indexKey = indexKey;
	}
	public Long getItemKey() {
		return itemKey;
	}
	public void setItemKey(Long itemKey) {
		this.itemKey = itemKey;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getIndexDescription() {
		return indexDescription;
	}
	public void setIndexDescription(String indexDescription) {
		this.indexDescription = indexDescription;
	}
	public String getMainKey() {
		return mainKey;
	}
	public void setMainKey(String mainKey) {
		this.mainKey = mainKey;
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
	public List<MonthAssessment> getMonthAssessments() {
		return monthAssessments;
	}
	public void setMonthAssessments(List<MonthAssessment> monthAssessments) {
		this.monthAssessments = monthAssessments;
	}
	public Item getItems() {
		return items;
	}
	public void setItems(Item items) {
		this.items = items;
	}
 

	// 获取月度考核数量
	public int getMonthNum()
	{
		if (monthAssessments != null)
		{
			return monthAssessments.size();
		}
		return 0;
	}
    
}
