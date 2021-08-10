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

/**
 * IQC绩效得分算法类
 * <p>
 * @author Lin, 2015-7-23 下午11:03:11
 */
public class NIqcStrategy extends NAbstractComputeStrategy
{

	static final String ITEM_INDEX_CODE_1 = "F1";
	static final String ITEM_INDEX_CODE_2 = "F2";
	static final String ITEM_INDEX_CODE_3 = "F3";
	
	static final String ITEM_INDEX_CODE_4 = "F4";
	static final String ITEM_INDEX_CODE_5 = "F5";
	static final String ITEM_INDEX_CODE_6 = "F6";

	
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
		
		//组装物料批不良-供应商责任（包含停线）
		Index idx1 = itemIndexMap.get(ITEM_INDEX_CODE_1);
		MonthAssessment month1 = monthMap.get(ITEM_INDEX_CODE_1);
		double actualValue1 = mesDataSum.getCol25();
		double indexScore1 =absMinRangeFormula(actualValue1, month1);
		addIndexScoreToList(customItem, idx1, actualValue1, indexScore1);
		
		//OQC不良（有责）
		Index idx2 = itemIndexMap.get(ITEM_INDEX_CODE_2);
		MonthAssessment month2 = monthMap.get(ITEM_INDEX_CODE_2);
		double actualValue2 = 0 ;	
		if(mesDataSum.getCol16() != null){
			actualValue2 = mesDataSum.getCol16();
		}	
		double indexScore2 =-100*month2.getItemScale()*0.5*actualValue2;
		if(indexScore2 <-100*month2.getItemScale()*month2.getIndexScale()){
			indexScore2 = -100*month2.getItemScale()*month2.getIndexScale();
		}
		addIndexScoreToList(customItem, idx2, actualValue2, indexScore2);
		
		//客户质量得分
		double itemCustomScore =100*month1.getItemScale()+ indexScore1 + indexScore2;
		if(itemCustomScore < 0){
			itemCustomScore = 0;
		}
		addItemScoreToList(customItem, itemCustomScore);
		
	/*******************************************过程审核******************************************/
		Item auditingItem = itemMap.get(ITEM_CODE_PROCESS_AUDITING);
		

		//外审不符
		double auditNum = 0;
		Index idx3 = itemIndexMap.get(ITEM_INDEX_CODE_3);
		MonthAssessment month3 = monthMap.get(ITEM_INDEX_CODE_3);
		if(month3 != null){
			auditNum = mesDataSum.getCol27();
		}			
		double indexScore3 = -(50 * auditNum);
		addIndexScoreToList(auditingItem, idx3, auditNum, indexScore3);
		
		//内审不符
		double auditNum2 = 0;
		Index idx4 = itemIndexMap.get(ITEM_INDEX_CODE_4);
		MonthAssessment month4 = monthMap.get(ITEM_INDEX_CODE_4);
		if(month4 != null ){
			auditNum2 = mesDataSum.getCol28();
		}		
		double indexScore4 = -(25 * auditNum2);
		addIndexScoreToList(auditingItem, idx4, auditNum2, indexScore4);
		
		//系统内审不符
		double auditNum3 = 0;
		Index idx5 = itemIndexMap.get(ITEM_INDEX_CODE_5);
		MonthAssessment month5 = monthMap.get(ITEM_INDEX_CODE_5);
		if(month5 != null ){
			auditNum3 = mesDataSum.getCol29();
		}		
		double indexScore5 = -(10 * auditNum3);
		addIndexScoreToList(auditingItem, idx5, auditNum3, indexScore5);
		
		//过程审核项目得分
		double itemAuditingScore = indexScore3 + indexScore4 + indexScore5 ;
		if(itemAuditingScore<-100){
			itemAuditingScore =-100;
		}
		addItemScoreToList(auditingItem, itemAuditingScore);
	
	/*******************************************附加项******************************************/
		Item additionalItem = itemMap.get(ITEM_CODE_ADDITIONAL);

		//月度责任班组附加分数指标
		Index idx6 = itemIndexMap.get(ITEM_INDEX_CODE_6);
		MonthAssessment month6 = monthMap.get(ITEM_INDEX_CODE_6);
		double additionalActValue = 0;
		if(month6 != null ){
			additionalActValue = mesDataSum.getCol9();
		}
		double indexScore6 = computeAdditionalScore(additionalActValue);
		addIndexScoreToList(additionalItem , idx6, additionalActValue, indexScore6);
				
		addItemScoreToList(additionalItem, indexScore6);
	}

}
