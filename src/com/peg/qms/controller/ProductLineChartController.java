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
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.StatisTypeComparator;
import com.peg.web.util.WebUtil;

/**
 * 按产线分类相关图表
 * @author song
 */
@Controller
@RequestMapping("plineChart")
public class ProductLineChartController extends BaseController{

	@Autowired
	private CommonServiceI commonService;
	
	@RequestMapping("/singleChart")
	public String singleChart(Model model, CommonVo vo){
		setBaseData(model);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		vo.setQueryMonth(DateFormatUtils.format(cal.getTime(), "yyyy-MM"));
		vo.setxCount(9);
		model.addAttribute("vo", vo);
		model.addAttribute("factorys", commonService.getFactorys(null));
		ConditionUtil.loadPline(model, vo, commonService);
		return "qms/chart/pline/singleChart";
	}
	
	@RequestMapping("/analysisChart")
	public String analysisChart(Model model, CommonVo vo){
		setBaseData(model);
		ConditionUtil.loadPline(model, vo, commonService);
		vo.setProductType(WebUtil.ISOToUTF8(vo.getProductType()));
		vo.setQueryMonth(vo.getQueryMonth());
		vo.setxCount(9);
		model.addAttribute("vo", vo);
		return "qms/chart/pline/analysisChart";
	}
	/**
	 * 产线排列图
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getPlineInfo")
	public void getInfo(CommonVo vo, HttpServletResponse respon) {
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
	 * 产线区间排列图
	 */
	@RequestMapping("/getBetweenPlineInfo")
	public void getBetweenPlineInfo(CommonVo vo, HttpServletResponse respon) {
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
	 * 获取区间排列图数值
	 */
	private ChartObj createBetweenRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		//modify by lin
		WebUtil.convertMultiSelectCondition(vo);
		//维修数获取
		List<CommonVo> repairlist = commonService.getReCountGroupByPline(vo);
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
					retList.get(xCount).setFaultTypeName("其他");
				}
			}
		}
		//构造图表对象
		String title = "产线百台维修排列图";
		if(StringUtils.isNotEmpty(vo.getPartType())){
			title = vo.getPartType()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getRegion())){
			title = (vo.getRegion().replaceAll("服务中心", ""))+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getProductLineNumber())){
			title = vo.getProductLineNumber()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getMeshFaultName())){
			title = vo.getMeshFaultName()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getFaultReasonTxt())){
			title = vo.getFaultReasonTxt()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getFaultTypeName())){
			title = vo.getFaultTypeName()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getProductType())){
			title = vo.getProductType()+"-" + title;
		}
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_COLUMN);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(title);
		chartVo.setSubtitle("统计期间："+vo.getStartTime()+"~"+vo.getEndTime());
		chartVo.setxTitle("产线");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<Double> yvalue2 = new ArrayList<Double>();//折线图
		List<String>  tipValues = new ArrayList<String>();//发货数提示
		String[] seriesNames = {"维修数"};
		
		for (int i = 0; i < retList.size(); i++) {
			CommonVo tmpVo = retList.get(i);
			xvalues.add(tmpVo.getProductLineNumber());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
			tipValues.add(tmpVo.getRepairCount()+"");
		}
		yValues.add(yvalue1);
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipText("维修数");
		return chartVo;
	}
	
	/**
	 * 获取排列图数值
	 */
	private ChartObj createRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		//modify by lin
		WebUtil.convertMultiSelectCondition(vo);
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		if(StringUtils.isNotBlank(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")){//恢复服务中心
			vo.setRegion(vo.getRegion()+"服务中心");
		}
		
		//维修数获取
		//判断是否有维修开始时间 by JiangFeng
		if(StringUtils.isNotEmpty(vo.getQueryMonth()))
		{		
			vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
			vo.setEndTime(vo.getQueryMonth());
		}
		//vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		//vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairlist = commonService.getReCountGroupByPline(vo);
		
		//获取下线数
		//判断是否有维修开始时间 by JiangFeng
		if(StringUtils.isNotEmpty(vo.getQueryMonth()))
		{		
			vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
			vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		}
		//vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
		//vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		List<CommonVo> shiplist = commonService.getShipCountGroupByPline(vo);
		

		List<CommonVo> retList = new ArrayList<CommonVo>();
		Map<String, CommonVo> ShipMap = new HashMap<String, CommonVo>();
		for (int i = 0; i < shiplist.size(); i++) {
			ShipMap.put(shiplist.get(i).getProductLineNumber(), shiplist.get(i));
		}
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			CommonVo shipVo = ShipMap.get(repairVo.getProductLineNumber());
			if(shipVo != null){
				repairVo.setShipCount(shipVo.getShipCount());
			} else {
				repairVo.setShipCount(0L); //发货数为空时，将发货数设为0
			}
			
			if (StringUtils.isNotEmpty(vo.getTitle())) {
				retList.add(repairVo);
			} else {
				//累加
				if(repairVo.getShipCount()!=0 && retList.size() == (xCount+1)){
					CommonVo vo_21 = retList.get(xCount);
					vo_21.setRepairCount(vo_21.getRepairCount()+repairVo.getRepairCount());
					vo_21.setShipCount(vo_21.getShipCount()+repairVo.getShipCount());
				}

				if(repairVo.getShipCount()!= null && retList.size()<(xCount+1)){
//					if(StringUtils.isBlank(repairVo.getProductLineNumber())){//如果产线为空则去掉，用户需要的话
//						continue;
//					}
					retList.add(repairVo);
					if(retList.size() == (xCount+1)){
						retList.get(xCount).setProductLineNumber("其他");
					}
				}
			}
		}
		if (StringUtils.isNotEmpty(vo.getTitle()) && ShipMap != null) {
			for (Map.Entry<String, CommonVo> entry : ShipMap.entrySet()) {
				CommonVo tempVo = entry.getValue();
				tempVo.setRepairCount(0L);
				retList.add(tempVo);
			}
		}
		//构造图表对象
