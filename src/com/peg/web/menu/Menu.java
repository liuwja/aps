/*
 * @(#) Menu.java 2014-3-2 下午02:52:17
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-2 下午02:52:17
 */
public class Menu  implements Cloneable  
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
	 * 打开的tabID
	 */
	private String tabId;
	
	/**
	 * 权限代码
	 */
	private String permissionCode;
	
	/**
	 * 菜单打开方式。0：navTab; 1:_blank.默认为navTab
	 */
	private int targetType;
	
	/**
	 * 权限列表，1、看(view);2、增(add);3、改(edit);4、删(del).多个则以逗号分隔
	 */
	private String commonOptions;
	
	/**
	 * 是否显示。1为显示；0为不显示。默认显示
	 */
	private int display;

	private List<Operation>  optList = new ArrayList<Operation>();
	
	private boolean  fresh;
	
	@Override
	public String toString()
	{
		return "\n@Menu [ name=" + name + ", display=" + display + ",permissionCode=" + permissionCode
			+ ", commonOptions=" + commonOptions + ", tabId=" + tabId + ", targetType="
			+ targetType + ", url=" + url + "]";
	}

	private String getTarget(int targetType)
	{
		return targetType == 0 ? "navTab" : "_blank";
	}
	
	public String getHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<li><a href=");
		sb.append("\"").append(url).append("\" ");
		sb.append("target=\"").append(getTarget(targetType)).append("\" ");
		sb.append("rel=\"").append(tabId).append("\" ");
		sb.append("title=\"").append(name).append("\" ");
		sb.append("fresh=\"").append(fresh).append("\" ");
		sb.append("> ");
		sb.append(name);
		sb.append("</a></li> ");
//		<li><a href="base/maincontent.sp?method=page1" target="navTab" rel="showUsers4" >车间作业计划</a></li>
		return sb.toString();
	}
	
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

	public String getTabId()
	{
		return tabId;
	}

	public void setTabId(String tabId)
	{
		this.tabId = tabId;
	}

	public String getPermissionCode()
	{
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode)
	{
		this.permissionCode = permissionCode;
	}

	public int getTargetType()
	{
		return targetType;
	}

	public void setTargetType(int targetType)
	{
		this.targetType = targetType;
	}


	public String getCommonOptions() {
		return commonOptions;
	}

	public void setCommonOptions(String commonOptions) {
		this.commonOptions = commonOptions;
		Operation op = null;
		if(StringUtils.isNotBlank(commonOptions))
		{
			String[] arr = commonOptions.split(",");
			OperatorEnum optEnum = null;
			for(String s : arr)
			{
				op = new Operation();
				optEnum = OperatorEnum.getEnum(s);
				op.setPermissionCode(getPermissionCode() + ":" + optEnum);
				op.setName(optEnum.getName());
				optList.add(op);
			}
		}
		else
		{
			op = new Operation();
			op.setName(OperatorEnum.VIEW.getName());
			op.setPermissionCode(getPermissionCode() + ":" + OperatorEnum.VIEW);
			optList.add(op);
		}
	}

	public List<String> getPermissionCodeList()
	{
		List<String> pcodeList = new ArrayList<String>();
		if(StringUtils.isNotBlank(commonOptions))
		{
			String[] arr = commonOptions.split(",");
			OperatorEnum optEnum = null;
			for(String s : arr)
			{
				optEnum = OperatorEnum.getEnum(s);
				pcodeList.add(getPermissionCode() + ":" + optEnum);
			}
		}
		if(null != optList)
		{
			for(Operation op : optList)
			{
				pcodeList.add(getPermissionCode() + ":" + op.getPermissionCode());
				//TODO url这里暂时不配置
			}
		}
		return pcodeList;
	}
	
	public int getDisplay()
	{
		return display;
	}

	public void setDisplay(int display)
	{
		this.display = display;
	}

	@Override
	protected Menu clone()
		throws CloneNotSupportedException
	{
		Menu m = null;
        try    
        {   
        	m = (Menu)super.clone();
        	if(optList != null && !optList.isEmpty())
        	{
        		m.optList = new ArrayList<Operation>();
        		for(Operation opt : optList)
        		{
        			m.optList.add(opt.clone());
        		}
        	}
        }    
        catch(CloneNotSupportedException e)    
        {    
            System.out.println(e.toString());    
        }  
        
		return m;
	}

	public List<Operation> getOptList()
	{
		return optList;
	}

	public void setOptList(List<Operation> optList)
	{
		this.optList = optList;
	}

	public boolean isFresh()
	{
		return fresh;
	}

	public void setFresh(boolean fresh)
	{
		this.fresh = fresh;
	}
}
