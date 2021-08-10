package com.peg.model.bph;

import java.util.List;

import com.peg.model.baseCommonVo;

/**
 * 班组类别
 * @author Administrator
 *
 */
public class GroupCategory extends baseCommonVo{

	private Long gcKey;
	private String factory;
	private String area;
	private String category;

	private List<Item> item ;
	private Group uigroup;
	
	public Long getGcKey() {
		return gcKey;
	}
	public void setGcKey(Long gcKey) {
		this.gcKey = gcKey;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
	public Group getUigroup() {
		return uigroup;
	}
	public void setUigroup(Group uigroup) {
		this.uigroup = uigroup;
	}
	//获取index数量
	public int getIndexNum()
	{
		int len = 0;
		if(item != null)
		{
			for(Item rs : item)
			{
				if(rs.getIndexNum()!= 0){
					len = len + rs.getIndexNum()-1;
				}			
			}
			len = len + item.size();
		}
		return len;
	}
	
	//获取index数量
	public int getMonthNum()
	{
		int len = 0;
		if(item != null)
		{
			for(Item rs : item)
			{
				if(rs.getMonthNum()!= 0){
					len = len + rs.getMonthNum()-1;
				}			
			}
			len = len + item.size();
		}
		return len;
	}
}
