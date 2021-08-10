/*
 * @(#) MenuFilter.java 2014-3-3 下午01:57:19
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.peg.model.system.SysMesUser;

/**
 * 菜单过滤器
 * admin用户不用过滤
 * <p>
 * @author Lin, 2014-3-3 下午01:57:19
 */
public class MenuFilter
{
Subject currentUser;
	
	public MenuFilter()
	{
		this.currentUser = SecurityUtils.getSubject();
	}

	public MesMenu filterByPrivilege(MesMenu mesMenu)
	{
		SysMesUser u = (SysMesUser)currentUser.getSession().getAttribute("user");
		int accessPrivilege = NumberUtils.toInt(u.getUda3());
		
		MesMenu  cloneMenu = mesMenu.clone();
		
		filterMenu(cloneMenu.getNavMenu().getMenuList());
		List<Accordion> accList = cloneMenu.getAccordionList();
		Iterator<Accordion> it = accList.iterator();
		while(it.hasNext())
		{
			Accordion accordion = it.next();
//			if(!WebUtil.canAccess(accessPrivilege, accordion.getSystemType()))
//			{
//				it.remove();
//			}
//			else
//			{
				filterFolder(accessPrivilege, accordion.getFolderList());
				filterMenu(accordion.getMenuList());
//			}
		}
		
		removeEmptyAccordion(accList);
		
		Accordion nav = cloneMenu.getNavMenu();
		filterMenu(nav.getMenuList());
		
		return cloneMenu;
	}
	
	private void removeEmptyAccordion(List<Accordion> accList)
	{
		Iterator<Accordion> it = accList.iterator();
		Accordion accd = null;
		while(it.hasNext())
		{
			accd = it.next();
			if(accd.isEmptyChild())
			{
				it.remove();
			}
			else
			{
				if(!accd.getFolderList().isEmpty())
				{
					removeEmptyFolder(accd.getFolderList());
				}
			}
		}
	}
	
	private void removeEmptyFolder(List<Folder> folderList)
	{
		Iterator<Folder> it = folderList.iterator();
		Folder fld = null;
		while(it.hasNext())
		{
			fld = it.next();
			if(fld.isEmptyChild())
			{
				it.remove();
			}
			else
			{
				removeEmptyFolder(fld.getFolderList());
			}
		}
	}
	
	private void filterFolder(int accessPrivilege, List<Folder> folderList)
	{
		if(null != folderList && !folderList.isEmpty())
		{
			Iterator<Folder> it = folderList.iterator();
			while(it.hasNext())
			{
				Folder f = it.next();
//				if(WebUtil.canAccess(accessPrivilege, f.getSystemType()))
//				{
					filterFolder(accessPrivilege, f.getFolderList());
					filterMenu(f.getMenuList());
//				}
//				else
//				{
//					it.remove();
//				}
			}
		}
	}
	
	private void filterMenu(List<Menu> menuList)
	{
		if(null != menuList && !menuList.isEmpty())
		{
			Iterator<Menu> it = menuList.iterator();
			Menu menu = null;
			while(it.hasNext())
			{
				menu = it.next();
//				if(StringUtils.isBlank(menu.getUrl()) || !currentUser.isPermitted(menu.getUrl().replace("?", ":")))
				if(!currentUser.isPermitted(menu.getPermissionCode() + ":" + OperatorEnum.VIEW))
				{
					it.remove();
				}
			}
		}
	}
}
