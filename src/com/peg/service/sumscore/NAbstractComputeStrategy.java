/*
 * @(#) ISumScoreStrategy.java 2015-7-23 下午08:37:00
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.sumscore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.peg.base.exception.QmsException;
import com.peg.model.bph.Group;
import com.peg.model.bph.Index;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.Item;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MesDataSum;
import com.peg.model.bph.MonthAssessment;
import com.peg.service.bph.NComputeScoreServiceI;
import com.peg.web.util.MathUtil;

/**
 * TODO
 * <p>
 * @author Lin, 2015-7-23 下午08:37:00
 */
public abstract class NAbstractComputeStrategy 
{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	static final String ITEM_CODE_CUSTOM_QUALITY = "客户质量";
	static final String ITEM_CODE_PROCESS_QUALITY = "过程质量";
	static final String ITEM_CODE_PROCESS_AUDITING = "过程审核";
	static final String ITEM_CODE_ADDITIONAL = "附加项";
	static final double PROCESS_NODE_D_SCORE = -2.5;

	/**
	 * 考核项目得分列表（用于存入数据库中）
	 */
	protected List<IndexScroe> indexScroeList;
	
	/**
	 * 考核指标得分列表（用于存入数据库中）
	 */
	protected List<ItemScore>  itemScoreList;
	
	/**
	 * 指标计算所需数据
	 */
	protected MesDataSum  mesDataSum;
	
	//Map<月份,月度考核基准>
	protected Group group;
	
	/**
	 * 是否设置了月度考核基准，默认为false ,未设置
	 */
	protected boolean validGroup;
	
	
	
	protected NComputeScoreServiceI computeScoreService;
	
	/**
	 * 计算考核项目，绩效得分
	 */
	public abstract void compute();
	
	private void save() throws QmsException
	{
		if(indexScroeList != null)
		{
			computeScoreService.insertIndexScore(indexScroeList);
			logger.info("insert indexScroeList finish. size: " + indexScroeList.size());
		}
		if(itemScoreList != null)
		{
			computeScoreService.insertItemScore(itemScoreList);
			logger.info("insert itemScoreList finish. size: " + itemScoreList.size());
		}
	}

	public void computeAndSave() throws QmsException
	{ 
		if(validGroup)
		{
			compute();
			save();
		}
	}
	
	/**
	 * 根据数值的范围计算指标得分
	 * 计算方法如下：
	 * 1.目标=<实际<=基准，score=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；
	 * 2.实际=0，score=150*该指标权重；
	 * 3.0<实际<目标，score=MIN(目标/实际*100,150)*该指标权重；
	 * 4.否则  score=0
	 * @param actualValue 实际值
	 * @return 指标得分
	 */
	protected double absMinRangeFormula(double actualValue,MonthAssessment month)
	{
		if(month == null){
			logger.warn("月度基准值为空");
			return -1;
		}

		
		//基准值
		double baseValue = month.getBaseValue();
		//目标值
		double targetValue = month.getTargetValue();
		//权重
		double weight = month.getItemScale() * month.getIndexScale();
		
		double score = 0;
		if(actualValue == 0)
		{
			score = 150 * weight;
		}else if(actualValue >=targetValue && actualValue <= baseValue)//TODO 如果分母为0怎么处理？
		{
			score = (MathUtil.divide(Math.abs(actualValue - baseValue) *40 , Math.abs(targetValue - baseValue)) + 60) * weight;
		}else if(actualValue > 0 && actualValue < targetValue)
		{
			score = Math.min(MathUtil.divide(targetValue * 100 , actualValue), 150) * weight;
		}
		else
		{
			score = 0;
		}
		
		return score;
	}
	
	/**
	 * 附加分之和
	 * 计算公式：该班组所有附加分之和，且<=20,大于20则取20
	 * @param additionalActValue
	 * @return
	 */
	protected double computeAdditionalScore(double additionalActValue)
	{
		return additionalActValue <= 20 ? additionalActValue : 20 ;
	}
	
