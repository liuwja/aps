/*
 * @(#) BaseSearch.java 2014-9-3 下午07:16:51
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.dao.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * <p>
 * 
 * @author Lin, 2014-9-3 下午07:16:51
 */
public class BaseSearch
{
	private PageParameter page;

	private String sort;

	private String dir = "asc";

	private Map<String, Object> hashMap = new HashMap<String, Object>();
	
	private List<String> list; 
	private List<String> list2; 
	private List<String> list3; 

	public Map<String, Object> getHashMap()
	{
		return hashMap;
	}

	public void setHashMap(Map<String, Object> hashMap)
	{
		this.hashMap = hashMap;
	}

	public PageParameter getPage()
	{
		return page;
	}

	public void setPage(PageParameter page)
	{
		this.page = page;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

	public String getDir()
	{
		return dir;
	}

	public void setDir(String dir)
	{
		this.dir = dir;
	}
	
	public void put(String key, Object value)
	{
		if(value != null && value.toString().trim() != "" )
		{
			hashMap.put(key, value);
		}
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<String> getList2() {
		return list2;
	}

	public void setList2(List<String> list2) {
		this.list2 = list2;
	}

	public List<String> getList3() {
		return list3;
	}

	public void setList3(List<String> list3) {
		this.list3 = list3;
	}
	
}