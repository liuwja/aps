/*
 * @(#) PrivilegeUtil.java 2014-3-3 下午04:39:25
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.peg.model.system.SysPrivilege;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-3 下午04:39:25
 */
public class PrivilegeSetting
{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private List<String> userPrivilegeList;
	private Map<String,SysPrivilege> privilegeCodeMap;
	
    public static void main(String[] argv) {
    	
    	System.out.println(OperatorEnum.getEnum("1"));
    	
    	PrivilegeSetting ps = new PrivilegeSetting(new ArrayList<String>(), null);
    	
    	ps.createPrivilegeSettingHtml();
    }
    
	public PrivilegeSetting(List<String> userPrivilegeList, 
		Map<String,SysPrivilege> privilegeCodeMap)
	{
		this.privilegeCodeMap = privilegeCodeMap;
		this.userPrivilegeList = userPrivilegeList;
	}

	/**
	 * 获取权限设置菜单树html
	 * @return
	 */
	public String createPrivilegeSettingHtml()
	{
		MesMenu menu = JdomMenuParser.getMesMenu();
		
		List<Accordion> accordionList = menu.getAccordionList();
		
		List<Accordion> list = new ArrayList<Accordion>();
		
		if(!menu.getNavMenu().isEmptyChild())
		{
			list.add(menu.getNavMenu());
		}
		list.addAll(accordionList);
		
		int index = 1;
		int parentId = 0;
		StringBuilder sb = new StringBuilder();
		for(Accordion a : list)
		{
			parentId = index;
			index = createAccordiontHtml(sb,a,index, 0);
			if(!a.getFolderList().isEmpty())
			{
				index = createFolderListHtml(sb, a.getFolderList(), index, parentId);
			}
			if(!a.getMenuList().isEmpty())
			{
				index = createMenuListHtml(sb, a.getMenuList(), index, parentId);
			}
		}
//		logger.debug(sb);
		return sb.toString();
	}
	
	
//	<tr id="2" pid="1">
//	<td>
//	<span ref="treeChk" class="button chk checkbox_false_full"/>
//	冲压
//	</td>
//	<td>
//	</td>
//	</tr>
	public int createFolderHtml(StringBuilder sb, Folder folder, int index, int parentIndex)
	{
		sb.append("<tr id=\"").append(index).append("\" pid=\"").append(parentIndex).append("\">");
		sb.append("<td>").append("<span ref=\"treeChk\" class=\"button chk checkbox_false_full\"/>");
		sb.append(folder.getName());
		sb.append("</td><td></td></tr>");
		sb.append("\n");
		return index + 1;
	}
	
	private int createAccordiontHtml(StringBuilder sb, Accordion accdion, int index, int parentIndex)
	{
		sb.append("<tr id=\"").append(index).append("\" pid=\"").append(parentIndex).append("\">");
		sb.append("<td>").append("<span ref=\"treeChk\" class=\"button chk checkbox_false_full\"/>");
		sb.append(accdion.getName());
		sb.append("</td><td></td></tr>");
		sb.append("\n");
		return index + 1;
	}
	
	public int createFolderListHtml(StringBuilder sb, List<Folder> folderList, int index, int parentIndex)
	{
		int pid = 0;
		for(Folder f : folderList)
		{
			pid = index;
			index = createFolderHtml(sb, f,index,parentIndex);
			if(!f.getFolderList().isEmpty())
			{
				index = createFolderListHtml(sb, f.getFolderList(),index,pid);
			}
			if(!f.getMenuList().isEmpty())
			{
				index = createMenuListHtml(sb, f.getMenuList(),index,pid);
			}
		}
		return index;
	}
	
//	<tr id="3" pid="2">
//	<td>
//	<span ref="treeChk" class="button chk checkbox_false_full"/>
//	<span ref="allChk" class="button chk checkbox_false_full setAll"/>车间作业计划
//	</td>
//	
//	<td>
//		<span class='inputValueRole'>
//		看<input type='checkbox' name='privilegeKey' <%=getCheckStatus(plgList,"pm:workshopPlan:view") %> value="pm:workshopPlan:view"/>
//		增<input type='checkbox' name='privilegeKey' <%=getCheckStatus(plgList,"pm:workshopPlan:add") %> value="pm:workshopPlan:add"/>
//		删<input type='checkbox' name='privilegeKey' <%=getCheckStatus(plgList,"pm:workshopPlan:del") %> value="pm:workshopPlan:del"/>
//		改<input type='checkbox' name='privilegeKey' <%=getCheckStatus(plgList,"pm:workshopPlan:edit") %> value="pm:workshopPlan:edit"/>
//	</td>
//	</tr>
	