	/**
	/**
	 * 喷涂一次合格率
	 * 如果该月份设置了月指标考核基准
	 * 根据数值的范围计算指标得分
	 * 计算方法如下：
	 * 1.基准=<实际<=目标，score=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；
	 * 2.实际>目标，score=MIN((实绩-基准/目标-基准)*100,150)*该指标权重；
	 * 4.否则  score=0
	 * @param actualValue 实际值
	 * @return 指标得分
	 */
	protected double absMinRangeFormulaByMonthPait(double actualValue,MonthAssessment month){

		if(month == null)
		{
			logger.warn("指标项为空。");
			return -1;
		}
		
		//基准值
		double baseValue = month.getBaseValue();
		//目标值
		double targetValue = month.getTargetValue();
		//权重
		double weight = month.getItemScale()*month.getIndexScale();
		
		double score = 0;
	    if(actualValue >=baseValue && actualValue <= targetValue)//TODO 如果分母为0怎么处理？
		{
			score = (MathUtil.divide(Math.abs(actualValue - baseValue) *40 , Math.abs(targetValue - baseValue)) + 60) * weight;
		}else if( actualValue > targetValue)
		{
			score = Math.min(MathUtil.divide(Math.abs(actualValue - baseValue) *100, Math.abs(targetValue - baseValue)), 150) * weight;
		}
		else
		{
			score = 0;
		}
		
		return score;
	}
	
	/**
	 * 把指标得分加入列表
	 * @param citem
	 * @param cidx
	 * @param actualValue
	 * @param score
	 */
	protected void addIndexScoreToList(Item citem, Index cidx,double actualValue,double score)
	{
		IndexScroe indexScore = null;
		indexScore = new IndexScroe(mesDataSum.getFactory(),mesDataSum.getAreas(),mesDataSum.getShiftGroupCode(),mesDataSum.getShiftGroupName(),
            mesDataSum.getSumDate(),citem == null ? 0 : citem.getItemKey(), cidx == null ? 0 : cidx.getIndexKey());
		indexScore.setIndexActValue(BigDecimal.valueOf(actualValue));
		indexScore.setIndexScore(BigDecimal.valueOf(score));
		indexScroeList.add(indexScore);		
	}
	
	/**
	 * 把项目得分加入列表
	 * @param citem
	 * @param score
	 */
	protected void addItemScoreToList(Item citem, double score)
	{
		ItemScore itemScore = null;
		itemScore = new ItemScore(mesDataSum.getFactory(),mesDataSum.getAreas(),mesDataSum.getShiftGroupCode(),mesDataSum.getShiftGroupName(),
            mesDataSum.getSumDate(),citem == null ? 0 : citem.getItemKey());
		itemScore.setItemScore(BigDecimal.valueOf(score));
		itemScoreList.add(itemScore);		
	}
	
	protected List<IndexScroe> getIndexScroeList()
	{
		return indexScroeList;
	}

	protected void setIndexScroeList(List<IndexScroe> indexScroeList)
	{
		this.indexScroeList = indexScroeList;
	}

	protected List<ItemScore> getItemScoreList()
	{
		return itemScoreList;
	}

	protected void setItemScoreList(List<ItemScore> itemScoreList)
	{
		this.itemScoreList = itemScoreList;
	}

	protected MesDataSum getMesDataSum()
	{
		return mesDataSum;
	}

	protected void setMesDataSum(MesDataSum mesDataSum)
	{
		this.mesDataSum = mesDataSum;
	}



	public Map<String, Item> getItemMap(Group group)
	{
		Map<String,Item> itemMap = new HashMap<String, Item>();
		List<Item> itemList = group.getUigroupCategory().getItem();
		for(Item item : itemList){
			String itemName= item.getItemName();
			if(!itemMap.containsKey(itemName)){
				itemMap.put(itemName, item);
			}
			
		}
		return itemMap;
	}
	
	public Map<String, Index> getIndexMap(Group group){
		Map<String ,Index > indexMap = new HashMap<String, Index>();
		List<Item> itemList = group.getUigroupCategory().getItem();
		
		for(Item item : itemList){
			for(Index index : item.getUiindexs()){
				String indexCode = index.getIndexCode();
				if(!indexMap.containsKey(indexCode)){
					indexMap.put(indexCode, index);
				}
			}
		}
		return indexMap;
	}
	
	public Map<String,MonthAssessment> getMonthMap(Group group){
		Map<String,MonthAssessment> monthMap = new HashMap<String, MonthAssessment>();
       List<Item> itemList = group.getUigroupCategory().getItem();
		
		for(Item item : itemList){
			for(Index index : item.getUiindexs()){
				String indexCode = index.getIndexCode();
				for(MonthAssessment month : index.getMonthAssessments()){
					if(!monthMap.containsKey(indexCode)){
						monthMap.put(indexCode, month);
					}
				}
			}
		}
		return monthMap;
	}

	public NComputeScoreServiceI getComputeScoreService()
	{
		return computeScoreService;
	}

	public void setComputeScoreService(NComputeScoreServiceI computeScoreService)
	{
		this.computeScoreService = computeScoreService;
	}


	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	public boolean isValidGroup() {
		return validGroup;
	}

	public void setValidGroup(boolean validGroup) {
		this.validGroup = validGroup;
	}

}
