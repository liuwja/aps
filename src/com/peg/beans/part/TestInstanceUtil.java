package com.peg.beans.part;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.peg.model.part.MarkPoint;
import com.peg.model.part.TestInstance;
import com.peg.web.util.MathUtil;

@SuppressWarnings("static-access")
public class TestInstanceUtil {

    /**
     * 转换Map-供应商
     * @param list
     * @return
     */
	public static Map<String, String> getSupMap(List<TestInstance> list){
		Map<String,String> map = new LinkedHashMap<String, String>();
		for(TestInstance in : list){
			map.put(in.getSupplier(), in.getSupplier());
		}
		return map;
	}
	
	/**
     * 转换Map-物料大类
     * @param list
     * @return
     */
	public static Map<String, String> getPartMap(List<TestInstance> list){
		Map<String,String> map = new LinkedHashMap<String, String>();
		for(TestInstance in : list){
			map.put(in.getPartType(), in.getPartType());
		}
		return map;
	}
	
    /**
     * 构造图形option
     * @param testInstance
     * @param supList
     * @return
     */
	public static String getData(TestInstance test,
			List<TestInstance> supList) {
		StringBuffer str = new StringBuffer();
		int type = test.getType();
		//去除不良批或不良数为0的记录
		for(int i =0; i<supList.size(); i++){
			if(type==3 || type==4 || type ==5 || type==6 || type==7|| type ==8){
				break;
			}
			if("0".equals(test.getAnalysisType())){
				if(supList.get(i).getDefectLot()==0){
					supList.remove(i);
					i--; 
				}
			}else{
				if(supList.get(i).getDefectQty()==0){
					supList.remove(i);
					i--; 
				}
			}
		}
		/**
		 * 供应商排列图
		 */
		int barWidth = 35;
		String formatter = "";
		if(type==1){
			str.append("{");
			str.append("title:{ text: '");
			//供应商进料不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商进料不良批次(率)排列图");
					
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商进料不良批次(率)排列图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商进料不良数(率)排列图");
					
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商进料不良数(率)排列图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 } },");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20, top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['不良批数','不良率','累计不良率'],");
				formatter ="批";
				str.append("selected: { '不良批数': true,'不良率': false,'累计不良率' : true} },");
			}else{
				str.append("data:['不良数','不良率','累计不良率'],");
				formatter ="个";
				str.append("selected: { '不良数': true,'不良率': false,'累计不良率' : true} },");
			}
			str.append(" tooltip : {trigger: 'axis',formatter:'{b}<br /> {a0}: {c0}("+formatter+") <br/>{a1}: {c1}%' },");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{ axisLabel: { show:true, interval: 0, rotate: 30, textStyle: { fontWeight :'lighter',fontSize: 8} }, type : 'category',");
			int columnNum = test.getColumnNum();
			int isOth = 0;//其他标志位
			if(columnNum>=supList.size() ){
				columnNum = supList.size();
			}else{
				isOth = 1;
				columnNum = columnNum+1;
			}
			
			String[] supliers = new String[columnNum];
			for(int i=0; i<columnNum; i++){
				if(isOth ==1){
					if(i==columnNum-1){
						supliers[i] ="其他";
						continue;
					}
				}
//				supliers[i] = supList.get(i).getSupplier();
				//供应商简称
				supliers[i] = supList.get(i).getSupplierBrief();
			}
			
			str.append(" data : "+JSONObject.toJSONString(supliers).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value', name : '不良率' ,splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append("不良批数',");
			}else{
				str.append("不良数',");
			}
			str.append("type:'bar',label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 15){
				str.append("barWidth:"+barWidth+",");
			}
			int[] datas = new int[columnNum];
			int lotSum =0;
			int tlotSum =0;
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							datas[i] = test.getDefectS()-lotSum;
							continue;
						}
					}
					lotSum += supList.get(i).getDefectLot();
					tlotSum += supList.get(i).getTotalLot();
					datas[i] = supList.get(i).getDefectLot();
				}
				
			}else{
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							datas[i] = test.getDefectS()-lotSum;
							continue;
						}
					}
					lotSum += supList.get(i).getDefectQty();
					tlotSum += supList.get(i).getTotalQty();
					datas[i] = supList.get(i).getDefectQty();
				}
			}
			
			str.append("data:"+JSONObject.toJSONString(datas)+"},");
			str.append("{name:'不良率',type:'line',yAxisIndex: 1, lineStyle:{normal:{width: 1 } },label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}, formatter: '{c}%'}},");
			double[] rate = new double[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							rate[i] = roundNumber(new MathUtil().divide(test.getDefectS()-lotSum,test.getTotalS()-tlotSum,4)*100);
							continue;
						}
					}
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
				}
			}else{
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							rate[i] = roundNumber(new MathUtil().divide(test.getDefectS()-lotSum,test.getTotalS()-tlotSum,4)*100);
							continue;
						}
					}
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
				}
			}
			str.append(" data:"+JSONObject.toJSONString(rate)+" },");
			str.append("{name:'累计不良率',type:'line',yAxisIndex: 1, lineStyle:{normal:{width: 1 } },label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}, formatter: '{c}%'}},");
			double[] totrate = new double[columnNum];
			int tolNum = 0;
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					tolNum += supList.get(i).getDefectLot();
					if(isOth ==1){
						if(i==columnNum-1){
							totrate[i] =  roundNumber(new MathUtil().divide(test.getDefectS(), test.getDefectS(),4)*100);
							continue;
						}
					}
					totrate[i] = roundNumber(new MathUtil().divide(tolNum, test.getDefectS(),4)*100);
				}
			}else{
				for(int i=0; i<columnNum; i++){
					tolNum += supList.get(i).getDefectQty();
					if(isOth ==1){
						if(i==columnNum-1){
							totrate[i] =  roundNumber(new MathUtil().divide(test.getDefectS(), test.getDefectS(),4)*100);
							continue;
						}
					}
					totrate[i] = roundNumber(new MathUtil().divide(tolNum, test.getDefectS(),4)*100);
				}
			}
			str.append(" data:"+JSONObject.toJSONString(totrate)+" }]");
			str.append("}");
		}
		/**
		 * 零部件排列图
		 */
		if(type==2){
			str.append("{");
			str.append("title:{ text: '");
			//零部件进料不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周零部件进料不良批次(率)排列图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件进料不良批次(率)排列图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周零部件进料不良数(率)排列图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件进料不良数(率)排列图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false},restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20,top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['不良批数','不良率','累计不良率'],");
				formatter ="批";
				str.append("selected: { '不良批数': true,'不良率': false,'累计不良率' : true} },");
			}else{
				str.append("data:['不良数','不良率','累计不良率'],");
				formatter ="个";
				str.append("selected: { '不良数': true,'不良率': false,'累计不良率' : true} },");
			}
			str.append(" tooltip : {trigger: 'axis',formatter:'{b}<br /> {a0}: {c0}("+formatter+") <br/>{a1}: {c1}%' },");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{ axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}},  type : 'category',");
			int columnNum = test.getColumnNum();
			int isOth = 0;//其他标志位
			if(columnNum>=supList.size()){
				columnNum = supList.size();
			}else{
				isOth = 1;
				columnNum = columnNum+1;
			}
			String[] supliers = new String[columnNum];
			for(int i=0; i<columnNum; i++){
				if(isOth ==1){
					if(i==columnNum-1){
						supliers[i] ="其他";
						continue;
					}
				}
				supliers[i] = supList.get(i).getPartName();
			}
			str.append(" data : "+JSONObject.toJSONString(supliers).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value',name : '不良率',splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append("不良批数',");
			}else{
				str.append("不良数',");
			}
			str.append("type:'bar',label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 15){
				str.append("barWidth:"+barWidth+",");
			}
			int lotSum =0;
			int tlotSum =0;
			int[] datas = new int[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							datas[i] = test.getDefectS()-lotSum;
							continue;
						}
					}
					lotSum += supList.get(i).getDefectLot();
					tlotSum += supList.get(i).getTotalLot();
					datas[i] = supList.get(i).getDefectLot();
				}
			}else{
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							datas[i] = test.getDefectS()-lotSum;
							continue;
						}
					}
					lotSum += supList.get(i).getDefectQty();
					tlotSum += supList.get(i).getTotalQty();
					datas[i] = supList.get(i).getDefectQty();
				}
			}
			
			str.append("data:"+JSONObject.toJSONString(datas)+"},");
			str.append("{name:'不良率',type:'line',yAxisIndex: 1,lineStyle:{normal:{width: 1 } },label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'},formatter: '{c}%'}},");
			double[] rate = new double[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							rate[i] = roundNumber(new MathUtil().divide(test.getDefectS()-lotSum,test.getTotalS()-tlotSum,4)*100);
							continue;
						}
					}
					rate[i] =roundNumber( new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
				}
			}else{
				for(int i=0; i<columnNum; i++){
					if(isOth ==1){
						if(i==columnNum-1){
							rate[i] = roundNumber(new MathUtil().divide(test.getDefectS()-lotSum,test.getTotalS()-tlotSum,4)*100);
							continue;
						}
					}
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
				}
			}
			str.append(" data:"+JSONObject.toJSONString(rate)+" },");
			str.append("{name:'累计不良率',type:'line',yAxisIndex: 1, lineStyle:{normal:{width: 1 } },label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}, formatter: '{c}%'}},");
			double[] totrate = new double[columnNum];
			int tolNum = 0;
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					tolNum += supList.get(i).getDefectLot();
					if(isOth ==1){
						if(i==columnNum-1){
							totrate[i] =  roundNumber(new MathUtil().divide(test.getDefectS(), test.getDefectS(),4)*100);
							continue;
						}
					}
					totrate[i] = roundNumber(new MathUtil().divide(tolNum, test.getDefectS(),4)*100);
				}
			}else{
				for(int i=0; i<columnNum; i++){
					tolNum += supList.get(i).getDefectQty();
					if(isOth ==1){
						if(i==columnNum-1){
							totrate[i] =  roundNumber(new MathUtil().divide(test.getDefectS(), test.getDefectS(),4)*100);
							continue;
						}
					}
					totrate[i] = roundNumber(new MathUtil().divide(tolNum, test.getDefectS(),4)*100);
				}
			}
			str.append(" data:"+JSONObject.toJSONString(totrate)+" }]");
			str.append("}");
		}
		/**
		 * 不良现象饼图
		 */
