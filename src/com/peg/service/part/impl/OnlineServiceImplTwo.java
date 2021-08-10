package com.peg.service.part.impl;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.part.OnlineMapper;
import com.peg.dao.part.TestInstanceMapper;
import com.peg.echarts.EChartObj;
import com.peg.model.Location;
import com.peg.model.part.OnlineModel;
import com.peg.model.part.TestInstance;
import com.peg.service.part.OnlineServiceITwo;
import com.peg.web.util.MathUtil;
import com.peg.web.util.TmStringUtils;


@Service("OnlineServiceTwo")
public class OnlineServiceImplTwo implements OnlineServiceITwo {

	@Resource
	private OnlineMapper onlineMapper;
	
	@Resource
	private TestInstanceMapper testInstanceMapper;
	
	
	/**
	 * 图形处理
	 */
	@Override
	public EChartObj getEChartObj(OnlineModel o) {
		//补充数据
		finishData(o);
		EChartObj obj=new EChartObj();
		int chartid=Integer.parseInt(o.getCharthid());
		switch (chartid) {
			case 1:
				obj=getChart1(o);
				break;
			case 2:
				obj=getChart2(o);
				break;
			case 3:
				obj=getChart3(o);
				break;
			case 4:
				obj=getChart4(o);
				break;
			case 5:
				obj=getChart5(o);
				break;
			case 6:
				obj=getChart6(o);
				break;
			case 7:
				obj=getChart7(o);
				break;
			case 8:
				obj=getChart8(o);
				break;
			case 9:
				obj=getChart9(o);
				break;
			case 10:
				obj=getChart10(o);
				break;
			case 11:
				obj=getChart11(o);
				break;
			case 12:
				obj=getChart12(o);
				break;
			case 13:
				obj=getChart13(o);
				break;
			case 14:
				obj=getChart14(o);
				break;
			case 15:
				obj=getChart15(o);
				break;
			default:
				break;
		}
		return obj;
	}
	
	/**
	 * 供应商不良批次数
	 * @param o
	 * @return
	 */
	private EChartObj getChart1(OnlineModel o){
		EChartObj chart=new EChartObj();
//		StringBuilder sb=new StringBuilder("select * from (select account_key,supplier,numbers,count(1) as badcount from (select d.account_key,d.description as supplier,NVL(d.supplier_number_n,d.account_name) as numbers,a.defect_qty_i as badnum,a.total_qty_i as num from  At_Batchdefectrecord a left join (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on pp.part_number=nn.old_part_number) b on a.part_number_s=b.part_number left join uda_part c on b.part_key=c.object_key left join (select a.account_key,a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number) d on a.supplier_number_s=d.account_name where 1=1  and a.me_s='料'");
//		//如果工厂不为空，机型为空
//		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
//			sb.append(" and c.mold_type_s in ("+o.getProductTypes()+")");
//		}
//		if(o.getProductType()!=null && !o.getProductType().equals("")){
//			sb.append(" and c.mold_type_s = '"+o.getProductType()+"'");
//		}
//		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.record_date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
//		}
//		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.record_date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
//		}
//		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
//			sb.append(" and c.part_level_s='"+o.getPartClass()+"'");
//		}
//		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
//			sb.append(" and NVL(d.supplier_number_n,d.account_name) in ("+getSupStringByComma(o.getSupplierCode())+")");
//		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and NVL(b.new_part_number,b.part_number) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
//		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
//			sb.append(" and a.product_maturity_s = '"+o.getIsNew()+"'");
//		}
//		if(o.getIscrux().equals("0")){//关键件
//			sb.append(" and b.consumption_type ='SerialNumber'");
//		}
//		if(o.getIscrux().equals("1")){//非关键件
//			sb.append(" and b.consumption_type <>'SerialNumber'");
//		}
//		sb.append(" ) m group by account_key,numbers,supplier order by count(badnum) desc) where account_key is not null and rownum <= "+o.getColumnNum()+"");
//		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		//------
		List<OnlineModel> list = onlineMapper.getBatchDefectRecordList(o);
		//编号map
		Map<String,String> numberMap = new HashMap<String, String>();
		
		chart.setBarWidth("50");
		chart.setChartHight(100);
		chart.setTitle("供应商不良批次数/率排列图");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良批次数","累计不良率"};
		chart.setColors(new String[]{"#3398DB","#675bba"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> yList2 = new ArrayList<Double>();
		double countnum=0.0;
		double count=0.0;
		for (int i = 0; i < list.size(); i++) {
			countnum=countnum+list.get(i).getBadcount();
		}
		
		//排列图数量----
		int columnNum = o.getColumnNum();
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setSupplier_name("其他");
				on.setBadcount(countnum-count);
				nList.add(on);
			}else{
				count += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				nList.add(on);
			}
		}
		count = 0;
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getSupplier_name());
			yList.add(nList.get(i).getBadcount());
			
