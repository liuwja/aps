/*
 * @(#) LoginController.java 2014-8-8 下午04:21:37
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.qms.controller.system;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.UserGroup;
import com.peg.model.system.SysGroup;
import com.peg.model.system.SysPrivilege;
import com.peg.qms.controller.BaseController;
import com.peg.service.system.SysGroupServiceI;
import com.peg.service.system.SysMesUserServiceI;
import com.peg.service.system.SysPrivilegeServiceI;
import com.peg.web.menu.Accordion;
import com.peg.web.menu.Folder;
import com.peg.web.menu.JdomMenuParser;
import com.peg.web.menu.Menu;
import com.peg.web.menu.Operation;
import com.peg.web.menu.UiMenu;
import com.peg.web.util.ConstantInterface;

/**
 * TODO
 * <p>
 * @author Lin, 2014-8-8 下午04:21:37
 */
@Controller
@RequestMapping("system/group")
public class GroupController extends BaseController
{
	@Autowired
	private SysGroupServiceI sysGroupService;
	
	@Autowired
	private SysMesUserServiceI sysUserService;
	
	@Autowired
	private SysPrivilegeServiceI sysPrivilegeService;
	
	@RequestMapping("/list")
	public String list(Model model, SysGroup group,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		List<SysGroup>  list = sysGroupService.findAllByPage(page, group.getGroupCode(), group.getGroupName());
		if(list!=null && list.size()!=0){
			sysUserService.findUsersByGroupKey(list);
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/system/group/list";
	}
	
	@RequestMapping("/groupList")
	public String groupList(Model model, PageParameter page, SysGroup group)
	{
		List<SysGroup> list = sysGroupService.findAllByPage(page,group.getGroupCode(), group.getGroupName());
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/system/group/groupQueryResult";
	}
	
	@RequestMapping("/addUserGroup")
	public ModelAndView addUserGroup(Model model, UserGroup ugroup)
	{
		try
		{
			if(ugroup.getGroupKey() != null)
			{
				sysGroupService.addUserGroup(ugroup);
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("添加成功");
	}
	
	@RequestMapping("/delUserGroup")
	public ModelAndView delUserGroup(Model model, UserGroup ugroup)
	{
		try
		{
			if(ugroup.getGroupKey() != null)
			{
				sysGroupService.delUserGroup(ugroup);
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");
	}
	
	
	@RequestMapping("/add")
	public String add()
	{
		return "qms/system/group/add";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(SysGroup group)
	{
		try
		{
			sysGroupService.insert(group);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			if(e instanceof SQLIntegrityConstraintViolationException)
			{
				return ajaxDoneError("用户组编码已存在，请重新填写");
			}
			else
			{
				return ajaxDoneError(e.getMessage());
			}
		}
		return ajaxDoneSuccess("保存成功");
	}

	
	
	@RequestMapping("/edit")
	public String edit(Model model, SysGroup group)
	{
		SysGroup vo = sysGroupService.selectByPrimaryKey(group.getGroupKey());
		model.addAttribute("vo", vo);
		return "qms/system/group/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView update(SysGroup group)
	{
		try
		{
			if(group.getGroupKey() != null)
			{
				sysGroupService.updateByPrimaryKeySelective(group);
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(SysGroup group)
	{
		try
		{
			if(group.getGroupKey() != null)
			{
				sysGroupService.deleteByPrimaryKey(group.getGroupKey());
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");
	}
	/**
	 * 用户组权限编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editGroupPrivilege")
	public ModelAndView editGroupPrivilege(SysGroup gp)
	{
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("qms/system/group/groupPrivilege");
		try
		{
			if(gp.getGroupKey() != null)
			{
				SysGroup group = sysGroupService.selectByPrimaryKey(gp.getGroupKey());
				List<SysPrivilege> plgList = sysPrivilegeService.findPrivilegeByGroupKey(gp.getGroupKey());
				List<Accordion> accList = JdomMenuParser.getMesMenu().getAccordionList();
				List<UiMenu> uiMenus = convertToUiMenu(accList);
				checkOperationSelected(plgList, uiMenus);
				modelView.addObject("group", group);
				modelView.addObject("uiMenus", uiMenus);
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return modelView;
	}
	
	private List<UiMenu> convertToUiMenu(List<Accordion> accList)
	{
		List<UiMenu> umenuList = new LinkedList<UiMenu>();
		UiMenu umenu = null;
		Accordion ad = null;
		String rowCode = null;
		for(int i=0; i<accList.size(); i++)
		{
			rowCode = String.valueOf(i);
			ad = accList.get(i);
			umenu = new UiMenu(ad.getName(), rowCode,null, ConstantInterface.UIMENU_TYPE_ACCORDION);
			umenuList.add(umenu);
			convertFoldersToUiMenus(rowCode, ad.getFolderList(), umenuList);
			convertMenusToUiMenus(rowCode, ad.getMenuList(), umenuList);
		}
		return umenuList;
	}
	
	private void convertFoldersToUiMenus(String parent,List<Folder> foldList, List<UiMenu> umenuList)
	{
		if(foldList == null || foldList.isEmpty())
		{
			return;
		}
		Folder fd = null;
		UiMenu umenu = null;
		String rowCode = null;
		for(int i=0; i<foldList.size(); i++)
		{
			fd = foldList.get(i);
			rowCode = parent + "-" + i;
			umenu = new UiMenu(fd.getName(), rowCode, parent, ConstantInterface.UIMENU_TYPE_FOLDER);
			umenuList.add(umenu);
			convertFoldersToUiMenus(rowCode, fd.getFolderList(), umenuList);
			convertMenusToUiMenus(rowCode, fd.getMenuList(), umenuList);
		}
	}
	
	private void convertMenusToUiMenus(String parent,List<Menu> menuList, List<UiMenu> umenuList)
	{
		if(menuList == null || menuList.isEmpty())
		{
			return;
		}
		Menu menu = null;
		UiMenu umenu = null;
		for(int i=0; i<menuList.size(); i++)
		{
			menu = menuList.get(i);
			umenu = new UiMenu(menu.getName(), parent + "-" + i, parent, ConstantInterface.UIMENU_TYPE_MENU);
			umenu.setOptList(menu.getOptList());
			umenuList.add(umenu);
		}
	}
	
	private void checkOperationSelected(List<SysPrivilege> rsList, List<UiMenu> uiMenus)
	{
		Map<String, SysPrivilege> plgMap = convertToPlgMap(rsList);
		for(UiMenu m : uiMenus)
		{
			if(m.getOptList() != null)
			{
				for(Operation op : m.getOptList())
				{
					if(plgMap.containsKey(op.getPermissionCode()))
					{
						op.setSelected(true);
					}
					else
					{
						op.setSelected(false);
					}
				}
			}
		}
	}
	
	private Map<String, SysPrivilege> convertToPlgMap(List<SysPrivilege> rsList)
	{
		Map<String, SysPrivilege> rsMap = new HashMap<String, SysPrivilege>();
		for(SysPrivilege r : rsList)
		{
			rsMap.put(r.getOperationCode(), r);
		}
		return rsMap;
	}
	
	/**
	 * 保存组权限
	 * @param ugroup
	 * @return
	 */
	@RequestMapping("/saveGroupPrivilege")
	public ModelAndView saveGroupPrivilege(@RequestParam(value = "groupKey") long groupKey,
			@RequestParam(value = "operationCode") List<String> operationCodeList)
	{
		try
		{
			if(groupKey > 0)
			{
				sysPrivilegeService.insertNUpdateGroupPrivilege(groupKey, operationCodeList);
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("更新权限成功");
	}

}
