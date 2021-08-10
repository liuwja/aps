/*
 * @(#) StampingStrategy.java 2015-7-23 下午11:03:11
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.sumscore;

import java.util.ArrayList;
import java.util.Map;

import com.peg.model.bph.Index;
import com.peg.model.bph.Item;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MonthAssessment;
import com.peg.web.util.MathUtil;

/**
 * 喷涂-喷漆/易清洁绩效得分算法类
 * <p>
 * @author Lin, 2015-7-23 下午11:03:11
 */
public class NPaintingStrategyOne extends NAbstractComputeStrategy
{

	static final String ITEM_INDEX_CODE_1 = "B1";
	static final String ITEM_INDEX_CODE_2 = "B2";
	static final String ITEM_INDEX_CODE_3 = "B3";
	
	static final String ITEM_INDEX_CODE_4 = "B4";
	static final String ITEM_INDEX_CODE_5 = "B5";
	static final String ITEM_INDEX_CODE_6 = "B6";
	
	static final String ITEM_INDEX_CODE_7 = "B7";
	static final String ITEM_INDEX_CODE_8 = "B8";
	static final String ITEM_INDEX_CODE_9 = "B9";
	static final String ITEM_INDEX_CODE_10 = "B10";
	static final String ITEM_INDEX_CODE_11 = "B11";
	
	static final String ITEM_INDEX_CODE_12 = "B12";

