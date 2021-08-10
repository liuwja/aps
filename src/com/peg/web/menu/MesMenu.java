/*
 * @(#) MesMenu.java 2014-3-3 上午10:55:07
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-3 上午10:55:07
 */
public class MesMenu  implements Cloneable  
{
	private static MesMenu mesMenu;  
	private Accordion navMenu;
	private List<Accordion> accordionList;
	private List<Menu> allMenus;
	
	private MesMenu() {
	}
	
	public static synchronized MesMenu getInstance() { 
		if(mesMenu == null)
		{
			mesMenu = new MesMenu();
		}
		return mesMenu;
	}
	
	public Accordion getNavMenu()
	{
		return navMenu;
	}
	public void setNavMenu(Accordion navMenu)
	{
		this.navMenu = navMenu;
	}
	public List<Accordion> getAccordionList()
	{
		return accordionList;
	}
	public void setAccordionList(List<Accordion> accordionList)
	{
		this.accordionList = accordionList;
	}
	
	public String getAccordionHtml()
	{
		StringBuilder sb = new StringBuilder();
		for(Accordion a : accordionList)
		{
			if(a.getDisplay() == 0)
			{
				sb.append(a.getHtml());
			}
		}
		return sb.toString();
	}
	
	public String getNavMenuHtml()
	{
		return navMenu.getMenuListHtml(navMenu.getMenuList());
	}
	
	public List<Menu> getAllMenus()
	{
		if(allMenus == null || allMenus.isEmpty())
		{
			allMenus = new ArrayList<Menu>();
			allMenus.addAll(navMenu.getMenuList());
			for(Accordion acd : accordionList)
			{
				allMenus.addAll(acd.getMenuList());
				getFolderMenus(acd.getFolderList(), allMenus);
			}
		}
		return allMenus;
	}
	
	private void getFolderMenus(List<Folder> foldlList, List<Menu> menuList )
	{
		for(Folder f : foldlList)
		{
			menuList.addAll(f.getMenuList());
			getFolderMenus(f.getFolderList(), menuList);
		}
	}
	
	@Override
	protected MesMenu clone()
	{
		MesMenu mMenu = null;
        try    
        {    
        	mMenu = (MesMenu)super.clone();    
        	mMenu.accordionList = new ArrayList<Accordion>();
            for(Accordion accd : accordionList)
            {
            	mMenu.accordionList.add(accd.clone());
            }
        }    
        catch(CloneNotSupportedException e)    
        {    
            System.out.println(e.toString());    
        }   
        return mMenu;
	}
	
	/**
	 * 检查权限代码是否重复
	 */
	public void checkRepeatPermissionCode()
	{
		try
		{
			Set<String> set = new HashSet<String>();
			List<Menu> list = getAllMenus();
			boolean result = false;
			int repeatNums = 0;
			for(Menu m : list)
			{
				result = set.add(m.getPermissionCode());
				if(!result)
				{
					repeatNums++;
					System.out.println("重复的权限代码："+m.getPermissionCode());
				}
			}
			if(repeatNums == 0)
			{
				System.out.println("菜单权限码无重复.");
			}
			else
			{
				System.out.println("共有" + repeatNums + "个菜单权限码重复.");
			}
		}
		catch (Exception e)
		{
			 
		}
	}
}
