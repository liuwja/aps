package com.peg.qms.controller.jxmb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.ChartsDataVo;
import com.peg.model.jxmb.PerDeparment;
import com.peg.model.jxmb.PerformanceCheck;
import com.peg.qms.controller.BaseController;
import com.peg.service.jxmb.ChartsDateService;
import com.peg.service.jxmb.DeparmentServicel;
import com.peg.service.jxmb.PerformanceCheckServicel;

@Controller
@RequestMapping("per/chartsDate")
public class ChartsDateController extends BaseController{
	
	@Autowired
	private DeparmentServicel dtService;
	
	@Autowired
	private PerformanceCheckServicel performanceCheckService;
	
	@Autowired
	private ChartsDateService chartsDateService;
	
	@RequestMapping("/monthFactTrend")
	public String monthFactTrend(Model model ,ChartsDataVo vo){
		BaseSearch bs = new BaseSearch();
		List<PerDeparment> deparmentList = dtService.getAllByPage(bs, false);
		model.addAttribute("deparmentList", deparmentList);
		model.addAttribute("vo", vo);
		return "qms/bph/base/charts/monthFactTrend";
	}
	@RequestMapping("/anYearFactTrend")
	public String anYearFactTrend(Model model ,ChartsDataVo vo){
		BaseSearch bs = new BaseSearch();
		List<PerDeparment> deparmentList = dtService.getAllByPage(bs, false);
		model.addAttribute("deparmentList", deparmentList);
		model.addAttribute("vo", vo);
		return "qms/bph/base/charts/anYearFactTrend";
	}
	@RequestMapping("/anYearFactResult")
	public String anYearFactResult(Model model ,ChartsDataVo vo){
		BaseSearch bs = new BaseSearch();
		List<PerDeparment> deparmentList = dtService.getAllByPage(bs, false);
		model.addAttribute("deparmentList", deparmentList);
		model.addAttribute("vo", vo);
		return "qms/bph/base/charts/anYearFactResult";
	}
	@RequestMapping("/getBigClass")
	public void getBigClass(String dape,HttpServletResponse respon){
		Map<String, Object> map = new HashMap<String, Object>();
		BaseSearch bs = new BaseSearch();
		List<PerformanceCheck> formanceCheckList =performanceCheckService.getAll(bs);
		List<String> bigClassList=chartsDateService.getBigClass(formanceCheckList);
		map.put("rows", bigClassList);
        JSONObject jobject = JSONObject.fromObject(map);
        System.out.println(jobject.toString());
        printResponContent(respon,jobject.toString());
	}
	@RequestMapping("/getIndexContent")
	public void getIndexContent(String dape,String bigClass,HttpServletResponse respon){
		Map<String, Object> map = new HashMap<String, Object>();
		BaseSearch bs = new BaseSearch();
		List<PerformanceCheck> formanceCheckList =performanceCheckService.getAll(bs);
		List<String> indexContentList=chartsDateService.getIndexContent(formanceCheckList,dape,bigClass);
		map.put("rows", indexContentList);
        JSONObject jobject = JSONObject.fromObject(map);
        System.out.println(jobject.toString());
        printResponContent(respon,jobject.toString());
	}
	@RequestMapping("/loadCharts")
	public void loadCharts(ChartsDataVo vo,HttpServletResponse respon,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		vo=chartsDateService.setCharts(vo);
		map.put("vo", vo);
		JSONObject jobject = JSONObject.fromObject(map);
	    System.out.println(jobject.toString());
	    printResponContent(respon,jobject.toString());
	}
	
	@RequestMapping("/loadChartsByAnYear")
	public void loadChartsByAnYear(ChartsDataVo vo,HttpServletResponse respon,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		vo=chartsDateService.setChartsByAnYear(vo);
		map.put("vo", vo);
		JSONObject jobject = JSONObject.fromObject(map);
	    System.out.println(jobject.toString());
	    printResponContent(respon,jobject.toString());
	}
	
	@RequestMapping("/reachRateTable")
	public String reachRateTable(ChartsDataVo vo,HttpServletResponse respon,Model model){
		if(vo.getType()!=null){
			List<ChartsDataVo> list=chartsDateService.setReachRateTable(vo);
			model.addAttribute("list", list);
		}
		model.addAttribute("vo", vo);
		return "qms/bph/base/charts/reachRateTable";
	}
	@RequestMapping("/loadChartsByYear")
	public void loadChartsByYear(ChartsDataVo vo,HttpServletResponse respon,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		vo=chartsDateService.setChartsByYear(vo);
		map.put("vo", vo);
		JSONObject jobject = JSONObject.fromObject(map);
	    System.out.println(jobject.toString());
	    printResponContent(respon,jobject.toString());
	}
	
	
}