//		if(type==3){
//			str.append("{");
//			str.append("title:{ text: '");
//			//不良现象饼图
////			String title = "";
////			if(StringUtils.isNotBlank(test.getSupplier())){
////				title = test.getSupplier();
////			}
//			
//			
//			if( "0".equals(test.getAnalysisType())){
//				if(test.getDateType().equals("周")){
//					Long week = test.getWeek();
//					str.append("第"+week+"周进料不良现象批次饼图");
//				}else {
//					str.append(test.getDateT().replaceAll("-", "/")+"进料不良现象批次饼图");
//				}
//			}else {
//				if(test.getDateType().equals("周")){
//					Long week = test.getWeek();
//					str.append("第"+week+"进料不良现象数饼图");
//				}else {
//					str.append(test.getDateT().replaceAll("-", "/")+"进料不良现象数饼图");
//				}
//			}
//			str.append("',x:'center',textStyle:{ fontSize:15 }},");
//			str.append(" tooltip : {trigger: 'item',formatter: '{a} <br/>{b} : {c} ({d}%)'},");
//			str.append(" legend: { orient : 'vertical', x : 'left',  ");
//			if( "0".equals(test.getAnalysisType())){
//				  str.append(" data:['性能类不良批','尺寸类不良批','外观类不良批','其他类不良批'] }, ");
//			}else{
//				str.append(" data:['性能类不良数','尺寸类不良数','外观类不良数','其他类不良数'] }, ");
//			}
//			str.append(" toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},magicType : {show: true,  type: ['pie', 'funnel'], option: { funnel: { x: '25%', width: '50%',funnelAlign: 'left', max: 1548 } } }, restore : {show: true}, saveAsImage : {show: true} }},");
//			str.append("calculable : true,");
//			str.append("  series : [ {name:'");
//			if( "0".equals(test.getAnalysisType())){
//				str.append("不良现象',");
//			}else{
//				str.append("不良现象',");
//			}
//			str.append("type:'pie', radius : '55%', center: ['50%', '60%'],");
//			str.append("data:[ ");
//			if( "0".equals(test.getAnalysisType())){
//				if(supList.size()>0){
//				  str.append(" {value:"+supList.get(0).getPropertyLot()+", name:'性能类不良批'}, ");
//				  str.append(" {value:"+supList.get(0).getSizeLot()+", name:'尺寸类不良批'}, ");
//				  str.append(" {value:"+supList.get(0).getAspectLot()+", name:'外观类不良批'}, ");
//				  str.append(" {value:"+supList.get(0).getOtherLot()+", name:'其他类不良批'} ");
//				  }
//			}else{
//				if(supList.size()>0){
//				  str.append(" {value:"+supList.get(0).getPropertyQty()+", name:'性能类不良数'}, ");
//				  str.append(" {value:"+supList.get(0).getSizeQty()+", name:'尺寸类不良数'}, ");
//				  str.append(" {value:"+supList.get(0).getAspectQty()+", name:'外观类不良数'}, ");
//				  str.append(" {value:"+supList.get(0).getOtherQty()+", name:'其他类不良数'} ");
//				}
//			}
//			
//			str.append("] } ] ");
//		
//			str.append("}");
//		}
		/**
		 * 不良现象排列图
		 */
		if(type==3){
			str.append("{");
			str.append("title:{ text: '");

			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周进料不良现象不良批次数/率");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"进料不良现象不良批次数/率");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"进料不良现象不良数/率");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"进料不良现象不良数/率");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20,top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['不良批数','不良率']},");
				formatter = "批";
			}else{
				str.append("data:['不良数','不良率']},");
				formatter = "个";
			}
			str.append(" tooltip : {trigger: 'axis', formatter:'{b}<br /> {a0}: {c0}("+formatter+") <br/>{a1}: {c1}%' },");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}}, type : 'category',");
			str.append(" data : ['外观','尺寸','性能','其他']}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value',name : '不良率',splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append("不良批数',");
			}else{
				str.append("不良数',");
			}
			str.append("type:'bar',label: { normal: {show: true, position: 'inside'}},");
			str.append("barWidth:"+barWidth+",");
			int[] datas = new int[4];
			if( "0".equals(test.getAnalysisType())){
				if(supList.size()!=0){
					datas[0] = supList.get(0).getAspectLot();   //外观
					datas[1] = supList.get(0).getSizeLot();     //尺寸
					datas[2] = supList.get(0).getPropertyLot(); //性能
					datas[3] = supList.get(0).getOtherLot();    //其他
				}
			}else{
				if(supList.size()!=0){
				    datas[0] = supList.get(0).getAspectQty();   //外观
				    datas[1] = supList.get(0).getSizeQty();     //尺寸
					datas[2] = supList.get(0).getPropertyQty(); //性能
					datas[3] = supList.get(0).getOtherQty();    //其他
				}
			}
			
			str.append("data:"+JSONObject.toJSONString(datas)+"},");
			str.append("{name:'不良率',type:'line',yAxisIndex: 1,lineStyle:{normal:{width: 1 } }, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'},formatter: '{c}%'}},");
			double[] rate = new double[4];
			if(supList.size()!=0){
				if( "0".equals(test.getAnalysisType())){
					rate[0] = roundNumber(new MathUtil().divide(supList.get(0).getAspectLot(), supList.get(0).getDefectLot(),4)*100);
					rate[1] = roundNumber(new MathUtil().divide(supList.get(0).getSizeLot(), supList.get(0).getDefectLot(),4)*100);
					rate[2] = roundNumber(new MathUtil().divide(supList.get(0).getPropertyLot(), supList.get(0).getDefectLot(),4)*100);
					rate[3] = roundNumber(new MathUtil().divide(supList.get(0).getOtherLot(), supList.get(0).getDefectLot(),4)*100);
			}else{
				rate[0] = roundNumber(new MathUtil().divide(supList.get(0).getAspectQty(), supList.get(0).getTotalQty(),4)*100);
				rate[1] = roundNumber(new MathUtil().divide(supList.get(0).getSizeQty(), supList.get(0).getTotalQty(),4)*100);
				rate[2] = roundNumber(new MathUtil().divide(supList.get(0).getPropertyQty(), supList.get(0).getTotalQty(),4)*100);
				rate[3] = roundNumber(new MathUtil().divide(supList.get(0).getOtherQty(), supList.get(0).getTotalQty(),4)*100);
			}
			}
			str.append(" data:"+JSONObject.toJSONString(rate)+" }]");
			str.append("}");
			
		}
        /**
         * 供应商趋势图
         */
		if(type==4){
			str.append("{");
			str.append("title:{ text: '");
			//供应商不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商不良批(率)趋势图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商不良批(率)趋势图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"供应商不良数(率)趋势图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商不良数(率)趋势图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20,top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['不良批数','不良率','上控制线','下控制线'],");
				formatter = "批";
				str.append("selected: { '不良批数': true,'不良率': true,'上控制线' : false,'下控制线' : false} },");
			}else{
				str.append("data:['不良数','不良率','上控制线','下控制线'],");
				formatter = "个";
				str.append("selected: { '不良数': true,'不良率': true,'上控制线' : false,'下控制线' : false} },");
			}
			str.append(" tooltip : {trigger: 'axis' ,formatter:'{b}<br /> {a0}: {c0}("+formatter+") <br/>{a1}: {c1}% <br/>{a2}: {c2}% <br/>{a3}: {c3}%' },");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}}, type : 'category',");
			int columnNum = test.getColumnNum();
			if(columnNum>supList.size()){
				columnNum = supList.size();
			}
			String[] dates = new String[columnNum];
			for(int i=0; i<columnNum; i++){
				dates[i] = supList.get(i).getDateT()+(test.getDateType().equals("周")==true ? "周" :"" );
			}
			str.append(" data : "+JSONObject.toJSONString(dates).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value',name : '不良率',splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append("不良批数',");
			}else{
				str.append("不良数',");
			}
			str.append("type:'bar', label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 15){
				str.append("barWidth:"+barWidth+",");
			}
			int[] datas = new int[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectLot();
				}
			}else{
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectQty();
				}
			}
			
			str.append("data:"+JSONObject.toJSONString(datas)+"},");
			str.append("{name:'不良率',type:'line', yAxisIndex: 1,lineStyle:{normal:{width: 1 } }, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'},formatter: '{c}%'}},");
			double[] rate = new double[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
				}
			}else{
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
				}
			}
			str.append(" data:"+JSONObject.toJSONString(rate)+" },");
			str.append("{name:'上控制线',type:'line',step: 'middle',lineStyle:{normal:{width: 1 } },yAxisIndex: 1,");
			double[] uprate = new double[columnNum];
			for(int i=0; i<columnNum; i++){
				uprate[i] = roundNumber(getUpRate(test,supList.get(i))*100);
			}
			str.append(" data:"+JSONObject.toJSONString(uprate)+" },");
			str.append("{name:'下控制线',type:'line',step: 'middle',lineStyle:{normal:{width: 1 } },yAxisIndex: 1,");
			double[] downrate = new double[columnNum];
			for(int i=0; i<columnNum; i++){
				downrate[i] = roundNumber(getDownRate(test,supList.get(i))*100);
			}
			str.append(" data:"+JSONObject.toJSONString(downrate)+" }]");
			
			str.append("}");
		}
		/**
		 * 零部件趋势图
		 */
		if(type==5){
			str.append("{");
			str.append("title:{ text: '");
			//供应商不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"零部件不良批(率)趋势图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件不良批(率)趋势图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"零部件不良数(率)趋势图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件不良数(率)趋势图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20,top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['不良批数','不良率','上控制线','下控制线'],");
				formatter = "批";
				str.append("selected: { '不良批数': true,'不良率': true,'上控制线' : false,'下控制线' : false} },");
			}else{
				str.append("data:['不良数','不良率','上控制线','下控制线'],");
				formatter ="个";
				str.append("selected: { '不良数': true,'不良率': true,'上控制线' : false,'下控制线' : false} },");
			}
			str.append(" tooltip : {trigger: 'axis',formatter:'{b}<br /> {a0}: {c0}("+formatter+") <br/>{a1}: {c1}% <br/> {a2}: {c2}% <br/> {a3}: {c3}%'},");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}}, type : 'category',");
			int columnNum = test.getColumnNum();
			if(columnNum>supList.size()){
				columnNum = supList.size();
			}
			String[] dates = new String[columnNum];
			for(int i=0; i<columnNum; i++){
				dates[i] = supList.get(i).getDateT()+(test.getDateType().equals("周")==true ? "周" :"" );
			}
			str.append(" data : "+JSONObject.toJSONString(dates).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value',name : '不良率',splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append("不良批数',");
			}else{
				str.append("不良数',");
			}
			str.append("type:'bar', label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 15){
				str.append("barWidth:"+barWidth+",");
			}
			int[] datas = new int[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectLot();
				}
			}else{
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectQty();
				}
			}
			
			str.append("data:"+JSONObject.toJSONString(datas)+"},");
			str.append("{name:'不良率',type:'line',yAxisIndex: 1,lineStyle:{normal:{width: 1 } }, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'},formatter: '{c}%'}},");
			double[] rate = new double[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
				}
			}else{
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
				}
			}
			str.append(" data:"+JSONObject.toJSONString(rate)+" },");
			str.append("{name:'上控制线',type:'line',step: 'middle',lineStyle:{normal:{width: 1 } },yAxisIndex: 1,");
			double[] uprate = new double[columnNum];
			for(int i=0; i<columnNum; i++){
				uprate[i] = roundNumber(getUpRate(test,supList.get(i))*100);
			}
			str.append(" data:"+JSONObject.toJSONString(uprate)+" },");
			str.append("{name:'下控制线',type:'line',step: 'middle',lineStyle:{normal:{width: 1 } },yAxisIndex: 1,");
			double[] downrate = new double[columnNum];
			for(int i=0; i<columnNum; i++){
				downrate[i] = roundNumber(getDownRate(test,supList.get(i))*100);
			}
			str.append(" data:"+JSONObject.toJSONString(downrate)+" }]");
			str.append("}");
		}
		
		/**
		 * 供应商对比图
		 */
		/*if(type==6){
			String nDay = "";
			String yDay = "";
			if("天".equals(test.getDateType()) ){
				nDay = "今日";
				yDay = "昨日";
			}else if("周".equals(test.getDateType())){
				nDay = "本周";
				yDay = "上周";
			}else if("月".equals(test.getDateType())){
				nDay = "本月";
				yDay = "上月";
			}
			else if("年".equals(test.getDateType())){
				nDay = "今年";
				yDay = "去年";
			}
			str.append("{");
			str.append("title:{ text: '");
			//供应商不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商不良批(率)日期对比排列图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商不良批(率)日期对比排列图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商不良数(率)日期对比排列图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商不良数(率)日期对比排列图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
//			str.append("  tooltip : { trigger: 'axis', formatter: function(params) { var svalue ; if((params[0].dataIndex+1)%2===1){ if(params[1].value !=='-'){ svalue = params[1].seriesName + ' : ' + params[1].value ; }else{  svalue = params[1].seriesName + ' : ' + params[2].value ; }  } if((params[0].dataIndex+1)%2===0){ if(params[1].value !=='-'){ svalue = params[2].seriesName + ' : ' + params[1].value ; }else{  svalue = params[2].seriesName + ' : ' + params[2].value ;  } } return params[0].name +' : ' + params[0].value + '<br/>' + svalue ; }, },");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, magicType: {show: true, type: ['line', 'bar']},restore : {show: true}, saveAsImage : {show: true}}},");
			str.append("calculable : true,");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 1, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 2}}, type : 'category',");
			int columnNum = test.getColumnNum();
			List<TestInstance> realList = getRealList(supList);
			if(columnNum>realList.size()){
				columnNum = realList.size();
			}
			List<String> supName = new ArrayList<String>();
			for(int i=0; i<columnNum; i++){
				    supName.add(realList.get(i).getSupplier()) ;
				    supName.add(realList.get(i).getSupplier() );
			}
			
			str.append(" data : "+JSONObject.toJSONString(supName).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value',name : '不良率',splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append(nDay+"不良批',");
			}else{
				str.append(yDay+"不良数',");
			}
			str.append("type:'bar', label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 5){
				str.append("barWidth:"+barWidth+",");
			}
			int[] datas = new int[columnNum];
			int[] cdatas = new int[columnNum];
			List<Integer> codatas = new ArrayList<Integer>();
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectLot();
					if(comtains(supList.get(i),supList,0)){
						cdatas[i] = getComTest(supList.get(i),supList,0).getDefectLot();
					}else{
						cdatas[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectQty();
					if(comtains(supList.get(i),supList,0)){
						cdatas[i] = getComTest(supList.get(i),supList,0).getDefectQty();
					}else{
						cdatas[i] = 0;
					}
				}
			}
			for(int i=0; i<columnNum; i++){
				codatas.add(datas[i]);
				codatas.add(cdatas[i]);
			}
			str.append("data:"+JSONObject.toJSONString(codatas)+"},");
			str.append("{name:'"+nDay+"不良率',type:'line',yAxisIndex: 1, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}}},");
			double[] rate = new double[columnNum];
			double[] crate = new double[columnNum];
			List<String> corate1 = new ArrayList<String>();
			List<String> corate2 = new ArrayList<String>();
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
					if(comtains(supList.get(i),supList,0)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,0).getDefectLot(), getComTest(supList.get(i),supList,0).getTotalLot(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
					if(comtains(supList.get(i),supList,0)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,0).getDefectQty(), getComTest(supList.get(i),supList,0).getDefectQty(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}
			for(int i=0; i<columnNum; i++){
				if(i%2==0){
					corate1.add(String.valueOf(rate[i]));
					corate1.add(String.valueOf(crate[i]));
				}else{
					corate1.add("-");
					corate1.add("-");
				}
				if(i%2==1){
					corate2.add(String.valueOf(rate[i]));
					corate2.add(String.valueOf(crate[i]));
				}else{
					corate2.add("-");
					corate2.add("-");
				}
			}
			str.append(" data:"+JSONObject.toJSONString(corate1).replace("\"", "'")+" },");
			str.append(" { name:'"+yDay+"不良率', type:'line',  yAxisIndex: 1, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}}},");
			str.append(" data:"+JSONObject.toJSONString(corate2).replace("\"", "'")+" }");
			str.append("] }");
		}
		*/
		/**
		 * 零部件对比图
		 */
		/*
		if(type==7){
			String nDay = "";
			String yDay = "";
			if("天".equals(test.getDateType()) ){
				nDay = "今日";
				yDay = "昨日";
			}else if("周".equals(test.getDateType())){
				nDay = "本周";
				yDay = "上周";
			}else if("月".equals(test.getDateType())){
				nDay = "本月";
				yDay = "上月";
			}
			else if("年".equals(test.getDateType())){
				nDay = "今年";
				yDay = "去年";
			}
			str.append("{");
			str.append("title:{ text: '");
			//供应商不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周零部件不良批(率)日期对比排列图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件不良批(率)日期对比排列图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周零部件不良数(率)日期对比排列图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件不良数(率)日期对比排列图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
//			str.append("  tooltip : { trigger: 'axis', formatter: function(params) { var svalue ; if((params[0].dataIndex+1)%2===1){ if(params[1].value !=='-'){ svalue = params[1].seriesName + ' : ' + params[1].value ; }else{  svalue = params[1].seriesName + ' : ' + params[2].value ; }  } if((params[0].dataIndex+1)%2===0){ if(params[1].value !=='-'){ svalue = params[2].seriesName + ' : ' + params[1].value ; }else{  svalue = params[2].seriesName + ' : ' + params[2].value ;  } } return params[0].name +' : ' + params[0].value + '<br/>' + svalue ; }, },");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, magicType: {show: true, type: ['line', 'bar']},restore : {show: true}, saveAsImage : {show: true}}},");
			str.append("calculable : true,");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 1, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}}, type : 'category',");
			int columnNum = test.getColumnNum();
			List<TestInstance> realList = getRealList(supList);
			if(columnNum>realList.size()){
				columnNum = realList.size();
			}
			List<String> supName = new ArrayList<String>();
			for(int i=0; i<columnNum; i++){
				    supName.add(realList.get(i).getPartName()) ;
				    supName.add(realList.get(i).getPartName());
			}
			
			str.append(" data : "+JSONObject.toJSONString(supName).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } },{type : 'value',name : '不良率',splitLine:{ show:false},axisLabel : {formatter: '{value} %' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append(nDay+"不良批',");
			}else{
				str.append(yDay+"不良数',");
			}
			str.append("type:'bar', label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 5){
				str.append("barWidth:"+barWidth+",");
			}
			int[] datas = new int[columnNum];
			int[] cdatas = new int[columnNum];
			List<Integer> codatas = new ArrayList<Integer>();
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectLot();
					if(comtains(supList.get(i),supList,1)){
						cdatas[i] = getComTest(supList.get(i),supList,1).getDefectLot();
					}else{
						cdatas[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectQty();
					if(comtains(supList.get(i),supList,1)){
						cdatas[i] = getComTest(supList.get(i),supList,1).getDefectQty();
					}else{
						cdatas[i] = 0;
					}
				}
			}
			for(int i=0; i<columnNum; i++){
				codatas.add(datas[i]);
				codatas.add(cdatas[i]);
			}
			str.append("data:"+JSONObject.toJSONString(codatas)+"},");
			str.append("{name:'"+nDay+"不良率',type:'line',yAxisIndex: 1, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}}},");
			double[] rate = new double[columnNum];
			double[] crate = new double[columnNum];
			List<String> corate1 = new ArrayList<String>();
			List<String> corate2 = new ArrayList<String>();
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
					if(comtains(supList.get(i),supList,1)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,1).getDefectLot(), getComTest(supList.get(i),supList,1).getTotalLot(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
					if(comtains(supList.get(i),supList,1)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,1).getDefectQty(), getComTest(supList.get(i),supList,1).getDefectQty(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}
			for(int i=0; i<columnNum; i++){
				if(i%2==0){
					corate1.add(String.valueOf(rate[i]));
					corate1.add(String.valueOf(crate[i]));
				}else{
					corate1.add("-");
					corate1.add("-");
				}
				if(i%2==1){
					corate2.add(String.valueOf(rate[i]));
					corate2.add(String.valueOf(crate[i]));
				}else{
					corate2.add("-");
					corate2.add("-");
				}
			}
			str.append(" data:"+JSONObject.toJSONString(corate1).replace("\"", "'")+" },");
			str.append(" { name:'"+yDay+"不良率', type:'line',  yAxisIndex: 1, label: { normal: {show: true, position: 'top',textStyle: {color: '#000000'}}},");
			str.append(" data:"+JSONObject.toJSONString(corate2).replace("\"", "'")+" }");
			str.append("] }");
		}*/
		//供应商对比图
		if(type==6){
			String nDay = "";
			String yDay = "";
			if("天".equals(test.getDateType()) ){
				nDay = "今日";
				yDay = "昨日";
			}else if("周".equals(test.getDateType())){
				nDay = "本周";
				yDay = "上周";
			}else if("月".equals(test.getDateType())){
				nDay = "本月";
				yDay = "上月";
			}
			else if("年".equals(test.getDateType())){
				nDay = "今年";
				yDay = "去年";
			}
			str.append("{");
			str.append("title:{ text: '");
			//供应商不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商不良批(率)日期对比图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商不良批(率)日期对比图");
				}
				formatter = "批";
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商不良数(率)日期对比图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商不良数(率)日期对比图");
				}
				formatter ="个";
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
			str.append("  tooltip : { trigger: 'axis'},");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false},restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20,top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['"+yDay+"','"+nDay+"']},");
			}else{
				str.append("data:['"+yDay+"','"+nDay+"']},");
			}
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 2}}, type : 'category',");
			int columnNum = test.getColumnNum();
			List<TestInstance> realList = getRealList(supList);
			if(columnNum>realList.size()){
				columnNum = realList.size();
			}
			List<String> supName = new ArrayList<String>();
			for(int i=0; i<columnNum; i++){
//				    supName.add(realList.get(i).getSupplier() );
				//供应商简称
				supName.add(realList.get(i).getSupplierBrief() );
			}
			
			str.append(" data : "+JSONObject.toJSONString(supName).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } }],");
			int[] datas = new int[columnNum];
			int[] cdatas = new int[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectLot();
					if(comtains(supList.get(i),supList,0)){
						cdatas[i] = getComTest(supList.get(i),supList,0).getDefectLot();
					}else{
						cdatas[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectQty();
					if(comtains(supList.get(i),supList,0)){
						cdatas[i] = getComTest(supList.get(i),supList,0).getDefectQty();
					}else{
						cdatas[i] = 0;
					}
				}
			}
			double[] rate = new double[columnNum];
			double[] crate = new double[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
					if(comtains(supList.get(i),supList,0)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,0).getDefectLot(), getComTest(supList.get(i),supList,0).getTotalLot(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
					if(comtains(supList.get(i),supList,0)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,0).getDefectQty(), getComTest(supList.get(i),supList,0).getDefectQty(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}
			
			str.append("  series : [ ");
			str.append(" { name:'"+yDay+"',type:'bar',label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 10){
				str.append("barWidth:"+barWidth+",");
			}
			str.append("data:"+JSONObject.toJSONString(cdatas)+",");
			str.append("markPoint : {");
			str.append(" itemStyle: { normal: {color: 'rgba(10%,20%,30%,0.0)',borderColor: 'rgba(10%,20%,30%,0.0)' } },label: {normal: {formatter: '{c}%',textStyle: {color: '#000', fontSize: 12,  }  } },");
		    List<MarkPoint> pointData = new ArrayList<MarkPoint>();
			for(int i=0; i<columnNum; i++){
				MarkPoint mark = new MarkPoint();
				mark.setName("不良率");
				mark.setValue(crate[i]);
				mark.setXAxis(i);
				mark.setYAxis(new Double(cdatas[i]));
				pointData.add(mark);
			}
			str.append("data:"+JSONObject.toJSONString(pointData)+"");  
			str.append(" } },");
			str.append(" { name:'"+nDay+"',type:'bar',label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 10){
				str.append("barWidth:"+barWidth+",");
			}
			str.append("data:"+JSONObject.toJSONString(datas)+",");    
			str.append("markPoint : { ");
			str.append(" itemStyle: { normal: {color: 'rgba(10%,20%,30%,0.0)',borderColor: 'rgba(10%,20%,30%,0.0)' } },label: { normal: {formatter: '{c}%',textStyle: {color: '#000', fontSize: 12,  }  } },");
		    List<MarkPoint> cpointData = new ArrayList<MarkPoint>();
			for(int i=0; i<columnNum; i++){
				MarkPoint mark = new MarkPoint();
				mark.setName("不良率");
				mark.setValue(rate[i]);
				mark.setXAxis(i);
				mark.setYAxis(new Double(datas[i]));
				cpointData.add(mark);
			}
			str.append("data:"+JSONObject.toJSONString(cpointData)+"");  
			str.append(" } }");    
			str.append("] }");
		}
		
		/**
		 * 零部件对比图
		 */
		if(type==7){
			String nDay = "";
			String yDay = "";
			if("天".equals(test.getDateType()) ){
				nDay = "今日";
				yDay = "昨日";
			}else if("周".equals(test.getDateType())){
				nDay = "本周";
				yDay = "上周";
			}else if("月".equals(test.getDateType())){
				nDay = "本月";
				yDay = "上月";
			}
			else if("年".equals(test.getDateType())){
				nDay = "今年";
				yDay = "去年";
			}
			str.append("{");
			str.append("title:{ text: '");
			//供应商不良批次
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周零部件不良批(率)日期对比图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件不良批(率)日期对比图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周零部件不良数(率)日期对比图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"零部件不良数(率)日期对比图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 }},");
			str.append("  tooltip : { trigger: 'axis' },");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false}, restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { align: 'left',left: 20,top: '20',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['"+yDay+"','"+nDay+"']},");
				formatter = "批";
			}else{
				str.append("data:['"+yDay+"','"+nDay+"']},");
				formatter ="个";
			}
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',top: '25%',  containLabel: true },");
			str.append("xAxis : [{axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}}, type : 'category',");
			int columnNum = test.getColumnNum();
			List<TestInstance> realList = getRealList(supList);
			if(columnNum>realList.size()){
				columnNum = realList.size();
			}
			List<String> supName = new ArrayList<String>();
			for(int i=0; i<columnNum; i++){
				    supName.add(realList.get(i).getPartName()) ;
			}
			
			str.append(" data : "+JSONObject.toJSONString(supName).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } }],");
			
			int[] datas = new int[columnNum];
			int[] cdatas = new int[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectLot();
					if(comtains(supList.get(i),supList,1)){
						cdatas[i] = getComTest(supList.get(i),supList,1).getDefectLot();
					}else{
						cdatas[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					datas[i] = supList.get(i).getDefectQty();
					if(comtains(supList.get(i),supList,1)){
						cdatas[i] = getComTest(supList.get(i),supList,1).getDefectQty();
					}else{
						cdatas[i] = 0;
					}
				}
			}
			double[] rate = new double[columnNum];
			double[] crate = new double[columnNum];
			if( "0".equals(test.getAnalysisType())){
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectLot(), supList.get(i).getTotalLot(),4)*100);
					if(comtains(supList.get(i),supList,1)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,1).getDefectLot(), getComTest(supList.get(i),supList,1).getTotalLot(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}else{
				for(int i=0; i<columnNum; i++){
					rate[i] = roundNumber(new MathUtil().divide(supList.get(i).getDefectQty(), supList.get(i).getTotalQty(),4)*100);
					if(comtains(supList.get(i),supList,1)){
						crate[i] = roundNumber(new MathUtil().divide(getComTest(supList.get(i),supList,1).getDefectQty(), getComTest(supList.get(i),supList,1).getDefectQty(),4)*100);
					}else{
						crate[i] = 0;
					}
				}
			}
			str.append("  series : [ ");
			str.append(" { name:'"+yDay+"',type:'bar',label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 10){
				str.append("barWidth:"+barWidth+",");
			}
			str.append("data:"+JSONObject.toJSONString(cdatas)+",");
			str.append("markPoint : {");
			str.append(" itemStyle: { normal: {color: 'rgba(10%,20%,30%,0.0)',borderColor: 'rgba(10%,20%,30%,0.0)' } },label: {normal: {formatter: '{c}%',textStyle: {color: '#000', fontSize: 12,  }  } },");
			List<MarkPoint> pointData = new ArrayList<MarkPoint>();
			for(int i=0; i<columnNum; i++){
				MarkPoint mark = new MarkPoint();
				mark.setName("不良率");
				mark.setValue(crate[i]);
				mark.setXAxis(i);
				mark.setYAxis(new Double(cdatas[i]));
				pointData.add(mark);
			}
		    str.append("data:"+JSONObject.toJSONString(pointData)+"");  
			str.append(" } },");
			str.append(" { name:'"+nDay+"',type:'bar',label: { normal: {show: true, position: 'inside'}},");
			if(columnNum <= 10){
				str.append("barWidth:"+barWidth+",");
			}
			str.append("data:"+JSONObject.toJSONString(datas)+",");    
			str.append("markPoint : {");
			str.append(" itemStyle: { normal: {color: 'rgba(10%,20%,30%,0.0)',borderColor: 'rgba(10%,20%,30%,0.0)' } },label: {normal: {formatter: '{c}%',textStyle: {color: '#000', fontSize: 12,  }  } },");
		    List<MarkPoint> cpointData = new ArrayList<MarkPoint>();
			for(int i=0; i<columnNum; i++){
				MarkPoint mark = new MarkPoint();
				mark.setName("不良率");
				mark.setValue(rate[i]);
				mark.setXAxis(i);
				mark.setYAxis(new Double(datas[i]));
				cpointData.add(mark);
			}
			str.append("data:"+JSONObject.toJSONString(cpointData)+"");  
			str.append(" } }");    
		
			str.append("] }");
		}
		//原料退料排列图
		if(type==8){
			str.append("{");
			str.append("title:{ text: '");
			//
			if( "0".equals(test.getAnalysisType())){
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商原料退料批次柱状图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商原料退料批次柱状图");
				}
			}else {
				if(test.getDateType().equals("周")){
					Long week = test.getWeek();
					str.append("第"+week+"周供应商原料退料不良数柱状图");
				}else {
					str.append(test.getDateT().replaceAll("-", "/")+"供应商原料退料不良数柱状图");
				}
			}
			str.append("',x:'center',textStyle:{ fontSize:15 } },");
			str.append(" toolbox: {show : true, feature : { mark : {show: true},dataView : {show: true, readOnly: false},restore : {show: true}}},");
			str.append("calculable : true,");
			str.append("legend: { bottom:'3',");
			if( "0".equals(test.getAnalysisType())){
				str.append("data:['不良批数']},");
				formatter ="批";
			}else{
				str.append("data:['不良数']},");
				formatter ="个";
			}
		
			str.append(" tooltip : {trigger: 'axis',formatter:'{b}<br /> {a0}: {c0}("+formatter+") ' },");
			str.append("grid: { left: '6%', right: '3%', bottom: '30%',  containLabel: true },");
			str.append("xAxis : [{ axisLabel: { show:true, interval: 0, rotate: 30, textStyle: { fontWeight :'lighter',fontSize: 8} }, type : 'category',");
			int columnNum = test.getColumnNum();
			if(columnNum>supList.size()){
				columnNum = supList.size();
			}
			
			String[] supliers = new String[columnNum];
			for(int i=0; i<columnNum; i++){
				supliers[i] = supList.get(i).getSupplier();
			}
			str.append(" data : "+JSONObject.toJSONString(supliers).replace("\"", "'")+"}],");
			str.append(" yAxis : [ {type : 'value',splitLine:{ show:false}, name : '数量' ,minInterval: 1,axisLabel : { formatter: '{value} 次' } }],");
			str.append("  series : [ {name:'");
			if( "0".equals(test.getAnalysisType())){
				str.append("不良批数',");
			}else{
				str.append("不良数',");
			}
			str.append("type:'bar', label: { normal: {show: true, position: 'inside'}},");
			
			if(columnNum <= 15){
				str.append("barWidth:"+barWidth+",");
			}
			int[] datas = new int[columnNum];
			for(int i=0; i<columnNum; i++){
				datas[i] = supList.get(i).getDefectLot();
			}
			
			str.append("data:"+JSONObject.toJSONString(datas.length==0 ? "0" : datas)+"}]");
		
			str.append("}");
		}
		
		return str.toString();
	}
	
	

	/**
	 * 获取下控制线
	 * @param test
	 * @return
	 */
    private static Double getDownRate(TestInstance test,TestInstance down) {
    	if(down.getTotalLot() ==0){
			return 0.0;
		}
    	double avg =  new Double(test.getDefectS())/ new Double(test.getTotalS());
		double rate =(3*Math.sqrt(avg*(1-avg)))/Math.sqrt(down.getTotalLot());
		double downrate = (avg-rate)<0 ? 0 : (avg-rate);
		return downrate;
	}

	/**
     * 获取上控制线
     * @param test
     * @return
     */
	private static Double getUpRate(TestInstance test,TestInstance up) {
		if(up.getTotalLot() ==0){
			return 0.0;
		}
		double avg =  new Double(test.getDefectS())/ new Double(test.getTotalS());
		double rate =(3*Math.sqrt(avg*(1-avg)))/Math.sqrt(up.getTotalLot());
		double uprate = avg+rate;
		return uprate;
	}

	private static List<TestInstance> getRealList(List<TestInstance> supList) {
		String date = null;
		List<TestInstance> list = new ArrayList<TestInstance>();
		if(supList.size() > 0){
			date = supList.get(0).getDateT();
		}
		for(TestInstance test : supList){
			if(test.getDateT().equals(date)){
				list.add(test);
			}
		}
		return list;
	}

	private static TestInstance getComTest(TestInstance test,
			List<TestInstance> supList,int code) {
		//供应商
		if(code == 0){
			for(TestInstance t : supList){
//				if(t.getSupplier().equals(test.getSupplier()) && !t.getDateT().equals(test.getDateT())){
//					return t;
//				}
				if(t.getSupplierBrief().equals(test.getSupplierBrief()) && !t.getDateT().equals(test.getDateT())){
					return t;
				}
			}
		}
		//零部件
		if(code == 1){
			for(TestInstance t : supList){
				if(t.getPartName().equals(test.getPartName()) && !t.getDateT().equals(test.getDateT())){
					return t;
				}
			}
		}
		
		return null;
	}

	private static boolean comtains(TestInstance test,
			List<TestInstance> supList,int code) {
		//供应商
		if(code==0){
			for(TestInstance t : supList){
//				if(t.getSupplier().equals(test.getSupplier()) && !t.getDateT().equals(test.getDateT())){
//					return true;
//				}
				if(t.getSupplierBrief().equals(test.getSupplierBrief()) && !t.getDateT().equals(test.getDateT())){
					return true;
				}
			}
		}
		//零部件
		if(code==1){
			for(TestInstance t : supList){
				if(t.getPartName().equals(test.getPartName()) && !t.getDateT().equals(test.getDateT())){
					return true;
				}
			}
		}	
		return false;
	}

	private static Double roundNumber(Double num){
		if(num.isNaN()){
			num = 0.0;
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		String db = df.format(num);
		return Double.valueOf(db);
	}
	
	public static void main(String args[]){
		TestInstance test = new TestInstance();
		test.setType(7);
		test.setAnalysisType("0");
		test.setColumnNum(5);
		test.setDateT("2016-06-16");
		List<TestInstance> list = new ArrayList<TestInstance>();
		TestInstance t1 = new TestInstance();
		TestInstance t2 = new TestInstance();
		TestInstance t3 = new TestInstance();
		t1.setSupplier("aa");
		t1.setPartName("aaa");
		t1.setDefectLot(20);
		t1.setTotalLot(100);
	    t1.setDefectQty(50);
	    t1.setTotalQty(1000);
	    t1.setAspectLot(10);
	    t1.setSizeLot(2);
	    t1.setPropertyLot(5);
	    t1.setOtherLot(12);
	    t1.setAspectQty(1200);
	    t1.setSizeQty(800);
	    t1.setPropertyQty(500);
	    t1.setOtherQty(2000);
	    t1.setDateT("2016-06-13");
	    
	    t2.setSupplier("bb");
	    t2.setPartName("bbb");
		t2.setDefectLot(30);
		t2.setTotalLot(130);
	    t2.setDefectQty(20);
	    t2.setTotalQty(1100);
	    t2.setAspectLot(12);
	    t2.setSizeLot(22);
	    t2.setPropertyLot(15);
	    t2.setOtherLot(12);
	    t2.setAspectQty(1100);
	    t2.setSizeQty(1800);
	    t2.setPropertyQty(600);
	    t2.setOtherQty(2100);
	    t2.setDateT("2016-06-14");
	    
	    t3.setSupplier("cc");
	    t3.setPartName("ccc");
		t3.setDefectLot(10);
		t3.setTotalLot(120);
	    t3.setDefectQty(30);
	    t3.setTotalQty(1200);
	    list.add(t1);
	    list.add(t2);
	    list.add(t3);
	    t3.setAspectLot(10);
	    t3.setSizeLot(2);
	    t3.setPropertyLot(5);
	    t3.setOtherLot(12);
	    t3.setAspectQty(1200);
	    t3.setSizeQty(800);
	    t3.setPropertyQty(500);
	    t3.setOtherQty(2000);
	    t3.setDateT("2016-06-15");
		Object o = getData(test, list);
		System.out.println(o.toString());
	}
	
	
}