	@Override
	public void compute()
	{
		indexScroeList = new ArrayList<IndexScroe>();
		itemScoreList = new ArrayList<ItemScore>();
		Map<String,Item> itemMap = getItemMap(group);
		Map<String, Index> itemIndexMap = getIndexMap(group);
		Map<String,MonthAssessment> monthMap = getMonthMap(group);
		
		/**************************************客户质量***********************************************/
		//客户质量考核项目
		Item customItem = itemMap.get(ITEM_CODE_CUSTOM_QUALITY);
		
		//组装上线退次不良率
        //实际 =【月度责任班组的退次数（不良数）】/【月度组装投产数】*100%*1000000
		Index idx1 = itemIndexMap.get(ITEM_INDEX_CODE_1);
		MonthAssessment month1 = monthMap.get(ITEM_INDEX_CODE_1);
		double actualValue1 = 0;
	    actualValue1 = MathUtil.divide(mesDataSum.getCol1(), mesDataSum.getCol2(),6) * 1000000;
		double indexScore1 = 0;
		if(month1 != null){
			indexScore1 = absMinRangeFormula(actualValue1, month1);
		}
		addIndexScoreToList(customItem, idx1, actualValue1, indexScore1);
		
		//客户端物料批不良-涂装责任
        //B2为负数，根据（发生流程节点*批量大小*质量后果）的结果值从扣分规则中获取对应的扣分值
		Index idx2 = itemIndexMap.get(ITEM_INDEX_CODE_2);
		MonthAssessment month2 = monthMap.get(ITEM_INDEX_CODE_2);
		double actualValue2 = 0 ;
	    actualValue2 =mesDataSum.getCol25() ;
		double indexScore2 = 0;
		if(month2 != null){
			indexScore2 = actualValue2;
		}
		addIndexScoreToList(customItem, idx2, actualValue2, indexScore2);

		

		//客户质量项目得分
		double itemCustomQualityScore = indexScore1 + indexScore2 ;
		if(itemCustomQualityScore < -100){
			itemCustomQualityScore = -100 ;
		}
		addItemScoreToList(customItem, itemCustomQualityScore);
		/***************************************过程质量**********************************************/
		Item processItem = itemMap.get(ITEM_CODE_PROCESS_QUALITY);
		
		//涂装IPQC不良率
		Index idx3 = itemIndexMap.get(ITEM_INDEX_CODE_3);
		MonthAssessment month3 = monthMap.get(ITEM_INDEX_CODE_3);
		double actualValue3 = 0;
		if(month3 != null ){
			actualValue3 =  MathUtil.divide(mesDataSum.getCol5(), mesDataSum.getCol6(),6) ;
		}	
		double indexScore3 = absMinRangeFormula(actualValue3,month3);
		addIndexScoreToList(processItem, idx3, actualValue3, indexScore3);
		
		//喷涂一次合格率
		Index idx4 = itemIndexMap.get(ITEM_INDEX_CODE_4);
		MonthAssessment month4 = monthMap.get(ITEM_INDEX_CODE_4);
		double actualValue4 = 0;
		actualValue4 = MathUtil.divide(mesDataSum.getCol12(), mesDataSum.getCol13(),6) ;
		double indexScore4 = 0;
		if(month4 != null){
			indexScore4 = absMinRangeFormulaByMonthPait(actualValue4, month4);
		}
		addIndexScoreToList(processItem, idx4, actualValue4, indexScore4);
		
		//喷涂批不良(有责)
		Index idx6 = itemIndexMap.get(ITEM_INDEX_CODE_6);
		MonthAssessment month6 = monthMap.get(ITEM_INDEX_CODE_6);
		double actualValue6 = 0;
		if(month6 != null ){
			actualValue6 = mesDataSum.getCol8();;
		}
		double indexScore6 = -2.5* actualValue6;
		addIndexScoreToList(processItem, idx6, actualValue6, indexScore6);

		//过程质量项目得分
		double itemProcessQualityScore = indexScore3 + indexScore4 + indexScore6;
		if(itemProcessQualityScore < -100){
			itemProcessQualityScore = -100;
		}
		addItemScoreToList(processItem, itemProcessQualityScore);
		
		
		/*******************************************过程审核******************************************/
		Item auditingItem = itemMap.get(ITEM_CODE_PROCESS_AUDITING);
		
		//公司外审
		double auditNum1 = 0 ;
		Index idx7 = itemIndexMap.get(ITEM_INDEX_CODE_7);
		MonthAssessment month7 = monthMap.get(ITEM_INDEX_CODE_7);
		if(month7 != null ){
			auditNum1 = mesDataSum.getCol27(); ;
		}
		double indexScore7 = -(40 * auditNum1);
		addIndexScoreToList(auditingItem, idx7, auditNum1, indexScore7);
		
		//公司内审
		double auditNum2 = 0 ;
		Index idx8 = itemIndexMap.get(ITEM_INDEX_CODE_8);
		MonthAssessment month8 = monthMap.get(ITEM_INDEX_CODE_8);
		if(month8 != null ){
			auditNum2 = mesDataSum.getCol28();;
		}
		double indexScore8 = -(20 * auditNum2);
		addIndexScoreToList(auditingItem, idx8, auditNum2, indexScore8);
		
		//系统内审
		double auditNum3 = 0 ;
		Index idx9 = itemIndexMap.get(ITEM_INDEX_CODE_9);
		MonthAssessment month9 = monthMap.get(ITEM_INDEX_CODE_9);
		if(month9 != null ){
			auditNum3 = mesDataSum.getCol29();;
		}	
		double indexScore9 = -(10 * auditNum3);
		addIndexScoreToList(auditingItem, idx9, auditNum3, indexScore9);
		
		//巡检5M1E
		double auditNum4 = 0;
		Index idx10 = itemIndexMap.get(ITEM_INDEX_CODE_10);
		MonthAssessment month10 = monthMap.get(ITEM_INDEX_CODE_10);
		if(month10 != null){
			auditNum4 = mesDataSum.getCol30();;
		}	
		double indexScore10 = -(5 * auditNum4);
		addIndexScoreToList(auditingItem, idx10, auditNum4, indexScore10);
		
		//工艺纪律检查
		double auditNum5 = 0;
		Index idx11 = itemIndexMap.get(ITEM_INDEX_CODE_11);
		MonthAssessment month11 = monthMap.get(ITEM_INDEX_CODE_11);
		if(month11 != null ){
			auditNum5 = mesDataSum.getCol31();;
		}	
		double indexScore11 = -(5 * auditNum5);
		addIndexScoreToList(auditingItem, idx11, auditNum5, indexScore11);
		
		//过程审核项目得分
		double itemAuditingScore = indexScore7 + indexScore8 + indexScore9 + indexScore10 + indexScore11;
		if(itemAuditingScore < -100){
			itemAuditingScore = -100 ;
		}
		addItemScoreToList(auditingItem, itemAuditingScore);
		
		/*******************************************附加项******************************************/
		Item additionalItem = itemMap.get(ITEM_CODE_ADDITIONAL);
		//月度责任班组附加分数指标
		Index idx12 = itemIndexMap.get(ITEM_INDEX_CODE_12);
		MonthAssessment month12 = monthMap.get(ITEM_INDEX_CODE_12);
		double additionalActValue = 0;
		if(month12 != null ){
			additionalActValue = mesDataSum.getCol9();
		}	
		double indexScore12 = computeAdditionalScore(additionalActValue);
		addIndexScoreToList(additionalItem, idx12, additionalActValue, indexScore12);
				
		addItemScoreToList(additionalItem, indexScore12);
		

	}

}
