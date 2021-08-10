/*
 * @(#) StampingStrategy.java 2015-7-23 下午11:03:11
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.sumscore;

import java.util.ArrayList;
import java.util.Map;

import com.peg.model.bph.Index;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.Item;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MonthAssessment;
import com.peg.web.util.MathUtil;

/**
 * 部装（电脑板车间）绩效得分算法类
 * <p>
 * @author Lin, 2015-7-23 下午11:03:11
 */
public class NComputerBoardStrategy extends NAbstractComputeStrategy
{

	static final String ITEM_INDEX_CODE_1 = "I1";
	static final String ITEM_INDEX_CODE_2 = "I2";
	static final String ITEM_INDEX_CODE_3 = "I3";
	
	static final String ITEM_INDEX_CODE_4 = "I4";
	static final String ITEM_INDEX_CODE_5 = "I5";
	static final String ITEM_INDEX_CODE_6 = "I6";
	
	static final String ITEM_INDEX_CODE_7 = "I7";
	static final String ITEM_INDEX_CODE_8 = "I8";
	static final String ITEM_INDEX_CODE_9 = "I9";
	static final String ITEM_INDEX_CODE_10 = "I10";
	static final String ITEM_INDEX_CODE_11 = "I11";
	
	static final String ITEM_INDEX_CODE_12 = "I12";

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
		
		//OQC（有责）
		//OQC抽检责任单位判定为对应班组的不良台数
		Index idx1 = itemIndexMap.get(ITEM_INDEX_CODE_1);
		MonthAssessment month1 = monthMap.get(ITEM_INDEX_CODE_1);
		double actualValue1 = 0 ;
		double indexScore1 = 0;
		if (month1 != null){
				actualValue1 = mesDataSum.getCol16()==null ? 0 : mesDataSum.getCol16();
				if(actualValue1 == 0){
					indexScore1 = 100*month1.getItemScale()*month1.getIndexScale() ;
				}else if(actualValue1 == 1){
					indexScore1 = 100*month1.getItemScale()*month1.getIndexScale()*0.5 ;
				}else if(actualValue1 > 1)	{
					indexScore1 = 0 ;
				}	
		}	
		addIndexScoreToList(customItem, idx1, actualValue1, indexScore1);
		//开箱不良次数+有责	
		Index idx2 = itemIndexMap.get(ITEM_INDEX_CODE_2);
		MonthAssessment month2 = monthMap.get(ITEM_INDEX_CODE_2);
		double actualValue2 = 0;
		double indexScore2 = 0;
		if(month2 != null ){
			actualValue2 = mesDataSum.getCol19();
			if(actualValue2 == 0){
				indexScore2 = 100*month2.getItemScale()*month2.getIndexScale() ;
			}else if(actualValue1 == 1){
				indexScore2 = 100*month2.getItemScale()*month2.getIndexScale()*0.5 ;
			}else if(actualValue1 > 1)	{
				indexScore2 = 0 ;
			}	
		}			
		addIndexScoreToList(customItem, idx2, actualValue2, indexScore2);
		
		//消毒柜在线不良(电脑板责任)
		Index idx3 = itemIndexMap.get(ITEM_INDEX_CODE_3);
		MonthAssessment month3 = monthMap.get(ITEM_INDEX_CODE_3);
		double actualValue3 = 0 ;			
		double indexScore3 = 0;
		if(month3 != null){
			actualValue3 = MathUtil.divide(mesDataSum.getCol18(), mesDataSum.getCol2(),6)*1000000;
			indexScore3 = absMinRangeFormula(actualValue3, month3);
		}
		addIndexScoreToList(customItem, idx3, actualValue3, indexScore3);
		
