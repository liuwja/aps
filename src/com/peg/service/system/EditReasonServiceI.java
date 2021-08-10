/*
 * @(#) SysOperatorLogServiceI.java 2014-9-2 下午04:10:34
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.system;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.EditReason;
import com.peg.service.BaseService;

/**
 * 绩效管理修改日志接口
 * @author xuanm
 *
 */
public interface EditReasonServiceI extends BaseService<EditReason, Long> 
{
	/**
	 * 获取绩效管理所有修改日志记录
	 * @return 日志记录
	 */
	List<EditReason> getAll();
	
	/**
	 * 获取绩效管理所有修改日志记录,分页实现，附带模糊查询
	 * @param page 分页信息
	 * @param log 模糊查询条件
	 * @return 日志记录
	 */
	List<EditReason> findAllByPage(PageParameter page,EditReason log);
	
	/**
	 * 获取绩效管理修改日志
	 * @param bs 模糊查询条件和分页查询
	 * @return 绩效管理修改日志记录
	 */
	List<EditReason>getAllByPage(BaseSearch bs);
	
	/**
	 * 绩效管理修改记录插入，并返回插入之后的主键
	 * @param editReason 待插入记录信息
	 */
    @Override
    int insertSelective(EditReason editReason);
}
