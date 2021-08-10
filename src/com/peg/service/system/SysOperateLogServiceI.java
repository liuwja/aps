/*
 * @(#) SysOperatorLogServiceI.java 2014-9-2 下午04:10:34
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.system;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysOperateLog;
import com.peg.service.BaseService;

/**
 * TODO
 * <p>
 * @author Lin, 2014-9-2 下午04:10:34
 */
public interface SysOperateLogServiceI extends BaseService<SysOperateLog, Long> 
{
	List<SysOperateLog> findAllByPage(PageParameter page,SysOperateLog log);
}
