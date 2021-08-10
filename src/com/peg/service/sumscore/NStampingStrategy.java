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
 * 冲压班组绩效得分算法类
 * <p>
 * @author Lin, 2015-7-23 下午11:03:11
 */
public class NStampingStrategy extends NAbstractComputeStrategy
{
	static final String ITEM_INDEX_CODE_1 = "A1";
	static final String ITEM_INDEX_CODE_2 = "A2";
	static final String ITEM_INDEX_CODE_3 = "A3";
	
	static final String ITEM_INDEX_CODE_4 = "A4";
	static final String ITEM_INDEX_CODE_5 = "A5";
	static final String ITEM_INDEX_CODE_6 = "A6";
	
	static final String ITEM_INDEX_CODE_7 = "A7";
	static final String ITEM_INDEX_CODE_8 = "A8";
	static final String ITEM_INDEX_CODE_9 = "A9";
	static final String ITEM_INDEX_CODE_10 = "A10";
	static final String ITEM_INDEX_CODE_11 = "A11";
	
	static final String ITEM_INDEX_CODE_12 = "A12";
	

	
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
		
		//组装退次率指标
		//公式：实际 =【月度责任班组的退次数（不良数）】/【月度组装投产数】*100%*1000000
		Index idx1 = itemIndexMap.get(ITEM_INDEX_CODE_1);
		MonthAssessment month1 = monthMap.get(ITEM_INDEX_CODE_1);
		double actualValue1 = 0;
		actualValue1 = MathUtil.divide(mesDataSum.getCol1(), mesDataSum.getCol2(),6) * 1000000;
		double indexScore1 = 0;
		if(month1 != null){
			indexScore1 = absMinRangeFormula(actualValue1, month1);
		}
		addIndexScoreToList(customItem, idx1, actualValue1, indexScore1);
		
		//喷涂退次率
		//1. 目标=<实际<=基准，A2=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；2.实际=0，A2=150*该指标权重；3.0<实际<目标，A2=MIN(目标/实际*100,150)*该指标权重；4.否则  A2=0
		Index idx2 = itemIndexMap.get(ITEM_INDEX_CODE_2);
		MonthAssessment month2 = monthMap.get(ITEM_INDEX_CODE_2);
		double actualValue2 = 0 ;
		actualValue2 = MathUtil.divide(mesDataSum.getCol15(), mesDataSum.getCol23(),6) ;
		double indexScore2 = 0;
		if(month2 != null){
			indexScore2 = absMinRangeFormula(actualValue2, month2);
		}
		addIndexScoreToList(customItem, idx2, actualValue2, indexScore2);
		
		//客户端批不良-冲压责任
		//A3为负数，根据（发生流程节点*批量大小*质量后果）的结果值从扣分规则中获取对应的扣分值
		Index idx3 = itemIndexMap.get(ITEM_INDEX_CODE_3);
		MonthAssessment month3 = monthMap.get(ITEM_INDEX_CODE_3);
		double actualValue3 = 0;
		if(month3 != null ){
			actualValue3 = mesDataSum.getCol25();
		}		
		double indexScore3 = actualValue3;
		addIndexScoreToList(customItem, idx3, actualValue3, indexScore3);
		
		//客户质量项目得分
		double itemCustomQualityScore = indexScore1 + indexScore2 + indexScore3;
		if(itemCustomQualityScore < 0){
			itemCustomQualityScore = 0;
		}
		addItemScoreToList(customItem, itemCustomQualityScore);
		/***************************************过程质量**********************************************/
		Item processItem = itemMap.get(ITEM_CODE_PROCESS_QUALITY);
		
