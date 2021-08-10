/*
 * @(#) HomePageController.java 2015-4-7 下午02:47:55
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.qms.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.beans.bph.HomeBean;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonVo;
import com.peg.model.ExceptionEnter;
import com.peg.model.LaterSumtime;
import com.peg.model.MachineType;
import com.peg.model.RepairRateDashboard;
import com.peg.model.RepairRateResult;
import com.peg.model.bph.BphCommonVo;
import com.peg.model.bph.CountPerformanceMonth;
import com.peg.model.part.MarketPart;
import com.peg.qms.controller.BaseController;
import com.peg.service.ExceptionEnterServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.RepairRateComputeServiceI;
import com.peg.service.RepairRateDashboardServiceI;
import com.peg.service.RepairRateServiceI;
import com.peg.service.bph.BphCommonServiceI;
import com.peg.service.bph.CountPerformanceMonthServiceI;
import com.peg.web.util.DateEditor;
import com.peg.web.util.SortUtils;
import com.peg.web.util.WebUtil;

/**
 * <p>
 * @author Lin, 2015-4-7 下午02:47:55
 */
@SuppressWarnings("static-access")
@Controller
@RequestMapping("system/homePage")
public class NHomePageController extends BaseController
{

	@Autowired
	private RepairRateComputeServiceI repairRateComputeService;
	
	@Autowired
	private BphCommonServiceI commonService;
	
	@Autowired
	private CountPerformanceMonthServiceI countPerformanceMonthService;
	
	@Autowired
	private ExceptionEnterServiceI exceptionEnterService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	
	@Autowired
	private RepairRateServiceI repairRateService;
	
	@Autowired
	private RepairRateDashboardServiceI repairRateDashboardService;
	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@RequestMapping("/alarmInfo")
	public String alarmInfo(Model model) throws Exception {
		
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		String queryMonth = laterTime.getSumMonth();
		String queryProdType = null;
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.reverse(monthList);
		CommonVo revo = new CommonVo();
		revo.setProductType(queryProdType);
		revo.setQueryMonth(queryMonth);
		Map<String, List<RepairRateResult>> resultMap = repairRateComputeService.getRepairRateResultByProdTypeAndMonth(revo);
		model.addAttribute("queryMonth", queryMonth);
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("monthList", monthList);
		
		return "qms/homepage/alarm";
	}
	
