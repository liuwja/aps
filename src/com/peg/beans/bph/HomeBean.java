package com.peg.beans.bph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.peg.model.bph.CountPerformanceMonth;

/**
 * 首页工具类
 * @author Administrator
 *
 */
public class HomeBean {
	private static final String YouYanJiBoxDefect ="油烟机开箱不良";
	private static final String XiaoDuGuiBoxDefect ="消毒柜开箱不良";
	private static final String WeiZhengKaoBoxDefect ="微蒸烤开箱不良";
	private static final String ZaoJuBoxDefect ="灶具开箱不良";
	private static final String ReShuiQiBoxDefect ="热水器开箱不良";
	
	private static final String XiaoDuGuiOqcDefect ="消毒柜OQC不良";	
	private static final String YouYanJiOqcDefect ="油烟机OQC不良";
	private static final String WeiZhengKaoOqcDefect ="微蒸烤OQC不良";
	private static final String ZaoJuBoxOqcDefect ="灶具OQC不良";
	private static final String ReShuiQiOqcDefect ="热水器OQC不良";


	private static final String AssembleQualityRate ="组装一次合格率";
	private static final String ZaoJuAssembleQualityRate ="灶具组装一次合格率";
	private static final String ReShuiQiAssembleQualityRate ="热水器组装一次合格率";
	private static final String PaintingQualityRate ="喷涂一次合格率";
	private static final String StepQualityRate ="冲压一次合格率";
	private static final String FashioningQualityRate ="精加工一次合格率";

	/**
	 * 生产首页年度指标报表
	 * @param indexList
	 * @return
	 */
	public String getHtmlText(List<CountPerformanceMonth> indexList){
		List<CountPerformanceMonth> dindexList = new ArrayList<CountPerformanceMonth>();
		List<CountPerformanceMonth> rindexList = new ArrayList<CountPerformanceMonth>();
		List<CountPerformanceMonth> deindexList = new ArrayList<CountPerformanceMonth>();
		for(CountPerformanceMonth month : indexList){
			if(month.getFactory().equals("电器一厂")){
				dindexList.add(month);
			}else if(month.getFactory().equals("燃气工厂")){
				rindexList.add(month);
			}else if(month.getFactory().equals("电器二厂")){
				deindexList.add(month);
			}
		}
/************************************/		
		StringBuffer text = new StringBuffer();
		text.append("<tbody><tr>") ;
		text.append("<td rowspan='2' style='border-right: 2px solid #E5E5E5;'>市场开箱不良</td>") ;
		text.append("<td>" +"油烟机" +"</td>") ;
		String text1 = "";
		for(CountPerformanceMonth vo : dindexList){			
			if(vo.getCheckIndexName().equals(YouYanJiBoxDefect)){
				text1 = text1 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text1 = text1 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text1 = text1 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}	
		}
		if(text1.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text1) ;
		}
		
/************************************/		
		text.append("<td>" +"灶具" +"</td>") ;
		String text3 = "";
		for(CountPerformanceMonth vo : rindexList){
			
			if(vo.getCheckIndexName().equals(ZaoJuBoxDefect)){
				text3 = text3 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text3 = text3 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text3 = text3 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}
						
		}	
		if(text3.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>") ;
		}else{
			text.append(text3) ;
		}
		text.append("<td>" +"微蒸烤" +"</td>");
		String text2 = "";
		for(CountPerformanceMonth vo : deindexList){
		
			if(vo.getCheckIndexName().equals(WeiZhengKaoBoxDefect)){
				text2 = text2 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text2 = text2 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text2 = text2 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}
		
		}
		if(text2.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text2) ;
		}
		
		text.append("</tr>") ;
/************************************/			
//		System.out.println(text);
		text.append("<tr>")  ;
		
		text.append("<td>" +"消毒柜" +"</td>");
		String text4 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(XiaoDuGuiBoxDefect)){
				text4 = text4 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text4 = text4 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text4 = text4 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}			
		}
		if(text4.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text4) ;
		}
/************************************/	
		text.append("<td>" +"热水器" +"</td>");
		String text5 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(ReShuiQiBoxDefect)){
				text5 = text5 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text5 = text5 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text5 = text5 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}			
		}
		if(text5.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text5) ;
		}
