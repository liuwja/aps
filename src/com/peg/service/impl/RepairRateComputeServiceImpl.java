/*
 * @(#) RepairRateComputeServiceImpl.java 2015-4-10 下午03:41:55
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonVo;
import com.peg.model.RepairRate;
import com.peg.model.RepairRateResult;
import com.peg.service.CommonServiceI;
import com.peg.service.RepairRateComputeServiceI;
import com.peg.service.RepairRateServiceI;
import com.peg.web.util.WebUtil;

/**
 * TODO
 * <p>
 * @author Lin, 2015-4-10 下午03:41:55
 */

@Service("repairRateComputeService")
public class RepairRateComputeServiceImpl implements RepairRateComputeServiceI
{
	@Autowired
	private RepairRateServiceI repairRateService;
	
	@Autowired
	private CommonServiceI commonService;
	
	@Override
	public Map<String, List<RepairRateResult>> getRepairRateResultByProdTypeAndMonth(CommonVo vo) throws Exception {
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		//计算开始月份，用于查询维修数据，时间为22个月前
		String  startMonth = WebUtil.rebackMonths(vo.getQueryMonth(), -22);
		
		//查询维修数
		vo.setStartTime(startMonth);
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairList = commonService.getReCountGroupByProdType(vo);
		//查询发货数
		startMonth = WebUtil.rebackMonths(vo.getQueryMonth(), -25);
		vo.setStartTime(startMonth);
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> shipList = commonService.getShipCountGroupByProdType(vo);
		
		//查询设定的基准维修率
		BaseSearch bs = new BaseSearch();
		bs.put("startMon", WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		bs.put("endMon", vo.getQueryMonth());
		bs.put("prodType", vo.getProductType());
		List<RepairRate>  rateList = repairRateService.findByDateRange(bs);
		Map<String,Double> rateMap = parseToMap(rateList);
		
		Map<String, Map<String,CommonVo>> repairMap = convertToMap(repairList);
		Map<String, Map<String,CommonVo>> shipMap = convertToMap(shipList);
		
		//用于存储查询结果的Map
		Map<String, List<RepairRateResult>> resultMap = new HashMap<String, List<RepairRateResult>>();
		String prodType = null;
		RepairRateResult cvo = null;
		
		//获取要展示的月份列表
		List<String> monthList = WebUtil.getDateList(vo.getQueryMonth(), 12);
		Collections.reverse(monthList);
		//计算过去12个月的累计维修率
		List<RepairRateResult> tmpList = null;
		if(shipMap!=null && !shipMap.isEmpty())
		{
			for(String month : monthList)
			{
				List<String> shipMonthList = WebUtil.getDateList(month, 12, 3);
				List<String> repairMonList = WebUtil.getDateList(month, 12);
				for(Map.Entry<String, Map<String,CommonVo>> en : shipMap.entrySet())//类型-时间-obj
				{
					prodType = en.getKey();
					if(!resultMap.containsKey(prodType))
					{
						tmpList = new ArrayList<RepairRateResult>();
						resultMap.put(prodType, tmpList);
					}
					cvo = new RepairRateResult();
					cvo.setMonth(month);
					//累计发货数
					long sumShipCount = sumShipTotalBydate(en.getValue(), shipMonthList);
					//累计维修数
					long sumReCount = sumRepairTotalBydate(repairMap.get(prodType), repairMonList);
					cvo.setTotalShipCount(sumShipCount);
					cvo.setTotalRepairCount(sumReCount);
					//单月维修数
					cvo.setSingleRepairCount(getRepairCount(repairMap.get(prodType), month));
					this.caculateRate(cvo);
					resultMap.get(prodType).add(cvo);
				}
			}
		}
		
		//维修是否超过设置的基准维修率
		for(Map.Entry<String, List<RepairRateResult>> en : resultMap.entrySet())
		{
			for(RepairRateResult v : en.getValue())
			{
				Double rate = rateMap.get(en.getKey() + "_" + v.getMonth());
				if(rate != null)
				{
					v.setBaseRepairRate(rate);
					v.setExceedRate(v.getTotalRepairRate() > rate);
				}
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, List<RepairRateResult>> getUnderWarrantyRepairRateResultByProdTypeAndMonth(CommonVo vo) throws Exception {
		String startMonth = WebUtil.rebackMonths(vo.getQueryMonth(), -60);
		vo.setUnderWarranty("是");
		vo.setStartTime(startMonth);
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairList = commonService.getReCountGroupByProdType(vo);
		List<CommonVo> shipList = commonService.getShipCountGroupByProdType(vo);
		Map<String, Map<String,CommonVo>> repairMap = convertToMap(repairList);
		Map<String, Map<String,CommonVo>> shipMap = convertToMap(shipList);
		List<String> monthList = WebUtil.getDateList(vo.getQueryMonth(), 60);
		Collections.reverse(monthList);
		List<RepairRateResult> tmpList = null;
		Map<String, List<RepairRateResult>> resultMap = new HashMap<String, List<RepairRateResult>>();
		String prodType = null;
		RepairRateResult cvo = null;
		if(shipMap!=null && !shipMap.isEmpty())
		{
			for(String month : monthList)
			{
				List<String> shipMonthList = WebUtil.getDateList(month, 12, 3);
				List<String> repairMonList = WebUtil.getDateList(month, 12);
				for(Map.Entry<String, Map<String,CommonVo>> en : shipMap.entrySet())//类型-时间-obj
				{
					prodType = en.getKey();
					if(!resultMap.containsKey(prodType))
					{
						tmpList = new ArrayList<RepairRateResult>();
						resultMap.put(prodType, tmpList);
					}
					cvo = new RepairRateResult();
					cvo.setMonth(month);
					//累计发货数
					long sumShipCount = sumShipTotalBydate(en.getValue(), shipMonthList);
					//累计维修数
					long sumReCount = sumRepairTotalBydate(repairMap.get(prodType), repairMonList);
					cvo.setTotalShipCount(sumShipCount);
					cvo.setTotalRepairCount(sumReCount);
					//单月维修数
					cvo.setSingleRepairCount(getRepairCount(repairMap.get(prodType), month));
					this.caculateRate(cvo);
					resultMap.get(prodType).add(cvo);
				}
			}
		}
		return resultMap;
	}
	
	/**
	 * 设定的各机型月度累计百台维修率
	 * <机型类别_月份, 设定的维修率>
	 * @param rateList
	 * @return
	 */
	private Map<String,Double> parseToMap(List<RepairRate>  rateList)
	{
		Map<String,Double> resultMap = new HashMap<String,Double>();
		if(null == rateList || rateList.isEmpty())
		{
			return resultMap;
		}
		//乘以100
		for(RepairRate rate : rateList)
		{
			resultMap.put(rate.getMachineType() + "_" + rate.getYearMon(), rate.getHundredRepairRate().doubleValue() * 100);
		}
		return resultMap;
	}
	
	/**
	 * 计算维修率
	 * @param vo
	 * @return
	 */
	private void caculateRate(RepairRateResult vo)
	{
		if(vo.getTotalShipCount() == 0)
		{
			//发货数为0
			vo.setTotalRepairRatePercent("-");
			vo.setSingleRepairRatePercent("-");
			return ;
		}
		BigDecimal bgTotalRate = new BigDecimal(vo.getTotalRepairCount().doubleValue()*100/vo.getTotalShipCount());
        //累计维修率
		double totalRate = bgTotalRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        vo.setTotalRepairRate(totalRate);
		BigDecimal bgSingleRate = vo.getTotalShipCount() == 0 ? new BigDecimal(
				0) : new BigDecimal(vo.getSingleRepairCount().doubleValue()
				* 100 / (vo.getTotalShipCount().doubleValue() / 12));
        //单月维修率
        double singleRepairRate = bgSingleRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        vo.setSingleRepairRate(singleRepairRate);
        vo.setSingleRepairRatePercent(singleRepairRate + "%");
        vo.setTotalRepairRatePercent(totalRate + "%");
	}
	
	/**
	 * 获取维修数
	 * @param map
	 * @param baseMonth
	 * @return
	 */
	private Long getRepairCount(Map<String,CommonVo> map, String baseMonth)
	{
		if(map!=null && map.containsKey(baseMonth))
		{
			return map.get(baseMonth).getRepairCount();
		}
		return 0L;
	}
	
	/**
	 * List转换成map方便取数
	 * Map<机型类别, <月份,对应的数量vo列表>>
	 * @param shipList
	 * @return
	 */
	private Map<String, Map<String,CommonVo>> convertToMap(List<CommonVo> shipList)
	{
		Map<String, Map<String,CommonVo>> map = new HashMap<String, Map<String,CommonVo>>();
		if(null == shipList || shipList.isEmpty())
		{
			return map;
		}
		Map<String,CommonVo> tmpMap = null;
		String productType = null;
		for(CommonVo vo : shipList)
		{
			productType = vo.getProductType();
			if(!map.containsKey(productType))
			{
				tmpMap = new HashMap<String, CommonVo>();
				map.put(productType, tmpMap);
			}
			map.get(productType).put(vo.getBaseMonth(), vo);
		}
		return map;
	}
	
	/**
	 *计算startMonth到endMonth月份的发货总数 
	 * @param shipList
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	private Long sumShipTotalBydate(Map<String,CommonVo> shipMap, List<String> shipMonthList)
	{
		long sum = 0;
		if(null == shipMap || shipMap.isEmpty())
		{
			return sum;
		}
		
		try
		{
			for(String month : shipMonthList)
			{
				if(shipMap.containsKey(month))
				{
					sum = sum + shipMap.get(month).getShipCount();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sum;
	}
	
	/**
	 * 计算startMonth到endMonth月份的维修总数
	 * @param shipList
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	private Long sumRepairTotalBydate(Map<String,CommonVo> shipMap, List<String> shipMonthList)
	{
		long sum = 0;
		if(null == shipMap || shipMap.isEmpty())
		{
			return sum;
		}
		
		try
		{
			for(String month : shipMonthList)
			{
				if(shipMap.containsKey(month))
				{
					sum = sum + shipMap.get(month).getRepairCount();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sum;
	}	
}