			count=count+nList.get(i).getBadcount();
			double d=0.0;
			if(countnum!=0.0){
				d=(count/countnum)*100;
			}
			yList2.add(MathUtil.round(d, 4));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getSupplier_number()+","+nList.get(i).getSupplier_name()+";");
			if(nList.get(i).getSupplier_name() != null){
				numberMap.put(nList.get(i).getSupplier_name(), nList.get(i).getSupplier_number());
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getSupplier());
//			yList.add(list.get(i).getBadcount());
//			count=count+list.get(i).getBadcount();
//			DecimalFormat df=new DecimalFormat("######0.00");
//			double d=0.0;
//			if(countnum!=0.0){
//				d=(count/countnum)*100;
//			}
//			String s=df.format(d);
//			yList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getAccount_key()+","+list.get(i).getNumbers()+","+list.get(i).getSupplier()+";");
//		}
		ylist.add(yList);
		ylist.add(yList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setOnline(o);
		chart.setOnlineList(nList);
		chart.setNumberMap(numberMap);
		return chart;
	}
	/**
	 * 零部件不良批次数
	 * @param o
	 * @return
	 */
	private EChartObj getChart2(OnlineModel o){
		EChartObj chart=new EChartObj();
//		StringBuilder sb=new StringBuilder("select * from (select part_key,productname,product_number_s as productnumber,count(1) as badcount from (select b.part_key,NVL(b.new_part_number,b.part_number) as product_number_s,b.description as productname,a.defect_qty_i as badnum,a.total_qty_i as num from At_Batchdefectrecord a left join (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on pp.part_number=nn.old_part_number) b on a.part_number_s=b.part_number left join uda_part c on b.part_key=c.object_key left join (select a.account_key,a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number) d on a.supplier_number_s=d.account_name where 1=1  and a.me_s='料'");
//		//如果工厂不为空，机型为空
//		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
//			sb.append(" and c.mold_type_s in ("+o.getProductTypes()+")");
//		}
//		if(o.getProductType()!=null && !o.getProductType().equals("")){
//			sb.append(" and c.mold_type_s = '"+o.getProductType()+"'");
//		}
//		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.record_date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
//		}
//		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.record_date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
//		}
//		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
//			sb.append(" and c.part_level_s='"+o.getPartClass()+"'");
//		}
//		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
//			sb.append(" and NVL(d.supplier_number_n,d.account_name) in ("+getSupStringByComma(o.getSupplierCode())+")");
//		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and NVL(b.new_part_number,b.part_number) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
//		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
//			sb.append(" and a.product_maturity_s = '"+o.getIsNew()+"'");
//		}
//		if(o.getIscrux().equals("0")){//关键件
//			sb.append(" and b.consumption_type ='SerialNumber'");
//		}
//		if(o.getIscrux().equals("1")){//非关键件
//			sb.append(" and b.consumption_type <>'SerialNumber'");
//		}
//		sb.append(" ) m group by part_key,productname,product_number_s order by count(badnum) desc) where rownum <= "+o.getColumnNum()+"");
//		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		//------
		List<OnlineModel> list = onlineMapper.getBatchDefectRecordList(o);
		//编号map
		Map<String,String> numberMap = new HashMap<String, String>();
		
		chart.setBarWidth("50");
		chart.setTitle("零部件不良批次数/率排列图");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良批次数","累计不良率"};
		chart.setColors(new String[]{"#3398DB","#675bba"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList2 = new ArrayList<Double>();
		double countnum=0.0;
		double count=0.0;
		for (int i = 0; i < list.size(); i++) {
			countnum=countnum+list.get(i).getBadcount();
		}
		//排列图数量----
		int columnNum = o.getColumnNum();
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setPart_name("其他");
				on.setBadcount(countnum-count);
				nList.add(on);
			}else{
				count += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				nList.add(on);
			}
		}
		count = 0;
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getPart_name());
			yList.add(nList.get(i).getBadcount());
			
			count=count+nList.get(i).getBadcount();
			double d=0.0;
			if(countnum!=0.0){
				d=(count/countnum)*100;
			}
			yList2.add(MathUtil.round(d, 4));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getPart_number()+","+nList.get(i).getPart_name()+";");
			if(nList.get(i).getPart_name() != null){
				numberMap.put(nList.get(i).getPart_name(), nList.get(i).getPart_number());
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getProductname());
//			yList.add(list.get(i).getBadcount());
//			count=count+list.get(i).getBadcount();
//			DecimalFormat df=new DecimalFormat("######0.00");
//			double d=0.0;
//			if(countnum!=0.0){
//				d=(count/countnum)*100;
//			}
//			String s=df.format(d);
//			yList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getPart_key()+","+list.get(i).getProductnumber()+","+list.get(i).getProductname()+";");
//		}
		ylist.add(yList);
		ylist.add(yList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setOnline(o);
		chart.setOnlineList(list);
		chart.setNumberMap(numberMap);
		return chart;
	}
	/**
	 * 不良现象不量批次
	 * @param o
	 * @return
	 */
	private EChartObj getChart3(OnlineModel o){
		EChartObj chart=new EChartObj();
//		StringBuilder sb=new StringBuilder("select * from (select defect_s,count(1) as badcount from (select a.defect_s,a.defect_qty_i as badnum,a.total_qty_i as num from At_Batchdefectrecord a left join (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on pp.part_number=nn.old_part_number) b on a.part_number_s=b.part_number left join uda_part c on b.part_key=c.object_key left join (select a.account_key,a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number) d on a.supplier_number_s=d.account_name where 1=1  and a.me_s='料'");
//		//如果工厂不为空，机型为空
//		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
//			sb.append(" and c.mold_type_s in ("+o.getProductTypes()+")");
//		}
//		if(o.getProductType()!=null && !o.getProductType().equals("")){
//			sb.append(" and c.mold_type_s = '"+o.getProductType()+"'");
//		}
//		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.record_date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
//		}
//		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.record_date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
//		}
//		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
//			sb.append(" and c.part_level_s='"+o.getPartClass()+"'");
//		}
//		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
//			sb.append(" and NVL(d.supplier_number_n,d.account_name) in ("+getSupStringByComma(o.getSupplierCode())+")");
//		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and NVL(b.new_part_number,b.part_number) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
//		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
//			sb.append(" and a.product_maturity_s = '"+o.getIsNew()+"'");
//		}
//		if(o.getIscrux().equals("0")){//关键件
//			sb.append(" and b.consumption_type ='SerialNumber'");
//		}
//		if(o.getIscrux().equals("1")){//非关键件
//			sb.append(" and b.consumption_type <>'SerialNumber'");
//		}
//		sb.append(" ) m group by defect_s order by count(badnum) desc) where rownum <= "+o.getColumnNum()+"");
//		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		//----
		List<OnlineModel> list = onlineMapper.getBatchDefectRecordList(o);
		
		chart.setTitle("不良现象不良批次数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		String[] seriesNames ={"不良批次数","累计不良率"};
		chart.setColors(new String[]{"#3398DB","#675bba"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> yList2 = new ArrayList<Double>();
		List<String> Value = new ArrayList<String>();
		double countnum=0.0;
		double count=0.0;
		for (int i = 0; i < list.size(); i++) {
			countnum=countnum+list.get(i).getBadcount();
		}
		//排列图数量----
		int columnNum = o.getColumnNum();
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setDefect_s("其他");
				on.setBadcount(countnum-count);
				nList.add(on);
			}else{
				count += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				nList.add(on);
			}
		}
		count = 0;
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getDefect_s());
			yList.add(nList.get(i).getBadcount());
			
			count=count+nList.get(i).getBadcount();
			double d=0.0;
			if(countnum!=0.0){
				d=(count/countnum)*100;
			}
			yList2.add(MathUtil.round(d, 4));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getDefect_s()+";");
		}
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getDefect_s());
//			yList.add(list.get(i).getBadcount());
//			count=count+list.get(i).getBadcount();
//			DecimalFormat df=new DecimalFormat("######0.00");
//			double d=0.0;
//			if(countnum!=0.0){
//				d=(count/countnum)*100;
//			}
//			String s=df.format(d);
//			yList2.add(Double.valueOf(s));
//		}
		ylist.add(yList);
		ylist.add(yList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		//----
		chart.setValue(Value);
		chart.setOnline(o);
		chart.setOnlineList(nList);
		return chart;
	}
	/**
	 * 供应商不良批次趋势图
	 * @param o
	 * @return
	 */
	private EChartObj getChart4(OnlineModel o){
		EChartObj chart=new EChartObj();
//		StringBuilder sb=new StringBuilder("select * from (select to_char(record_date_t,'"+o.getDate_type()+"') as date_t,count(1) as badcount from (select a.record_date_t as record_date_t from At_Batchdefectrecord a left join (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on pp.part_number=nn.old_part_number) b on a.part_number_s=b.part_number left join uda_part c on b.part_key=c.object_key left join (select a.account_key,a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number) d on a.supplier_number_s=d.account_name where 1=1  and a.me_s='料'");
//		//如果工厂不为空，机型为空
//		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
//			sb.append(" and c.mold_type_s in ("+o.getProductTypes()+")");
//		}
//		if(o.getProductType()!=null && !o.getProductType().equals("")){
//			sb.append(" and c.mold_type_s = '"+o.getProductType()+"'");
//		}
//		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.record_date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
//		}
//		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.record_date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
//		}
//		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
//			sb.append(" and c.part_level_s='"+o.getPartClass()+"'");
//		}
//		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
//			sb.append(" and NVL(d.supplier_number_n,d.account_name) in ("+getSupStringByComma(o.getSupplierCode())+")");
//		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and NVL(b.new_part_number,b.part_number) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
//		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
//			sb.append(" and a.product_maturity_s = '"+o.getIsNew()+"'");
//		}
//		if(o.getIscrux().equals("0")){//关键件
//			sb.append(" and b.consumption_type ='SerialNumber'");
//		}
//		if(o.getIscrux().equals("1")){//非关键件
//			sb.append(" and b.consumption_type <>'SerialNumber'");
//		}
//		sb.append(" ) group by to_char(record_date_t,'"+o.getDate_type()+"')) where rownum <= "+o.getColumnNum()+"");
//		List<OnlineModel> volist=onlineMapper.getEChartObj(sb.toString());
		//----
		List<OnlineModel> volist = onlineMapper.getBatchDefectRecordList(o);
		List<String> datelist=getdateList(o);
		volist=setModelList(volist,datelist);
		try {
			chart.setTitle("供应商不良批次数/率趋势图");
			chart.setBarWidth("50");
			chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
			String[] seriesNames ={"不良批次数","累计不良率"};
			chart.setColors(new String[]{"#3398DB","#675bba"});
			List<List<Double>> ylist = new ArrayList<List<Double>>();
			List<String> xValue = new ArrayList<String>();
			List<Double> yList = new ArrayList<Double>();
			List<Double> yList2 = new ArrayList<Double>();
			List<String> Value = new ArrayList<String>();
			double countnum=0.0;
			double count=0.0;
			for (int i = 0; i < volist.size(); i++) {
				countnum=countnum+volist.get(i).getBadcount();
			}
			//排列图数量----
			int columnNum = o.getColumnNum();
			//重构list
			List<OnlineModel> nList = new ArrayList<OnlineModel>();
			//如果list的长度大于colunmNum----
			boolean isMore = false;
			if( columnNum >=volist.size()  ){
				columnNum = volist.size();
			}else{
				isMore = true;
				columnNum = columnNum+1;
			}
			for(int i=0; i<columnNum; i++){
				if(isMore && i==columnNum-1){
					OnlineModel on = new OnlineModel();
					on.setDate_t("其他");
					on.setBadcount(countnum-count);
					nList.add(on);
				}else{
					count += volist.get(i).getBadcount();
					OnlineModel on = volist.get(i);
					nList.add(on);
				}
			}
			count = 0;
			for (int i = 0; i < nList.size(); i++) {
				if(o.getDateType().equals("周")){
					Integer week=Integer.parseInt(nList.get(i).getDate_t().substring(5, 7))+1;
					Integer year=Integer.parseInt(nList.get(i).getDate_t().substring(0, 4));
					System.out.println(year+":"+week);
					String weekstr=getDayOfWeek(year,week);
					xValue.add(weekstr);
					yList.add(nList.get(i).getBadcount());
				}else {
					xValue.add(nList.get(i).getDate_t());
					yList.add(nList.get(i).getBadcount());
				}
				count=count+nList.get(i).getBadcount();
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=0.0;
				if(countnum!=0.0){
					d=(count/countnum)*100;
				}
				String s=df.format(d);
				yList2.add(Double.valueOf(s));
				//----
				Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getDate_t()+";");
			}
			ylist.add(yList);
			ylist.add(yList2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
			//----
			chart.setValue(Value);
			chart.setOnline(o);
			chart.setOnlineList(volist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}
	/**
	 * 不良现象不良批次趋势图
	 * @param o
	 * @return
	 */
	private EChartObj getChart5(OnlineModel o){
		EChartObj chart=new EChartObj();
//		StringBuilder sb=new StringBuilder(" select * from (select to_char(record_date_t,'"+o.getDate_type()+"') as date_t,count(1) as badcount from (select a.record_date_t as record_date_t from At_Batchdefectrecord a left join (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on pp.part_number=nn.old_part_number) b on a.part_number_s=b.part_number left join uda_part c on b.part_key=c.object_key left join (select a.account_key,a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number)  d on a.supplier_number_s=d.account_name where 1=1  and a.me_s='料'");
//		//如果工厂不为空，机型为空
//		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
//			sb.append(" and c.mold_type_s in ("+o.getProductTypes()+")");
//		}
//		if(o.getProductType()!=null && !o.getProductType().equals("")){
//			sb.append(" and c.mold_type_s = '"+o.getProductType()+"'");
//		}
//		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.record_date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
//		}
//		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.record_date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
//		}
//		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
//			sb.append(" and c.part_level_s='"+o.getPartClass()+"'");
//		}
//		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
//			sb.append(" and NVL(d.supplier_number_n,d.account_name) in ("+getSupStringByComma(o.getSupplierCode())+")");
//		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and NVL(b.new_part_number,b.part_number) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
//		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
//			sb.append(" and a.product_maturity_s = '"+o.getIsNew()+"'");
//		}
//		if(o.getIscrux().equals("0")){//关键件
//			sb.append(" and b.consumption_type ='SerialNumber'");
//		}
//		if(o.getIscrux().equals("1")){//非关键件
//			sb.append(" and b.consumption_type <>'SerialNumber'");
//		}
//		sb.append(" ) group by to_char(record_date_t,'"+o.getDate_type()+"')) where rownum <= "+o.getColumnNum()+"");
//		List<OnlineModel> volist=onlineMapper.getEChartObj(sb.toString());
		//-----
		List<OnlineModel> volist = onlineMapper.getBatchDefectRecordList(o);
		
		List<String> datelist=getdateList(o);
		volist=setModelList(volist,datelist);
		try {
			chart.setTitle("零部件不良批次数/率趋势图");
			chart.setBarWidth(o.getColumnNum() >= 15 ? "30" :"50");
			chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
			String[] seriesNames ={"不良批次数","累计不良率"};
			chart.setColors(new String[]{"#3398DB","#675bba"});
			List<List<Double>> ylist = new ArrayList<List<Double>>();
			List<String> xValue = new ArrayList<String>();
			List<Double> yList = new ArrayList<Double>();
			List<Double> yList2 = new ArrayList<Double>();
			List<String> Value = new ArrayList<String>();
			double countnum=0.0;
			double count=0.0;
			for (int i = 0; i < volist.size(); i++) {
				countnum=countnum+volist.get(i).getBadcount();
			}
			//排列图数量----
			int columnNum = o.getColumnNum();
			//重构list
			List<OnlineModel> nList = new ArrayList<OnlineModel>();
			//如果list的长度大于colunmNum----
			boolean isMore = false;
			if( columnNum >=volist.size()  ){
				columnNum = volist.size();
			}else{
				isMore = true;
				columnNum = columnNum+1;
			}
			for(int i=0; i<columnNum; i++){
				if(isMore && i==columnNum-1){
					OnlineModel on = new OnlineModel();
					on.setDate_t("其他");
					on.setBadcount(countnum-count);
					nList.add(on);
				}else{
					count += volist.get(i).getBadcount();
					OnlineModel on = volist.get(i);
					nList.add(on);
				}
			}
			for (int i = 0; i < nList.size(); i++) {
				if(o.getDate_type().equals("周")){
					Integer week=Integer.parseInt(nList.get(i).getDate_t().substring(5, 7));
					Integer year=Integer.parseInt(nList.get(i).getDate_t().substring(0, 4));
					System.out.println(year+":"+week);
					String weekstr=getDayOfWeek(year,week);
					xValue.add(weekstr);
					yList.add(nList.get(i).getBadcount());
				}else {
					xValue.add(nList.get(i).getDate_t());
					yList.add(nList.get(i).getBadcount());
				}
				count=count+nList.get(i).getBadcount();
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=0.0;
				if(countnum!=0.0){
					d=(count/countnum)*100;
				}
				String s=df.format(d);
				yList2.add(Double.valueOf(s));
				//
				Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getDate_t()+";");
			}
			ylist.add(yList);
			ylist.add(yList2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
			//----
			chart.setValue(Value);
			chart.setOnline(o);
			chart.setOnlineList(volist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}
	/**
	 * 供应商不良数/率 更换
	 * @param o
	 * @return
	 */
	private EChartObj getChart6(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;
		
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//供应商绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		//编号map
		Map<String,String> numberMap = new HashMap<String, String>();
		
//		StringBuilder sb=new StringBuilder("select * from (select z.account_key,z.account_number_n as supcode,z.account_name as supname,count(1) as badcount from t_replace_part z where 1=1");
		
//		//无需控制查询数据，如果查询结果多过柱子数量，多的则用‘其他’表示,增加供应商简称----
//		StringBuilder sb=new StringBuilder("select z.account_key,z.account_number_n as supcode,z.account_abbreviation as supname,count(1) as badcount from t_replace_part z where 1=1");
		StringBuilder sb=new StringBuilder("select nvl(z.account_number_n, '无') as supcode,nvl(nvl(z.account_abbreviation, z.account_name), '无') as supname,count(1) as badcount from t_replace_part z where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and z.modeltype in ("+o.getProductTypes()+")");
		}
		
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and z.modeltype = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and z.replacetime >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//-----
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and z.replacetime <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and z.part_level='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and z.account_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本
		if("否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and z.maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and z.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and z.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
//		sb.append(" group by z.account_key,z.account_number_n,z.account_name order by badcount desc) where rownum<= "+o.getColumnNum()+"");
	    //------
//		sb.append(" group by z.account_key,z.account_number_n,z.account_abbreviation order by badcount desc ");
		sb.append(" group by nvl(z.account_number_n, '无'),nvl(nvl(z.account_abbreviation, z.account_name), '无') order by badcount desc ");
		
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("供应商不良数/率排列图");
		chart.setBarWidth(o.getColumnNum() >= 15 ? "30" :"50");
		chart.setSeriesType(new String[]{"bar","line","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率","累计不良率"};
		String[] axisIndex ={"0","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#d14a61","#675bba"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<Double> rateyList2 = new ArrayList<Double>();
		
		//排列图数量----
		int columnNum = o.getColumnNum();
		//插入数据到bindMap
		for(OnlineModel on : bindList){
			if(on.getAccount_name() != null){
				bingMap.put(on.getAccount_name(), on.getTotal_qty_i().longValue());
			}
		}
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		
		double badcount=0.0;
		double sumcount=0.0;
		DecimalFormat df=new DecimalFormat("######0.00");
		for (int i = 0; i < list.size(); i++) {
			sumcount=sumcount+list.get(i).getBadcount();
			
			//----
//			bingMap.put(list.get(i).getSupname(), Long.valueOf(count));
		}
		
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		//如果是按不良率排序
		if("不良率".equals(o.getSordType())){
			for(OnlineModel m1 : list){
				for(OnlineModel m2 : bindList){
					if(m1.getSupcode() != null && m1.getSupcode().equals(m2.getAccount_name())){
						m1.setTotal_qty_i(m2.getTotal_qty_i());
					}
				}
			}
			Collections.sort(list, new Comparator<OnlineModel>() {
				@Override
				public int compare(OnlineModel o1, OnlineModel o2) {
					if(o1.getTotal_qty_i() != null && o2.getTotal_qty_i() != null){
						 return o1.getBadcount()/o1.getTotal_qty_i().doubleValue()>o2.getBadcount()/o2.getTotal_qty_i().doubleValue() ? -1 : 1 ; 
					}else if(o1.getTotal_qty_i() != null && o2.getTotal_qty_i() == null){
						return -1;
					}else if(o1.getTotal_qty_i() == null && o2.getTotal_qty_i() != null){
						return 1;
					}else {
						return 0;
					}
				}
			});
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setSupname("其他");
				on.setBadcount(sumcount-badcount);
				on.setTonum(0);
				on.setTotal_qty_i(BigDecimal.valueOf(0));
				nList.add(on);
			}else{
				badcount += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				on.setTonum(Integer.valueOf((bingMap.get(on.getSupcode())==null? 0 : bingMap.get(on.getSupcode()))+""));
				on.setTotal_qty_i(BigDecimal.valueOf((bingMap.get(on.getSupcode())==null? 0 : bingMap.get(on.getSupcode()))));
				nList.add(on);
			}
		}
		badcount = 0;
		bingMap.clear();
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getSupname());
			yList.add(nList.get(i).getBadcount());
			if(nList.get(i).getTonum()==0){
				rateyList.add(0.0);
			}else{
				rateyList.add(MathUtil.round((nList.get(i).getBadcount()/nList.get(i).getTonum())*100, 4));
			}
			badcount=badcount+nList.get(i).getBadcount();
			double d=0.0;
			if(sumcount!=0.0){
				d=(badcount/sumcount)*100;
			}
			String s=df.format(d);
			rateyList2.add(Double.valueOf(s));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getSupcode()+","+nList.get(i).getSupname()+";");
			if(nList.get(i).getSupname() != null){
				bingMap.put(nList.get(i).getSupname(), nList.get(i).getTotal_qty_i().longValue());
				numberMap.put(nList.get(i).getSupname(), nList.get(i).getSupcode());
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getSupname());
//			yList.add(list.get(i).getBadcount());
//			if(count!=0){
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
//			badcount=badcount+list.get(i).getBadcount();
//			double d=0.0;
//			if(sumcount!=0.0){
//				d=(badcount/sumcount)*100;
//			}
//			String s=df.format(d);
//			rateyList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getAccount_key()+","+list.get(i).getSupcode()+","+list.get(i).getSupname()+";");
//		    //----
//			list.get(i).setTotal_qty_i(BigDecimal.valueOf(count));
//		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(rateyList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
//		chart.setOnlineList(list);
		chart.setOnlineList(nList);
		chart.setNumberMap(numberMap);
		return chart;
	}
	/**
	 * 零部件不良数/率 更换
	 * @param o
	 * @return
	 */
	private EChartObj getChart7(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;

		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		//编号map
		Map<String,String> numberMap = new HashMap<String, String>();
		
//		StringBuilder sb=new StringBuilder("select * from (select z.part_key,z.new_part_number as productnumber,z.part_name as productname,count(1) as badcount from t_replace_part z where 1=1");
		//是否带版本----
		StringBuilder sb = new StringBuilder();
		if(TmStringUtils.isNotEmpty(o.getPartVersion()) && "否".equals(o.getPartVersion())){
		    sb.append("select z.part_key,substr(z.new_part_number,0,length(z.new_part_number)-1) as productnumber,z.part_name as productname,count(1) as badcount from t_replace_part z where 1=1");
		}else{
			sb.append("select z.part_key,z.new_part_number as productnumber,z.part_name as productname,count(1) as badcount from t_replace_part z where 1=1");
		}
		
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and z.modeltype in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and z.modeltype = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and z.replacetime >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and z.replacetime <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//--
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and z.part_level='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and z.account_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本----
		if(TmStringUtils.isNotEmpty(o.getPartVersion()) && "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and z.maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and z.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and z.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
//		sb.append(" group by z.part_key,z.new_part_number,z.part_name order by badcount desc) where rownum<= "+o.getColumnNum()+"");
		//是否带版本---
		if("否".equals(o.getPartVersion())){
			sb.append(" group by z.part_key,substr(z.new_part_number,0,length(z.new_part_number)-1),z.part_name order by badcount desc ");
		}else{
			sb.append(" group by z.part_key,z.new_part_number,z.part_name order by badcount desc");
		}
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle(o.getDateT()+o.getDateType()+"零部件在线更换不良数/率排列图");
		chart.setBarWidth(o.getColumnNum() >= 15 ? "30" :"50");
		chart.setSeriesType(new String[]{"bar","line","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率","累计不良率"};
		String[] axisIndex ={"0","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#d14a61","#675bba"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<Double> rateyList2 = new ArrayList<Double>();
		//排列图数量----
		int columnNum = o.getColumnNum();
		//插入数据到bindMap
		for(OnlineModel on : bindList){
			if(on.getPart_number() != null){
				bingMap.put(on.getPart_number(), on.getTotal_qty_i().longValue());
			}
		}
		//如果不带版本，则对list进行筛选
		if("否".equals(o.getPartVersion())){
			getPartVersionList(list);
		}
		
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		
		double badcount=0.0;
		double sumcount=0.0;
		DecimalFormat df=new DecimalFormat("######0.00");
		for (int i = 0; i < list.size(); i++) {
			sumcount=sumcount+list.get(i).getBadcount();
			
			//----
//			bingMap.put(list.get(i).getProductname(), Long.valueOf(count));
		}
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		//如果是按不良率排序
		if("不良率".equals(o.getSordType())){
			for(OnlineModel m1 : list){
				for(OnlineModel m2 : bindList){
					if(m1.getProductnumber() != null && m1.getProductnumber().equals(m2.getPart_number())){
						m1.setTotal_qty_i(m2.getTotal_qty_i());
					}
				}
			}
			Collections.sort(list, new Comparator<OnlineModel>() {
				@Override
				public int compare(OnlineModel o1, OnlineModel o2) {
					if(o1.getTotal_qty_i() != null && o2.getTotal_qty_i() != null){
						 return o1.getBadcount()/o1.getTotal_qty_i().doubleValue()>o2.getBadcount()/o2.getTotal_qty_i().doubleValue() ? -1 : 1 ; 
					}else if(o1.getTotal_qty_i() != null && o2.getTotal_qty_i() == null){
						return -1;
					}else if(o1.getTotal_qty_i() == null && o2.getTotal_qty_i() != null){
						return 1;
					}else {
						return 0;
					}
				}
			});
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setProductname("其他");
				on.setBadcount(sumcount-badcount);
				on.setTonum(0);
				on.setTotal_qty_i(BigDecimal.valueOf(0));
				nList.add(on);
			}else{
				badcount += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				on.setTonum(Integer.valueOf((bingMap.get(on.getProductnumber())==null? 0 : bingMap.get(on.getProductnumber()))+""));
				on.setTotal_qty_i(BigDecimal.valueOf((bingMap.get(on.getProductnumber())==null? 0 : bingMap.get(on.getProductnumber()))));
				nList.add(on);
			}
		}
		badcount = 0;
		bingMap.clear();
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getProductname());
			yList.add(nList.get(i).getBadcount());
			if(nList.get(i).getTonum()==0){
				rateyList.add(0.0);
			}else{
				rateyList.add(MathUtil.round((nList.get(i).getBadcount()/nList.get(i).getTonum())*100, 4));
			}
			badcount=badcount+nList.get(i).getBadcount();
			double d=0.0;
			if(sumcount!=0.0){
				d=(badcount/sumcount)*100;
			}
			String s=df.format(d);
			rateyList2.add(Double.valueOf(s));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getProductnumber()+","+nList.get(i).getProductname()+";");
			if(nList.get(i).getProductname() != null){
				bingMap.put(nList.get(i).getProductname(), nList.get(i).getTotal_qty_i().longValue());
				numberMap.put(nList.get(i).getProductname(), nList.get(i).getProductnumber());
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getProductname());
//			yList.add(list.get(i).getBadcount());
//			if(count!=0){
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
//			badcount=badcount+list.get(i).getBadcount();
//			double d=0.0;
//			if(sumcount!=0.0){
//				d=(badcount/sumcount)*100;
//			} 
//			String s=df.format(d);
//			rateyList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getPart_key()+","+list.get(i).getProductnumber()+","+list.get(i).getProductname()+";");
//			//----
//			list.get(i).setTotal_qty_i(BigDecimal.valueOf(count));
//		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(rateyList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		chart.setNumberMap(numberMap);
		return chart;
	}

	/**
	 * 不良现象不良数/率 更换
	 * @param o
	 * @return
	 */
	private EChartObj getChart8(OnlineModel o){
		EChartObj chart=new EChartObj();
		int count=0;
		
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
//		StringBuilder sb=new StringBuilder("select * from (select z.defect_name as badphenomenon,count(1) as badcount from t_replace_part z where 1=1");
		//----
		StringBuilder sb=new StringBuilder("select z.defect_name as badphenomenon,count(1) as badcount from t_replace_part z where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and z.modeltype in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and z.modeltype = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and z.replacetime >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') >='"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and z.replacetime <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and z.part_level='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and z.account_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and z.maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and z.consumption_type ='SerialNumber'");
			Integer val=onlineMapper.getbingTotal(o);
			count=val==null?0:val;
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and z.consumption_type <>'SerialNumber'");
			Integer val=onlineMapper.getInTotal(o);
			count=val==null?0:val;
		}
//		sb.append(" group by z.defect_name order by badcount desc) where rownum<= "+o.getColumnNum()+"");
		//----
		sb.append(" group by z.defect_name order by badcount desc ");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("不良现象不良数/率排列图");
		chart.setBarWidth(o.getColumnNum() >= 15 ? "30" :"50");
		chart.setSeriesType(new String[]{"bar","line","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率","累计不良率"};
		String[] axisIndex ={"0","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#d14a61","#675bba"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<Double> rateyList2 = new ArrayList<Double>();
		//排列图数量----
		int columnNum = o.getColumnNum();
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		
		double badcount=0.0;
		double sumcount=0.0;
		DecimalFormat df=new DecimalFormat("######0.00");
		for (int i = 0; i < list.size(); i++) {
			sumcount=sumcount+list.get(i).getBadcount();
			//----
			bingMap.put(list.get(i).getBadphenomenon(), Long.valueOf(count));
		}
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setBadphenomenon("其他不良现象");
				on.setBadcount(sumcount-badcount);
				on.setTonum(0);
				on.setTotal_qty_i(BigDecimal.valueOf(0));
				nList.add(on);
			}else{
				badcount += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				on.setTonum(count);
				on.setTotal_qty_i(BigDecimal.valueOf(count));
				nList.add(on);
			}
		}
		badcount = 0;
		bingMap.clear();
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getBadphenomenon());
			yList.add(nList.get(i).getBadcount());
			if(nList.get(i).getTonum()==0){
				rateyList.add(0.0);
			}else{
				rateyList.add(MathUtil.round((nList.get(i).getBadcount()/nList.get(i).getTonum())*100, 4));
			}
			badcount=badcount+nList.get(i).getBadcount();
			double d=0.0;
			if(sumcount!=0.0){
				d=(badcount/sumcount)*100;
			}
			String s=df.format(d);
			rateyList2.add(Double.valueOf(s));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getProductnumber()+","+nList.get(i).getProductname()+";");
			if(nList.get(i).getBadphenomenon() != null){
				bingMap.put(nList.get(i).getBadphenomenon(), nList.get(i).getTotal_qty_i().longValue());
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getBadphenomenon());
//			yList.add(list.get(i).getBadcount());
//			//Double dou=list.get(i).getCount();
//			if(count!=0){
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
//			badcount=badcount+list.get(i).getBadcount();
//			double d=0.0;
//			if(sumcount!=0.0){
//				d=(badcount/sumcount)*100;
//			}
//			String s=df.format(d);
//			rateyList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getBadphenomenon());
//			//----
//			list.get(i).setTotal_qty_i(BigDecimal.valueOf(count));
//		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(rateyList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		return chart;
	}
	
	/**
	 * 供应商不良数/率 更换 趋势
	 * @param o
	 * @return
	 */
	private EChartObj getChart9(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;
		
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
		StringBuilder sb=new StringBuilder("select * from (select to_char(z.replacetime,'"+o.getDate_type()+"') as date_t,count(1) as badcount from t_replace_part z where 1=1 ");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and z.modeltype in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and z.modeltype = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and z.replacetime >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and z.replacetime <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");			
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and z.part_level='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and z.account_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and z.maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and z.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and z.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
		sb.append(" group by to_char(z.replacetime,'"+o.getDate_type()+"') order by date_t desc) where rownum<= "+o.getColumnNum()+"");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("供应商不良数/率趋势图");
		chart.setBarWidth(o.getColumnNum() >= 15 ? "30" :"50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		String[] axisIndex ={"0","1","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#d14a61","#675bba","#9999ff"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		//List<String> Value = new ArrayList<String>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<String> datelist=getdateList(o);
		list=setModelList(list,datelist);
		//插入数据到bindMap---
		for(OnlineModel on : bindList){
			bingMap.put(on.getDate_t(), on.getTotal_qty_i().longValue());
		}
		//三十个月绑定数----
		String time=o.getEnddate();
		o=setupVo2(o,time);
		Integer a=onlineMapper.getPrimaryTotal(o);//获取30个月绑定数
		int bingInteger=a==null?0:a;//当前日期维度的入库数
		Integer c=onlineMapper.getRTotal(o);
		int TotalInteger=c==null?0:c;//30个月的总更换数
		
		
		for (int i = 0; i < list.size(); i++) {
			if(o.getDateType().equals("周")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7))+1;
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				System.out.println(year+":"+week);
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(weekstr, Long.valueOf(count));
			}else {
				xValue.add(list.get(i).getDate_t());
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(list.get(i).getDate_t(), Long.valueOf(count));
			}
//			if(count!=0){
//				DecimalFormat df=new DecimalFormat("######0.00");
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
			if(bingMap.get(list.get(i).getDate_t()) != null && bingMap.get(list.get(i).getDate_t()) != 0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/bingMap.get(list.get(i).getDate_t()))*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			
//			String time=list.get(i).getDate_t();
//			o=setupVo(o,time);
//			Integer a=onlineMapper.getStorageTotal(o);
//			int SameInteger=a==null?0:a;//当前日期维度的入库数
//			o=setupVo2(o,time);
//			Integer b=onlineMapper.getStorageTotal(o);
//			int StorageInteger=b==null?0:b;//30个月的入库数
//			Integer c=onlineMapper.getRTotal(o);
//			int TotalInteger=c==null?0:c;//30个月的总更换数
//			Double p=new Double(TotalInteger/(double)StorageInteger);//计算公式参数p
//			Double q=Math.pow(p*(1-p),1.0/3)/SameInteger;	
//			if(new Double((p+q)).isNaN()){
//				conList.add(0.00);
//			}else {
//				conList.add(( ( int )( (p+q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
//			if(new Double((p-q)).isNaN()){
//				conList2.add(0.00);
//			}else {
//				conList2.add(( ( int )( (p-q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
			//------
			if(bingMap.get(list.get(i).getDate_t())!= null && bingMap.get(list.get(i).getDate_t()) != 0 && bingInteger != 0){
				Double p = Double.valueOf(TotalInteger)/Double.valueOf(bingInteger);
				Double q = 3*Math.sqrt(p*(1-p))/Math.sqrt(bingMap.get(list.get(i).getDate_t()));
					conList.add(MathUtil.round((p+q)*100,4));
				if(new Double((p-q)).isNaN()){
					conList2.add(0.00);
				}else {
					conList2.add(0.00);
				}
			}else{
				conList.add(0.00);
				conList2.add(0.00);
			}
			
			//----
			list.get(i).setTotal_qty_i(BigDecimal.valueOf(bingMap.get(list.get(i).getDate_t())==null ? 0 : bingMap.get(list.get(i).getDate_t())));
		}
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		ylist.add(conList);
		ylist.add(conList2);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		return chart;
	}
	
	/**
	 * 零部件不良数/率 更换 趋势
	 * @param o
	 * @return
	 */
	private EChartObj getChart10(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;

		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
		StringBuilder sb=new StringBuilder("select * from (select to_char(z.replacetime,'"+o.getDate_type()+"') as date_t,count(1) as badcount from t_replace_part z where 1=1 ");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and z.modeltype in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and z.modeltype = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and z.replacetime >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and z.replacetime <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and z.part_level='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and z.account_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and z.maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and z.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and z.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
		sb.append(" group by to_char(z.replacetime,'"+o.getDate_type()+"') order by date_t desc) where rownum<= "+o.getColumnNum()+"");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("零部件不良数/率趋势图");
		chart.setBarWidth(o.getColumnNum() >= 15 ? "30" :"50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
//		String[] axisIndex ={"0","1","2","2"};
		String[] axisIndex ={"0","1","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#d14a61","#675bba","#9999ff"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		//List<String> Value = new ArrayList<String>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<String> datelist=getdateList(o);
		list=setModelList(list,datelist);
		
		//插入数据到bindMap---
		for(OnlineModel on : bindList){
			bingMap.put(on.getDate_t(), on.getTotal_qty_i().longValue());
		}
		//三十个月绑定数----
		String time=o.getEnddate();
		o=setupVo2(o,time);
		Integer a=onlineMapper.getPrimaryTotal(o);//获取30个月绑定数
		int bingInteger=a==null?0:a;//当前日期维度的入库数
		Integer c=onlineMapper.getRTotal(o);
		int TotalInteger=c==null?0:c;//30个月的总更换数
		
		for (int i = 0; i < list.size(); i++) {
			if(o.getDateType().equals("周")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7))+1;
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				System.out.println(year+":"+week);
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(weekstr, Long.valueOf(count));
			}else {
				xValue.add(list.get(i).getDate_t());
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(list.get(i).getDate_t(), Long.valueOf(count));
			}
//			if(count!=0){
//				DecimalFormat df=new DecimalFormat("######0.00");
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
			if(bingMap.get(list.get(i).getDate_t()) != null && bingMap.get(list.get(i).getDate_t()) != 0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/bingMap.get(list.get(i).getDate_t()))*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
//			String time=list.get(i).getDate_t();
//			o=setupVo(o,time);
//			Integer a=onlineMapper.getStorageTotal(o);
//			int SameInteger=a==null?0:a;//当前日期维度的入库数
//			o=setupVo2(o,time);
//			Integer b=onlineMapper.getStorageTotal(o);
//			int StorageInteger=b==null?0:b;//30个月的入库数
//			Integer c=onlineMapper.getRTotal(o);
//			int TotalInteger=c==null?0:c;//30个月的总更换数
//			Double p=new Double(TotalInteger/(double)StorageInteger);//计算公式参数p
//			Double q=Math.pow(p*(1-p),1.0/3)/SameInteger;	
////			conList.add(p+q);
////			conList2.add(p-q);
//			if(new Double((p+q)).isNaN()){
//				conList.add(0.00);
//			}else {
//				conList.add(( ( int )( (p+q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
//			if(new Double((p-q)).isNaN()){
//				conList2.add(0.00);
//			}else {
//				conList2.add(( ( int )( (p-q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
			//------
			if(bingMap.get(list.get(i).getDate_t())!= null && bingMap.get(list.get(i).getDate_t()) != 0 && bingInteger != 0){
				Double p = Double.valueOf(TotalInteger)/Double.valueOf(bingInteger);
				Double q = 3*Math.sqrt(p*(1-p))/Math.sqrt(bingMap.get(list.get(i).getDate_t()));
					conList.add(MathUtil.round((p+q)*100,4));
				if(new Double((p-q)).isNaN()){
					conList2.add(0.00);
				}else {
					conList2.add(0.00);
				}
			}else{
				conList.add(0.00);
				conList2.add(0.00);
			}
			//----
			list.get(i).setTotal_qty_i(BigDecimal.valueOf(bingMap.get(list.get(i).getDate_t())==null ? 0 : bingMap.get(list.get(i).getDate_t())));
		}
		
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		ylist.add(conList);
		ylist.add(conList2);
		//chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		return chart;
	}
	/**
	 * 供应商不良数/率 退次
	 * @param o
	 * @return
	 */
	private EChartObj getChart11(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;
		
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//供应商绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
		//编号map
		Map<String,String> numberMap = new HashMap<String, String>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
//		StringBuilder sb=new StringBuilder("select * from (select a.account_key,a.supplier_number_n as numbers,a.supplier_name as supname,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		//---
		StringBuilder sb=new StringBuilder("select a.account_key,a.supplier_number_n as numbers,a.supplier_name as supname,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.production_type in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.production_type = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.return_date >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.return_date <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_class='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number_n in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(a.part_number_n ,0,length(a.part_number_n )-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and a.part_number_n  in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.product_maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and a.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and a.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
//		sb.append(" group by a.account_key,a.supplier_number_n,a.supplier_name order by badcount desc) where rownum<= "+o.getColumnNum()+"");
		//----
		sb.append(" group by a.account_key,a.supplier_number_n,a.supplier_name order by badcount desc");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("供应商不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率","累计不良率"};
		String[] axisIndex ={"0","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#675bba","#d14a61"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<Double> rateyList2 = new ArrayList<Double>();
		//排列图数量----
		int columnNum = o.getColumnNum();
		//插入数据到bindMap
		for(OnlineModel on : bindList){
			if(on.getAccount_name() != null){
				bingMap.put(on.getAccount_name(), on.getTotal_qty_i().longValue());
			}
		}
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		
		double badcount=0.0;
		double sumcount=0.0;
		DecimalFormat df=new DecimalFormat("######0.00");
		for (int i = 0; i < list.size(); i++) {
			sumcount=sumcount+list.get(i).getBadcount();
			//----
//			bingMap.put(list.get(i).getSupname(), Long.valueOf(count));
		}
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setSupname("其他");
				on.setBadcount(sumcount-badcount);
				on.setTonum(0);
				on.setTotal_qty_i(BigDecimal.valueOf(0));
				nList.add(on);
			}else{
				badcount += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				on.setTonum(Integer.valueOf((bingMap.get(on.getNumbers())==null? 0 : bingMap.get(on.getNumbers()))+""));
				on.setTotal_qty_i(BigDecimal.valueOf((bingMap.get(on.getNumbers())==null? 0 : bingMap.get(on.getNumbers()))));
				nList.add(on);
			}
		}
		badcount = 0;
		bingMap.clear();
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getSupname());
			yList.add(nList.get(i).getBadcount());
			if(nList.get(i).getTonum()==0){
				rateyList.add(0.0);
			}else{
				rateyList.add(MathUtil.round((nList.get(i).getBadcount()/nList.get(i).getTonum())*100, 4));
			}
			badcount=badcount+nList.get(i).getBadcount();
			double d=0.0;
			if(sumcount!=0.0){
				d=(badcount/sumcount)*100;
			}
			String s=df.format(d);
			rateyList2.add(Double.valueOf(s));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getNumbers()+","+nList.get(i).getSupname()+";");
			if(nList.get(i).getSupname() != null){
				bingMap.put(nList.get(i).getSupname(), nList.get(i).getTotal_qty_i().longValue());
				numberMap.put(nList.get(i).getSupname(), nList.get(i).getNumbers());
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getSupname());
//			yList.add(list.get(i).getBadcount());
//			if(count!=0){
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
//			badcount=badcount+list.get(i).getBadcount();
//			double d=0.0;
//			if(sumcount!=0.0){
//				d=(badcount/sumcount)*100;
//			}
//			String s=df.format(d);
//			rateyList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getAccount_key()+","+list.get(i).getNumbers()+","+list.get(i).getSupname()+";");
//			//----
//			list.get(i).setTotal_qty_i(BigDecimal.valueOf(count));
//		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(rateyList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(nList);
		chart.setNumberMap(numberMap);
		return chart;
	}
	/**
	 * 零部件不良数/率 退次
	 * @param o
	 * @return
	 */
	private EChartObj getChart12(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		//供应商绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
		//编号map
		Map<String,String> numberMap = new HashMap<String, String>();
		
//		StringBuilder sb=new StringBuilder(" select * from (select a.part_key,a.part_number_n as productnumber,a.part_name as productname,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		//是否带版本
		StringBuilder sb = new StringBuilder();
		if("否".equals(o.getPartVersion())){
			sb.append(" select a.part_key,substr(a.part_number_n,0,length(a.part_number_n)-1) as productnumber,a.part_name as productname,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		}else{
			sb.append(" select a.part_key,a.part_number_n as productnumber,a.part_name as productname,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		}
		//如果工厂不为空，机型为空,则查询该工厂下的所有机型
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.production_type in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.production_type = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.return_date >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//------
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.return_date <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_class='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number_n in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(a.part_number_n ,0,length(a.part_number_n )-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and a.part_number_n  in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.product_maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and a.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//---
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and a.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//-----
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
//		sb.append(" group by a.part_key,a.part_number_n,a.part_name order by badcount desc) where rownum<="+o.getColumnNum()+"");
		if("否".equals(o.getPartVersion())){
			sb.append(" group by a.part_key,substr(a.part_number_n,0,length(a.part_number_n)-1),a.part_name order by badcount desc");
		}else{
			sb.append(" group by a.part_key,a.part_number_n,a.part_name order by badcount desc");
		}
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle(o.getDateT()+o.getDateType()+"零部件在线退次不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率","累计不良率"};
		String[] axisIndex ={"0","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#675bba","#d14a61"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<Double> rateyList2 = new ArrayList<Double>();
		//排列图数量----
		int columnNum = o.getColumnNum();
		//插入数据到bindMap
		for(OnlineModel on : bindList){
			if(on.getPart_number() != null){
				bingMap.put(on.getPart_number(), on.getTotal_qty_i().longValue());
			}
		}
		//重构list
		List<OnlineModel> nList = new ArrayList<OnlineModel>();
		double badcount=0.0;
		double sumcount=0.0;
		DecimalFormat df=new DecimalFormat("######0.00");
		for (int i = 0; i < list.size(); i++) {
			sumcount=sumcount+list.get(i).getBadcount();
			//----
//			bingMap.put(list.get(i).getProductname(), Long.valueOf(count));
		}
		//如果list的长度大于colunmNum----
		boolean isMore = false;
		if( columnNum >=list.size()  ){
			columnNum = list.size();
		}else{
			isMore = true;
			columnNum = columnNum+1;
		}
		for(int i=0; i<columnNum; i++){
			if(isMore && i==columnNum-1){
				OnlineModel on = new OnlineModel();
				on.setProductname("其他");
				on.setBadcount(sumcount-badcount);
				on.setTonum(0);
				on.setTotal_qty_i(BigDecimal.valueOf(0));
				nList.add(on);
			}else{
				badcount += list.get(i).getBadcount();
				OnlineModel on = list.get(i);
				on.setTonum(Integer.valueOf((bingMap.get(on.getProductnumber())==null? 0 : bingMap.get(on.getProductnumber()))+""));
				on.setTotal_qty_i(BigDecimal.valueOf((bingMap.get(on.getProductnumber())==null? 0 : bingMap.get(on.getProductnumber()))));
				nList.add(on);
			}
		}
		badcount = 0;
		bingMap.clear();
		for (int i = 0; i < nList.size(); i++) {
			xValue.add(nList.get(i).getProductname());
			yList.add(nList.get(i).getBadcount());
			if(nList.get(i).getTonum()==0){
				rateyList.add(0.0);
			}else{
				rateyList.add(MathUtil.round((nList.get(i).getBadcount()/nList.get(i).getTonum())*100, 4));
			}
			badcount=badcount+nList.get(i).getBadcount();
			double d=0.0;
			if(sumcount!=0.0){
				d=(badcount/sumcount)*100;
			}
			String s=df.format(d);
			rateyList2.add(Double.valueOf(s));
			Value.add(nList.get(i).getAccount_key()+","+nList.get(i).getProductnumber()+","+nList.get(i).getProductname()+";");
			if(nList.get(i).getProductname() != null){
				bingMap.put(nList.get(i).getProductname(), nList.get(i).getTotal_qty_i().longValue());
				numberMap.put(nList.get(i).getProductname(), nList.get(i).getProductnumber());
			}
		}
//		for (int i = 0; i < list.size(); i++) {
//			xValue.add(list.get(i).getProductname());
//			yList.add(list.get(i).getBadcount());
//			//Double dou=list.get(i).getCount();
//			if(count!=0){
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
//			badcount=badcount+list.get(i).getBadcount();
//			double d=0.0;
//			if(sumcount!=0.0){
//				d=(badcount/sumcount)*100;
//			}
//			String s=df.format(d);
//			rateyList2.add(Double.valueOf(s));
//			Value.add(list.get(i).getPart_key()+","+list.get(i).getProductnumber()+","+list.get(i).getProductname()+";");
//			//----
//			list.get(i).setTotal_qty_i(BigDecimal.valueOf(count));
//		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(rateyList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(nList);
		chart.setNumberMap(numberMap);
		return chart;
	}
	/**
	 * 不良现象不良数/率 退次
	 * @param o
	 * @return
	 */
	private EChartObj getChart13(OnlineModel o){
		EChartObj chart=new EChartObj();
		int count=0;
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
		StringBuilder sb=new StringBuilder("select * from (select a.defect_s as badphenomenon,sum(a.defect_qty_i) as badcount from t_return_ware a where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.production_type in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.line_s = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.date_t,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.date_t,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_level_s='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(a.part_number ,0,length(a.part_number )-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and a.part_number  in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.is_new_s = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and a.consumption_type ='SerialNumber'");
			Integer val=onlineMapper.getbingTotal(o);
			count=val==null?0:val;
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and a.consumption_type <>'SerialNumber'");
			Integer val=onlineMapper.getInTotal(o);
			count=val==null?0:val;
		}
		sb.append(" group by a.defect_s order by badcount desc) where rownum<= "+o.getColumnNum()+"");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("不良现象不良数/率排列图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line"} );                //设置系列类型
		String[] seriesNames ={"不良数","不良率","累计不良率"};
		String[] axisIndex ={"0","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#675bba","#d14a61"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		List<String> Value = new ArrayList<String>();
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<Double> rateyList2 = new ArrayList<Double>();
		double badcount=0.0;
		double sumcount=0.0;
		DecimalFormat df=new DecimalFormat("######0.00");
		for (int i = 0; i < list.size(); i++) {
			sumcount=sumcount+list.get(i).getBadcount();
			//----
			bingMap.put(list.get(i).getBadphenomenon(), Long.valueOf(count));
		}
		for (int i = 0; i < list.size(); i++) {
			xValue.add(list.get(i).getBadphenomenon());
			yList.add(list.get(i).getBadcount());
			if(count!=0){
				double d=(list.get(i).getBadcount()/count)*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
			badcount=badcount+list.get(i).getBadcount();
			double d=0.0;
			if(sumcount!=0.0){
				d=(badcount/sumcount)*100;
			}
			String s=df.format(d);
			rateyList2.add(Double.valueOf(s));
			Value.add(list.get(i).getBadphenomenon());
		}
		ylist.add(yList);
		ylist.add(rateyList);
		ylist.add(rateyList2);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		return chart;
	}
	/**
	 * 供应商不良数/率 退次 趋势
	 * @param o
	 * @return
	 */
	private EChartObj getChart14(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;
		
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
		StringBuilder sb=new StringBuilder("select * from (select to_char(a.return_date,'"+o.getDate_type()+"') as date_t,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.production_type in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.production_type = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.return_date >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.return_date <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_class='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number_n in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(a.part_number_n ,0,length(a.part_number_n )-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and a.part_number_n  in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.product_maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and a.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and a.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//---
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
		sb.append(" group by to_char(a.return_date,'"+o.getDate_type()+"') order by date_t asc) where rownum<= "+o.getColumnNum()+"");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("供应商不良数/率趋势图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		String[] axisIndex ={"0","1","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#675bba","#d14a61","#9999ff"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		//List<String> Value = new ArrayList<String>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<String> datelist=getdateList(o);
		list=setModelList(list,datelist);
		//插入数据到bindMap---
		for(OnlineModel on : bindList){
			bingMap.put(on.getDate_t(), on.getTotal_qty_i().longValue());
		}
		//三十个月绑定数----
		String time=o.getEnddate();
		o=setupVo2(o,time);
		Integer a=onlineMapper.getStorageTotal(o);//获取30个月入库数
		int bingInteger=a==null?0:a;//当前日期维度的入库数
		Integer c=onlineMapper.getTotal(o);
		int TotalInteger=c==null?0:c;//30个月的总生产退次数
		
		for (int i = 0; i < list.size(); i++) {
			if(o.getDateType().equals("周")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7))+1;
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				System.out.println(year+":"+week);
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(weekstr, Long.valueOf(count));
			}else {
				xValue.add(list.get(i).getDate_t());
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(list.get(i).getDate_t(), Long.valueOf(count));
			}
//			if(count!=0){
//				DecimalFormat df=new DecimalFormat("######0.00");
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
			//----
			if(bingMap.get(list.get(i).getDate_t()) != null && bingMap.get(list.get(i).getDate_t()) != 0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/bingMap.get(list.get(i).getDate_t()))*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
//			String time=list.get(i).getDate_t();
//			o=setupVo(o,time);
//			Integer a=onlineMapper.getStorageTotal(o);
//			int SameInteger=a==null?0:a;//当前日期维度的入库数
//			o=setupVo2(o,time);
//			Integer b=onlineMapper.getStorageTotal(o);
//			int StorageInteger=b==null?0:b;//30个月的入库数
//			Integer c=onlineMapper.getTotal(o);
//			int TotalInteger=c==null?0:c;//30个月的总退次数
//			Double p= new Double(TotalInteger/(double)StorageInteger);//计算公式参数p
//			Double q=java.lang.Math.sqrt(p*(1-p))/SameInteger;	
////			conList.add(p+q);
////			conList2.add(p-q);
//			if(new Double((p+q)).isNaN()){
//				conList.add(0.00);
//			}else {
//				conList.add(( ( int )( (p+q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
//			if(new Double((p-q)).isNaN()){
//				conList2.add(0.00);
//			}else {
//				conList2.add(( ( int )( (p-q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
			//------
			if(bingMap.get(list.get(i).getDate_t())!= null && bingMap.get(list.get(i).getDate_t()) != 0 && bingInteger != 0){
				Double p = Double.valueOf(TotalInteger)/Double.valueOf(bingInteger);
				Double q = 3*Math.sqrt(p*(1-p))/Math.sqrt(bingMap.get(list.get(i).getDate_t()));
					conList.add(MathUtil.round((p+q)*100,4));
				if(new Double((p-q)).isNaN()){
					conList2.add(0.00);
				}else {
					conList2.add(0.00);
				}
			}else{
				conList.add(0.00);
				conList2.add(0.00);
			}
			
			//----
			list.get(i).setTotal_qty_i(BigDecimal.valueOf(bingMap.get(list.get(i).getDate_t())==null ? 0 : bingMap.get(list.get(i).getDate_t())));
		}
		
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		ylist.add(conList);
		ylist.add(conList2);
		//chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		return chart;
	}
	/**
	 * 零部件不良数/率 退次 趋势
	 * @param o
	 * @return
	 */
	private EChartObj getChart15(OnlineModel o){
		EChartObj chart=new EChartObj();
//		int count=0;
		String arr[]=new String[]{};
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			arr=o.getSupplierCode().split(",");
		}
		List<String> arrList = Arrays.asList(arr);
		o.setStrList(arrList);
		
		String arr2[]=new String[]{};
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			arr2=o.getPartNumber().split(",");
			if("否".equals(o.getPartVersion())){
				arr2=o.getPartNumber().split(",");
				for(String str : arr2){
					str = str.substring(0, str.length()-1);
				}
			}else{
				arr2=o.getPartNumber().split(",");
			}
		}
		List<String> arrList2 = Arrays.asList(arr2);
		o.setStrList2(arrList2);
		//绑定----
		List<OnlineModel> bindList = new ArrayList<OnlineModel>();
//		//map<供应商，绑定数>-----
		Map<String,Long> bingMap = new HashMap<String, Long>();
		
		StringBuilder sb=new StringBuilder(" select * from (select to_char(a.return_date,'"+o.getDate_type()+"') as date_t,sum(a.return_number) as badcount from ERP_ASSEMBLE_PRODUCT_RETURN a where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.production_type in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.production_type = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.return_date >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.return_date <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_class='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number_n in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否版本---
		if( "否".equals(o.getPartVersion())){
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and substr(a.part_number_n ,0,length(a.part_number_n )-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}else{
			if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
				sb.append(" and a.part_number_n  in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.product_maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and a.consumption_type ='SerialNumber'");
//			Integer val=onlineMapper.getbingTotal(o);
//			count=val==null?0:val;
			//---
			bindList = onlineMapper.getPrimaryPartList(o);
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and a.consumption_type <>'SerialNumber'");
//			Integer val=onlineMapper.getInTotal(o);
//			count=val==null?0:val;
			//----
			bindList = onlineMapper.getUnprimaryKeyPartList(o);
		}
		sb.append(" group by to_char(a.return_date,'"+o.getDate_type()+"') order by date_t asc) where rownum<= "+o.getColumnNum()+"");
		List<OnlineModel> list=onlineMapper.getEChartObj(sb.toString());
		chart.setTitle("零部件不良数/率趋势图");
		chart.setBarWidth("50");
		chart.setSeriesType(new String[]{"bar","line","line","line"});                //设置系列类型
		String[] seriesNames ={"不良数","不良率","上控制线","下控制线"};
		String[] axisIndex ={"0","1","1","1"};
		chart.setyAxisIndex(axisIndex);
		chart.setColors(new String[]{"#3398DB","#675bba","#d14a61","#9999ff"});
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		List<String> xValue = new ArrayList<String>();
		//List<String> Value = new ArrayList<String>();
		List<Double> conList=new ArrayList<Double>();//上控制线
		List<Double> conList2=new ArrayList<Double>();//下控制线
		List<Double> yList = new ArrayList<Double>();
		List<Double> rateyList = new ArrayList<Double>();
		List<String> datelist=getdateList(o);
		list=setModelList(list,datelist);
		//插入数据到bindMap---
		for(OnlineModel on : bindList){
			bingMap.put(on.getDate_t(), on.getTotal_qty_i().longValue());
		}
		//三十个月----
		String time=o.getEnddate();
		o=setupVo2(o,time);
		Integer a=onlineMapper.getStorageTotal(o);//获取30个月入库数
		int bingInteger=a==null?0:a;//当前日期维度的入库数
		Integer c=onlineMapper.getTotal(o);
		int TotalInteger=c==null?0:c;//30个月的总生产退次数
		
		for (int i = 0; i < list.size(); i++) {
			if(o.getDateType().equals("周")){
				Integer week=Integer.parseInt(list.get(i).getDate_t().substring(5, 7))+1;
				Integer year=Integer.parseInt(list.get(i).getDate_t().substring(0, 4));
				System.out.println(year+":"+week);
				String weekstr=getDayOfWeek(year,week);
				xValue.add(weekstr);
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(weekstr, Long.valueOf(count));
			}else {
				xValue.add(list.get(i).getDate_t());
				yList.add(list.get(i).getBadcount());
				//----
//				bingMap.put(list.get(i).getDate_t(), Long.valueOf(count));
			}
//			if(count!=0){
//				DecimalFormat df=new DecimalFormat("######0.00");
//				double d=(list.get(i).getBadcount()/count)*100; 
//				String s=df.format(d);
//				rateyList.add(Double.valueOf(s));
//			}else {
//				rateyList.add(0.00);
//			}
			//----
			if(bingMap.get(list.get(i).getDate_t()) != null && bingMap.get(list.get(i).getDate_t()) != 0){
				DecimalFormat df=new DecimalFormat("######0.00");
				double d=(list.get(i).getBadcount()/bingMap.get(list.get(i).getDate_t()))*100; 
				String s=df.format(d);
				rateyList.add(Double.valueOf(s));
			}else {
				rateyList.add(0.00);
			}
//			String time=list.get(i).getDate_t();
//			o=setupVo(o,time);
//			int SameInteger=onlineMapper.getStorageTotal(o)==null?0:onlineMapper.getStorageTotal(o);//当前日期维度的入库数
//			o=setupVo2(o,time);
//			int StorageInteger=onlineMapper.getStorageTotal(o)==null?0:onlineMapper.getStorageTotal(o);//30个月的入库数
//			int TotalInteger=onlineMapper.getTotal(o)==null?0:onlineMapper.getTotal(o);//30个月的总退次数
//			Double p= new Double(TotalInteger/(double)StorageInteger);//计算公式参数p
//			Double q=java.lang.Math.sqrt(p*(1-p))/SameInteger;	
////			conList.add(p+q);
////			conList2.add(p-q);
//			if(new Double((p+q)).isNaN()){
//				conList.add(0.00);
//			}else {
//				conList.add(( ( int )( (p+q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
//			if(new Double((p-q)).isNaN()){
//				conList2.add(0.00);
//			}else {
//				conList2.add(( ( int )( (p-q) * 1000000 + 0.5 ) ) / 1000000.0);
//			}
			//------
			if(bingMap.get(list.get(i).getDate_t())!= null && bingMap.get(list.get(i).getDate_t()) != 0 && bingInteger != 0){
				Double p = Double.valueOf(TotalInteger)/Double.valueOf(bingInteger);
				Double q = 3*Math.sqrt(p*(1-p))/Math.sqrt(bingMap.get(list.get(i).getDate_t()));
					conList.add(MathUtil.round((p+q)*100,4));
				if(new Double((p-q)).isNaN()){
					conList2.add(0.00);
				}else {
					conList2.add(0.00);
				}
			}else{
				conList.add(0.00);
				conList2.add(0.00);
			}
			//----
			list.get(i).setTotal_qty_i(BigDecimal.valueOf(bingMap.get(list.get(i).getDate_t())==null ? 0 : bingMap.get(list.get(i).getDate_t())));
		}
		
		ylist.add(yList);
		ylist.add(rateyList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		ylist.add(conList);
		ylist.add(conList2);
		//chart.setValue(Value);
		//----
		chart.setMap(bingMap);
		chart.setOnline(o);
		chart.setOnlineList(list);
		return chart;
	}
	/**
	 * 在线批次数据展示
	 */
	@Override
	public List<OnlineModel> getlisttab(OnlineModel o,PageParameter page) {
		finishData(o);
		StringBuilder sb=new StringBuilder("select a.record_date_t as record_date_t,c.mold_type_s as line_s,b.part_number as part_number,b.description,a.product_maturity_s as product_maturity_s,d.account_name as account_name,d.description as description_2,a.defect_s as defect_s,a.defect_qty_i as defect_qty_i,a.total_qty_i as total_qty_i from At_Batchdefectrecord a left join (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,up.control_number_s from part pp left join" 
              +" new_part_ref nn on pp.part_number=nn.old_part_number left join uda_part up on pp.part_key=up.object_key) b on a.part_number_s=b.part_number left join uda_part c on b.part_key=c.object_key left join (select a.account_key,a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number) d on a.supplier_number_s=d.account_name where 1=1 and a.me_s='料'");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and c.mold_type_s in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and c.mold_type_s = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.record_date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.record_date_t,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.record_date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			if("4".equals(o.getCharthid()) || "5".equals(o.getCharthid()) ){
				sb.append(" and to_char(a.record_date_t,'yyyy-mm-dd') < '"+o.getEnddate()+"'");
			}else{
				sb.append(" and to_char(a.record_date_t,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
			}
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and c.part_level_s='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and d.account_name in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			sb.append(" and b.part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.product_maturity_s = ('"+o.getIsNew()+"')");
		}
		if(o.getBadphenomenon()!=null && !o.getBadphenomenon().equals("")){
			sb.append("and a.defect_s=('"+o.getBadphenomenon()+"')");
		}
		if(o.getIscrux().equals("0")){//关键件
			sb.append(" and b.control_number_s ='关键件'");
		}
		if(o.getIscrux().equals("1")){//非关键件
			sb.append(" and b.control_number_s ='非关键件'");
		}
		if(o.getIscrux().equals("2")){//非关键件
			sb.append(" and b.control_number_s ='附件'");
		}
		BaseSearch bs=new BaseSearch();
		bs.put("sql", sb.toString());
		bs.setPage(page);
		return onlineMapper.gettablistPage(bs);
	}
	/**
	 * 在线不良明细(mes)更换
	 */
	@Override
	public List<OnlineModel> getlisttabMes(OnlineModel o, PageParameter page) {
		finishData(o);
		StringBuilder sb=new StringBuilder("select z.abroad,z.product_NO,z.gas,z.defect_d,z.defect_res,z.pl_name,z.defect_b,z.verifying_Con,z.text,z.group_name,z.isnew_end_time_s,z.MAINTENANCE_MODE,z.material_old,z.material_new,z.serial_number,z.modeltype as moldtype,z.account_number_n as supcode,z.account_name as supname,z.part_level as category,z.new_part_number as part_number,z.part_name as description,z.defect_name as defect_s,z.line_s,z.replacetime as date_TT,z.maturity,1 as badnum from t_replace_part z where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and z.modeltype in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and z.modeltype = '"+o.getProductType()+"'");
		}
		if(o.getMaintenanceMode() !=null && !o.getMaintenanceMode().equals("")){
			sb.append(" and z.MAINTENANCE_MODE like '%"+o.getMaintenanceMode()+"%'");
		}
		
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and z.replacetime >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and z.replacetime <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			if("9".equals(o.getCharthid()) || "10".equals(o.getCharthid()) ){
				if("月".equals(o.getDateType())){
					sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
				}else{
					sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') < '"+o.getEnddate()+"'");
				}
			}else{
				sb.append(" and to_char(z.replacetime,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
			}
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and z.part_level='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			if ("无".equals(o.getSupplierCode())) {
				sb.append(" and z.account_number_n is null");
			} else {
				sb.append(" and z.account_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
			}
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否带版本---
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			if("否".equals(o.getPartVersion())){
//				if(o.getDistinction()!=null && 1 == o.getDistinction() ){
//					sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),"")+")");
//				}else{
					sb.append(" and substr(z.new_part_number,0,length(z.new_part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//				}
			}else{
				sb.append(" and z.new_part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and z.maturity = '"+o.getIsNew()+"'");
		}
		if(o.getBadphenomenon()!=null && !o.getBadphenomenon().equals("")){
			sb.append(" and z.defect_name = '"+o.getBadphenomenon()+"'");
		}
		if(o.getIscrux()!=null && !o.getIscrux().equals("")){
			if(o.getIscrux().equals("0")){
				sb.append(" and z.consumption_type ='关键件'");
			}
			if(o.getIscrux().equals("1")){
				sb.append(" and z.consumption_type ='非关键件'");
			}
			if(o.getIscrux().equals("2")){
				sb.append(" and z.consumption_type ='附件'");
			}
			
		}
		BaseSearch bs=new BaseSearch();
		bs.put("sql", sb.toString());
		bs.setPage(page);
		System.out.println("sql:"+sb.toString());
		return onlineMapper.gettablistPage(bs);
	}
	/**
	 * 在线不良明细(mes)退次
	 */
	@Override
	public List<OnlineModel> getlisttabMesR(OnlineModel o, PageParameter page) {
		finishData(o);
		StringBuilder sb=new StringBuilder("select a.date_t as date_TT,a.line_s as moldtype,a.supplier_number as supcode,a.description_sup as supname,a.part_number as part_number,a.description_part as description,a.defect_s,a.defect_qty_i as badnum from t_return_ware a where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.line_s in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.line_s = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.date_t >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//---
			sb.append(" and to_char(a.date_t,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.date_t <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//----
			//----
			if("4".equals(o.getCharthid()) || "5".equals(o.getCharthid()) ){
				if("月".equals(o.getDateType())){
					sb.append(" and to_char(a.date_t,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
				}else{
					sb.append(" and to_char(a.date_t,'yyyy-mm-dd') < '"+o.getEnddate()+"'");
				}
			}else{
				sb.append(" and to_char(a.date_t,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
			}
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_level_s='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否带版本---
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			if("否".equals(o.getPartVersion())){
				if(o.getDistinction()!=null && 1 == o.getDistinction()){
					sb.append(" and substr(a.part_number,0,length(a.part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),"")+")");
				}else{
					sb.append(" and substr(a.part_number,0,length(a.part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
				}
			}else{
				sb.append(" and a.part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.is_new_s = '"+o.getIsNew()+"'");
		}
		if(o.getBadphenomenon()!=null && !o.getBadphenomenon().equals("")){
			sb.append(" and a.defect_s = '"+o.getBadphenomenon()+"'");
		}
		if(o.getIscrux()!=null && !o.getIscrux().equals("")){
			if(o.getIscrux().equals("0")){
				sb.append(" and a.consumption_type ='关键件'");
			}
			if(o.getIscrux().equals("1")){
				sb.append(" and a.consumption_type =='非关键件'");
			}
			if(o.getIscrux().equals("2")){
				sb.append(" and a.consumption_type =='附件'");
			}
		}
		BaseSearch bs=new BaseSearch();
		bs.put("sql", sb.toString());
		bs.setPage(page);
		System.out.println("sql:"+sb.toString());
		return onlineMapper.gettablistPage(bs);
	}
	/**
	 * 在线不良明细ERP(erp组装生产退次)
	 */
	@Override
	public List<OnlineModel> getlisttabErp(OnlineModel o, PageParameter page) {
		finishData(o);
		StringBuilder sb=new StringBuilder("select a.return_date,a.part_number,a.part_name,a.supplier_number,a.supplier_name,a.return_number as return_number,a.ware_house from ERP_ASSEMBLE_PRODUCT_RETURN a left join account b on a.supplier_number=b.account_name left join part c on a.part_number=c.part_number left join uda_part d on c.part_key=d.object_key where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and a.production_type in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and a.production_type = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and a.return_date >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(a.return_date,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and a.return_date <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
			//-----
			if("14".equals(o.getCharthid()) || "15".equals(o.getCharthid())){
				sb.append(" and to_char(a.return_date,'yyyy-mm-dd') < '"+o.getEnddate()+"'");
			}else{
				sb.append(" and to_char(a.return_date,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
			}
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and a.part_class='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number_n in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and a.part_number_n in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否带版本---
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			if("否".equals(o.getPartVersion())){
				if(o.getDistinction()!=null && 1 == o.getDistinction()){
					sb.append(" and substr(a.part_number_n,0,length(a.part_number_n)-1) in ("+getSupStringByComma(o.getPartNumber(),"")+")");
				}else{
					sb.append(" and substr(a.part_number_n,0,length(a.part_number_n)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
				}
			}else{
				sb.append(" and a.part_number_n in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIsNew()!=null && !o.getIsNew().equals("")){
			sb.append(" and a.product_maturity = '"+o.getIsNew()+"'");
		}
		if(o.getIscrux()!=null && !o.getIscrux().equals("")){
			if(o.getIscrux().equals("0")){
				sb.append(" and d.CONTROL_NUMBER_S ='关键件'");
			}
			if(o.getIscrux().equals("1")){
				sb.append(" and d.CONTROL_NUMBER_S ='非关键件'");
			}
			if(o.getIscrux().equals("2")){
				sb.append(" and d.CONTROL_NUMBER_S ='附件'");
			}
			
		}
		BaseSearch bs=new BaseSearch();
		bs.put("sql", sb.toString());
		bs.setPage(page);
		System.out.println("sql:"+sb.toString());
		return onlineMapper.gettablistPage(bs);
	}
	/**
	 * 来料入库
	 */
	@Override
	public List<OnlineModel> getlisttabMesStorage(OnlineModel o,PageParameter page) {
		finishData(o);
		StringBuilder sb=new StringBuilder("select b.udt_2 as arrival,b.part_number as partnumber,c.description as partname,a.supplier_number_s as supcode,a.supplier_s as supname,b.uda_6 as tonum,a.ware_house_s as location from uda_lot@mes_test_link a inner join lot@mes_test_link b on b.lot_key=a.object_key inner join part c on b.part_number=c.part_number inner join uda_part d on c. part_key=d.object_key where 1=1");
		//如果工厂不为空，机型为空
		if(StringUtils.isNotBlank(o.getFactory()) && StringUtils.isBlank(o.getProductType())){
			sb.append(" and d.mold_type_s in ("+o.getProductTypes()+")");
		}
		if(o.getProductType()!=null && !o.getProductType().equals("")){
			sb.append(" and d.mold_type_s = '"+o.getProductType()+"'");
		}
		if(o.getStartdate()!=null && !o.getStartdate().equals("")){
//			sb.append(" and b.udt_2 >=TO_DATE('"+o.getStartdate()+"','YYYY-MM-DD')");
			//----
			sb.append(" and to_char(b.udt_2,'yyyy-mm-dd') >= '"+o.getStartdate()+"'");
		}
		if(o.getEnddate()!=null && !o.getEnddate().equals("")){
//			sb.append(" and b.udt_2 <TO_DATE('"+o.getEnddate()+"','YYYY-MM-DD')");
            //----			
			if("天".equals(o.getDateType()) || "周".equals(o.getDateType())){
				sb.append(" and to_char(b.udt_2,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
			}else{
				sb.append(" and to_char(b.udt_2,'yyyy-mm-dd') <= '"+o.getEnddate()+"'");
			}
		}
		if(o.getPartClass()!=null && !o.getPartClass().equals("")){
			sb.append(" and d.part_level_s='"+o.getPartClass()+"'");
		}
		if(o.getSupplierCode()!=null && !o.getSupplierCode().equals("")){
			sb.append(" and a.supplier_number_s in ("+getSupStringByComma(o.getSupplierCode())+")");
		}
//		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
//			sb.append(" and b.part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
//		}
		//是否带版本---
		if(o.getPartNumber()!=null && !o.getPartNumber().equals("")){
			if("否".equals(o.getPartVersion())){
				if(o.getDistinction()!=null && 1 == o.getDistinction()){
					sb.append(" and substr(b.part_number,0,length(b.part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),"")+")");
				}else{
					sb.append(" and substr(b.part_number,0,length(b.part_number)-1) in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
				}
			}else{
				sb.append(" and b.part_number in ("+getSupStringByComma(o.getPartNumber(),o.getPartVersion())+")");
			}
		}
		if(o.getIscrux()!=null && !o.getIscrux().equals("")){
			if(o.getIscrux().equals("0")){
				sb.append(" and d.CONTROL_NUMBER_S ='关键件'");
			}
			if(o.getIscrux().equals("1")){
				sb.append(" and d.CONTROL_NUMBER_S ='非关键件'");
			}
			if(o.getIscrux().equals("1")){
				sb.append(" and d.CONTROL_NUMBER_S ='附件'");
			}
		}
		BaseSearch bs=new BaseSearch();
		bs.put("sql", sb.toString());
		bs.setPage(page);
		System.out.println("sql:"+sb.toString());
		return onlineMapper.gettablistPage(bs);
	}
	/**
	 * 更换数据月汇总
	 */
	@Override
	public void SumDataMonth(String queryMonth) {
		String strsql="delete t_replace_part t where TO_CHAR(t.replacetime,'YYYY-MM')='"+queryMonth+"'";
		onlineMapper.dateDelete(strsql);
		StringBuilder insertSql = new StringBuilder("insert into t_replace_part (ABROAD,PRODUCT_NO,GAS,DEFECT_D,DEFECT_RES,PL_NAME,DEFECT_B,VERIFYING_CON,TEXT,GROUP_NAME,ISNEW_END_TIME_S,MAINTENANCE_MODE,serial_number, modeltype,defect_code,  defect_name, replaceTime, new_part_number, supplier_code, material_old, material_new)");
		insertSql
				.append("select DISTINCT a.abroad,a.productNO,a.gas,a.defect_d,a.defect_s,a.pl_name,a.defect_b,a.verifyingCon,a.text,a.group_name,a.isnew_end_time_s,a.MAINTENANCE_MODE, a.serial_number,a.mold_type_s,a.defect_code,a.defect_comment,a.udt_1,substr(at.material_old_s, 0, length(at.material_old_s) - 8) as part_number, substr(at.material_old_s, length(at.material_old_s)-7, 1) as supplier_code,at.material_old_s,at.material_new_s from (select decode(u1.serial_number_s,null,'维修R','更换C') as MAINTENANCE_MODE, u.serial_number,up.mold_type_s,dr.defect_code,dr.defect_comment,dr.udt_1,nvl(d.uda_3,' ') as abroad,nvl(d.uda_5,' ') as productNO,nvl(d.uda_6,' ') as gas,concat(concat(at.test_code_s,'-'), at.test_name_s) as defect_d,nvl(d.uda_0,' ') as defect_s,pl.description pl_name,(d.defect_sub_location||'-'||at3.test_name_s) as defect_b,d.uda_8 as verifyingCon,d.uda_9 as text,ag.description group_name,ud.isnew_end_time_s  from defect_repair_entry d,test_instance t,unit u,work_order w,app_user au,work_order_items woi,shift s,defect_repair_entry dr,uda_part up,production_line pl,part p,uda_defectrepairentry@MES_TEST_LINK ud, at_testcodedefinition at,account ac,(select * from at_testcodedefinition aa where aa.type_s = '不良原因分类') at2,at_testcodedefinition at3,(select distinct uc.serial_number_s from at_unitchangematerialrecord uc) u1,app_group ag,app_user auser,app_user au2 where 1=1 and d.defect_code=at.test_code_s and au.user_name(+)=d.repair_user_name and p.part_key = up.object_key and d.test_instance_key=t.test_instance_key and t.object_key=u.unit_key and w.order_key = woi.order_key and u.order_item_key=woi.order_item_key and t.location='在线检验' and woi.uda_2 = s.shift_name(+) and u.serial_number = u1.serial_number_s(+) and pl.p_line_name=woi.planned_line and u.part_number=p.part_number and at2.test_code_s(+)=d.uda_2 and at3.test_code_s(+)=d.defect_sub_location and ac.account_name(+) = d.uda_4 and woi.uda_5=ag.group_name and ag.uda_0=auser.user_name(+) and d.repair_location=au2.user_name(+) and dr.test_instance_key = t.test_instance_key and d.defect_repair_entry_key = ud.object_key(+)  and to_char(d.udt_1,'yyyy-MM')='"+queryMonth+"' order by to_char(d.last_modified_time,'yyyy-MM-dd HH24:mi:ss') desc,w.order_number desc) a left join at_unitchangematerialrecord at on at.serial_number_s = a.serial_number");
		
		String updateOldPartNumber = "update t_replace_part t set new_part_number = (select new_part_number from new_part_ref n where t.new_part_number = n.old_part_number)"
				+ " where t.new_part_number in (select old_part_number from new_part_ref)";
		
		String updateAccount = "update t_replace_part t set (account_number_n, account_name, account_abbreviation) = (select supplier_number_n, supplier_name, supplier_short_name from t_supplier_part s"
				+ " where t.new_part_number = s.part_number and t.supplier_code = trim(s.supplier_code) and rownum = 1)"
				+ " where t.new_part_number in (select part_number from t_supplier_part)"
				+ " and t.supplier_code in (select supplier_code from t_supplier_part)";
		
		String updatePart = "update t_replace_part t set (ware_house, part_class, part_level, maturity, consumption_type, part_name) = (select p.uda_2, p.uda_0, up.part_level_s, up.is_new_s, up.control_number_s, p.description from part p, uda_part up where p.part_key = up.object_key and t.new_part_number = p.part_number)"
				+ "where t.new_part_number in (select part_number from part)";
		
		onlineMapper.dateTransfer(insertSql.toString()); //插入更换主数据
		onlineMapper.dateTransfer(updateOldPartNumber); //将旧物料编码更新为新物料编码
		onlineMapper.dateTransfer(updateAccount); //更新供应商数据
		onlineMapper.dateTransfer(updatePart); //更新物料数据
	}
	
	/**
	 * 更换数据日汇总
	 */
	public void SumDataDay(String startTime, String endTime) {
		String strsql="delete t_replace_part t where TO_CHAR(replacetime, 'yyyy-mm-dd') BETWEEN '"+startTime+"' and '"+endTime+"'";
		onlineMapper.dateDelete(strsql);
		StringBuilder insertSql = new StringBuilder("insert into t_replace_part (serial_number, modeltype, new_part_number, supplier_code, material_old, material_new, defect_code, defect_name, replaceTime)");
		insertSql.append("select u.serial_number,up.mold_type_s,substr(at.material_old_s, 0, length(at.material_old_s) - 8) as part_number,"
				+ "substr(at.material_old_s, length(at.material_old_s)-7, 1) as supplier_code,"
				+ "at.material_old_s,at.material_new_s,dr.defect_code,dr.defect_comment,dr.udt_1"
				+ " from at_unitchangematerialrecord at,unit u ,test_instance ti,defect_repair_entry dr, part p, uda_part up"
				+ " where 1=1 and at.serial_number_s = u.serial_number and u.unit_key = ti.object_key"
				+ " and dr.test_instance_key = ti.test_instance_key"
				+ " and u.part_number=p.part_number"
				+ " and p.part_key = up.object_key"
				+ " and to_char(at.creation_time,'yyyy-mm-dd hh24:mi:ss') <= to_char(dr.udt_1+(2/24/60/60),'yyyy-mm-dd hh24:mi:ss')"
				+ " and to_char(at.creation_time,'yyyy-mm-dd hh24:mi:ss') >= to_char(dr.udt_1-(2/24/60/60),'yyyy-mm-dd hh24:mi:ss')"
				+ " and to_char(dr.udt_1,'yyyy-mm-dd') between '" + startTime + "' and '" + endTime + "'");
		String updateOldPartNumber = "update t_replace_part t set new_part_number = (select new_part_number from new_part_ref n where t.new_part_number = n.old_part_number)"
				+ " where t.new_part_number in (select old_part_number from new_part_ref)";
		
		String updateAccount = "update t_replace_part t set (account_number_n, account_name, account_abbreviation) = (select supplier_number_n, supplier_name, supplier_short_name from t_supplier_part s"
				+ " where t.new_part_number = s.part_number and t.supplier_code = trim(s.supplier_code) and rownum=1)"
				+ " where t.new_part_number in (select part_number from t_supplier_part)"
				+ " and t.supplier_code in (select supplier_code from t_supplier_part)";
		
		String updatePart = "update t_replace_part t set (ware_house, part_class, part_level, maturity, consumption_type, part_name) = (select p.uda_2, p.uda_0, up.part_level_s, up.is_new_s, p.consumption_type, p.description from part p, uda_part up where p.part_key = up.object_key and t.new_part_number = p.part_number and rownum=1)"
				+ "where t.new_part_number in (select part_number from part)";
		
		onlineMapper.dateTransfer(insertSql.toString()); //插入更换主数据
		onlineMapper.dateTransfer(updateOldPartNumber); //将旧物料编码更新为新物料编码
		onlineMapper.dateTransfer(updateAccount); //更新供应商数据
		onlineMapper.dateTransfer(updatePart); //更新物料数据
	}
	
//	private void fromSql(String strsql,String strsql2){
//		StringBuilder sb=new StringBuilder("delete t_replace_part t where "+strsql);
//		onlineMapper.dateDelete(sb.toString());
		/*StringBuilder sb2=new StringBuilder("insert into t_replace_part (modeltype,account_number,account_name,account_Abbreviation,part_number,part_name,ware_house,part_class,part_level,Maturity,bindingtime,defect_name,replaceTime,consumption_type)"
				+" select ud.mold_type_s,ac.account_name,ac.description,ac.uda_3,p.part_number,p.description,p.uda_2,p.uda_0,ud.part_level_s,ud.is_new_s ,cp.creation_time,dr.defect_comment,dr.creation_time,p.consumption_type"
				+" from account ac,defect_repair_entry dr,part p,uda_part ud, unit u,consumed_part@mes_test_link cp,test_instance t"
				+" where u.unit_key = cp.tobj_key"
				+" and cp.part_number = p.part_number"
				+" and p.part_key = ud.object_key"
				+" and t.object_key = u.unit_key"
				+" and t.test_instance_key = dr.test_instance_key"
				+" and  dr.uda_4 = ac.account_name"
				+" and  dr.defect_user_name = '供应商'"
				+" and t.test_valid='1' "
				+" and t.location='在线检验'");*/
//		StringBuilder sb2=new StringBuilder("insert into t_replace_part(serial_number,modeltype,account_number,account_name,account_Abbreviation,part_number,part_name,ware_house,part_class,part_level,Maturity,bindingtime,defect_name,replaceTime,consumption_type,account_number_n,new_part_number)"
//				+" select ud.mold_type_s,ac.account_name,ac.description,ac.uda_3,p.part_number,p.description,p.uda_2,p.uda_0,ud.part_level_s,ud.is_new_s ,cp.creation_time,dr.defect_comment,dr.creation_time,p.consumption_type,NVL(ac.supplier_number_n,ac.account_name),NVL(p.new_part_number,p.part_number)"
                //修改，如果供应商简称没有，则写入供应商全称
//                +" select u.serial_number,ud.mold_type_s,ac.account_name,ac.description,nvl(ac.uda_3,ac.description),p.part_number,p.description,p.uda_2,p.uda_0,ud.part_level_s,ud.is_new_s ,cp.creation_time,dr.defect_comment,dr.creation_time,p.consumption_type,NVL(ac.supplier_number_n,ac.account_name),NVL(p.new_part_number,p.part_number)" 
//               
//                +" from (select a.account_name,a.description,a.uda_3,b.supplier_number_n from account a left join t_supplier_ref b on a.account_name=b.supplier_number) ac,"
//				+" defect_repair_entry dr,"
//				+" (select pp.part_key,pp.part_number,pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on pp.part_number=nn.old_part_number) p,uda_part ud, unit u,consumed_part@mes_test_link cp,test_instance t "
//				+" where u.unit_key = cp.tobj_key"
//				+" and cp.part_number = p.part_number"
//				+" and p.part_key = ud.object_key"
//				+" and t.object_key = u.unit_key"
//				+" and t.test_instance_key = dr.test_instance_key"
//				+" and  dr.uda_4 = ac.account_name"
//				+" and  dr.defect_user_name = '供应商'"
//				+" and t.test_valid='1'" 
//				+" and t.location='在线检验'");
//		StringBuilder sb2=new StringBuilder("insert into t_replace_part(serial_number,modeltype,account_number,account_name,account_Abbreviation,part_number,part_name,ware_house,"
//        + " part_class,part_level,Maturity,bindingtime,defect_name,replaceTime,consumption_type,account_number_n,new_part_number) "
//        + " select u.serial_number, ud.mold_type_s,ac.account_name,ac.description,nvl(ac.uda_3,ac.description),p.part_number,p.description,"
//        + " p.uda_2,p.uda_0,ud.part_level_s,ud.is_new_s,cp.creation_time,dr.defect_comment,dr.creation_time,p.consumption_type,"
//        + " NVL(tsr.supplier_number_n,ac.account_name),NVL(np.new_part_number,p.part_number) "
//        + " from " 
//        + " account ac, t_supplier_ref tsr,defect_repair_entry dr,part p ,uda_part ud,unit u,consumed_part@mes_test_link cp,test_instance t,new_part_ref np"
//        + " where u.unit_key = cp.tobj_key"
//        + " and cp.part_number = p.part_number"
//        + " and p.part_key = ud.object_key "
//        + " and t.object_key = u.unit_key "
//        + " and dr.uda_4 = ac.account_name "
//        + " and dr.defect_user_name = '供应商'"
//        + " and t.test_valid='1'"
//        + " and t.location='在线检验'"
//        +" and ac.account_name = tsr.supplier_number(+)"
//        +" and p.part_number = np.old_part_number(+) ");
		
//		StringBuilder sb3 = new StringBuilder("insert into t_replace_part (serial_number, modeltype, new_part_number, supplier_code, material_old, material_new, defect_code, defect_name, replaceTime)");
//		sb3.append("select u.serial_number,up.mold_type_s,substr(at.material_old_s, 0, length(at.material_old_s) - 8) as part_number,"
//				+ "substr(at.material_old_s, length(at.material_old_s)-7, 1) as supplier_code,"
//				+ "at.material_old_s,at.material_new_s,dr.defect_code,dr.defect_comment,dr.udt_1"
//				+ " from at_unitchangematerialrecord at,unit u ,test_instance ti,defect_repair_entry dr, part p, uda_part up"
//				+ " where 1=1 and at.serial_number_s = u.serial_number and u.unit_key = ti.object_key"
//				+ " and dr.test_instance_key = ti.test_instance_key"
//				+ " and u.part_number=p.part_number"
//				+ " and p.part_key = up.object_key"
//				+ " and to_char(at.creation_time,'yyyy-mm-dd hh24:mi:ss') <= to_char(dr.udt_1+(2/24/60/60),'yyyy-mm-dd hh24:mi:ss')"
//				+ " and to_char(at.creation_time,'yyyy-mm-dd hh24:mi:ss') >= to_char(dr.udt_1-(2/24/60/60),'yyyy-mm-dd hh24:mi:ss')");
//		
//		sb3.append(strsql2);
//		onlineMapper.dateTransfer(sb3.toString());
//		String str = "update t_replace_part t set new_part_number = (select new_part_number from new_part_ref n where t.new_part_number = n.old_part_number)"
//				+ " where t.new_part_number in (select old_part_number from new_part_ref)";
//		System.out.println();
//	}
	/**
	 * 查区域
	 */
	@Override
	public List<Location> getLocal(Location mo,String strarr,PageParameter page) {
		StringBuilder sb=new StringBuilder("select distinct ID,location_code as locationCode,merge_region as location,province from t_Location_Region where merge_region is not null");
		String sup=getSupStringByComma(strarr);
		if (!sup.equals("")) {
			sb.append(" and id in ("+sup+")");
		}
		if(mo.getLocationCode()!=null && !mo.getLocationCode().equals("")){
			sb.append(" and location_code like '%"+mo.getLocationCode()+"%'");
		}
		if(mo.getLocation()!=null && !mo.getLocation().equals("")){
			sb.append(" and merge_region like '%"+mo.getLocation()+"%'");
		}
		if(mo.getProvince()!=null && !mo.getProvince().equals("")){
			sb.append(" and province like '%"+mo.getProvince()+"%'");
		}
		sb.append(" order by NLSSORT(merge_region ,'NLS_SORT = SCHINESE_PINYIN_M')");
		BaseSearch bs=new BaseSearch();
		bs.put("sql", sb.toString());
		bs.setPage(page);
		System.out.println("sql:"+sb.toString());
		return onlineMapper.getlistPage(bs);
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
	/**
	 * 通过“,”拆分字符串
	 * @param supplier
	 * @return
	 */
	public String getSupStringByComma(String supplier) {
		String sup = "";
		if(supplier != null && !"".equals(supplier)){
			String[] str = supplier.split(",");
			for(int i= 0; i< str.length; i++){
				    sup += "'"+str[i]+"',";
					
			}
			if(sup.endsWith(",")){
				sup = sup.substring(0, sup.length()-1);
			}
		}
		return sup;
	}
	/**
	 * 通过“,”拆分字符串
	 * @param supplier
	 * @return
	 */
	public String getSupStringByComma(String supplier,String partVersion) {
		String sup = "";
		if(supplier != null && !"".equals(supplier)){
			String[] str = supplier.split(",");
			for(int i= 0; i< str.length; i++){
				    if(partVersion.equals("否")){
				    	sup += "'"+str[i].substring(0,str[i].length()-1)+"',";
				    }else {
				    	sup += "'"+str[i]+"',";
					}
					
			}
			if(sup.endsWith(",")){
				sup = sup.substring(0, sup.length()-1);
			}
		}
		return sup;
	}
	/**
	 * 设置vo 查当前日期维度的入库数
	 * @param vo
	 * @return
	 */
	public OnlineModel setupVo(OnlineModel vo,String time){
		Calendar curr = Calendar.getInstance();//获取一个日历对象
		String DateType=vo.getDate_type();
		DateFormat sdf=null;
		try {
			if(DateType.equals("yyyy")){
				sdf = new SimpleDateFormat("yyyy");
				Date date = sdf.parse(time);
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				curr.add(Calendar.YEAR, -1);
				vo.setStartdate(sdf.format(curr.getTime()));
			}
			if(DateType.equals("yyyy-mm")){
				sdf = new SimpleDateFormat("yyyy-MM");
				Date date = sdf.parse(time);
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				curr.add(Calendar.MONTH, -1);
				vo.setStartdate(sdf.format(curr.getTime()));
				
			}
			if(DateType.equals("yyyy-IW")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Integer week=Integer.parseInt(time.substring(5, 7))+1;
				Integer year=Integer.parseInt(time.substring(0, 4));
				String weekstr=getDayOfWeek(year,week);
				Date date = sdf.parse(weekstr);
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				date = sdf.parse(vo.getStartdate());
				curr.add(Calendar.DAY_OF_YEAR, -7);
				vo.setDate_type("yyyy-mm-dd");
			}
			if(DateType.equals("yyyy-mm-dd")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(time);
				curr.setTime(date);
				vo.setEnddate(sdf.format(curr.getTime()));
				curr.add(Calendar.DAY_OF_YEAR, 1);
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
	private OnlineModel setupVo2(OnlineModel vo,String time){
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
			if(DateType.equals("yyyy-mm")){
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
	        	vo.setEnddate(sdf.format(curr.getTime()));
	        	curr.add(Calendar.DAY_OF_YEAR, -30*7);
	        	vo.setStartdate(sdf.format(curr.getTime()));
	        	vo.setDate_type("yyyy-mm-dd");
			}
			if(DateType.equals("yyyy-mm-dd")){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(time);
	        	curr.setTime(date);
	        	vo.setEnddate(sdf.format(curr.getTime()));
	        	curr.add(Calendar.DAY_OF_YEAR, -30);
	        	vo.setStartdate(sdf.format(curr.getTime()));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return vo;
	}
	
	public List<OnlineModel> setModelList(List<OnlineModel> list,List<String> strdate){
		List<OnlineModel> listArray=new ArrayList<OnlineModel>();
		for (int i = 0; i < strdate.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(strdate.get(i).equals(list.get(j).getDate_t())){
					listArray.add(list.get(j));
					break;
				}else {
					if(list.size()-1==j){
						OnlineModel mo=new OnlineModel();
						mo.setDate_t(strdate.get(i));
						listArray.add(mo);
					}
				}
			}
		}
		return listArray;
	}
	/**
	 * 查找y轴值集合
	 * @param o
	 * @return
	 */
	public List<String> getdateList(OnlineModel o){
		List<String> list=new ArrayList<String>();
		StringBuilder sb =new StringBuilder();
		int level=getInt(o);
		if(o.getDateType().equals("天")){
			sb.append("select to_char(to_date('"+o.getEnddate()+"','yyyy-mm-dd')-level+1,'yyyy-mm-dd') t1 from dual connect by  level<="+o.getColumnNum()+" order by t1");
		}
		if(o.getDateType().equals("周")){
			sb.append("select to_char(to_date('"+o.getEnddate()+"','yyyy-mm-dd')-level*7+7,'yyyy-iw') t1 from dual connect by  level<="+(level+1)+" order by t1");
		}
		if(o.getDateType().equals("月")){
			sb.append("select to_char(add_months(to_date('"+o.getEnddate()+"','yyyy-mm-dd'),-level+1),'yyyy-mm') t1 from dual connect by  level<="+level+" order by t1");
		}
		if(o.getDateType().equals("年")){
			sb.append("select to_char(add_months(to_date('"+o.getEnddate()+"','yyyy-mm-dd'),-level*12+1),'yyyy') t1 from dual connect by  level<="+level+" order by t1");
		}
		list=onlineMapper.getListStr(sb.toString());
		if(o.getDateType().equals("周")){
			list.remove(list.size()-1);
		}
		return list;
	}
	
	/**
	 * 汇总—插入——定时程序（关键件绑定）
	 */
	@Override
	public int insertdataTkeypartsum() {
		int result=0;
		try {
			onlineMapper.dataTkeypartsum();
		} catch (Exception e) {
			result=-1;
			e.getMessage();
		}
		return result;
	}
	/**
	 * 汇总—插入——定时程序（mes退次）
	 */
	public int insertTreturnwaresum(){
		int result=0;
		try {
			onlineMapper.dataTreturnwaresum();
		} catch (Exception e) {
			result=-1;
			e.getMessage();
		}
		return result;
	}
	/**
	 * 计算时间间隔并与排列图数量比较，返回较小值
	 * @param o
	 * @return
	 * add(1,-1)表示年份减一.
	 * add(2,-1)表示月份减一.
	 * add(3.-1)表示周减一.
	 * add(5,-1)表示天减一.
	 */
	public int getInt(OnlineModel o){
		int type=5;
		String data_type="yyyy-mm-dd";
	    if(o.getDateType().equals("周")){
	    	type=3;
		}
	    if(o.getDateType().equals("月")){
	    	type=2;
	    	data_type=o.getDate_type();
		}
		if(o.getDateType().equals("年")){
			type=1;
			data_type=o.getDate_type();
		}
		SimpleDateFormat sdf=new SimpleDateFormat(data_type);
	    Calendar c1=Calendar.getInstance();
	    Calendar c2=Calendar.getInstance();   
	    try {
			c1.setTime(sdf.parse(o.getStartdate()));
			c2.setTime(sdf.parse(o.getEnddate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    int val=c2.get(type)-c1.get(type);
	    int num=o.getColumnNum();
		return val>=num?val:num;
	}
	/**
	 * 补充机型
	 * @param o
	 */
	private void finishData(OnlineModel o) {
		if(StringUtils.isNotBlank(o.getFactory())){
			List<TestInstance> list = testInstanceMapper.getMesProductType();
			String productTypes = "";
			for(TestInstance test : list){
				if(o.getFactory().equals(test.getFactory())){
					productTypes += test.getProductType()+",";
				}
			}
			o.setProductTypes(getSupStringByComma(productTypes.substring(0, productTypes.length()-1)));
		}
	}
	
	/**
	 * 如果不带版本，则对list补全
	 * @param list
	 */
	private void getPartVersionList(List<OnlineModel> list) {
		Map<String,Double> map = new HashMap<String, Double>();
		for(OnlineModel model : list){
			if(model.getProductnumber() == null){
				continue;
			}
			Double qty = 0D;
			if(!map.containsKey(model.getProductnumber())){
				map.put(model.getProductnumber(), model.getBadcount());
			}else{
				qty += map.get(model.getProductnumber()) + model.getBadcount();
				map.put(model.getProductnumber(), qty);
			}
		}
		for(Entry<String,Double> entry : map.entrySet()){
			Double qty = entry.getValue() ;
			for(OnlineModel on : list){
				if(entry.getKey().equals(on.getProductnumber())){
					on.setBadcount(qty);
				}
			}
		}
	}

}
