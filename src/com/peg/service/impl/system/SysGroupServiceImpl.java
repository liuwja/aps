/*
 * @(#) SysGroupServiceImpl.java 2014-8-17 下午10:25:42
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.UserGroupMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.system.SysGroupMapper;
import com.peg.model.UserGroup;
import com.peg.model.system.SysGroup;
import com.peg.service.AbstractService;
import com.peg.service.system.SysGroupServiceI;

/**
 * TODO
 * <p>
 * @author Lin, 2014-8-17 下午10:25:42
 */
@Service("sysGroupService")
public class SysGroupServiceImpl extends AbstractService<SysGroup, Long> implements SysGroupServiceI
{
	
	@Autowired
	private SysGroupMapper sysGroupMapper;
	
	@Autowired
	private UserGroupMapper userGroupMapper;

	@Override
	@Autowired
	public void setBaseMapper() {
		// TODO Auto-generated method stub
		super.setBaseMapper(sysGroupMapper);
	}

	@Override
	public List<SysGroup> findAllByPage(PageParameter page, String code,
			String name) {
		// TODO Auto-generated method stub
		return sysGroupMapper.findAllByPage(page, code, name);
	}

	@Override
	public List<SysGroup> findGroupsByUserKey(long userKey) {
		// TODO Auto-generated method stub
		return sysGroupMapper.findGroupsByUserKey(userKey);
	}
	
	@Override
	public int addUserGroup(UserGroup ugroup) 
	{
 		return userGroupMapper.insert(ugroup);
	}
	
	@Override
	public int delUserGroup(UserGroup ugroup)
	{
		return userGroupMapper.delUserGroup(ugroup);
	}

}
