package com.peg.qms.controller.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.echarts.EChartObj;
import com.peg.echarts.EChartsType;
import com.peg.echarts.EchartChild;
import com.peg.qms.controller.BaseController;

/**
 * Echar报表控制类
 * @author Administrator
 *
 */
@Controller("echartReport")
@RequestMapping("report/echart")
public class EchartReportController extends BaseController{
	
//	@Autowired
//	private EchartCommonMapper echartCommonMapper;
//	
//	@RequestMapping("/downDispatchingRate")
//	public String downDispatchingRate(){
//		
//		return "/aps/report/downDispatching/downDispatchingRate";
//	}

//	@RequestMapping("/todownDispatchingRate")
//	public void todownDispatchingRate(@RequestParam(value="odDate",required=false)String odDate,
//			HttpServletResponse rs){
//		//{date :{} }
//		int result = 0;
//		String msg = null ;
//		Map amap = new HashMap();
//		EChartObj chart = new EChartObj();
//		chart.setChartType(EChartsType.CHART_BAR);                      //图形类型
//		chart.setToolboxShow(true);                                       //开启工具配置项
//		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
//		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
//		chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
//		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
//		chart.setyAxisAxisLabelFormatter("{value}%");                    //配置坐标单位  
//		chart.setSeriesType(new String[]{"bar","bar"} );                //设置系列类型
//		chart.setyLeftUnit("%");                                       //左单位
//		String[] seriesNames ={"白班","晚班"};
////		String[] colorsList ={"#BEBEBE","#6C6C6C"};                    //length 不能小于seriesNames.length
//		List<String> xValue = new ArrayList<String>();
//		List<Double> baiList = new ArrayList<Double>();
//		List<Double> yeList = new ArrayList<Double>();
//		List<List<Double>> ylist = new ArrayList<List<Double>>();
//		try{
//			Map<String,List<EchartCommon> > map = new TreeMap<String, List<EchartCommon>>();
//			List<EchartCommon> clist = null;
//			String endDate = DateTimeUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
//			String startDate =  DateTimeUtils.getAfterDateFromNow(-(Integer.parseInt(odDate)));
//			chart.setTitle(startDate + "至" +endDate+ "下发排程设备变更率");                                //设置标题
//			List<EchartCommon> list = echartCommonMapper.getModifyRate(startDate, endDate);
//			for(EchartCommon com : list){
//				String date = DateTimeUtils.formatDate(com.getOdDate(), "yyyy-MM-dd");
//				clist = new ArrayList<EchartCommon>();
//				if(!map.containsKey(date)){
//					map.put(date, clist);
//				}
//				map.get(date).add(com);
//			}
//			for(Entry<String, List<EchartCommon>> nmap : map.entrySet()){
//				xValue.add(nmap.getKey());
//				for(EchartCommon comm : nmap.getValue()){
//					if("白班".equals(comm.getOdShift())){
//						int upnum = comm.getUpdateNum();
//						int tonum = comm.getTotalNum();
//						double brate = 0;
//						brate = MathUtil.round(MathUtil.divide(upnum, tonum)*100, 4);
//						baiList.add(brate);
//					}else if("晚班".equals(comm.getOdShift())){
//						int upnum = comm.getUpdateNum();
//						int tonum = comm.getTotalNum();
//						double yrate = 0;
//						yrate = MathUtil.round(MathUtil.divide(upnum, tonum)*100,4);
//						yeList.add(yrate);
//					}
//				}
//			}
//			ylist.add(baiList);
//			ylist.add(yeList);
//			chart.setSeriesNames(seriesNames);
//			chart.setxValue(xValue);
//			chart.setyValues(ylist);
////			chart.setColors(colorsList);
//		}catch(Exception e){
//			logger.error(e.getMessage(), e);
//			result = -1;
//			msg = e.getMessage();
//		}
//		amap.put("chartsInfo", chart);
//		amap.put("result", result);
//		amap.put("msg", msg);
//		JSONObject resultObject = JSONObject.fromObject(amap);
//		logger.debug(resultObject);
//		printResponContent(rs, resultObject.toString());
//	}
//	
	@RequestMapping("/testLine")
	public String testLine(){
		
		return "qms/echarts/echarts/line";
	}
	
