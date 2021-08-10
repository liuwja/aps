package com.peg.service.impl.jxmb;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.MonthTrendChartMapper;
import com.peg.model.jxmb.ChartsDataVo;
import com.peg.model.jxmb.MonthTrendChart;
import com.peg.model.jxmb.PerformanceCheck;
import com.peg.service.jxmb.ChartsDateService;
import com.peg.web.util.WebUtil;

@Service("chartsDateService")
public class ChartsDateServiceImpl implements ChartsDateService {
	@Autowired
	private MonthTrendChartMapper mtcmapper;
	
	@Override
	public List<MonthTrendChart> getAll(BaseSearch bs) {
		return mtcmapper.getAll(bs);
	}

	@Override
	public List<MonthTrendChart> getAllByYear(BaseSearch bs) {
		return mtcmapper.getAllByYear(bs);
	}

	@Override
	public List<String> getBigClass(List<PerformanceCheck> list) {
		Map<String, String> map = new HashMap<String, String>();
		String bigClass=null;
		for (int i = 0; i < list.size(); i++) {
			bigClass=list.get(i).getTargetclass();
			map.put(bigClass, bigClass);
		}
		List<String> strList=new ArrayList<String>(map.keySet());    
		return strList;
	}

	@Override
	public List<String> getIndexContent(List<PerformanceCheck> list,String depaName, String bigClass) {
		Map<String, String> map = new HashMap<String, String>();
		String indexContent=null;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDepartment().equals(depaName) && list.get(i).getTargetclass().equals(bigClass)){
				indexContent=list.get(i).getIndexcontent();
				map.put(indexContent, indexContent);
			}
		}
		List<String> strList=new ArrayList<String>(map.keySet());    
		return strList;
	}

	@Override
	public ChartsDataVo setCharts(ChartsDataVo vo) {
		BaseSearch bs = new BaseSearch();
		bs.put("smallMonth", vo.getSmallMonth());
		bs.put("bigMonth", vo.getBigMonth());
		bs.put("dape", vo.getDape());
		bs.put("bigClass", vo.getBigClass());
		bs.put("indexContent", vo.getIndexContent());
		List<MonthTrendChart> list=getAll(bs);
		/*List<String> betweenMonth = WebUtil.getBetweenMonthList(vo.getSmallMonth(),vo.getBigMonth());
		for(int i=0;i<betweenMonth.size();i++){
			if(list.size()==0){
				break;
			}
			if(!list.get(i).getMonth().substring(0, 7).equals(betweenMonth.get(i))){
				MonthTrendChart m = new MonthTrendChart();
				m.setMonth(betweenMonth.get(i));
				list.add(i,m);
			}
		}
		*/
		
		StringBuilder title=new StringBuilder(vo.getDape());
		title.append(vo.getType().equals("")?"月度所有绩效值":vo.getType());
		int num=list.size();
		if(num==1){
			title.append(list.get(0).getMonth());
		}
		if(num>1){
			title.append(vo.getSmallMonth()+"至"+vo.getBigMonth());
		}
		title.append(vo.getType());
		vo.setTitle(title.toString());
		String[] legendData = null;
		if(vo.getType().equals("")){
			legendData = new String[4];
			legendData[0]="月度目标";
			legendData[1]="月度实际值";
			legendData[2]="月度累计目标";
			legendData[3]="月度累计实际值";
		}else{
			legendData = new String[2];
			if(vo.getType().equals("月度实际绩效值")){
				legendData[0]="月度目标";
				legendData[1]="月度实际值";
			}
			if(vo.getType().equals("月度累计实际绩效值")){
				legendData[0]="月度累计目标";
				legendData[1]="月度累计实际值";
			}
		}
		vo.setLegendData(legendData);
		
		String[] xAxisData=new String[num];
		Double[] seriesData=new Double[num];
		Double[] seriesData2=new Double[num];
		Double[] seriesData3=new Double[num];
		Double[] seriesData4=new Double[num];
		
		String baseValue=null; //目标值
		String realityValue=null;//实际值
		
		String targetValue=null;//累计目标
		String accumuValue=null;//累计实际值
		for (int i = 0; i < list.size(); i++) {
			xAxisData[i]=list.get(i).getMonth().substring(0,7);
			baseValue=list.get(i).getBaseValue();
			realityValue=list.get(i).getMonthReality();
			targetValue=list.get(i).getTargetValue();
			accumuValue=list.get(i).getAccumuMonth();
			if(vo.getType().equals("")){				
				seriesData[i]=Double.valueOf(baseValue==null?"0":baseValue);
				seriesData2[i]=Double.valueOf(realityValue==null?"0":realityValue);
				seriesData3[i]=Double.valueOf(targetValue==null?"0":targetValue);
				seriesData4[i]=Double.valueOf(accumuValue==null?"0":accumuValue);
			}else{
				if(vo.getType().equals("月度实际绩效值")){				
					seriesData[i]=Double.valueOf(baseValue==null?"0":baseValue);
					seriesData2[i]=Double.valueOf(realityValue==null?"0":realityValue);
				}
				if(vo.getType().equals("月度累计实际绩效值")){				
					seriesData[i]=Double.valueOf(targetValue==null?"0":targetValue);
					seriesData2[i]=Double.valueOf(accumuValue==null?"0":accumuValue);
				}
			}
		}
		List<Double[]> dataList=new ArrayList<Double[]>();
		vo.setxAxisData(xAxisData);
		dataList.add(seriesData);
		dataList.add(seriesData2);
		dataList.add(seriesData3);
		dataList.add(seriesData4);
		vo.setList(dataList);
		return vo;
		
	}

	@Override
	public ChartsDataVo setChartsByAnYear(ChartsDataVo vo) {
		BaseSearch bs = new BaseSearch();
		bs.put("yearEquaTo", vo.getYearEquaTo());
		bs.put("dape", vo.getDape());
		bs.put("bigClass", vo.getBigClass());
		bs.put("indexContent", vo.getIndexContent());
		List<MonthTrendChart> list=getAll(bs);
		StringBuilder title=new StringBuilder(vo.getDape());
		title.append(vo.getType());
		int num=list.size();
		/*if(num==1){
			title.append(list.get(0).getMonth());
		}
		if(num>1){
			title.append(list.get(0).getMonth()+"至"+list.get(num-1).getMonth());
		}*/
		title.append(vo.getType());
		vo.setTitle(title.toString());
		
		String[] legendData=new String[3];
		if(vo.getType().equals("")){
			legendData[0]="当年当月实际值";
			legendData[1]="当年当月累计实际值";
			legendData[2]="上一年当月累计实际值";
		}
		if(vo.getType().equals("月度实际绩效值")){
			legendData[0]="当年当月目标值";
			legendData[1]="当年当月实际值";
			legendData[2]="上一年当月实际值";
		}
		if(vo.getType().equals("月度累计实际绩效值")){
			legendData[0]="当年当月累计目标";
			legendData[1]="当年当月累计实际值";
			legendData[2]="上一年当月累计实际值";
		}
		vo.setLegendData(legendData);
		
		String[] xAxisData=new String[num];
		Double[] seriesData=new Double[num];
		Double[] seriesData2=new Double[num];
		Double[] seriesData3=new Double[num];
		String baseValue=null; //目标值
		String realityValue=null;//实际值
		String oldRealityValue=null;//上一年当月实际值
		
		String targetValue=null;//累计目标
		String accumuValue=null;//累计实际值
		String oldAccumuValue=null;//上一年当月累计实际值
		for (int i = 0; i < list.size(); i++) {
			xAxisData[i]=list.get(i).getMonth().substring(0, 7);
			if(vo.getType().equals("")){
				realityValue=list.get(i).getMonthReality();
				accumuValue=list.get(i).getAccumuMonth();
				oldAccumuValue=list.get(i).getOldAccumumonth();
				
				seriesData[i]=Double.valueOf(realityValue==null?"0":realityValue);
				seriesData2[i]=Double.valueOf(accumuValue==null?"0":realityValue);
				seriesData3[i]=Double.valueOf(oldAccumuValue==null?"0":oldRealityValue);
			}
			if(vo.getType().equals("月度实际绩效值")){
				baseValue=list.get(i).getBaseValue();
				realityValue=list.get(i).getMonthReality();
				oldRealityValue=list.get(i).getOldMonthreality();
				
				seriesData[i]=Double.valueOf(baseValue==null?"0":baseValue);
				seriesData2[i]=Double.valueOf(realityValue==null?"0":realityValue);
				seriesData3[i]=Double.valueOf(oldRealityValue==null?"0":oldRealityValue);
			}
			if(vo.getType().equals("月度累计实际绩效值")){
				targetValue=list.get(i).getTargetValue();
				accumuValue=list.get(i).getAccumuMonth();
				oldAccumuValue=list.get(i).getOldAccumumonth();
				
				seriesData[i]=Double.valueOf(targetValue==null?"0":targetValue);
				seriesData2[i]=Double.valueOf(accumuValue==null?"0":accumuValue);
				seriesData3[i]=Double.valueOf(oldAccumuValue==null?"0":oldAccumuValue);
			}
		}
		List<Double[]> dataList=new ArrayList<Double[]>();
		vo.setxAxisData(xAxisData);
		dataList.add(seriesData);
		dataList.add(seriesData2);
		dataList.add(seriesData3);
		vo.setList(dataList);
		return vo;
	}

	@Override
	public List<ChartsDataVo> setReachRateTable(ChartsDataVo vo) {
		List<ChartsDataVo> tableList=null;
		BaseSearch bs = new BaseSearch();
		String bigYear=vo.getBigYear();
		int year=0;
		if(bigYear!=null && !bigYear.equals("")){
			year=Integer.valueOf(bigYear)+1;
		}
		bs.put("smallYear", vo.getSmallYear());
		bs.put("bigYear", year);
		List<MonthTrendChart> list=getAll(bs);
		if(vo.getType().equals("供应链")){
			tableList=getChartsDataBySupply(list);
		}
		if(vo.getType().equals("部门")){
			tableList=getChartsDataByDepa(list);
		}
		return tableList;
	}
	//按供应链
	private List<ChartsDataVo> getChartsDataBySupply(List<MonthTrendChart> list){
		List<ChartsDataVo> dataList=new ArrayList<ChartsDataVo>();
		List<String> monthList=getMonth(list);
		List<MonthTrendChart> groupList=null;
		for (int i = 0; i < monthList.size(); i++) {
			String month=monthList.get(i);
			groupList=new ArrayList<MonthTrendChart>();
			for (int j = 0; j < list.size(); j++) {
				MonthTrendChart mtc=list.get(j);
				if(mtc.getMonth().equals(month)){
					groupList.add(mtc);
				}
			}
			ChartsDataVo vo=totalData(groupList);
			dataList.add(vo);
		}
		return dataList;
	}
	//按部门
	private List<ChartsDataVo> getChartsDataByDepa(List<MonthTrendChart> list){
		List<ChartsDataVo> dataList=new ArrayList<ChartsDataVo>();
		List<String> monthList=getMonth(list);
		List<String> depaList=getDepa(list);
		List<MonthTrendChart> groupList=null;
//		for (int i = 0; i < monthList.size(); i++) {
//			String month=monthList.get(i);
//			for (int j = 0; j < depaList.size(); j++) {
//				String depa=depaList.get(j);
//				groupList=new ArrayList<MonthTrendChart>();
//				for (int k = 0; k < list.size(); k++) {
//					MonthTrendChart mtc=list.get(k);
//					if(mtc.getMonth().equals(month) && mtc.getDepaName().equals(depa)){
//						groupList.add(mtc);
//					}
//				}
//				ChartsDataVo vo=totalData(groupList);
//				dataList.add(vo);
//			}
//		}
		for (int j = 0; j < depaList.size(); j++) {
			String depa=depaList.get(j);
			for (int i = 0; i < monthList.size(); i++) {
				String month=monthList.get(i);
				groupList=new ArrayList<MonthTrendChart>();
				for (int k = 0; k < list.size(); k++) {
					MonthTrendChart mtc=list.get(k);
					if(mtc.getMonth().equals(month) && mtc.getDepaName().equals(depa)){
						groupList.add(mtc);
					}
				}
				ChartsDataVo vo=totalData(groupList);
				dataList.add(vo);
			}
		}
		return dataList;
	}
	//找出集合中的月份
	private List<String> getMonth(List<MonthTrendChart> list){
		Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 升序排序
                        return obj1.compareTo(obj2);
                    }
                }
        );
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=null){
				map.put(list.get(i).getMonth(), list.get(i).getMonth());
			}
		}
		List<String> monthList=new ArrayList<String>(map.keySet());
		return monthList;
	}
	//找出集合中的部门
	private List<String> getDepa(List<MonthTrendChart> list){
		Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 升序排序
                        return obj1.compareTo(obj2);
                    }
                }
        );
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDepaName(), list.get(i).getDepaName());
		}
		List<String> depaList=new ArrayList<String>(map.keySet());
		return depaList;
	}
	//把没个月的数据统计，封装成ChartsDataVo对象
	private ChartsDataVo totalData(List<MonthTrendChart> groupList){
		ChartsDataVo vo=new ChartsDataVo();
		if(groupList.size()<=0){
			return vo;
		}
		String baseValue=null; //目标值
		String realityValue=null;//实际值
		String targetValue=null;//累计目标
		String accumuValue=null;//累计实际值
		
		double intBaseValue=0; //目标值
		double intRealityValue=0;//实际值
		double intTargetValue=0;//累计目标
		double intAccumuValue=0;//累计实际值
		
		int standard=0;//目标达标的个数
		int totalStandard=0;//累计目标达标的个数
		
		String content;
		for (int i = 0; i < groupList.size(); i++) {
			MonthTrendChart mtc=groupList.get(i);
			content=mtc.getContent();
			String performanceType = mtc.getPerformanceType();
			baseValue=mtc.getBaseValue()==null?"0":mtc.getBaseValue();
			realityValue=mtc.getMonthReality()==null?"0":mtc.getMonthReality();
			targetValue=mtc.getTargetValue()==null?"0":mtc.getTargetValue();
			accumuValue=mtc.getAccumuMonth()==null?"0":mtc.getAccumuMonth();
			if(performanceType.indexOf("项目型")!=-1){
				String moncolumn1 = mtc.getMoncolumn1();
				if(moncolumn1!=null){
					if(moncolumn1.indexOf("A")!=-1||moncolumn1.indexOf("B+")!=-1||moncolumn1.indexOf("正常进行")!=-1){
						totalStandard+=1;					
					}
				}
			}else{
				intBaseValue=Double.valueOf(String.valueOf(baseValue));
				intRealityValue=Double.valueOf(String.valueOf(realityValue));
				intTargetValue=Double.valueOf(String.valueOf(targetValue));
				intAccumuValue=Double.valueOf(String.valueOf(accumuValue));
				if(content.equals("望大型")){
					if(intRealityValue>=intBaseValue){
						standard=standard+1;
					}
					if(intAccumuValue>=intTargetValue){
						totalStandard=totalStandard+1;
					}
				}
				if(content.equals("望小型")){
					if(intRealityValue<=intBaseValue){
						standard=standard+1;
					}
					if(intAccumuValue<=intTargetValue){
						totalStandard=totalStandard+1;
					}
				}
			}
			
		}
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
		double a=(double)standard/groupList.size()*100;
		double b=(double)totalStandard/groupList.size()*100;
		vo.setActualReach(df.format(a)+"%");
		vo.setTotalReach(df.format(b)+"%");
		vo.setMonthTime(groupList.get(0).getMonth());
		vo.setDepaName(groupList.get(0).getDepaName());
		return vo;
	}

	@Override
	public ChartsDataVo setChartsByYear(ChartsDataVo vo) {
		BaseSearch bs = new BaseSearch();
		String smallYear=vo.getSmallYear();
		int year=0;
		bs.put("smallYear", year);
		bs.put("bigYear", vo.getBigYear());
		bs.put("dape", vo.getDape());
		bs.put("bigClass", vo.getBigClass());
		bs.put("indexContent", vo.getIndexContent());
		List<MonthTrendChart> list=getAllByYear(bs);
		int num=list.size();
		vo.setTitle(vo.getDape()+vo.getIndexContent()+vo.getSmallYear()+"~"+vo.getBigYear()+"年度实际绩效统计");
		
		String[] legendData=new String[3];
		legendData[0]="年度基准值";
		legendData[1]="年度目标值";
		legendData[2]="年度累计实际值";
		vo.setLegendData(legendData);
		
		String[] xAxisData=new String[num];
		Double[] seriesData=new Double[num];
		Double[] seriesData2=new Double[num];
		Double[] seriesData3=new Double[num];
		
		String baseValue=null; //基准值
		String realityValue=null;//目标值
		String accumuValue=null;//累计实际值
		for (int i = 0; i < list.size(); i++) {
			xAxisData[i] = list.get(i).getYear().substring(0,4);
			baseValue=list.get(i).getBaseValueByYear();
			realityValue=list.get(i).getTargetValueByYear();
			accumuValue=list.get(i).getOldTargetValueByYear();
			
			seriesData[i]=Double.valueOf(baseValue==null?"0":baseValue);
			seriesData2[i]=Double.valueOf(realityValue==null?"0":realityValue);
			seriesData3[i]=Double.valueOf(accumuValue==null?"0":accumuValue);
		}
		List<Double[]> dataList=new ArrayList<Double[]>();
		vo.setxAxisData(xAxisData);
		dataList.add(seriesData);
		dataList.add(seriesData2);
		dataList.add(seriesData3);
		vo.setList(dataList);
		return vo;
	}
	

}