	@RequestMapping("/alarmPage")
	private String alarmPage(Model model,@RequestParam(value="selectDate",required=false)String selectDate){
		BaseSearch bs = new BaseSearch();
		String queryMonth = null;
		if(selectDate != null && selectDate != "")
		{
			queryMonth = selectDate ;
		}else
		{
			queryMonth = new DateEditor().getMonth(-1);
		}
		 
		bs.put("startTime", queryMonth);
		bs.put("endTime", queryMonth);
		bs.put("queryMonth",queryMonth);
		List<CountPerformanceMonth> indexList = countPerformanceMonthService.getAllByMonth(bs);
		HomeBean homebean = new HomeBean();
		String text = homebean.getHtmlText(indexList);
		
		List<BphCommonVo> categoryList =getCategory();
		List<BphCommonVo> glist = commonService.getShiftGroupPerformanceByMonth(bs);
	
       
	    //获取map<工厂-班组类别，list<BphCommonVo>>
		Map<String,Map<String,List<BphCommonVo>>> map = getDataMap(glist);
		
		List<ExceptionEnter> exList = exceptionEnterService.getExceptionByMonth(bs);
		int num = exList.size();
		
		model.addAttribute("category", categoryList);
		model.addAttribute("map", map);
		model.addAttribute("text", text);
		model.addAttribute("exList", exList);
		model.addAttribute("queryMonth", queryMonth);
		model.addAttribute("num", num);
		return "qms/homepage/alarmPage";
	}
	
	
	/**
	 * 单月
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getPerformanceSingleChar")
	private void getPerformanceSingleChar(BphCommonVo vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			 createPerformanceData(vo,map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 单班组多月折线图
	 * @param model
	 * @param vo 
	 * @return
	 */
	@RequestMapping("/getGroupPerfermanceChar")
	public void getGroupPerfermance(BphCommonVo vo,HttpServletResponse respon){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartVo = null;		
		try {
			String startTime = decode(vo.getStartTime());
			String endTime = new DateEditor().getMonth(startTime, -11);
			vo.setEndTime(endTime);
			//获取班组绩效
			List<BphCommonVo> shiftGroup = commonService.sumItemScoreByYear(vo); 
			List<String> monthList = WebUtil.getBackDateList(startTime, 11);
            List<BphCommonVo> glist = new ArrayList<BphCommonVo>();
			 for(int i=0; i<monthList.size(); i++){			   
			    	BphCommonVo com = new BphCommonVo();
			    	com.setBaseMonth(monthList.get(i));
			    	glist.add(com);
			    	}
			 for(BphCommonVo v : shiftGroup){
				 for(BphCommonVo c : glist){
					 if(v.getBaseMonth().equals(c.getBaseMonth())){
						 c.setShiftGroupScore(v.getShiftGroupScore());
					 }
				 }
			 }
			
            String group = vo.getShiftGroupTxt();
            String queryMonth = vo.getStartTime();
			
			//构造图表
			chartVo = new ChartObj();
			
			chartVo.setChartHight(300);
	    	chartVo.setChartWidth(vo.getWidth()-20);
			chartVo.setChartType("line");
	          
			chartVo.setTitle(group+endTime+"~"+queryMonth+"绩效统计图");
			chartVo.setxTitle("月份");
			chartVo.setyLeftTitle("分数");
			chartVo.setyLeftUnit("分");
			
			
			List<String> xvalues = new ArrayList<String>();
			List<List<Double>> yValues = new ArrayList<List<Double>>();
			String[] seriesNames = {"分数"};
			
			List<Double> yvalue1 = new ArrayList<Double>();
			for (int i = 0; i < glist.size(); i++) {
				BphCommonVo tmpVo = glist.get(i);
				xvalues.add(tmpVo.getBaseMonth());
				yvalue1.add(tmpVo.getShiftGroupScore()+0.0);
				
			}
			yValues.add(yvalue1);	
			chartVo.setxValue(xvalues);
			chartVo.setSeriesNames(seriesNames);
			chartVo.setyValues(yValues);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartVo);
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	private void createPerformanceData(BphCommonVo vo,Map<String, Object> map) throws Exception{
		BaseSearch bs = new BaseSearch();
		String queryMonth = null;
		if(vo.getQueryMonth() != null && vo.getQueryMonth() != "")
		{
			queryMonth = vo.getQueryMonth();
		}else{
			queryMonth = new DateEditor().getMonth(-1);
		}
		vo.setQueryMonth(queryMonth);
		bs.put("startTime", queryMonth);
		bs.put("endTime", queryMonth);
		bs.put("queryMonth",queryMonth);

		List<BphCommonVo> shiftGrouplist = commonService.getShiftGroupPerformanceByMonth(bs);
		Map<String,List<BphCommonVo>> cmap = new TreeMap<String, List<BphCommonVo>>();
	    for(BphCommonVo com : shiftGrouplist){
		   List<BphCommonVo> faclist = null;
	    	String fac = com.getFactory() ;  	
	    	if(!cmap.containsKey(fac)){
	    		faclist =new ArrayList<BphCommonVo>() ;
	    		cmap.put(fac, faclist);		
	    	}	    		
	    	cmap.get(fac).add(com);
	    }
		//获取List
	    List<BphCommonVo> dList = cmap.get("电器一厂");
	    List<BphCommonVo> rList = cmap.get("燃气工厂");
	    List<BphCommonVo> deList = cmap.get("电器二厂");
     
		//构造list
		 List<BphCommonVo> dpositiveList = getDataList(dList, 1);
		 List<BphCommonVo> dreverseList = getDataList(dList, 0);
		 List<BphCommonVo> rpositiveList = getDataList(rList, 1);
		 List<BphCommonVo> rreverseList = getDataList(rList, 0);
		 List<BphCommonVo> depositiveList = getDataList(deList, 1);
		 List<BphCommonVo> dereverseList = getDataList(deList, 0);
	
		 //构造图表对象
		 ChartObj dpositivechartVo = setData(dpositiveList,vo ,1,"电器一厂");
		 ChartObj dreversechartVo = setData(dreverseList,vo ,0,"电器一厂");
		 ChartObj rpositivechartVo =  setData(rpositiveList,vo ,1,"燃气工厂");;
		 ChartObj rreversechartVo = setData(rreverseList,vo ,0,"燃气工厂");
		 ChartObj depositivechartVo = setData(depositiveList,vo ,1,"电器二厂");
		 ChartObj dereversechartVo = setData(dereverseList,vo ,0,"电器二厂");
		 
		 map.put("dpositivechar", dpositivechartVo);
		 map.put("dreversechart", dreversechartVo);
		 map.put("rpositivechart", rpositivechartVo);
		 map.put("rreversechart", rreversechartVo);
		 map.put("depositivechart", depositivechartVo);
		 map.put("dereversechart", dereversechartVo);
	}

	private ChartObj setData(List<BphCommonVo> reList,BphCommonVo vo,int type,String factory){
		ChartObj chartVo = new ChartObj();
		chartVo.setChartHight(200);
	    chartVo.setChartWidth(vo.getWidth()/3-20);
		chartVo.setChartType("column");
		chartVo.setyLeftTitle("分数");
		chartVo.setyLeftUnit("分");
		chartVo.setSubtitle(factory);
		String top = "前5";
		String bottom = "后5";
		if(reList.isEmpty()){
			chartVo.setxTitle("");
		}else{
			chartVo.setxTitle("班组名称");
		}
		//构造标题 type=1,前5名，type=0后5名
		if(type==1){
			chartVo.setTitle(factory+"班组排名"+ top +"("+vo.getQueryMonth()+")");
		}else if(type==0){
			chartVo.setTitle(factory+"班组排名"+ bottom +"("+vo.getQueryMonth()+")");
		}
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		String[] seriesNames = {"分数"};
		
		List<Double> yvalue1 = new ArrayList<Double>();
		int t = reList.size();
		if(t > 5){
			t = 5;
		}
		for (int i = 0; i < t; i++) {
			BphCommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getShiftGroupTxt());
			yvalue1.add( (double)Math.round(tmpVo.getShiftGroupScore()*10)/10 +0.0);
			
		}
		yValues.add(yvalue1);	
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		
		return chartVo;
		
	}
	