	@RequestMapping("/totestLine")
	public void totestLine(HttpServletResponse rs){
		int result = 0;
		String msg = null ;
		Map<String, Object> amap = new HashMap<String, Object>();
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle("未来一周气温变化");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}℃");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"line","line"} );                //设置系列类型
		chart.setyLeftUnit("℃");                                        //左单位
		String[] seriesNames ={"最高气温","最低气温"};
		List<String> xValue = new ArrayList<String>();
		String[] xVal = {"周一","周二","周三","周四","周五","周六","周日"};
		for(String str : xVal){
			xValue.add(str);
		}
		double[] yVal1 = {11, 11, 15, 13, 12, 13, 10};
		double[] yVal2 = {1, -2, 2, 5, 3, 2, 0};
		List<Double> baiList = new ArrayList<Double>();
		List<Double> yeList = new ArrayList<Double>();
		for(double b : yVal1){
			baiList.add(b);
		}
		for(double c : yVal2){
			yeList.add(c);
		}
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		try{
			ylist.add(baiList);
			ylist.add(yeList);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		amap.put("info", chart);
		amap.put("result", result);
		amap.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(amap);
		logger.debug(resultObject);
		printResponContent(rs, resultObject.toString());
	}
	
	@RequestMapping("/totestPie")
	public void totestPie(HttpServletResponse rs){
		int result = 0;
		String msg = null ;
		Map<String, Object> amap = new HashMap<String, Object>();
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_PIE);                      //图形类型
		chart.setTitle("访问来源");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyLeftUnit("次");                                        //左单位
		String[] seriesNames ={"直接访问","邮件营销","联盟广告","视频广告","搜索引擎"};
		List<EchartChild> childList = new ArrayList<EchartChild>();
		List<Double> yvalueList = null;
		Random rand = new Random();
		for(String str : seriesNames){
		    EchartChild child = new EchartChild(); 
			yvalueList = new ArrayList<Double>();
			yvalueList.add(rand.nextInt(1000)+1.0);
			child.setName(str);
			child.setValue(yvalueList);
			childList.add(child);
	}
		try{
			chart.setChildList(childList);
			chart.setSeriesNames(seriesNames);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		amap.put("info", chart);
		amap.put("result", result);
		amap.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(amap);
		logger.debug(resultObject);
		printResponContent(rs, resultObject.toString());
	}
	