		//冲压一次合格率
		// 1.  基准=<实际<=目标，B4=【(实际-基准)/(目标-基准)*40+60】*该指标权重；2. 实际＞目标，B4=MIN【(实际-基准)/(目标-基准)*100,150】*该指标权重；3. 否则  B4=0
		Index idx4 = itemIndexMap.get(ITEM_INDEX_CODE_4);
		MonthAssessment month4 = monthMap.get(ITEM_INDEX_CODE_4);
		double actualValue4 = 0;
		actualValue4 = MathUtil.divide(mesDataSum.getCol11()-mesDataSum.getCol10(), mesDataSum.getCol11(),6) ;
		double indexScore4 = 0;
		if(month4 != null){
			indexScore4 = absMinRangeFormulaByMonthPait(actualValue4, month4);
		}
		addIndexScoreToList(processItem, idx4, actualValue4, indexScore4);
		
		//IPQC批次不合格率
		Index idx5 = itemIndexMap.get(ITEM_INDEX_CODE_5);
		MonthAssessment month5 = monthMap.get(ITEM_INDEX_CODE_5);
		double actualValue5 = 0 ;
		actualValue5 = MathUtil.divide(mesDataSum.getCol5(), mesDataSum.getCol6(),6) ;
		double indexScore5 = 0;
		if(month5 != null){
			indexScore5 = absMinRangeFormula(actualValue5, month5);
		}
		addIndexScoreToList(processItem, idx5, actualValue5, indexScore5);
		
		//冲压批不良
		Index idx6 = itemIndexMap.get(ITEM_INDEX_CODE_6);
		MonthAssessment month6 = monthMap.get(ITEM_INDEX_CODE_6);
		double actualValue6 = 0;
		if(month6 != null){
			actualValue6 = mesDataSum.getCol8();;
		}		
		double indexScore6 = PROCESS_NODE_D_SCORE*actualValue6;
		addIndexScoreToList(processItem, idx6, actualValue6, indexScore6);
		
		//过程质量项目得分
		double itemProcessQualityScore = indexScore4 + indexScore5 + indexScore6;
		if(itemProcessQualityScore < -100){
			itemProcessQualityScore = -100;
		}
		addItemScoreToList(processItem, itemProcessQualityScore);
		/*******************************************过程审核******************************************/
		Item auditingItem = itemMap.get(ITEM_CODE_PROCESS_AUDITING);
		
		//公司外审不符
		double auditNum1 = 0 ;
		Index idx7 = itemIndexMap.get(ITEM_INDEX_CODE_7);
		MonthAssessment month7 = monthMap.get(ITEM_INDEX_CODE_7);
		if(month7 != null ){
			auditNum1 = mesDataSum.getCol27(); ;
		}	
		double indexScore7 = -(40 * auditNum1);
		addIndexScoreToList(auditingItem, idx7, auditNum1, indexScore7);
		
		//公司内审不符
		double auditNum2 = 0 ;
		Index idx8 = itemIndexMap.get(ITEM_INDEX_CODE_8);
		MonthAssessment month8 = monthMap.get(ITEM_INDEX_CODE_8);
		if(month8 != null ){
			auditNum2 = mesDataSum.getCol28();;
		}		
		double indexScore8 = -(20 * auditNum2);
		addIndexScoreToList(auditingItem, idx8, auditNum2, indexScore8);
		
		//系统内审不符
		double auditNum3 = 0 ;
		Index idx9 = itemIndexMap.get(ITEM_INDEX_CODE_9);
		MonthAssessment month9 = monthMap.get(ITEM_INDEX_CODE_9);
		if(month9 != null ){
			auditNum3 = mesDataSum.getCol29();;
		}	 
		double indexScore9 = -(10 * auditNum3);
		addIndexScoreToList(auditingItem, idx9, auditNum3, indexScore9);
		
		//巡检5ME1
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
			auditNum5 =mesDataSum.getCol31();;
		}			
		double indexScore11 = -(5 * auditNum5);
		addIndexScoreToList(auditingItem, idx11, auditNum5, indexScore11);
		
		//过程审核项目得分
		double itemAuditingScore = indexScore7 + indexScore8 + indexScore9 + indexScore10 + indexScore11;
		if(itemAuditingScore<-100){
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