	@RequestMapping("/detailInfo")
	public String detailInfo(Model model, BphCommonVo vo){
		vo.setFactory(WebUtil.ISOToUTF8(vo.getFactory()));
		vo.setQueryMonth(WebUtil.ISOToUTF8(vo.getQueryMonth()));
		model.addAttribute("vo", vo);
		return "qms/homepage/detailInfo";
	}

//	 冲压班组、点焊班组、精加工班组、喷涂班组、组装班组、iqc班组、oqc班组
	public List<BphCommonVo> getCategory(){
		List<BphCommonVo> list = new ArrayList<BphCommonVo>();
		String [] cate = { "冲压班组","点焊班组","精加工班组","喷涂班组","组装班组","IQC","OQC","部装班组","电脑板车间"};
		for(int i=0; i<cate.length; i++){
			BphCommonVo vo = new BphCommonVo();
			vo.setCategory(cate[i]);
			list.add(vo);
		}
	    
		return list;
	}
	/**
	 * 传入List<BphCommonVo>，生成Map<工厂,Map<班组类别,List<BphCommonVo>>>
	 * @param glist
	 * @return
	 */
	private Map<String,Map<String,List<BphCommonVo>>> getDataMap(List<BphCommonVo> glist){
        Map<String,Map<String,List<BphCommonVo>>> map = new TreeMap<String, Map<String,List<BphCommonVo>>>();
		
		Map<String,List<BphCommonVo>> cmap = new TreeMap<String, List<BphCommonVo>>();
	    for(BphCommonVo vo : glist){
		   List<BphCommonVo> faclist = null;
	    	String fac = vo.getFactory() ;  	
	    	if(!cmap.containsKey(fac)){
	    		faclist =new ArrayList<BphCommonVo>() ;
	    		cmap.put(fac, faclist);		
	    	}	    		
	    	cmap.get(fac).add(vo);
	    }
	    double score = 0;
	    for(Entry<String, List<BphCommonVo>> vo : cmap.entrySet()){
	    	 Map<String,List<BphCommonVo>> camap = new TreeMap<String, List<BphCommonVo>>();
	    	 int i=0;
	    	 for(BphCommonVo v : vo.getValue()){
	    		 
	    		 if(v.getShiftGroupScore()!=-1){
	    			 if(v.getShiftGroupScore()!=0){
	    				 if(i!=0){
	    					 if(score!=v.getShiftGroupScore()){
	    						 score = v.getShiftGroupScore();
	    						 i++;
	    					 }
	    				 }else{
	    					 i++;
	    				 }
	    				 score = v.getShiftGroupScore();
	    			 }
	    			 v.setId(i);
	    		 }
	  		    List<BphCommonVo> cateList = null;
	  	    	String cate = v.getCategory() ;  	
	  	    	if(!camap.containsKey(cate)){
	  	    		cateList =new ArrayList<BphCommonVo>() ;
	  	    		camap.put(cate, cateList);		
	  	    	}	    		
	  	    	camap.get(cate).add(v);
	  	    	
	  	    }
	    	 map.put(vo.getKey(), camap);
	    }
        return map;
	}
	/**
	 * 对List<BphCommonVo> 排序,type=1 升序，type=0降序
	 * @param list
	 * @return
	 */
	private List<BphCommonVo> getDataList(List<BphCommonVo> list,int type){
		Iterator<BphCommonVo> iter = list.iterator();
		while(iter.hasNext()){
			BphCommonVo vo = iter.next();
			if(vo.getShiftGroupScore()==-1){
				iter.remove();
			}
		}
		List<BphCommonVo> nlist  = new ArrayList<BphCommonVo>();
		if(type==1){
			int t = 5;
			if(list.size()<t){
				t = list.size();
			}
			for(int i=0; i<t; i++){
				nlist.add(list.get(i));
			}
		}else if(type ==0){
			int t = 0;
			if(list.size()>5){
				t = list.size()-5;
			}
			for(int i=list.size()-1; i>=t; i--){
				nlist.add(list.get(i));
			}
		}
		return nlist;
	}
	
