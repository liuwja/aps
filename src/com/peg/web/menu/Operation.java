/*
 * @(#) Operation.java 2014-3-5 下午04:39:46
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.List;

/**
 * 每个menu对应页面的操作（权限设置页面使用）
 * <p>
 * @author Lin, 2014-3-5 下午04:39:46
 */
public class Operation  implements Cloneable  
{
	/**
	 * 目录名称
	 */
	private String name;
	
	/**
	 * 菜单路径,如果为空则默认为base/maincontent.sp?method=underConstruction
	 */
	private String url;
	
	/**
	 * 权限代码
	 */
	private String permissionCode;
	
	
	/**
	 * 是否显示。1为显示；0为不显示。默认显示
	 */
	private int display;

//	private String permissionName;
	
	private List<Url>  containUrls;
	
	//用于前台展示，是否选中
	private boolean selected;

	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getUrl()
	{
		return url;
	}


	public void setUrl(String url)
	{
		this.url = url;
	}


	public String getPermissionCode()
	{
		return permissionCode;
	}


	public void setPermissionCode(String permissionCode)
	{
		this.permissionCode = permissionCode;
	}


	public int getDisplay()
	{
		return display;
	}


	public void setDisplay(int display)
	{
		this.display = display;
	}


//	public String getPermissionName()
//	{
//		return permissionName;
//	}
//
//
//	public void setPermissionName(String permissionName)
//	{
//		this.permissionName = permissionName;
//	}


	@Override
	protected Operation clone()
		throws CloneNotSupportedException
	{
		return (Operation)super.clone();
	}
	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public List<Url> getContainUrls() {
		return containUrls;
	}


	public void setContainUrls(List<Url> containUrls) {
		this.containUrls = containUrls;
	}

	
}
