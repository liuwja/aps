/*
 * @(#) Folder.java 2014-3-2 下午02:52:44
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录
 * <p>
 * @author Lin, 2014-3-2 下午02:52:44
 */
public class Folder  implements Cloneable  
{
	/**
	 * 目录名称
	 */
	private String name;
	
	/**
	 * 是否显示。0为显示；1为不显示。默认显示(0)
	 */
	private int display;
	
	private int systemType;
	
	private List<Menu> menuList;
	
	private List<Folder> folderList;
	
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
	
	public Folder()
	{
		this.menuList = new ArrayList<Menu>();
		this.folderList = new ArrayList<Folder>();
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getDisplay()
	{
		return display;
	}
	public void setDisplay(int display)
	{
		this.display = display;
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
		return "\n#Folder [ name=" + name + ", display=" + display +  "\n  folderList=" + folderList + "\n    menuList="
			+ menuList + "]";
	}
	@Override
	protected Folder clone()
	{
		Folder f = null;
        try    
        {    
            f = (Folder)super.clone();    
            f.folderList = new ArrayList<Folder>();
            f.menuList = new ArrayList<Menu>();
            for(Folder fld : folderList)
            {
            	f.folderList.add(fld.clone());
            }
            for(Menu m : menuList)
            {
            	f.menuList.add(m.clone());
            }
        }    
        catch(CloneNotSupportedException e)    
        {    
            System.out.println(e.toString());    
        }   
        
		return f;
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
