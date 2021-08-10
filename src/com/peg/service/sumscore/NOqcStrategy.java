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
 * OQC绩效得分算法类
 * <p>
 * @author Lin, 2015-7-23 下午11:03:11
 */
public class NOqcStrategy extends NAbstractComputeStrategy
{


	static final String ITEM_INDEX_CODE_1 = "G1";
	static final String ITEM_INDEX_CODE_2 = "G2";
	static final String ITEM_INDEX_CODE_3 = "G3";
	
	static final String ITEM_INDEX_CODE_4 = "G4";
	static final String ITEM_INDEX_CODE_5 = "G5";
	static final String ITEM_INDEX_CODE_6 = "G6";
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
		
		//市场批量不良次数+有责
		Index idx1 = itemIndexMap.get(ITEM_INDEX_CODE_1);
		MonthAssessment month1 = monthMap.get(ITEM_INDEX_CODE_1);
		double actualValue1 = 0 ;			
		double indexScore1 = 0;
		if(mesDataSum.getCol20() != null){
			if(month1 != null ){
				actualValue1 = mesDataSum.getCol20();
			}
			if(actualValue1 >=1){
				indexScore1 = -100*month1.getItemScale();
			}
		}
		addIndexScoreToList(customItem, idx1, actualValue1, indexScore1);
       
		//开箱不良次数+有责
		Index idx2 = itemIndexMap.get(ITEM_INDEX_CODE_2);
		MonthAssessment month2 = monthMap.get(ITEM_INDEX_CODE_2);
		double actualValue2 = 0;
		double indexScore2 = 0;
		if(mesDataSum.getCol19() != null){
			if(month2 != null ){
				actualValue2 = mesDataSum.getCol19();
			}
			indexScore2 = -(5 * actualValue2) ;
			if(indexScore2 <= -100*month2.getItemScale()){
				indexScore2 = -100*month2.getItemScale();
			}
		}
		addIndexScoreToList(customItem, idx2, actualValue2, indexScore2);
		
		//客户质量项目得分
		double itemCustomQualityScore = 100*month1.getItemScale() + indexScore1 + indexScore2 ;
		if(itemCustomQualityScore <=0){
				itemCustomQualityScore = 0;
			}
		addItemScoreToList(customItem, itemCustomQualityScore);
		
		
	/*******************************************过程审核******************************************/
		Item auditingItem = itemMap.get(ITEM_CODE_PROCESS_AUDITING);
		
		
		
		//公司外审
        double auditNum = 0;
        Index idx3 = itemIndexMap.get(ITEM_INDEX_CODE_3);
        MonthAssessment month3 = monthMap.get(ITEM_INDEX_CODE_3);
    	if(month3 != null){
           	auditNum = mesDataSum.getCol27();
		}
		double indexScore3 = -(50 * auditNum);
		addIndexScoreToList(auditingItem, idx3, auditNum, indexScore3);
		
		//公司内审
		double auditNum2 = 0;
		Index idx4 = itemIndexMap.get(ITEM_INDEX_CODE_4);
		MonthAssessment month4 = monthMap.get(ITEM_INDEX_CODE_4);
		if(month4 != null ){
			auditNum2 = mesDataSum.getCol28();
		}
		double indexScore4 = -(25 * auditNum2);
		addIndexScoreToList(auditingItem, idx4, auditNum2, indexScore4);
		
		//系统内审
		double auditNum3 = 0;
		Index idx5 = itemIndexMap.get(ITEM_INDEX_CODE_5);
		MonthAssessment month5 = monthMap.get(ITEM_INDEX_CODE_5);
    	if(month5 != null){
    		auditNum3 = mesDataSum.getCol29();
		}      	
		double indexScore5 = -(10 * auditNum3);
		addIndexScoreToList(auditingItem, idx5, auditNum3, indexScore5);
		
		//过程审核项目得分
		double itemAuditingScore = 100 * month3.getItemScale() + (indexScore3 + indexScore4 + indexScore5 );
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
		addIndexScoreToList(additionalItem, idx6, additionalActValue, indexScore6);
				
		addItemScoreToList(additionalItem, indexScore6);

	}

}