	/**
	 * 市场质量主页面
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("marketHomePage")
	public String marketHomePage(Model model, String queryMonth) throws Exception {
		setBaseData(model);
		if (StringUtils.isEmpty(queryMonth)) {
			LaterSumtime laterTime = laterSumtimeService.getLaterDate();
			queryMonth = WebUtil.rebackMonths(laterTime.getSumMonth(), -1);
		}
		model.addAttribute("queryMonth", queryMonth);
		return "qms/homepage/marketHomePage";
	}
	
	/**
	 * 获取仪表盘数据
	 * @param model
	 * @param respon
	 */
	@RequestMapping("getDashboardData")
	public void getDashboardData(Model model, HttpServletResponse respon, RepairRateDashboard vo) {
		BaseSearch bs = new BaseSearch();
		Map<String,Object> map = new HashMap<String,Object>();
		List<RepairRateDashboard> list = new ArrayList<RepairRateDashboard>();
		List<MachineType> productList = new ArrayList<MachineType>();
		int result = 0;
		String msg = null;
		try {
			productList = machineTypeService.getAll();
			bs.put("month", vo.getMonth());
			list = repairRateDashboardService.getAll(bs);
		} catch (Exception e) {
			result = -1;
			msg = e.getMessage();
			e.printStackTrace();
		}
		map.put("list", SortUtils.sortRepairRate(list));
		map.put("msg", msg);
		map.put("vo", vo);
		map.put("result", result);
		map.put("productTypes", productList);
		JSONObject resultObject = JSONObject.fromObject(map);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("supplierHomePage")
	public String supplierHomePage(Model model, MarketPart vo) throws Exception {
		setBaseData(model);
		if (StringUtils.isNotEmpty(vo.getSupplierNumber())) { //供应商
			vo.setSupplierNumbers(vo.getSupplierNumber().split(","));
			vo.setSupplierNumberList(Arrays.asList(vo.getSupplierNumbers()));
		}
		if (StringUtils.isEmpty(vo.getQueryMonth())) {
			LaterSumtime laterTime = laterSumtimeService.getLaterDate();
			vo.setQueryMonth(WebUtil.rebackMonths(laterTime.getSumMonth(), -1));
		}
		if (StringUtils.isEmpty(vo.getProductType())) {
			vo.setProductType("油烟机");
		}
		if (vo.getSupplierNumbers() == null) {
			String supplierNumbers[] = {"100177"};
			vo.setSupplierNumbers(supplierNumbers);
			vo.setSupplierNumber("100177");
			vo.setSupplierId("3773538");
			vo.setSupplierListTxt("宁波开乐电机有限公司");
		}
		model.addAttribute("vo", vo);
		return "qms/homepage/supplierHomePage";
	}
}
