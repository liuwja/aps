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
 * 组装班组绩效得分算法类
 * <p>
 * @author Lin, 2015-7-23 下午11:03:11
 */
public class NAccembleStrategy extends NAbstractComputeStrategy
{

	static final String ITEM_INDEX_CODE_1 = "C1";
	static final String ITEM_INDEX_CODE_2 = "C2";
	static final String ITEM_INDEX_CODE_3 = "C3";
	
	static final String ITEM_INDEX_CODE_4 = "C4";
	static final String ITEM_INDEX_CODE_5 = "C5";
	static final String ITEM_INDEX_CODE_6 = "C6";
	
	static final String ITEM_INDEX_CODE_7 = "C7";
	static final String ITEM_INDEX_CODE_8 = "C8";
	static final String ITEM_INDEX_CODE_9 = "C9";
	static final String ITEM_INDEX_CODE_10 = "C10";
	static final String ITEM_INDEX_CODE_11 = "C11";
	
	static final String ITEM_INDEX_CODE_12 = "C12";
	static final String ITEM_INDEX_CODE_13 ="C13";

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
		if (month1 != null){
				actualValue1 = mesDataSum.getCol16()==null ? 0 : mesDataSum.getCol16();
		}	
		double indexScore1 = 0;
		if(actualValue1 == 0){
			indexScore1 = 0 ;
		}else if(actualValue1 == 1){
			indexScore1 = -(100*month1.getItemScale()*month1.getIndexScale()*0.5) ;
		}else if(actualValue1 > 1)	{
			indexScore1 = -(100*month1.getItemScale()*month1.getIndexScale()) ;
		}	
		addIndexScoreToList(customItem, idx1, actualValue1, indexScore1);
		//开箱不良次数+有责	
		Index idx2 = itemIndexMap.get(ITEM_INDEX_CODE_2);
		MonthAssessment month2 = monthMap.get(ITEM_INDEX_CODE_2);
		double actualValue2 = 0;
		double indexScore2 = 0;
		if(month2 != null ){
			actualValue2 = mesDataSum.getCol19();
			indexScore2 = -(100*month2.getItemScale()*month2.getIndexScale() * actualValue2) ;
			if(indexScore2 <= -100*month2.getItemScale()*month2.getIndexScale()){
				indexScore2 = -100*month2.getItemScale()*month2.getIndexScale();
			}
		}
		addIndexScoreToList(customItem, idx2, actualValue2, indexScore2);
		
		//市场批不良次数（有责）
		Index idx3 = itemIndexMap.get(ITEM_INDEX_CODE_3);
		MonthAssessment month3 = monthMap.get(ITEM_INDEX_CODE_3);
		double actualValue3 = 0 ;			
		double indexScore3 = 0;
		if(month3 != null){
			
			actualValue3 = mesDataSum.getCol20();
			indexScore3 = -(100*month3.getItemScale()*actualValue3);
			if(indexScore3 <= -100*month3.getItemScale()){
				indexScore3 = -100*month3.getItemScale();
			}
		}
		addIndexScoreToList(customItem, idx3, actualValue3, indexScore3);	
		
		
		//BY JIANGFENG OQC不良
		/*二厂新增一个绩效指标 OQC 不良率 （涉及班组洗碗机班组2个，蒸烤微组装1组，蒸烤微组装2组）指标得分公式如下：
		1 、目标=<实际<=基准并且实际！=0，C4=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重； 
		2、.实际=0，C4=150*该指标权重； 
		3、0<实际<目标，C4=MIN(目标/实际*100,150)*该指标权重； 
		4、否则  C4=0
		 * */
		Index idx13 = itemIndexMap.get(ITEM_INDEX_CODE_13);
		MonthAssessment month13 = monthMap.get(ITEM_INDEX_CODE_13);
		double actualValue13=0;  //实际值
		double indexScore13=0;   //OQC不良率
		double targetValue13=0; //目标值
		double baseValue13=0;  //基准值
		double i=0;//指标权重
		if(month13 !=null)
		{
			actualValue13 = mesDataSum.getCol16()==null ? 0 : mesDataSum.getCol16(); //获取实际值
			baseValue13=month13.getBaseValue(); //获取基准值
			targetValue13=month13.getTargetValue(); //获取目标值
			i=month13.getItemScale()*month13.getIndexScale(); //计算指标权重  项目比例*指标比例
			if(actualValue13>=targetValue13 && actualValue13 <= baseValue13 && actualValue13!=0){
			indexScore13=(Math.abs(actualValue13-baseValue13)/(targetValue13-baseValue13)*40+60)*i;
			}else if(actualValue13 ==0){
				indexScore13=150*i;
			}else if (actualValue13>0 && actualValue13<targetValue13 ){
				indexScore13=Math.min(targetValue13/actualValue13*100,150)*i;
			}else{
				indexScore13=0;
			}
		}
		addIndexScoreToList(customItem, idx13, actualValue13, indexScore13);  //将OQC不良率加入到总的绩效指标
		
		
		//客户质量项目得分
		double itemCustomQualityScore =100*month3.getItemScale()+ indexScore1 + indexScore2 + indexScore3+indexScore13;
		if(itemCustomQualityScore <=0){
			itemCustomQualityScore = 0;
		}
		addItemScoreToList(customItem, itemCustomQualityScore);
		
