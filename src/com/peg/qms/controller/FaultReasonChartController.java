package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.mes.highcharts.ChartObj;
import com.peg.mes.highcharts.ChartsType;
import com.peg.model.CommonVo;
import com.peg.service.CommonServiceI;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.StatisTypeComparator;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("faultReasonChart")
public class FaultReasonChartController extends BaseController{

	@Autowired
	private CommonServiceI commonService;
	/**
	 * 单表
	 */
	@RequestMapping("/singleChart")
	public String singleChart(Model model, CommonVo vo){
		setBaseData(model);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		vo.setQueryMonth(DateFormatUtils.format(cal.getTime(), "yyyy-MM"));
		vo.setxCount(9);
		model.addAttribute("vo", vo);
		return "qms/chart/faultReason/singleChart";
	}
	/**
	 * 分析综合报表
	 */
	@RequestMapping("/analysisChart")
	public String analysisChart(Model model, CommonVo vo){
		setBaseData(model);
		vo.setProductType(WebUtil.ISOToUTF8(vo.getProductType()));
		vo.setQueryMonth(vo.getQueryMonth());
		vo.setxCount(9);
		model.addAttribute("vo", vo);
		return "qms/chart/faultReason/analysisChart";
	}
	/**
	 * 排列图
	 */
	@RequestMapping("/getFaultReasonInfo")
	public void getFaultReasonInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String,Object> map = new HashMap<String,Object>();
		ChartObj chartsInfo = null;
		try {
			chartsInfo = createRepairData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 区间排列图
	 */
	@RequestMapping("/getFaultReasonBetweenInfo")
	public void getFaultReasonBetweenInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String,Object> map = new HashMap<String,Object>();
		ChartObj chartsInfo = null;
		try {
			chartsInfo = createBetweenRepairData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 区间排列图取值
	 */
	private ChartObj createBetweenRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		WebUtil.convertMultiSelectCondition(vo);
		//维修数获取
		List<CommonVo> repairlist = commonService.getReCountGroupFaultReason(vo);
		List<CommonVo> retList = new ArrayList<CommonVo>();
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			//累加
			if(retList.size() == (xCount+1)){
				CommonVo vo_21 = retList.get(xCount);
				vo_21.setRepairCount(vo_21.getRepairCount()+repairVo.getRepairCount());
			}
			if(retList.size()<(xCount+1)){
				retList.add(repairVo);
				if(retList.size() == (xCount+1)){
					retList.get(xCount).setFaultReasonName("其他");
				}
			}
		}
		//构造图表对象
		String title = "故障小类区间百台维修排列图";
		if(vo.getPartType()!= null){
			title = vo.getPartType()+"-" + title;
		}
		else if(vo.getRegion()!= null){
			title = (vo.getRegion()==null?null:vo.getRegion().replaceAll("服务中心", ""))+"-" + title;
		}
		else if(vo.getProductLineNumber()!= null){
			title = vo.getProductLineNumber()+"-" + title;
		}
		else if(vo.getFaultReasonName()!=null){
			title = vo.getFaultReasonName()+"-" + title;
		}
		else if(vo.getProductType()!= null){
			title = vo.getProductType()+"-" + title;
		}
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_COLUMN);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(title);
		chartVo.setSubtitle("统计期间："+vo.getStartTime()+"~"+vo.getEndTime());
		chartVo.setxTitle("故障小类");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		String[] seriesNames = {"维修数"};
		for (int i = 0; i < retList.size(); i++) {
			CommonVo tmpVo = retList.get(i);
			xvalues.add(tmpVo.getFaultReasonName());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
		}
		yValues.add(yvalue1);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		return chartVo;
	}
	/**
	 * 排列图取值
	 */
	private ChartObj createRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		WebUtil.convertMultiSelectCondition(vo);
		if(StringUtils.isNotBlank(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")){//恢复服务中心
			vo.setRegion(vo.getRegion()+"服务中心");
		}
		//维修数获取
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairlist = commonService.getReCountGroupFaultReason(vo);
		
		List<CommonVo> retList = new ArrayList<CommonVo>();
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			//累加
			if(retList.size() == (xCount+1)){
				CommonVo vo_21 = retList.get(xCount);
				vo_21.setRepairCount(vo_21.getRepairCount()+repairVo.getRepairCount());
			}
			if(retList.size()<(xCount+1)){
				retList.add(repairVo);
				if(retList.size() == (xCount+1)){
					retList.get(xCount).setFaultReasonName("其他");
				}
			}
		}
		//构造图表对象
		String endMon = vo.getQueryMonth();
		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -11);
		if(TmStringUtils.isNotEmpty(startMon)&&startMon.contains("-")){
			startMon = startMon.replace("-",".");
		}
		if(TmStringUtils.isNotEmpty(endMon)&&endMon.contains("-")){
			endMon = endMon.replace("-",".");
		}
		String title = "故障小类百台维修排列图";
		if(StringUtils.isNotEmpty(vo.getTitle())) {
			title = vo.getTitle();
		}
		if(vo.getPartType()!= null){
			title = vo.getPartType()+"-" + title;
		}
		else if(vo.getRegion()!= null){
			title = (vo.getRegion()==null?null:vo.getRegion().replaceAll("服务中心", ""))+"-" + title;
		}
		else if(vo.getProductLineNumber()!= null){
			title = vo.getProductLineNumber()+"-" + title;
		}
		else if(vo.getFaultReasonName()!=null){
			title = vo.getFaultReasonName()+"-" + title;
		}
		else if(vo.getProductType()!= null){
			title = vo.getProductType()+"-" + title;
		}
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_COLUMN_LINE);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(title);
		chartVo.setSubtitle("统计期间："+startMon+"~"+endMon);
		chartVo.setxTitle("故障小类");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		chartVo.setyRightTitle("维修率");
		chartVo.setyRightUnit("%");
		return setData(retList, chartVo, vo);
	}
	
	/**
	 * 设置x轴的值，y轴的值，对应序列的值
	 * @param reList
	 * @param chartVo
	 * @return
	 */
	private ChartObj setData(List<CommonVo> reList, ChartObj chartVo,CommonVo vo) {
		try {
			vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
			vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//维修总数
		int sum = commonService.getShipCount(vo);
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<Double> yvalue2 = new ArrayList<Double>();//折线图
		List<String>  tipValues = new ArrayList<String>();//发货数提示
		String[] seriesNames = {"维修数","维修率"};
		//维修率排序
		if(vo.getStatisType()!=null && "1".endsWith(vo.getStatisType())){
			Collections.sort(reList,new StatisTypeComparator());
		}
		for (int i = 0; i < reList.size(); i++) {
			CommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getFaultReasonName());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
			float repairRate = sum==0?0:((float)tmpVo.getRepairCount()/sum)*100;
			yvalue2.add(Double.parseDouble(String.format("%.3f",repairRate)));
			tipValues.add(sum+"");
		}
		yValues.add(yvalue1);
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipText("发货总数");
		return chartVo;
		
	}
	
	/**
	 * 导出excel
	 * 
	 * @param vo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/execlOutput")
	public void execlOutput(CommonVo vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WebUtil.convertMultiSelectCondition(vo);
		if(StringUtils.isNotBlank(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")){//恢复服务中心
			vo.setRegion(vo.getRegion()+"服务中心");
		}
		//维修数获取
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairlist = commonService.getReCountGroupFaultReason(vo);
		
		//发货数
		int sum = commonService.getShipCount(vo);
		
		List<String[]> excelList = new ArrayList<String[]>();
		String[] CN = {"故障小类", "发货数", "维修数", "维修率"};
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			String[] itemStr = new String[CN.length];
			itemStr[0] = repairVo.getFaultReasonName();
			itemStr[1] = sum + "";
			itemStr[2] = repairVo.getRepairCount() + "";
			float repairRate = sum==0?0:((float)repairVo.getRepairCount()/sum)*100;
			itemStr[3] = Double.parseDouble(String.format("%.3f",repairRate)) + "";
			excelList.add(itemStr);
		}
		String rootPath = request.getSession().getServletContext().getRealPath("/");		
		String fname = System.currentTimeMillis() + ".xls" ;
		System.out.println(rootPath+fname);
		String filePath = rootPath + "/" + fname;
		ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
		String contentType =  "application/msexcel" ;
		ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-故障小类百台维修"+fname);
	}
}