	public int createMenuHtml(StringBuilder sb, Menu menu,  int index, int parentIndex)
	{
		sb.append("\n");
		sb.append("<tr id=\"").append(index).append("\" pid=\"").append(parentIndex).append("\">");
		sb.append("\n");
		sb.append("<td>");
		sb.append("\n");
		sb.append("<span ref=\"allChk\" class=\"button chk checkbox_false_full setAll\"/>");
		sb.append(menu.getName());
		sb.append("</td>");
		
		sb.append("\n");
		sb.append("<td><span class='inputValueRole'>");
		createMenuChkboxHtml(sb,menu);
		createMenuChkboxHtmlByOption(sb,menu);
		
		sb.append("</span>\n");
		sb.append("</td></tr>");
		sb.append("\n");
		
//		if(null != menu.getOptList() && !menu.getOptList().isEmpty())
//		{
//			return  createOptListHtml(sb, menu.getOptList(), index+1, index, menu.getPermissionCode());
//		}
//		else
//		{
//			return index + 1;
//		}
		return index + 1;
	}
	
	
	private long getPrivilegeIdByCode(String pcode)
	{
		return privilegeCodeMap.containsKey(pcode) ? privilegeCodeMap.get(pcode).getPrivilegeKey() : -1L;
	}
	/**
	 * 权限列表，1、看(view);2、增(add);3、改(edit);4、删(del).多个则以逗号分隔
	 * @param permissionList
	 * @return
	 */
	private void createMenuChkboxHtml(StringBuilder sb, Menu menu)
	{
		long pid = getPrivilegeIdByCode(menu.getPermissionCode());
		sb.append("\n");
		sb.append("<input type='checkbox' name='privilegeKey' id='chk_").append(pid).append("'");
		sb.append(getCheckStatus(menu.getPermissionCode()));
		sb.append(" value=\"").append(pid).append("\"/>");
		sb.append("<label for='chk_").append(pid).append("'>看</label>");
		sb.append("\n");
	}
	
	private void createMenuChkboxHtmlByOption(StringBuilder sb, Menu menu)
	{
		List<Operation> optList = menu.getOptList();
		if(optList != null && !optList.isEmpty())
		{
			sb.append("\n");
			String pcode = null;
			for(Operation o : optList)
			{
				pcode = menu.getPermissionCode() + ":" + o.getPermissionCode();
				long pid = getPrivilegeIdByCode(pcode);
				sb.append("<input type='checkbox' name='privilegeKey' id='chk_").append(pid).append("'");
				sb.append(getCheckStatus(pcode));
				sb.append(" value=\"").append(pid).append("\"/>");
				sb.append("<label for='chk_").append(pid).append("'>").append(o.getName()).append("</label>");
				sb.append("\n");
			}
		}
	}
	
	private String getCheckStatus(String permissionCode)
	{
		return userPrivilegeList.contains(permissionCode) ? "checked='checked'" : "";
	}
	
	public int createMenuListHtml(StringBuilder sb, List<Menu> menuList,  int index, int parentIndex)
	{
		for(Menu m : menuList)
		{
			index = createMenuHtml(sb, m, index,parentIndex);
		}
		return index;
	}


	public List<String> getUserPrivilegeList()
	{
		return userPrivilegeList;
	}


	public void setUserPrivilegeList(List<String> userPrivilegeList)
	{
		this.userPrivilegeList = userPrivilegeList;
	}

	public Map<String, SysPrivilege> getPrivilegeCodeMap()
	{
		return privilegeCodeMap;
	}

	public void setPrivilegeCodeMap(Map<String, SysPrivilege> privilegeCodeMap)
	{
		this.privilegeCodeMap = privilegeCodeMap;
	}
	
	
}