		/***************************************过程质量**********************************************/
		Item processItem = itemMap.get(ITEM_CODE_PROCESS_QUALITY);
		
		//组装在线不良（有责）
		Index idx4 = itemIndexMap.get(ITEM_INDEX_CODE_4);
		MonthAssessment month4 = monthMap.get(ITEM_INDEX_CODE_4);
		double actualValue4 =0;
	    double indexScore4 = 0;
	    double result4 = 0;
		if(mesDataSum.getFactory().equals("电器一厂")){
		     actualValue4 = MathUtil.divide(mesDataSum.getCol18(), mesDataSum.getCol2(),6);
			 if(month4 != null){
					indexScore4 = absMinRangeFormula(actualValue4, month4);
				}
			addIndexScoreToList(processItem, idx4, actualValue4, indexScore4);
		}else if(mesDataSum.getFactory().equals("燃气工厂"))
		{
			actualValue4 = mesDataSum.getCol18()==null ? 0 : mesDataSum.getCol18(); //获取实际值
			if(month4 != null){
				result4 = actualValue4*5 ;
				if(result4>=20){
					indexScore4 = 20*0.2;
				}else{
					indexScore4 = result4*0.2;
				}
			}else{
				logger.warn("指标项为空。");
				//return -1;
			}
			addIndexScoreToList(processItem, idx4, actualValue4, indexScore4);
		}
		else{
		     actualValue4 =0;
		     indexScore4 = 0;
		    addIndexScoreToList(processItem, idx4, actualValue4, indexScore4);
		}
		//组装在线不良（有责）
		Index idx5 = itemIndexMap.get(ITEM_INDEX_CODE_5);
		MonthAssessment month5 = monthMap.get(ITEM_INDEX_CODE_5);
		double actualValue5 =0;
	    double indexScore5 = 0;
		if(mesDataSum.getFactory().equals("燃气工厂")){
			 actualValue5 = MathUtil.divide(mesDataSum.getCol1(), mesDataSum.getCol2(),6);
			 if(month5 != null){
					indexScore5 = absMinRangeFormula(actualValue5, month5);
				}
			addIndexScoreToList(processItem, idx5, actualValue5, indexScore5);
		}else{
		     actualValue5 =0;
		     indexScore5 = 0;
		    addIndexScoreToList(processItem, idx5, actualValue5, indexScore5);
		}
		//过程质量项目得分
		double itemProcessQualityScore =  indexScore4 + indexScore5;
		addItemScoreToList(processItem, itemProcessQualityScore);
			
		/*******************************************过程审核******************************************/
		Item auditingItem = itemMap.get(ITEM_CODE_PROCESS_AUDITING);
		
        
		//公司外审		
		double auditNum1 = 0 ;
		Index idx7 = itemIndexMap.get(ITEM_INDEX_CODE_7);
		MonthAssessment month7 = monthMap.get(ITEM_INDEX_CODE_7);
		if(month7 != null ){
			auditNum1 = mesDataSum.getCol27() ;
		}	
		double indexScore7 = -(40 * auditNum1);
		addIndexScoreToList(auditingItem, idx7, auditNum1, indexScore7);
		
		//公司内审
		double auditNum2 = 0 ;
		Index idx8 = itemIndexMap.get(ITEM_INDEX_CODE_8);
		MonthAssessment month8 = monthMap.get(ITEM_INDEX_CODE_8);
		if(month8 != null ){
			auditNum2 = mesDataSum.getCol28();
		}	
		double indexScore8 = -(20 * auditNum2);
		addIndexScoreToList(auditingItem, idx8, auditNum2, indexScore8);
		
		//系统内审
		double auditNum3 = 0 ;
		Index idx9 = itemIndexMap.get(ITEM_INDEX_CODE_9);
		MonthAssessment month9 = monthMap.get(ITEM_INDEX_CODE_9);
		if(month9 != null ){
			auditNum3 = mesDataSum.getCol29();
		}	
		double indexScore9 = -(10 * auditNum3);
		addIndexScoreToList(auditingItem, idx9, auditNum3, indexScore9);
		
		//盲点测试
		double auditNum4 = 0;
		Index idx10 = itemIndexMap.get(ITEM_INDEX_CODE_10);
		MonthAssessment month10 = monthMap.get(ITEM_INDEX_CODE_10);
		if(month10 != null ){
			auditNum4 = mesDataSum.getCol32();
		}
		double indexScore10 = -(5 * auditNum4);
		addIndexScoreToList(auditingItem, idx10, auditNum4, indexScore10);
		
		//工艺纪律检查
		double auditNum5 = 0;
		Index idx11 = itemIndexMap.get(ITEM_INDEX_CODE_11);
		MonthAssessment month11 = monthMap.get(ITEM_INDEX_CODE_11);
		if(month11 != null ){
			auditNum5 = mesDataSum.getCol31();
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
