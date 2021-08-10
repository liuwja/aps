/*
 * @(#) ComputeContext.java 2015-7-23 下午08:54:26
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.sumscore;

import java.util.Map;

import org.apache.log4j.Logger;

import com.peg.base.exception.QmsException;
import com.peg.model.bph.Group;
import com.peg.model.bph.MesDataSum;
import com.peg.service.bph.NComputeScoreServiceI;
import com.peg.web.util.ShiftGroupTypeEnum;

/**
 * 绩效得分计算类
 * <p>
 * @author Lin, 2015-7-23 下午08:54:26
 */
public class NComputeContext
{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private NAbstractComputeStrategy computeStrategy;
	
	/**
	 * 初始化
	 * @param mesDataSum mes相关数据
	 * @param itemMap 考核项目
	 * @param boxDefectMap 开箱不良
	 * @param processMap  过程审核
	 * @param batchMap  过程批量
	 * @throws QmsException 
	 */
	@SuppressWarnings("unused")
	public NComputeContext(MesDataSum  mesDataSum, Map<String,Group> groupMap, NComputeScoreServiceI computeScoreService) throws QmsException {
		if(mesDataSum == null)
		{
			throw new QmsException("MES数据为空，无法初始化计算类。");
		}
		
		if(computeScoreService == null)
		{
			throw new QmsException("computeScoreService is null.");
		}
		
		if(groupMap == null || groupMap.isEmpty())
		{
			throw new QmsException("当月考核指标未设定！");
		}
		
		String groupType = mesDataSum.getGroupType();
		
		ShiftGroupTypeEnum type = ShiftGroupTypeEnum.getEnumByType(groupType);
		if(type == null)
		{
			throw new QmsException("找不到对应班组的算法。");
		}
		switch (type) {
			case STAMPING:
				this.computeStrategy = new NStampingStrategy();
				break;
			case ACCEMBLE:
				this.computeStrategy = new NAccembleStrategy();
				break;
			case PAINTING:
				this.computeStrategy = new NPaintingStrategyOne();
				break;
			case FASHIONING:
				this.computeStrategy = new NFashioningStrategy();
				break;
			case IQC:
				this.computeStrategy = new NIqcStrategy();
				break;
			case OQC:
				this.computeStrategy = new NOqcStrategy();
				break;
			case WELDING:
				this.computeStrategy = new NStampingStrategy();
				break;
			case SUBASSEMBLY:
				this.computeStrategy = new NSubassemblyStrategy();
				break;
			case COMPUTERBOARD:
				this.computeStrategy = new NComputerBoardStrategy();
				break;
			default:
				throw new QmsException("找不到对应班组的算法。");
			}
		    this.computeStrategy.setMesDataSum(mesDataSum);
		    this.computeStrategy.setComputeScoreService(computeScoreService);
		    
			String factory = mesDataSum.getFactory();
			String shiftGroup = mesDataSum.getShiftGroupName();
			String key = factory + "-" + groupType;
			String boxKey = factory + "-" + shiftGroup;
			
			
			//获取并设置月度考核基准
			if(groupMap != null && groupMap.get(boxKey) != null){
				this.computeStrategy.setValidGroup(true);
				this.computeStrategy.setGroup(groupMap.get(boxKey));
			}
			else{
				logger.warn("相关月度考核基准为空，计算将使用默认值工厂："+ factory +",班组类别:"+ groupType);
			}
			
	}

	public void compute() throws QmsException
	{
		this.computeStrategy.computeAndSave();
	}
	
}
