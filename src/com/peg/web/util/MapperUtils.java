/*
 * @(#) MapperUtil.java 2015-11-2 下午11:18:28
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.peg.dao.system.SysLoginHistoryMapper;
import com.peg.dao.system.SysOperateLogMapper;
import com.peg.service.impl.ApplicationContextUtils;



public class MapperUtils
{
	static SqlSession sqlSession = null;
	protected static Logger logger = Logger.getLogger(MapperUtils.class);
	
	public synchronized static SqlSession getSession()
	{
		if (sqlSession == null)
		{
			logger.info("-----getSession and openSession----");
			sqlSession = (SqlSession) ApplicationContextUtils
				.getBean("sqlSession");
		}
		return sqlSession;
	}

	public static boolean isConnectTestDb()
	{
		try {
			DruidDataSource ds = (DruidDataSource)ApplicationContextUtils.getBean("dataSource");
			long idx = ds.getUrl().indexOf("192.168.100.14");
			return idx > -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void clearCache()
	{
		getSession().clearCache();
	}

	
	public static SysLoginHistoryMapper getSysLoginHistoryMapper()
	{
		return getSession().getMapper(SysLoginHistoryMapper.class);
	}
	
	public static SysOperateLogMapper getSysOperateLogMapper()
	{
		return getSession().getMapper(SysOperateLogMapper.class);
	}
}
