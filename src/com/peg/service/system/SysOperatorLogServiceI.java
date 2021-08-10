/*
 * @(#) SysOperatorLogServiceI.java 2014-9-2 下午04:10:34
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.system;

import com.peg.model.system.SysOperateLog;

/**
 * TODO
 * <p>
 * @author Lin, 2014-9-2 下午04:10:34
 */
public interface SysOperatorLogServiceI
{
    int deleteByPrimaryKey(Long id);

    int insert(SysOperateLog record);

    int insertSelective(SysOperateLog record);

    SysOperateLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysOperateLog record);

    int updateByPrimaryKey(SysOperateLog record);
}
