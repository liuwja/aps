/*
 * @(#) RepairRateComputeServiceI.java 2015-4-10 下午03:37:53
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service;

import java.util.List;
import java.util.Map;

import com.peg.model.CommonVo;
import com.peg.model.RepairRateResult;

/**
 * 维修率相关计算接口
 * <p>
 * @author Lin, 2015-4-10 下午03:37:53
 */
public interface RepairRateComputeServiceI
{
	/**
	 * 根据机型类别和月份获取维修率列表(可设置机型类别，型号，产线，区域，基准月份)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Map<String, List<RepairRateResult>> getRepairRateResultByProdTypeAndMonth(CommonVo vo) throws Exception;
	
	Map<String, List<RepairRateResult>> getUnderWarrantyRepairRateResultByProdTypeAndMonth(CommonVo vo) throws Exception;
}
