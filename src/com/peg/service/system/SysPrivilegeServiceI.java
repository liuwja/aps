/*
 * @(#) SysUserService.java 2014-8-17 下午12:43:35
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.system;

import java.util.List;
import java.util.Map;

import com.peg.model.system.SysPrivilege;
import com.peg.web.menu.Menu;

/**
 * TODO
 * <p>
 * @author Lin, 2014-8-17 下午12:43:35
 */
public interface SysPrivilegeServiceI
{
Map<String, SysPrivilege> findAllSysPrivilege();
	
	boolean insertAndUpdateMenus(List<Menu> menus);
	
	/**
	 * 获得某个用户所有的权限
	 * @param userKey
	 * @return
	 */
	List<SysPrivilege> findPrivilegeByUserName(String userName);
	
	/**
	 * 获得某个组的所有权限
	 * @param groupKey
	 * @return
	 */
	List<SysPrivilege> findPrivilegeByGroupKey(long groupKey);
	
	
	int insertNUpdateGroupPrivilege(long groupKey , List<String> operationCodeList) throws Exception;
}
