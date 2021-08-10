/*
 * @(#) ChargeController.java 2014-8-19 上午10:24:35
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.mes.highcharts.ChartData;
import com.peg.mes.highcharts.yseries;
import com.peg.model.Student;
import com.peg.web.util.TwoGen;

/**
 * TODO
 * <p>
 * 
 * @author Lin, 2014-8-19 上午10:24:35
 */
@SuppressWarnings({ "deprecation", "unused" })
@Controller
@RequestMapping("highcharts/exemple")
public class HighChartsController extends BaseController {

	@RequestMapping("/classjx")
	public String classjx(Model model) throws Exception {
		return "highcharts_example/classjx";
	}

	@RequestMapping("/fixtable")
	public String fixtable(Model model) throws Exception {
		return "/test/fixTable";
	}
	
	@RequestMapping("/testGrid")
	public String testGrid(Model model) throws Exception {
		return "/test/testGrid";
	}
	
	@RequestMapping("/testData")
	public void testData(Model model,HttpServletResponse response) throws Exception {
//		Map<String, Student> map = new HashMap<String, Student>();
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("aa","f","19"));
		list.add(new Student("bb","m","22"));
		list.add(new Student("cc","f","24"));
		list.add(new Student("dd","m","25"));
		list.add(new Student("ee","m","22"));
		list.add(new Student("ff","f","33"));
		list.add(new Student("gg","m","16"));
		list.add(new Student("hh","f","22"));
		JSONArray caList = JSONArray.fromObject(list);
		System.out.println(caList);
		printResponContent(response, caList.toString());
	}
	
	
	@RequestMapping("/showGrapP2")
	public String showGrapP2(Model model) throws Exception {
		return "highcharts_example/showGrap";
	}

	@RequestMapping("/showGrapP1")
	public String showGrapP1(Model model) throws Exception {
		return "highcharts_example/showGrapP1";
	}

	@RequestMapping("/showGrapP3")
	public String showGrapP3(Model model) throws Exception {
		return "highcharts_example/showGrapP3";
	}

	@RequestMapping("/testVoice")
	public String testVoice(Model model) throws Exception {
		return "test/testVoice";
	}
	
	@RequestMapping("/showGrapP4")
	public String showGrapP4(Model model) throws Exception {
		return "highcharts_example/showGrapP4";
	}

	@RequestMapping("/testChart")
	public String testChart(Model model) throws Exception {
		return "highcharts_example/highcharts_model";
	}

	//多选下拉列表
	@RequestMapping("/dropDownListDemo")
	public String dropDownListDemo(Model model) throws Exception {
		return "qms/dropDownList/demo";
	}
	
	private TwoGen<List<ChartData>, List<yseries>> createTestData_doubleList() {
		List<ChartData> list = new ArrayList<ChartData>();
		List<yseries> ylist = new ArrayList<yseries>();
		TwoGen<List<ChartData>, List<yseries>> TwoGenList = new TwoGen<List<ChartData>, List<yseries>>();
		ChartData data = new ChartData();
		yseries ydata = new yseries();
		data.setX_coordinate("油烟机");
		data.setY_coordinate(5);
		ydata.setYseries(11.5);
		list.add(data);
		ylist.add(ydata);
		data = new ChartData();
		ydata = new yseries();
		data.setX_coordinate("热水器");
		data.setY_coordinate(2);
		ydata.setYseries(9);
		ylist.add(ydata);
		ydata = new yseries();
		ydata.setYseries(16);
		list.add(data);
		ylist.add(ydata);

		TwoGenList.setOb1(list);
		TwoGenList.setOb2(ylist);

		return TwoGenList;

	}

	private List<ChartData> createTestData_singleList() {
		List<ChartData> list = new ArrayList<ChartData>();

		ChartData data = new ChartData();

		data.setX_coordinate("油烟机");
		data.setY_coordinate(5);

		list.add(data);

		data = new ChartData();

		data.setX_coordinate("热水器");
		data.setY_coordinate(2);

		list.add(data);

		return list;

	}

	/**
	 * 两个list  暂时不用该方法
	 * 
	 * @param chartsInfo
	 * @param chartdata
	 * @param chartstype
	 * @param respon
	 */
//	@RequestMapping("/getInfo_doubleList")
//	public void getInfo(PublicChartsInfo chartsInfo, ChartData chartdata,
//			ChartsType chartstype, HttpServletResponse respon) {
//		int result = 0;
//		int i;
//		String seriesname[] = { "测试1", "测试2" };
//		Map map = new HashMap();
//		String msg = null;
//		try {
//			// List<ChartData> list = careGradeMapper.getChartData(record);
//			TwoGen<List<ChartData>, List<yseries>> list = createTestData_doubleList();
//			setHighCharts_line_columm("测试", seriesname, "成绩1", "成绩2", "分1",
//					"分2", 400, 800, chartstype.CHART_COLUMN_LINE,
//					chartstype.CHART_COLUMN, chartstype.CHART_LINE, list,
//					chartsInfo);
//			map.put("chartsInfo", chartsInfo);
//
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			result = -1;
//			msg = e.getMessage();
//		}
//
//		map.put("result", result);
//		map.put("msg", msg);
//
//		JSONObject resultObject = JSONObject.fromObject(map);
//		logger.debug(resultObject);
//		printResponContent(respon, resultObject.toString());
//	}

//	/**
//	 * 单个list的例子 暂时不用该方法
//	 * 
//	 * @param chartsInfo
//	 * @param chartdata
//	 * @param chartstype
//	 * @param respon
//	 */
//	@RequestMapping("/getInfo_singleList")
//	public void getInfo_singleList(PublicChartsInfo chartsInfo,
//			ChartData chartdata, ChartsType chartstype,
//			HttpServletResponse respon) {
//		int result = 0;
//		int i;
//		String seriesname[] = { "测试1"};
//		Map map = new HashMap();
//		String msg = null;
//		try {
//			// List<ChartData> list = careGradeMapper.getChartData(record);
//			List<ChartData> list = createTestData_singleList();
//			setHighCharts_singleList("测试", seriesname, "成绩1", "分1", 400, 800,
//					chartstype.CHART_COLUMN_LINE, list, chartsInfo);
//			map.put("chartsInfo", chartsInfo);
//
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			result = -1;
//			msg = e.getMessage();
//		}
//
//		map.put("result", result);
//		map.put("msg", msg);
//
//		JSONObject resultObject = JSONObject.fromObject(map);
//		logger.debug(resultObject);
//		printResponContent(respon, resultObject.toString());
//	}

}