//		String endMon = vo.getQueryMonth();
//		String endMon = vo.getQueryMonth();
//		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -11);
//

		
//		if(TmStringUtils.isNotEmpty(startMon)&&startMon.contains("-")){
//			startMon = startMon.replace("-",".");
//		}
//		if(TmStringUtils.isNotEmpty(endMon)&&endMon.contains("-")){
//			endMon = endMon.replace("-",".");
//		}
		String title = "产线百台维修排列图";
		if(StringUtils.isNotEmpty(vo.getTitle())) {
			title = vo.getTitle();
		}
		if(StringUtils.isNotEmpty(vo.getPartType())){
			title = vo.getPartType()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getRegion())){
			title = (vo.getRegion().replaceAll("服务中心", ""))+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getProductLineNumber())){
			title = vo.getProductLineNumber()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getMeshFaultName())){
			title = vo.getMeshFaultName()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getFaultReasonTxt())){
			title = vo.getFaultReasonTxt()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getFaultTypeName())){
			title = vo.getFaultTypeName()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getProductType())){
			title = vo.getProductType()+"-" + title;
		}
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_COLUMN_LINE);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(title);
		chartVo.setSubtitle("统计期间："+vo.getStartTime()+"~"+vo.getEndTime());
		chartVo.setxTitle("产线");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		chartVo.setyRightTitle("维修率");
		chartVo.setyRightUnit("%");
		
		return setData(retList, chartVo,vo);
	}
	
	private ChartObj setData(List<CommonVo> reList, ChartObj chartVo,CommonVo vo) {
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<Double> yvalue2 = new ArrayList<Double>();//折线图
		List<String>  tipValues = new ArrayList<String>();//发货数提示
		List<Double> recPercentlist = new ArrayList<Double>(); //统计维修率
		String[] seriesNames = {"维修数","维修率"};
		//维修率排序
		if(vo.getStatisType()!=null && "1".endsWith(vo.getStatisType())){
			Collections.sort(reList,new StatisTypeComparator());
		}
		for (int i = 0; i < reList.size(); i++) {
			CommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getProductLineNumber());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
			if (tmpVo.getRepairCount() > 0 && tmpVo.getShipCount() > 0) {
				yvalue2.add(Double.parseDouble(String.format("%.2f", (tmpVo
						.getRepairCount() * 100.0 / tmpVo.getShipCount()))));
			} else {
				yvalue2.add(0.00);
			}
			tipValues.add(tmpVo.getShipCount()+"");
			if (tmpVo.getRepairCount() != null && tmpVo.getRepairCount() != 0 && tmpVo.getShipCount() != null && tmpVo.getShipCount() != 0) {
				recPercentlist.add(Math.floor(tmpVo.getRepairCount() * 1000000.0 / tmpVo.getShipCount()));
			} else {
				recPercentlist.add(0d);
			}
		}
		if (StringUtils.isNotEmpty(vo.getStatisData()) && "repairRate".equals(vo.getStatisData())) {
			yValues.add(recPercentlist);
		} else {
			yValues.add(yvalue1);
		}
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipText("发货数");
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
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		if(StringUtils.isNotBlank(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")){//恢复服务中心
			vo.setRegion(vo.getRegion()+"服务中心");
		}
		//维修数获取
		//判断是否有维修开始时间 by JiangFeng
		if(StringUtils.isNotEmpty(vo.getQueryMonth()))
		{		
			vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
			vo.setEndTime(vo.getQueryMonth());
		}
		//vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		//vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairlist = commonService.getReCountGroupByPline(vo);
		
		//获取发货数
		//判断是否有维修开始时间 by JiangFeng
		if(StringUtils.isNotEmpty(vo.getQueryMonth()))
		{		
			vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
			vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		}
		//vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
		//vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		List<CommonVo> shiplist = commonService.getShipCountGroupByPline(vo);
		
		Map<String, CommonVo> ShipMap = new HashMap<String, CommonVo>();
		List<String[]> excelList = new ArrayList<String[]>();
		String[] CN = {"产线", "发货数", "维修数", "统计维修率", "维修率"};
		for (int i = 0; i < shiplist.size(); i++) {
			ShipMap.put(shiplist.get(i).getProductLineNumber(), shiplist.get(i));
		}
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			CommonVo shipVo = ShipMap.get(repairVo.getProductLineNumber());
			String[] itemStr = new String[CN.length];
			if(shipVo!=null){
				itemStr[0] = shipVo.getProductLineNumber();
				itemStr[1] = shipVo.getShipCount() + "";
				itemStr[2] = repairVo.getRepairCount() + "";
				if (repairVo.getRepairCount() != null && repairVo.getRepairCount() != 0 && shipVo.getShipCount() != null && shipVo.getShipCount() != 0) {
					itemStr[3] = Math.floor(repairVo.getRepairCount() * 1000000.0 / shipVo.getShipCount()) + "";
				} else {
					itemStr[3] = "0";
				}
				itemStr[4] = (Double.parseDouble(String.format("%.2f", (repairVo.getRepairCount() * 100.0 / shipVo.getShipCount())))) + "";
				excelList.add(itemStr);
			}
		}
		String rootPath = request.getSession().getServletContext().getRealPath("/");		
		String fname = System.currentTimeMillis() + ".xls" ;
		System.out.println(rootPath+fname);
		String filePath = rootPath + "/" + fname;
		ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
		String contentType =  "application/msexcel" ;
		ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-产线百台维修"+fname);
	}
}
