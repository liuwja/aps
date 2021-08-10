package com.peg.service.part.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peg.dao.bph.OnlinelookupMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.echarts.EChartObj;
import com.peg.model.bph.Onlinelookup;
import com.peg.service.part.OnlineServiceI;


@Service("OnlineService")
public class OnlineServiceImpl implements OnlineServiceI {

	@Resource
	private OnlinelookupMapper onlinelookup;
	
	@Override
	public EChartObj getEChartObj(Onlinelookup vo) {		
		EChartObj obj=new EChartObj();
		int bs=vo.getBs();
		String dimen=vo.getDimension();
		String time=vo.getEndtime();
		switch (bs) {
			case 1:
				obj=getAccountEObjpl(vo);
				break;
			case 2:
				obj=getSpareEObjpl(vo);
				break;
			case 3:
				obj=getBadEObjpl(vo);
				break;
			case 4:
				boolean b=false;
				if(vo.getDimension()==null || vo.getDimension().equals("") || vo.getEndtime()==null || vo.getEndtime().equals("")){
					vo.setDimension("2");
					String endtime=getLastWeekSunday();
					vo.setEndtime(endtime);
					b=true;
				}
				obj=getAccountEObjpl2(vo,1);
				if(b){
					vo.setDimension(dimen);
					vo.setEndtime(time);
				}
				break;
			case 5:
				boolean b2=false;
				if(vo.getDimension()==null || vo.getDimension().equals("") || vo.getEndtime()==null || vo.getEndtime().equals("")){
					vo.setDimension("2");
					String endtime=getLastWeekSunday();
					vo.setEndtime(endtime);
					b2=true;
				}
				obj=getAccountEObjpl2(vo,2);
				if(b2){
					vo.setDimension(dimen);
					vo.setEndtime(time);
				}
				break;
			case 6:
				obj=getAccountEObjsl(vo);
				break;
			case 7:
				obj=getSpareEObjsl(vo);
				break;
			case 8:
				obj=getBadEObjsl(vo);
				break;
			case 9:
				obj=getAccountEObjsltrend(vo);
				break;
			case 10:
				obj=getPartEObjsltrend(vo);
				break;
			case 11:
				obj=getAccountEObjsl2(vo);
				break;
			case 12:
				obj=getSpareEObjsl2(vo);
				break;
			case 13:
				obj=getBadEObjsl(vo);
				break;
			case 14:
				obj=getAccountEObjsltrend2(vo);
				break;
			case 15:
				obj=getPartEObjsltrend2(vo);
				break;
			default:
				break;
		}
		return obj;
	}
	/**
	 * 供应商排列图 （5M1E表）
	 * @param vo
	 * @return
	 */
	private EChartObj getAccountEObjpl(Onlinelookup vo){
		List<Onlinelookup> list=onlinelookup.getAccountChar(vo);
		EChartObj chart = new EChartObj();
		chart.setBarWidth("50");
		chart.setChartHight(100);
		chart.setTitle("供应商不良批次排列图");
		chart.setSeriesType(new String[]{"bar"} );                //设置系列类型
		String[] seriesNames ={"不良批次数"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getSupplier());
			yList.add(list.get(i).getBadcount());
			//Value.add(list.get(i).getNumbers());
			Value.add(list.get(i).getAccount_key()+","+list.get(i).getNumbers()+","+list.get(i).getSupplier()+";");
		}
		ylist.add(yList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		return chart;
	}
	/**
	 * 零部件排列图 （5M1E表）
	 * @param vo
	 * @return
	 */
	private EChartObj getSpareEObjpl(Onlinelookup vo){
		List<Onlinelookup> list=onlinelookup.getSpareChar(vo);
		EChartObj chart = new EChartObj();
		chart.setBarWidth("50");
		chart.setTitle("零部件不良批次排列图");
		chart.setSeriesType(new String[]{"bar"} );                //设置系列类型
		String[] seriesNames ={"不良批次数"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<String> Value = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getProductname());
			yList.add(list.get(i).getBadcount());
			//Value.add(list.get(i).getProductnumber());
			Value.add(list.get(i).getPart_key()+","+list.get(i).getProductnumber()+","+list.get(i).getProductname()+";");
		}
		ylist.add(yList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		return chart;
	}
	/**
	 * 不良现象排列图 （5M1E表）
	 * @param vo
	 * @return
	 */
	private EChartObj getBadEObjpl(Onlinelookup vo){
		List<Onlinelookup> list=onlinelookup.getBadChar(vo);
		EChartObj chart = new EChartObj();
		chart.setTitle("不良现象不良批次排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar"} );                //设置系列类型
		String[] seriesNames ={"不良批次数"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getDefect_s());
			yList.add(list.get(i).getBadcount());
		}
		ylist.add(yList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 供应商趋势图（5M1E表）
	 * @param vo
	 * @return
	 */
	private EChartObj getAccountEObjpl2(Onlinelookup vo,int y){
		EChartObj chart=new EChartObj();
//		List<String> list=new ArrayList<String>();
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
		try {
//			list=getdateList(vo);
			List<Onlinelookup> volist=null;
			if (y==1) {
				volist=onlinelookup.getAccountChar2(vo);
				chart.setTitle("供应商不良批次趋势图");
			}
			if (y==2) {
				volist=onlinelookup.getSpareChar2(vo);
				chart.setTitle("零部件不良批次趋势图");
			}
			chart.setBarWidth("50");
			chart.setSeriesType(new String[]{"bar"} );                //设置系列类型
			String[] seriesNames ={"不良批次数"};
			List<List<Double>> ylist = new ArrayList<List<Double>>();
			List<String> xValue = new ArrayList<String>();
			List<Double> yList = new ArrayList<Double>();
			for (int i = 0; i < volist.size(); i++) {
				if(vo.getDimension().equals("2")){
					Integer week=Integer.parseInt(volist.get(i).getDate_t().substring(5, 7));
					Integer year=Integer.parseInt(volist.get(i).getDate_t().substring(0, 4));
					System.out.println(year+":"+week);
					String weekstr=getDayOfWeek(year,week);
					xValue.add(weekstr);
					yList.add(volist.get(i).getBadcount());
				}else {
					xValue.add(volist.get(i).getDate_t());
					yList.add(volist.get(i).getBadcount());
				}
			}
			ylist.add(yList);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}
	/**
	 * 供应商不良数/率排列图 关键件
	 * @param vo
	 * @return
	 */
	private EChartObj getAccountEObjsl(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getAccountCharsl(vo);
		chart.setTitle("供应商不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getAbbreviation());
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				//rateyList.add(list.get(i).getBadcount()/list.get(i).getCount());
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			Value.add(list.get(i).getAccount_key()+","+list.get(i).getNumbers()+","+list.get(i).getAbbreviation()+";");
		}
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		return chart;
	}
	/**
	 * 供应商不良数/率排列图 非关键件
	 * @param vo
	 * @return
	 */
	private EChartObj getAccountEObjsl2(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getAccountCharsl2(vo);
		chart.setTitle("供应商不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> Value = new ArrayList<String>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getAbbreviation());
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				//rateyList.add(list.get(i).getBadcount()/list.get(i).getCount());
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			Value.add(list.get(i).getAccount_key()+","+list.get(i).getNumbers()+","+list.get(i).getAbbreviation()+";");
		}
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		return chart;
	}
	/**
	 * 零部件不良数/率排列图 关键件
	 * @return
	 */
	private EChartObj getSpareEObjsl(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getSpareCharsl(vo);
		chart.setTitle("零部件不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> Value = new ArrayList<String>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getPartnumber());
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				//rateyList.add(list.get(i).getBadcount()/list.get(i).getCount());
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			Value.add(list.get(i).getPart_key()+","+list.get(i).getPartnumber()+","+list.get(i).getProductname()+";");
		}
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		return chart;
	}
	/**
	 * 零部件不良数/率排列图 非关键件
	 * @return
	 */
	private EChartObj getSpareEObjsl2(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getSpareCharsl2(vo);
		chart.setTitle("零部件不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> Value = new ArrayList<String>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getPartnumber());
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				//rateyList.add(list.get(i).getBadcount()/list.get(i).getCount());
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			Value.add(list.get(i).getPart_key()+","+list.get(i).getPartnumber()+","+list.get(i).getProductname()+";");
		}
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		return chart;
	}
	/**
	 * 不良现象数/率图（关键件，非关键件）
	 * @param vo
	 * @return
	 */
	private EChartObj getBadEObjsl(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getBadcharsl(vo);
		chart.setTitle("不良现象不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getDefect_s());
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				//rateyList.add(list.get(i).getBadcount()/list.get(i).getCount());
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
		}
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 供应商不良数/率趋势图（关键件）
	 * @return
	 * @throws Exception 
	 */
	private EChartObj getAccountEObjsltrend(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getAccountRend(vo);
		chart.setTitle("供应商不良数/率趋势图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			if(vo.getDimension().equals("2")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7));
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
			}else{
				xValue.add(list.get(i).getDate_t());
			}
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			
			String time=list.get(i).getDate_t();
			vo=setupVo(vo);
			int SameInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//当前日期维度的入库数
			vo=setupVo2(vo,time);
			int StorageInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//30个月的入库数
			int TotalInteger=onlinelookup.getTotal(vo)==null?0:onlinelookup.getTotal(vo);//30个月的总退次数
			Double p=(double) (TotalInteger/StorageInteger);//计算公式参数p
			Double q=java.lang.Math.sqrt(p*(1-p))/SameInteger;	
			conList.add(p+q);
			conList2.add(p-q);
		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(conList);
		ylist.add(conList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 设置vo 查当前日期维度的入库数
	 * @param vo
	 * @return
	 */
	private Onlinelookup setupVo(Onlinelookup vo){
		Calendar curr = Calendar.getInstance();//获取一个日历对象
		String DateType=vo.getDate_type();
		DateFormat sdf=null;
		try {
			if(DateType.equals("yyyy")){
				sdf = new SimpleDateFormat("yyyy");
				Date date = sdf.parse(vo.getEnddate());
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				date = sdf.parse(vo.getStartdate());
				curr.setTime(date);
				vo.setStartdate(sdf.format(curr.getTime()));
			}
			if(DateType.equals("yyyy-mm")){
				sdf = new SimpleDateFormat("yyyy-MM");
				Date date = sdf.parse(vo.getEnddate());
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				date = sdf.parse(vo.getStartdate());
				curr.setTime(date);
				vo.setStartdate(sdf.format(curr.getTime()));
				
			}
			if(DateType.equals("yyyy-IW")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(vo.getEnddate());
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				date = sdf.parse(vo.getStartdate());
				curr.setTime(date);
				vo.setStartdate(sdf.format(curr.getTime()));
				vo.setDate_type("yyyy-mm-dd");
			}
			if(DateType.equals("yyyy-mm-dd")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(vo.getEnddate());
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				date = sdf.parse(vo.getStartdate());
				curr.setTime(date);
				vo.setStartdate(sdf.format(curr.getTime()));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return vo;
	}
	/**
	 * 设置vo 查当前日期维度的30个月
	 * @param vo
	 * @param time
	 * @return
	 */
	private Onlinelookup setupVo2(Onlinelookup vo,String time){
		String DateType=vo.getDate_type();
		Calendar curr = Calendar.getInstance();//获取一个日历对象
		DateFormat sdf=null;
		try {
			if(DateType.equals("yyyy")){
				sdf = new SimpleDateFormat("yyyy");
	        	Date date = sdf.parse(time);
	        	curr.setTime(date);
	        	vo.setEnddate(sdf.format(curr.getTime()));
	        	curr.add(Calendar.YEAR, -30);
	        	vo.setStartdate(sdf.format(curr.getTime()));
			}
			if(DateType.equals("yyyy-MM")){
				sdf = new SimpleDateFormat("yyyy-MM");
	        	Date date = sdf.parse(time);
	        	curr.setTime(date);
	        	vo.setEnddate(sdf.format(curr.getTime()));
	        	curr.add(Calendar.MONTH, -30);
	        	vo.setStartdate(sdf.format(curr.getTime()));
				
			}
			if(DateType.equals("yyyy-IW")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(time);
	        	curr.setTime(date);
	        	vo.setStartdate(sdf.format(curr.getTime()));
	        	curr.add(Calendar.DAY_OF_YEAR, -30*7);
	        	vo.setEnddate(sdf.format(curr.getTime()));
	        	vo.setDate_type("yyyy-mm-dd");
			}
			if(DateType.equals("yyyy-mm-dd")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(time);
	        	curr.setTime(date);
	        	vo.setStartdate(sdf.format(curr.getTime()));
	        	curr.add(Calendar.DAY_OF_YEAR, -30);
	        	vo.setEnddate(sdf.format(curr.getTime()));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return vo;
	}
	/**
	 * 供应商不良数/率趋势图（非关键件）
	 * @return
	 */
	private EChartObj getAccountEObjsltrend2(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getAccountRend2(vo);
		chart.setTitle("供应商不良数/率趋势图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			if(vo.getDimension().equals("2")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7));
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
			}else{
				xValue.add(list.get(i).getDate_t());
			}
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			String time=list.get(i).getDate_t();
			vo=setupVo(vo);
			int SameInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//当前日期维度的入库数
			vo=setupVo2(vo,time);
			int StorageInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//30个月的入库数
			int TotalInteger=onlinelookup.getTotal(vo)==null?0:onlinelookup.getTotal(vo);//30个月的总退次数
			Double p=(double) (TotalInteger/StorageInteger);//计算公式参数p
			Double q=java.lang.Math.sqrt(p*(1-p))/SameInteger;	
			conList.add(p+q);
			conList2.add(p-q);
		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(conList);
		ylist.add(conList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 零部件不良数/率趋势图（关键件）
	 * @param vo
	 * @return
	 */
	private EChartObj getPartEObjsltrend(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getSpareRend(vo);
		chart.setTitle("零部件不良数/率趋势图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			if(vo.getDimension().equals("2")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7));
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
			}else{
				xValue.add(list.get(i).getDate_t());
			}
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			String time=list.get(i).getDate_t();
			vo=setupVo(vo);
			int SameInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//当前日期维度的入库数
			vo=setupVo2(vo,time);
			int StorageInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//30个月的入库数
			int TotalInteger=onlinelookup.getTotal(vo)==null?0:onlinelookup.getTotal(vo);//30个月的总退次数
			Double p=(double) (TotalInteger/StorageInteger);//计算公式参数p
			Double q=java.lang.Math.sqrt(p*(1-p))/SameInteger;	
			conList.add(p+q);
			conList2.add(p-q);
		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(conList);
		ylist.add(conList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 零部件不良数/率趋势图（非关键件）
	 * @param vo
	 * @return
	 */
	private EChartObj getPartEObjsltrend2(Onlinelookup vo){
		EChartObj chart=new EChartObj();
		List<Onlinelookup> list=onlinelookup.getSpareRend2(vo);
		chart.setTitle("零部件不良数/率趋势图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> rateyList = new ArrayList<Double>();
		for (int i = 0; i < list.size(); i++) {
			if(vo.getDimension().equals("2")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7));
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
			}else{
				xValue.add(list.get(i).getDate_t());
			}
			yList.add(list.get(i).getBadcount());
			Double dou=list.get(i).getCount();
			if(dou!=null && dou!=0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/list.get(i).getCount())*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			String time=list.get(i).getDate_t();
			vo=setupVo(vo);
			int SameInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//当前日期维度的入库数
			vo=setupVo2(vo,time);
			int StorageInteger=onlinelookup.getStorageTotal(vo)==null?0:onlinelookup.getStorageTotal(vo);//30个月的入库数
			int TotalInteger=onlinelookup.getTotal(vo)==null?0:onlinelookup.getTotal(vo);//30个月的总退次数
			Double p=(double) (TotalInteger/StorageInteger);//计算公式参数p
			Double q=java.lang.Math.sqrt(p*(1-p))/SameInteger;	
			conList.add(p+q);
			conList2.add(p-q);
		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(conList);
		ylist.add(conList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 在线批次数据展示
	 */
	@Override
	public List<Onlinelookup> gettablist(BaseSearch bs) {
		return onlinelookup.getOnlineshowPage(bs);
	} 
	/**
	 * 来料入库明细
	 */
	@Override
	public List<Onlinelookup> gettabStoragelist(BaseSearch bs) {
		return onlinelookup.getOnlineshowupPage(bs);
	}
	/**
	 * 不良数展示（mes）
	 */
	@Override
	public List<Onlinelookup> getTabList(BaseSearch bs) {
		return onlinelookup.getshowPage(bs);
	}
	/**
	 * ERP在线不良数据展示
	 */
	@Override
	public List<Onlinelookup> getERPTabList(BaseSearch bs) {
		return onlinelookup.getERPshowPage(bs);
	} 
	/**
	 * 定时任务调度
	 */
	@Override
	public int insertDate() {
		int result=0;
		try {
			onlinelookup.dateTransfer();
		} catch (Exception e) {
			result=-1;
			e.getMessage();
		}
		return result;
	}
	//日期处理
	public List<String> getdateList(Onlinelookup vo) throws Exception{
		List<String> list=new ArrayList<String>();
		String time=vo.getEndtime();//截止日期
		String dimension=vo.getDimension()==null?"":vo.getDimension();//时间维度
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
        Calendar curr = Calendar.getInstance();//获取一个日历对象
        if(time!=null && !time.equals("")){
			if(dimension.equals("4")){//年
				for (int i = 0; i < vo.getCharNumber(); i++) {
					sdf = new SimpleDateFormat("yyyy");
					Date date = sdf.parse(time);
		        	curr.setTime(date);
		        	curr.add(Calendar.YEAR, -1*(vo.getCharNumber()-i));
		        	list.add(sdf.format(curr.getTime())+"-01"+"-01");
				}
			}
			if(dimension.equals("3")){//月
				for (int i = 0; i < vo.getCharNumber(); i++) {
					sdf = new SimpleDateFormat("yyyy-MM");
					Date date = sdf.parse(time);
		        	curr.setTime(date);
		        	curr.add(Calendar.MONTH, -1*(vo.getCharNumber()-i));
		        	list.add(sdf.format(curr.getTime())+"-01");
				}
				//list.add(sdf.format(time+"-01"));
			}
			if(dimension.equals("2")){//周
				for (int i = 0; i < vo.getCharNumber(); i++) {
					Date date = sdf.parse(time);
		        	curr.setTime(date);
		        	curr.add(Calendar.DAY_OF_YEAR, -7*(vo.getCharNumber()-i));
		        	list.add(sdf.format(curr.getTime()));
				}
				//list.add(time);
			}
        }
		return list;
	}
	//获取上周末的日期
	private static String getLastWeekSunday(){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
	    Calendar date=Calendar.getInstance(Locale.CHINA);
	    date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
	    date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周
	    date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//日子设为星期天
	    return sdf.format(date.getTime());

	}
	//根据某年某周得到周一的日期
	public static String getDayOfWeek(int year, int week) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance(); 
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		return sdf.format(c.getTime());
	}
}
