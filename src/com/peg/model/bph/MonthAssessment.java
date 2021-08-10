package com.peg.model.bph;

import java.util.Date;
import java.util.List;

import com.peg.model.baseCommonVo;

/**
 * 月份基准目标设定
 * @author Administrator
 *
 */
public class MonthAssessment extends baseCommonVo{

	private Long maKey;
	private Long indexKey;
	private String monthly;
	private Double itemScale;
	private Double indexScale;
	private String indexMainkey;
	private Double baseValue;
	private Double targetValue;
	private String createUser;
    private Date createTime;
    private String lastUpdateUser;
    private Date lastUpdateTime;
    private Long groupKey;

    private List<MonthAssessment> monList;
    private Group group;
    
    private float indexActValue;
    private float indexScore;
    private String urlStr;
    private boolean flag;
    private String queryMonth;
    private String factory;
    
	public Long getMaKey() {
		return maKey;
	}
	public void setMaKey(Long maKey) {
		this.maKey = maKey;
	}
	public Long getIndexKey() {
		return indexKey;
	}
	public void setIndexKey(Long indexKey) {
		this.indexKey = indexKey;
	}
	public String getMonthly() {
		return monthly;
	}
	public void setMonthly(String monthly) {
		this.monthly = monthly;
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
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(Long groupKey) {
		this.groupKey = groupKey;
	}
	public Double getItemScale() {
		return itemScale;
	}
	public void setItemScale(Double itemScale) {
		this.itemScale = itemScale;
	}
	public Double getIndexScale() {
		return indexScale;
	}
	public void setIndexScale(Double indexScale) {
		this.indexScale = indexScale;
	}
	public String getIndexMainkey() {
		return indexMainkey;
	}
	public void setIndexMainkey(String indexMainkey) {
		this.indexMainkey = indexMainkey;
	}
	public Double getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Double baseValue) {
		this.baseValue = baseValue;
	}
	public Double getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(Double targetValue) {
		this.targetValue = targetValue;
	}
	public List<MonthAssessment> getMonList() {
		return monList;
	}
	public void setMonList(List<MonthAssessment> monList) {
		this.monList = monList;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public float getIndexActValue() {
		return indexActValue;
	}
	public void setIndexActValue(float indexActValue) {
		this.indexActValue = indexActValue;
	}
	public float getIndexScore() {
		return indexScore;
	}
	public void setIndexScore(float indexScore) {
		this.indexScore = indexScore;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getQueryMonth() {
		return queryMonth;
	}
	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
    
    
}
