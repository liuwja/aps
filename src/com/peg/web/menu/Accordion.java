/*
 * @(#) Accordion.java 2014-3-2 下午02:52:08
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-2 下午02:52:08
 */
public class Accordion  implements Cloneable  
{

	/**
	 * 是否显示。1为显示；0为不显示。默认显示
	 */
	private int display;
	
	/**
	 * 目录名称
	 */
	private String name;
	
	/**
	 * 权限代码
	 */
	private String permissionCode;
	
	/**
	 * 菜单图标名称,默认为folder
	 */
	private String iconName;
	
	private List<Menu> menuList;
	
	private List<Folder> folderList;

	private int systemType;
	
	public boolean isEmptyChild()
	{
		return menuList.isEmpty() && isAllEmpty(folderList);
	}
	private boolean isAllEmpty(List<Folder> fList)
	{
		for(Folder f : folderList)
		{
			if(!f.isEmptyChild())
			{
				return false;
			}
		}
		return true;
	}
	public Accordion()
	{
		this.menuList = new ArrayList<Menu>();
		this.folderList = new ArrayList<Folder>();
	}

	public int getDisplay()
	{
		return display;
	}

	public void setDisplay(int display)
	{
		this.display = display;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPermissionCode()
	{
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode)
	{
		this.permissionCode = permissionCode;
	}

	public String getIconName()
	{
		return iconName;
	}

	public void setIconName(String iconName)
	{
		this.iconName = iconName;
	}

	public List<Menu> getMenuList()
	{
		return menuList;
	}

	public void setMenuList(List<Menu> menuList)
	{
		this.menuList = menuList;
	}

	public List<Folder> getFolderList()
	{
		return folderList;
	}

	public void setFolderList(List<Folder> folderList)
	{
		this.folderList = folderList;
	}

	@Override
	public String toString()
	{
		return "\n>>Accordion [name=" + name + " ,display=" + display +", permissionCode="
		+ permissionCode +  ", iconName="
		+ iconName + "\n   folderList=" + folderList + "\n   menuList=" + menuList  + "]";
	}
 
	public String getHtml()
	{
		StringBuilder sb = new StringBuilder();
//        <div class="accordionHeader">
//        <h2><span>Folder</span><%=acc.getName() %></h2>
//    </div>
//    <div class="accordionContent">
//        <ul class="tree treeFolder">
//            <% List<Folder> folderList = acc.getFolderList(); 
//               List<Menu> menuList = acc.getMenuList();
//            %>
//        </ul>
//    </div>  
		
		sb.append("<div class=\"accordionHeader\">");
		sb.append("<h2><span>"+iconName+"</span>"+name+"</h2>");
		sb.append("</div>");
		
		sb.append("<div class=\"accordionContent\">");
		sb.append("<ul class=\"tree treeFolder\">");
		sb.append(getFolderHtml(folderList));
		sb.append(getMenuListHtml(menuList));
		sb.append("</ul>");
		sb.append("</div>");
		
		return sb.toString();
	}
	
	
	public String getFolderHtml(List<Folder> folderList)
	{
		StringBuilder sb = new StringBuilder();
		for(Folder f : folderList)
		{
			if(f.getDisplay() == 0)
			{
				sb.append("<li><a>").append(f.getName());
				sb.append("</a><ul>");
				sb.append(getFolderHtml(f.getFolderList()));
				sb.append(getMenuListHtml(f.getMenuList()));
				sb.append("</ul>");
				sb.append("</li>");
			}
		}
		return sb.toString();
	}
	
	public String getMenuListHtml(List<Menu> menuList)
	{
		StringBuilder sb = new StringBuilder();
		for(Menu m : menuList)
		{
			if(m.getDisplay() == 0)
			{
				sb.append(m.getHtml());
			}
		}
		return sb.toString();
	}

	@Override
	protected Accordion clone()
	{
		Accordion a = null;
        try    
        {    
            a = (Accordion)super.clone();    
            a.folderList = new ArrayList<Folder>();
            a.menuList = new ArrayList<Menu>();
            for(Folder fld : folderList)
            {
            	a.folderList.add(fld.clone());
            }
            for(Menu m : menuList)
            {
            	a.menuList.add(m.clone());
            }
        }    
        catch(CloneNotSupportedException e)    
        {    
            System.out.println(e.toString());    
        }   
        return a;
	}
	public int getSystemType()
	{
		return systemType;
	}
	public void setSystemType(int systemType)
	{
		this.systemType = systemType;
	}
}
