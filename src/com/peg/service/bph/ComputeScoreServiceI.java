/*
 * @(#) ComputeScoreService.java 2015-7-27 上午08:59:58
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.bph;

import java.util.List;
import java.util.Map;

import com.peg.model.bph.CheckIndex;
import com.peg.model.bph.CheckItem;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MesDataSum;
import com.peg.model.bph.MonthlyAssessment;

/**
 * 班组得分计算接口
 * <p>
 * @author Lin, 2015-7-27 上午08:59:58
 */
public interface ComputeScoreServiceI
{
	/**
	 * 插入指标得分
	 * @param record
	 * @return
	 */
	int insertIndexScore(IndexScroe record);
	
	/**
	 * 插入指标得分列表月份
	 * @param recordList
	 * @return
	 */
	int insertIndexScore(List<IndexScroe> recordList);
	
	/**
	 * 插入指标得分列表天
	 * @method: insertIndexScoreDay() -by fjt
	 * @TODO:  
	 */
	int insertIndexScoreDay(List<IndexScroe> recordList);
	
	/**
	 * 插入考核项目得分列表
	 * @param recordList
	 * @return
	 */
	int insertItemScore(List<ItemScore> recordList);
	
	/**
	 * 插入考核项目得分
	 * @param record
	 * @return
	 */
	int insertItemScore(ItemScore record);
	
	/**
	 * 
	 * @return Map<工厂-班组,Map<考核项目名称,考核项目>
	 * 项目名称如：客户质量,过程质量...
	 */
	Map<String, Map<String,CheckItem>> QueryItemMap();
	
	/**
	 * 
	 * @return Map<工厂-班组,Map<考核指标代码,考核项目>
	 * 指标代码如：A1,A2,A3...
	 */
	Map<String,Map<String,CheckIndex>> QueryItemIndexMap();
	/**
	 * 
	 * @return Map<工厂-班组,Map<不良来源,不良次数>>月份
	 */
	Map<String,Map<String,Long>> QueryBoxDefectSource(MesDataSum mesDataSum);
	
	/**
	 * 
	 * @return Map<工厂-班组,Map<不良来源,不良次数>>天数
	 */
	Map<String,Map<String,Long>> QueryBoxDefectSourceByDay(MesDataSum mesDataSum);
	
    /**
     * 查询所有的MES数据
     * @method: getMesDataAll() -by fjt
     * @TODO:  
     * @return List<MesDataSum>
     */
    List<MesDataSum> getMesDataAll(MesDataSum mesDataSum);
    
    /**
     * 
     * @return Map<工厂-班组,Map<审核内容,审核次数>>月份
     * @TODO:  
     */
    Map<String,Map<String,Long>> QueryProcessAuditRecordByMonth(MesDataSum mesDataSum);
    
    /**
     * 
     * @return Map<工厂-班组,Map<审核内容,审核次数>>天
     * @TODO:  
     */
    Map<String,Map<String,Long>> QueryProcessAuditRecordByDay(MesDataSum mesDataSum);
    
    /**
     * 
     * @return Map<工厂-班组,Map<发生流程节点,质量风险系数>>月
     * @TODO:  
     */
    Map<String,Map<String,Double>> QueryBatchDeFectByMonth(MesDataSum mesDataSum);
    
    /**
     * 
     * @return Map<工厂-班组,Map<发生流程节点,质量风险系数>>天
     * @TODO:  
     */
    Map<String,Map<String,Double>> QueryBatchDefectByDay(MesDataSum mesDataSum);
    
    /**
     *  
     * @return Map<QPRN分数,分数>
     */
    Map<String,Object> QueryQPRNScore();
    
    
    /**
     * 
     * @method: getMesDataDayAll() -by fjt
     * @TODO:  得到所有MES数据天表
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> getMesDataDayAll(MesDataSum mesDataSum);
    
    /**
     * 
     * @return Map<工厂-班组,Map<月份,月度考核指标设定>>月
     * @TODO:  
     */
    Map<String,Map<String,MonthlyAssessment>> QueryMonthAssessment(MesDataSum mesDataSum);
    
    
    /**
     * 
     * @return Map<工厂-班组,Map<发生流程节点,质量风险分数和>>月
     * @TODO:  
     */
    Map<String,Map<String,Double>> QueryIpqcByMonth(MesDataSum mesDataSum);
    
    
    /**
     * 
     * @return Map<工厂-班组,Map<发生流程节点,质量风险分数和>>月
     * @TODO:  
     */
    Map<String,Map<String,Double>> QueryIpqcByDay(MesDataSum mesDataSum);
    
	
}
