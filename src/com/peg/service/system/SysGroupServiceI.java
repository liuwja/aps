/*
 * @(#) SysUserService.java 2014-8-17 下午12:43:35
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.system;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.UserGroup;
import com.peg.model.system.SysGroup;
import com.peg.service.BaseService;

/**
 * TODO
 * <p>
 * 
 * @author Lin, 2014-8-17 下午12:43:35
 */
public interface SysGroupServiceI extends BaseService<SysGroup, Long> {
	List<SysGroup> findAllByPage(PageParameter page, String code, String name);

	List<SysGroup> findGroupsByUserKey(long userKey);

	int addUserGroup(UserGroup ugroup);

	int delUserGroup(UserGroup ugroup);

}