		//客户端批不良（OQC、组装异常、市场）（有责）
		Index idx4 = itemIndexMap.get(ITEM_INDEX_CODE_4);
		MonthAssessment month4 = monthMap.get(ITEM_INDEX_CODE_4);
		double actualValue4 = 0 ;			
		double indexScore4 = 0;
		if(month4 != null){
			actualValue4 = mesDataSum.getCol25();
		}
		indexScore4 = actualValue4;
		addIndexScoreToList(customItem, idx4, actualValue4, indexScore4);
				
		
		//客户质量项目得分
		double itemCustomQualityScore = indexScore1 + indexScore2 + indexScore3 + indexScore4;
		if(itemCustomQualityScore <=0){
			itemCustomQualityScore = 0;
		}
		addItemScoreToList(customItem, itemCustomQualityScore);
		
		/***************************************过程质量**********************************************/
			
		/*******************************************过程审核******************************************/
		Item auditingItem = itemMap.get(ITEM_CODE_PROCESS_AUDITING);
		
        
		//公司外审		
		double auditNum1 = 0 ;
		Index idx5 = itemIndexMap.get(ITEM_INDEX_CODE_5);
		MonthAssessment month5 = monthMap.get(ITEM_INDEX_CODE_5);
		if(month5 != null ){
			auditNum1 = mesDataSum.getCol27() ;
		}	
		double indexScore5 = -(40 * auditNum1);
		addIndexScoreToList(auditingItem, idx5, auditNum1, indexScore5);
		
		//公司内审
		double auditNum2 = 0 ;
		Index idx6 = itemIndexMap.get(ITEM_INDEX_CODE_6);
		MonthAssessment month6 = monthMap.get(ITEM_INDEX_CODE_6);
		if(month6!= null ){
			auditNum2 = mesDataSum.getCol28();
		}	
		double indexScore6 = -(20 * auditNum2);
		addIndexScoreToList(auditingItem, idx6, auditNum2, indexScore6);
		
		//系统内审
		double auditNum3 = 0 ;
		Index idx7 = itemIndexMap.get(ITEM_INDEX_CODE_7);
		MonthAssessment month7 = monthMap.get(ITEM_INDEX_CODE_7);
		if(month7 != null ){
			auditNum3 = mesDataSum.getCol29();
		}	
		double indexScore7 = -(10 * auditNum3);
		addIndexScoreToList(auditingItem, idx7, auditNum3, indexScore7);
		
		//盲点测试
		double auditNum4 = 0;
		Index idx8 = itemIndexMap.get(ITEM_INDEX_CODE_8);
		MonthAssessment month8 = monthMap.get(ITEM_INDEX_CODE_8);
		if(month8 != null ){
			auditNum4 = mesDataSum.getCol32();
		}
		double indexScore8 = -(5 * auditNum4);
		addIndexScoreToList(auditingItem, idx8, auditNum4, indexScore8);
		
		//工艺纪律检查
		double auditNum5 = 0;
		Index idx9 = itemIndexMap.get(ITEM_INDEX_CODE_9);
		MonthAssessment month9 = monthMap.get(ITEM_INDEX_CODE_9);
		if(month9 != null ){
			auditNum5 = mesDataSum.getCol31();
		}
		double indexScore9 = -(5 * auditNum5);
		addIndexScoreToList(auditingItem, idx9, auditNum5, indexScore9);
				
		//过程审核项目得分
		double itemAuditingScore = indexScore5 + indexScore6 + indexScore7 + indexScore8 + indexScore9;
		addItemScoreToList(auditingItem, itemAuditingScore);
		
		
		/*******************************************附加项******************************************/
		Item additionalItem = itemMap.get(ITEM_CODE_ADDITIONAL);
		
		//月度责任班组附加分数指标
		Index idx10 = itemIndexMap.get(ITEM_INDEX_CODE_10);
		MonthAssessment month10 = monthMap.get(ITEM_INDEX_CODE_10);
		double additionalActValue = 0;
		if(month10 != null ){
			additionalActValue = mesDataSum.getCol9();
		}	
		double indexScore10 = computeAdditionalScore(additionalActValue);
		addIndexScoreToList(additionalItem, idx10, additionalActValue, indexScore10);
		
		addItemScoreToList(additionalItem, indexScore10);
	}

}
