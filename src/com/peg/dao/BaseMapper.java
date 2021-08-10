/*
 * @(#) BaseMapper.java 2015-10-12 下午04:34:27
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.dao;

import java.io.Serializable;

/**
 * 通用Mapper接口
 * <p>
 * @author Lin, 2015-10-12 下午04:34:27
 */
public interface BaseMapper<T, ID extends Serializable>
{
	int deleteByPrimaryKey(ID id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(ID id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
	
//	List<T> findAllByPage(PageParameter page, Map<String, Object> map);
}