/************************************/			
		text.append("<td>" +"-" +"</td>");
		text.append("<td></td><td></td><td style='border-right: 2px solid #E5E5E5;'></td>");
				
		text.append("</tr>");
		
/************************************/			
		text.append("<td rowspan='2' style='border-right: 2px solid #E5E5E5;'>" +"OQC有责" +"</td>");
		text.append("<td>" +"油烟机" +"</td>");
		String text6 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(YouYanJiOqcDefect)){
				text6 = text6 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text6 = text6 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text6 = text6 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}			
		}
		if(text6.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text6) ;
		}
		
/************************************/		
		
		text.append("<td>" +"灶具" +"</td>");
		String text8 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(ZaoJuBoxOqcDefect)){
				text8 = text8 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text8 = text8 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text8 = text8 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}			
		}	
		if(text8.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text8);
		}
/************************************/			
		text.append("<td>" +"微蒸烤" +"</td>");
		String text7 = "";
		for(CountPerformanceMonth vo : deindexList){
			if(vo.getCheckIndexName().equals(WeiZhengKaoOqcDefect)){
				text7 = text7 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text7 = text7 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text7 = text7 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text7.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text7);
		}
		text.append("</tr>") ;
/************************************/			
		text.append("<tr>") ;
		
		text.append("<td>" +"消毒柜" +"</td>");
		String text9 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(XiaoDuGuiOqcDefect)){
				text9 = text9 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text9 = text9 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text9 = text9 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}			
		}
		if(text9.equals("")){
			text.append("<td >-</td><td >-</td>-<td style='border-right: 2px solid #E5E5E5;'></td>");
		}else{
			text.append(text9) ;
		}

/************************************/			
		text.append("<td>" +"热水器" +"</td>");
		String text10 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(ReShuiQiOqcDefect)){
				text10 = text10 + "<td>" +returnNum(vo.getTargetValue()) +"</td>";
				text10 = text10 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text10 = text10 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text10.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text10);
		}
/************************************/			
		text.append("<td>" +"-" +"</td>");
		text.append("<td></td><td></td><td style='border-right: 2px solid #E5E5E5;'></td>");
		text.append("</tr>");
/************************************/			
		text.append("<tr>");
		
		text.append("<td rowspan='2' style='border-right: 2px solid #E5E5E5;'>" +"组裝一次合格率(%)" +"</td>");
		text.append("<td rowspan='2'>" +"-" +"</td>");
		String text11 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(AssembleQualityRate)){
				text11 = text11 + "<td rowspan='2'>" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text11 = text11 + "<td rowspan='2'>" +returnNumToPercent(new BigDecimal(1).subtract(returnZero(vo.getActualValue()))) +"</td>";
				text11 = text11 + "<td rowspan='2' style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(new BigDecimal(1).subtract(returnZero(vo.getTotalValue()))) +"</td>";
			}		
		}
		if(text11.equals("")){
			text.append("<td rowspan='2'>-</td><td rowspan='2'>-</td><td rowspan='2' style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text11) ;
		}
		
/************************************/		
		
		text.append("<td>" +"灶具" +"</td>");
		String text13 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(ZaoJuAssembleQualityRate)){
				text13 = text13 + "<td>" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text13 = text13 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text13 = text13 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text13.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td >");
		}else{
			text.append(text13);
		}

		/************************************/		
        text.append("<td rowspan='2'>" +"-" +"</td>");
		
		String text12 = "";
		for(CountPerformanceMonth vo : deindexList){
			if(vo.getCheckIndexName().equals(AssembleQualityRate)){
				text12 = text12 + "<td rowspan='2'>" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text12 = text12 + "<td rowspan='2'>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text12 = text12 + "<td rowspan='2' style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text12.equals("")){
			text.append("<td rowspan='2'></td><td rowspan='2'></td><td rowspan='2' style='border-right: 2px solid #E5E5E5;'></td>");
		}else{
			text.append(text12);
		}
		
		text.append("<tr><td>" +"热水器" +"</td>");
		String text14 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(ReShuiQiAssembleQualityRate)){
				text14 = text14 + "<td>" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text14 = text14 + "<td>" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text14 = text14 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td></tr>";
			}		
		}
		if(text14.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text14) ;
		}

		text.append("</tr>");
