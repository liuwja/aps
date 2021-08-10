/*
 * @(#) AbstractService.java 2015-10-12 下午04:38:18
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.peg.dao.BaseMapper;

/**
 * 通用service实现类
 * <p>
 * 
 * @author Lin, 2015-10-12 下午04:38:18
 */
@Service
public abstract class AbstractService<T, ID extends Serializable> implements BaseService<T, ID>
{
	private BaseMapper<T, ID> baseMapper;

	public void setBaseMapper(BaseMapper<T, ID> baseMapper)
	{
		this.baseMapper = baseMapper;
	}

	@Override
	public int deleteByPrimaryKey(ID id)
	{
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(T record)
	{
		return baseMapper.insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(ID id)
	{
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record)
	{
		return baseMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public int updateByPrimaryKey(T record)
	{
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insert(T record)
	{
		return baseMapper.insert(record);
	}

}
