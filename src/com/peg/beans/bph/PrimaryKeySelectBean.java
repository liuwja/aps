package com.peg.beans.bph;

import org.apache.commons.lang.StringUtils;

import com.peg.model.bph.IndexScroe;

public class PrimaryKeySelectBean {

	//指标编号
	static final String ASSEMBLEPRODUCE_INDEX = "A1_B1_D1_C5_E1_H3";
	static final String FORMERFQCCHECH_INDEX = "E2";
	static final String BATCHDEFECT_INDEX = "A3_B2_D3_E3_E6_F1_H4_I4" ;
	static final String STAMPINGDAYLY_INDEX = "A4_E4" ;
	static final String QUALITYINP_INDEX = "A12_B12_C12_D12_E12_F6_G6_H10_I10";
	static final String BOXDEFECT_INDEX = "C2_C3_G1_G2_H2_I2";
	static final String PAINTINGDAYLY_INDEX = "B4";
	static final String IPQCINSPECTS_INDEX = "A5_B3_B6_A6_D5_D6_E5";
	static final String OQCCHECK_INDEX = "C1_F2_H1_I1";
	static final String ASSEMBLYREPARIED_INDEX = "C4_I3";
	static final String PAINTINGPRODUCE_INDEX = "A2_D2";
	static final String FINISHINGDAILY_INDEX = "D4";
	static final String PROCESSAUDIT_INDEX_1 = "A7_B7_C7_D7_E7_F3_G3_H5_I5";      //公司外审
	static final String PROCESSAUDIT_INDEX_2 = "A8_B8_C8_D8_E8_F4_G4_H6_I6";      //公司内审
	static final String PROCESSAUDIT_INDEX_3 = "A9_B9_C9_D9_E9_F5_G5_H7_I7";      //系统内审
	static final String PROCESSAUDIT_INDEX_4 = "A10_B10_D10_E10";                 //巡检5M1E
	static final String PROCESSAUDIT_INDEX_5 = "A11_B11_C11_D11_E11_H9_I9";       //工艺纪律检查
	static final String PROCESSAUDIT_INDEX_6 = "C10_H8_I8";                       //盲点测试
	
	private String sql=" ";
	private String select=" ";
	private String orderBy=" ";
	
	public String primaryKeySql(IndexScroe indexScore){
		String startTime = indexScore.getStartTime();
		String endTime = indexScore.getEndTime();
		String factory = indexScore.getBaseFactory();
		String area =  indexScore.getBaseArea();
		String group =  indexScore.getBaseGroup();
		String index = indexScore.getCheckIndex();
		 if(ASSEMBLEPRODUCE_INDEX.indexOf(index)>=0){
			 sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_1 as col_1,a.col_2 as col_2,round(a.col_1/a.col_2, 6) as col_3 from t_mes_data_sum_day a where 1=1";
			}else if(FORMERFQCCHECH_INDEX.indexOf(index)>=0){
				
			}else if(BATCHDEFECT_INDEX.indexOf(index)>=0){
			 sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_25 as col_3 from t_mes_data_sum_day a where 1=1";	
				
			}else if(STAMPINGDAYLY_INDEX.indexOf(index)>=0){
			 sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_10 as col_1 ,a.col_11 as col_2,round(a.col_10/a.col_11, 6) as col_3 from t_mes_data_sum_day a where 1=1";		
				
			}else if(OQCCHECK_INDEX.indexOf(index)>=0 ){
			 sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_16 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(PROCESSAUDIT_INDEX_1.indexOf(index)>=0 ){
				sql =" select a.factory,a.area,a.shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_27 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(PROCESSAUDIT_INDEX_2.indexOf(index)>=0 ){
				sql =" select a.factory,a.area,a.shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_28 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(PROCESSAUDIT_INDEX_3.indexOf(index)>=0 ){
				sql =" select a.factory,a.area,a.shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_29 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(PROCESSAUDIT_INDEX_4.indexOf(index)>=0 ){
				sql =" select a.factory,a.area,a.shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_30 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(PROCESSAUDIT_INDEX_5.indexOf(index)>=0 ){
				sql =" select a.factory,a.area,a.shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_31 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(PROCESSAUDIT_INDEX_6.indexOf(index)>=0 ){
				sql =" select a.factory,a.area,a.shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_32 as col_3 from t_mes_data_sum_day a where 1=1";			
				 
			}else if(QUALITYINP_INDEX.indexOf(index)>=0 ){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_9 as col_3 from t_mes_data_sum_day a where 1=1";			
				
			}else if(BOXDEFECT_INDEX.indexOf(index)>=0){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_19 as col_3  from t_mes_data_sum_day a where 1=1";			
				
			}else if(PAINTINGDAYLY_INDEX.indexOf(index)>=0){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_12 as col_1,a.col_13 as col_2,round(a.col_12/a.col_13, 6) as col_3   from t_mes_data_sum_day a where 1=1";			
				
			}else if(IPQCINSPECTS_INDEX.indexOf(index)>=0){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_5 ,a.col_6,a.col_7,a.col_8  from t_mes_data_sum_day a where 1=1";			
				
			}else if(ASSEMBLYREPARIED_INDEX.indexOf(index)>=0){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd')  as sum_date, a.col_18 as col_1,a.col_2 as col_2 ,round(a.col_18/a.col_2, 6) as col_3 from t_mes_data_sum_day a where 1=1";			
			}
			else if(PAINTINGPRODUCE_INDEX.indexOf(index)>=0){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_15 as col_1,a.col_23 as col_2 ,round(a.col_15/a.col_23, 6) as col_3 from t_mes_data_sum_day a where 1=1";			
			}
			else if(FINISHINGDAILY_INDEX.indexOf(index)>=0){
				sql =" select a.factory as factory,a.area as area,a.shift_group_name as shift_group_name,to_char(sum_date,'yyyy-mm-dd') as sum_date, a.col_21 as col_1,a.col_22 as col_2,round(a.col_21/a.col_22, 6) as col_3  from t_mes_data_sum_day a where 1=1";			
			}
		 if(StringUtils.isNotBlank(factory)){
			 select += " and a.factory ='"+factory+"'";
		 }
		 if(StringUtils.isNotBlank(area)){
			 select += " and a.area ='"+area+"'";
		 }
		 if(StringUtils.isNotBlank(group)){
			 select += " and a.shift_group_name ='"+group+"'";
		 }
		 if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
			 select += " and to_char(a.sum_date,'yyyy-mm-dd') between '"+startTime +"' and '"+endTime+"'";
		 }
		 orderBy = " order by to_char(a.sum_date,'yyyy-mm-dd') desc" ;
		return sql + select + orderBy;
		
	}
}