/************************************/		
		text.append("<tr>");
		text.append("<td style='border-right: 2px solid #E5E5E5;'>" +"喷涂一次合格率(%)" +"</td>");
		text.append("<td >" +"-" +"</td>");
		String text15 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(PaintingQualityRate)){
				text15 = text15 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text15 = text15 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text15 = text15 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text15.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text15);
		}
		
/************************************/		
		text.append("<td >" +"-" +"</td>");
		String text17 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(PaintingQualityRate)){
				text17 = text17 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text17 = text17 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text17 = text17 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text17.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text17);
		}
		
/************************************/			
		text.append("<td >" +"-" +"</td>");
		String text16 = "";
		for(CountPerformanceMonth vo : deindexList){
			if(vo.getCheckIndexName().equals(PaintingQualityRate)){
				text16 = text16 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text16 = text16 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text16 = text16 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text16.equals("")){
			text .append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text16);
		}
		
		text.append("</tr>");
/************************************/		
		text.append("<tr>");
		text.append("<td style='border-right: 2px solid #E5E5E5;'>" +"精加工一次合格率(%)" +"</td>");
		text.append("<td >" +"-" +"</td>");
		String text18 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(FashioningQualityRate)){
				text18 = text18 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text18 = text18 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text18 = text18 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text18.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text18);
		}
/************************************/		
		
		
		text.append("<td >" +"-" +"</td>");
		String text20 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(FashioningQualityRate)){
				text20 = text20 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text20 = text20 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text20 = text20 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text20.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text20);
		}
/************************************/		
		text.append("<td >" +"-" +"</td>");
		String text19 = "";
		for(CountPerformanceMonth vo : deindexList){
			if(vo.getCheckIndexName().equals(FashioningQualityRate)){
				text19 = text19 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text19 = text19 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text19 = text19 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text19.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text19);
		}
		
		text.append("</tr>");
/************************************/		
		text.append("<tr>");
		text.append("<td style='border-right: 2px solid #E5E5E5;'>" +"冲压一次合格率(%)" +"</td>");
		text.append("<td >" +"-" +"</td>");
		String text21 = "";
		for(CountPerformanceMonth vo : dindexList){
			if(vo.getCheckIndexName().equals(StepQualityRate)){
				text21 = text21 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text21 = text21 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text21 = text21 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text21.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text21) ;
		}
/************************************/		
		text.append("<td >" +"-" +"</td>");
		String text23 = "";
		for(CountPerformanceMonth vo : rindexList){
			if(vo.getCheckIndexName().equals(StepQualityRate)){
				text23 = text23 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text23 = text23 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text23 = text23 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text23.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text23);
		}
/************************************/			
		text.append("<td >" +"-" +"</td>");
		String text22 = "";
		for(CountPerformanceMonth vo : deindexList){
			if(vo.getCheckIndexName().equals(StepQualityRate)){
				text22 = text22 + "<td >" +returnNumToPercent(vo.getTargetValue()) +"</td>";
				text22 = text22 + "<td >" +returnNumToPercent(vo.getActualValue()) +"</td>";
				text22 = text22 + "<td style='border-right: 2px solid #E5E5E5;'>" +returnNumToPercent(vo.getTotalValue()) +"</td>";
			}		
		}
		if(text22.equals("")){
			text.append("<td >-</td><td >-</td><td style='border-right: 2px solid #E5E5E5;'>-</td>");
		}else{
			text.append(text22);
		}
		
		text.append("</tr></tbody>");
		return text.toString();
	}
	

	public String returnNum(BigDecimal num){
		if(num==null){
			return "-";
		}
		return num.toString();
	}
	public BigDecimal returnZero(BigDecimal num){
		if(num==null){
			return new BigDecimal(0);
		}
		return num;
	}
	
	public String returnNumToPercent(BigDecimal num){
		double numb=0.0 ;
		if(num==null){
			return "-";
		}else if(num.equals(0)){
			return "-";
			
		}else{
		  numb = num.multiply(new BigDecimal(100)).doubleValue();
		}
		return String.valueOf(numb) +"%";
	}
}