	/**
	 * 饼图和柱状图联动
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/totestPieBar")
	public void totestPieBar(HttpServletResponse rs){
		int result = 0;
		String msg = null ;
		Map<String, Object> amap = new HashMap<String, Object>();
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_PIE_BAR);                      //图形类型
		chart.setTitle("网站访问量图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(false);                       //开启动态转换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}次");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"pie","bar"} );                //设置系列类型
		chart.setyLeftUnit("次");                                        //左单位
		chart.setChildSeriesName("访问量");                              //联动图子图系列名称
		chart.setBarWidth("50");                                        //设置联动柱状图柱子的宽度
		String[] seriesNames ={"直接访问","邮件营销","联盟广告","视频广告","搜索引擎"};
		List<String> xValue = new ArrayList<String>();
		String[] xVal = {"周一","周二","周三","周四","周五","周六","周日"};
		for(String str : xVal){
			xValue.add(str);
		}
		List<EchartChild> childList = new ArrayList<EchartChild>();
		List<Double> yvalueList = null;
		Random rand = new Random();
		for(String str : seriesNames){
			    EchartChild child = new EchartChild(); 
				yvalueList = new ArrayList<Double>();
				for(String str1 : xVal){
					yvalueList.add(rand.nextInt(1000)+1.0);
				}
				child.setName(str);
				child.setValue(yvalueList);
				childList.add(child);
		}
		
		try{
			chart.setSeriesNames(seriesNames);
			chart.setChildList(childList);
			chart.setxValue(xValue);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		amap.put("info", chart);
		amap.put("result", result);
		amap.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(amap);
		logger.debug(resultObject);
		printResponContent(rs, resultObject.toString());
	}
	
	/**
	 * 柱状图和柱状图联动
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/totestBarBar")
	public void totestBarBar(HttpServletResponse rs){
		int result = 0;
		String msg = null ;
		Map<String, Object> amap = new HashMap<String, Object>();
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_BAR_BAR);                      //图形类型
		chart.setTitle("网站访问量图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(false);                       //开启动态转换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}次");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar","bar"} );                //设置系列类型
		chart.setyLeftUnit("次");                                        //左单位
		chart.setChildSeriesName("访问量");                              //联动图子图系列名称
		chart.setBarWidth("50");                                        //设置联动柱状图柱子的宽度
		String[] seriesNames ={"直接访问","邮件营销","联盟广告","视频广告","搜索引擎"};
		List<String> xValue = new ArrayList<String>();
		String[] xVal = {"周一","周二","周三","周四","周五","周六","周日"};
		for(String str : xVal){
			xValue.add(str);
		}
		List<EchartChild> childList = new ArrayList<EchartChild>();
		List<Double> yvalueList = null;
		Random rand = new Random();
		for(String str : seriesNames){
			    EchartChild child = new EchartChild(); 
				yvalueList = new ArrayList<Double>();
				for(String str1 : xVal){
					yvalueList.add(rand.nextInt(1000)+1.0);
				}
				child.setName(str);
				child.setValue(yvalueList);
				childList.add(child);
		}
		
		
		try{
			chart.setSeriesNames(seriesNames);
			chart.setChildList(childList);
			chart.setxValue(xValue);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		amap.put("info", chart);
		amap.put("result", result);
		amap.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(amap);
		logger.debug(resultObject);
		printResponContent(rs, resultObject.toString());
	}
	
	/**
	 * 柱状图和饼图联动
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/totestBarPie")
	public void totestBarPie(HttpServletResponse rs){
		int result = 0;
		String msg = null ;
		Map<String, Object> amap = new HashMap<String, Object>();
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_PIE_BAR);                      //图形类型
		chart.setTitle("2016-04-01～2016-05-12 供应量不良批次数 ");                     //设置标题
//		chart.setSubtext("2016-04-01～2016-04-07 供应量不良批次数");         //设置副标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(false);                       //开启动态转换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}个");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar","pie"} );                //设置系列类型
		chart.setyLeftUnit("个");                                        //左单位
		chart.setChildSeriesName("数量");                              //联动图子图系列名称
		chart.setBarWidth("50");                                        //设置联动柱状图柱子的宽度
		String[] seriesNames ={"慈溪市天行电器有限公司","三峰实业有限公司","上海华驰电子有限公司 ","深圳市合信达控制系统股份\n有限公司","上虞市阳达塑料有限公司"};
		List<String> xValue = new ArrayList<String>();
		String[] xVal = {"性能 ","外观","尺寸","其他 "};
		for(String str : xVal){
			xValue.add(str);
		}
		List<EchartChild> childList = new ArrayList<EchartChild>();
		List<Double> yvalueList = null;
		Random rand = new Random();
		double[][] values={{13,9,7,5},{10,5,3,5},{1,4,2,5},{1,3,2,2},{1,1,2,0}};
//		for(String str : seriesNames){
//			    EchartChild child = new EchartChild(); 
//				yvalueList = new ArrayList<Double>();
//				for(String str1 : xVal){
//					yvalueList.add(rand.nextInt(10)+1.0);
//				}
//				child.setName(str);
//				child.setValue(yvalueList);
//				childList.add(child);
//		}
		int i=0;
        
		for(String str : seriesNames){
			int j=0;
		    EchartChild child = new EchartChild(); 
			yvalueList = new ArrayList<Double>();
			for(String str1 : xVal){
				yvalueList.add(values[i][j]);
				j++;
			}
			i++;
			child.setName(str);
			child.setValue(yvalueList);
			childList.add(child);
	}
		try{
			chart.setSeriesNames(seriesNames);
			chart.setChildList(childList);
			chart.setxValue(xValue);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		amap.put("info", chart);
		amap.put("result", result);
		amap.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(amap);
		logger.debug(resultObject);
		printResponContent(rs, resultObject.toString());
	}
//	/**
//	 * 下发排程比例作业计划
//	 * @return
//	 */
//	@RequestMapping("/downScheduleRate")
//	public String downScheduleRate(Model model,BaseModel vo){
//		LoadFAPG(model, vo);
//		return "/aps/report/downDispatching/downScheduleRate";
//	}
//	
//	@RequestMapping("/toDownScheduleRate")
//	public void toDownScheduleRate(@RequestParam(value="factory",required=false)String factory,@RequestParam(value="area",required=false)String area,
//			@RequestParam(value="plName",required=false)String plName,@RequestParam(value="odDate",required=false)String odDate,HttpServletResponse rs){
//		int result = 0;
//		String msg = null ;
//		Map amap = new HashMap();
//		EChartObj chart = new EChartObj();
//		chart.setChartType(EChartsType.CHART_STACK_BAR);                      //图形类型
//		chart.setToolboxShow(true);                                       //开启工具配置项
//		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
//		chart.setToolboxFeatureMagicTypeShow(false);                       //开启动态转换
//		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
//		chart.setSeriesType(new String[]{"bar","bar","line"} );                //设置系列类型
//		chart.setyLeftUnit("个");                                        //左单位
//		chart.setyRightUnit("%");                                         //右单位
//		chart.setStack(new String[]{"数量","数量"," "});                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
//		chart.setyAxisIndex(new String[]{"0","0","1"});                  //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。        
//		
//		EchartYAxis yaxis1 = new EchartYAxis();                         //设置y轴1
//		EchartLable lable1 = new EchartLable();
//		lable1.setFormatter("{value}个");
//		yaxis1.setType("value");
//		yaxis1.setName("数量");
//		yaxis1.setAxisLabel(lable1);
//		EchartYAxis yaxis2 = new EchartYAxis();                         //设置y轴2
//		EchartLable lable2 = new EchartLable();
//		lable2.setFormatter("{value}%");
//		yaxis2.setType("value");
//		yaxis2.setName("比例");
//		yaxis2.setAxisLabel(lable2);
//		
//		List<EchartYAxis> yaxiss = new ArrayList<EchartYAxis>();
//		yaxiss.add(yaxis1);
//		yaxiss.add(yaxis2);
//		chart.setyAxiss(yaxiss);
//		
//		
//		String[] seriesNames ={"排程计划","补充作业","排程计划比例"};
//		List<String> xValue = new ArrayList<String>();
//		
//		List<Double> buList = new ArrayList<Double>();
//		List<Double> btList = new ArrayList<Double>();
//		List<Double> breteList = new ArrayList<Double>();
//		
//	
//		List<List<Double>> ylist = new ArrayList<List<Double>>();
//		
//		try{
//			String endDate = DateTimeUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
//			String startDate =  DateTimeUtils.getAfterDateFromNow(-(Integer.parseInt(odDate)));
//			//设置标题
//			chart.setTitle(factory+area+plName+startDate+"~"+endDate+"下发排程作业计划比例报表"); 
//			List<EchartCommon> list = echartCommonMapper.getDownScheduleRate(startDate, endDate, factory, area, plName);
//			for(EchartCommon com : list){
//				buList.add(Double.valueOf(com.getUpdateNum()));
//				btList.add(Double.valueOf(com.getTotalNum()));
//				double brate = 0;
//				brate = MathUtil.round(MathUtil.divide(com.getTotalNum(),com.getUpdateNum() + com.getTotalNum())*100, 4);
//				breteList.add(brate);
//				xValue.add( DateTimeUtils.formatDate(com.getOdDate(), "MM/dd")+com.getOdShift());
//			}
//			ylist.add(btList);
//			ylist.add(buList);
//			ylist.add(breteList);
//			chart.setSeriesNames(seriesNames);
//			chart.setxValue(xValue);
//			chart.setyValues(ylist);
//		}catch(Exception e){
//			logger.error(e.getMessage(), e);
//			result = -1;
//			msg = e.getMessage();
//		}
//		amap.put("info", chart);
//		amap.put("result", result);
//		amap.put("msg", msg);
//		JSONObject resultObject = JSONObject.fromObject(amap);
//		logger.debug(resultObject);
//		printResponContent(rs, resultObject.toString());
//	}
	
	@RequestMapping("/test")
	public String test(Model model){
		return "qms/echarts/echarts/line";
	}
	
}
