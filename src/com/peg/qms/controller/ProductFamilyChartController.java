package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.mes.highcharts.ChartObj;
import com.peg.mes.highcharts.ChartsType;
import com.peg.model.CommonVo;
import com.peg.service.CommonServiceI;
import com.peg.web.util.StatisTypeComparator;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

/**
 * 按产品系列分类排列图
 */
@Controller
@RequestMapping("productFamilyChart")
public class ProductFamilyChartController extends BaseController{
	
	@Autowired
	private CommonServiceI commonService;

	/**
	 * 获取型号排列图信息
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getProductFamilyInfo")
	public void getProductFamilyInfo(CommonVo vo, HttpServletResponse respon) {
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
	 * 获取型号排列图信息
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getBetweenProdFamilyInfo")
	public void getBetweenProdFamilyInfo(CommonVo vo, HttpServletResponse respon) {
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
	 * 获取数据
	 */
	private ChartObj createRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		//modify by lin
		WebUtil.convertMultiSelectCondition(vo);
		
		//维修数获取
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairlist = commonService.getReCountGroupByProdFamily(vo);
		
		//获取发货数
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
		vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		List<CommonVo> shiplist = commonService.getShipCountGroupByProdFamily(vo);
		

		List<CommonVo> retList = new ArrayList<CommonVo>();
		Map<String, CommonVo> ShipMap = new HashMap<String, CommonVo>();
		for (int i = 0; i < shiplist.size(); i++) {
			ShipMap.put(shiplist.get(i).getProductFamily(), shiplist.get(i));
		}
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			CommonVo shipVo = ShipMap.get(repairVo.getProductFamily());
			if(shipVo!=null){
				repairVo.setShipCount(shipVo.getShipCount());
			}

			//累加
			if(repairVo.getShipCount()!=0 && retList.size() == (xCount+1)){
				CommonVo vo_21 = retList.get(xCount);
				vo_21.setRepairCount(vo_21.getRepairCount()+repairVo.getRepairCount());
				vo_21.setShipCount(vo_21.getShipCount()+repairVo.getShipCount());
			}

			if(repairVo.getShipCount()!=0 && retList.size()<(xCount+1)){
				retList.add(repairVo);
				if(retList.size() == (xCount+1)){
					retList.get(xCount).setProductFamily("其他");
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
		
		String title ="产品系列百台维修排列图";
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
		chartVo.setSubtitle("统计期间："+startMon+"~"+endMon);
		chartVo.setxTitle("型号");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		chartVo.setyRightTitle("维修率");
		chartVo.setyRightUnit("%");
		return setData(retList, chartVo,vo);
	}
	
	private ChartObj createBetweenRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		WebUtil.convertMultiSelectCondition(vo);
		
		if(StringUtils.isNotBlank(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")){//恢复服务中心
			vo.setRegion(vo.getRegion()+"服务中心");
		}
		List<CommonVo> repairlist = commonService.getReCountGroupByProdFamily(vo);
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
					retList.get(xCount).setProductFamily("其他");
				}
			}
		}
		//构造图表对象
		String title ="产品系列区间百台维修排列图";
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
		chartVo.setxTitle("型号");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<String>  tipValues = new ArrayList<String>();//维修数提示
		String[] seriesNames = {"维修数"};
		for (int i = 0; i < retList.size(); i++) {
			CommonVo tmpVo = retList.get(i);
			xvalues.add(tmpVo.getProductFamily());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
			tipValues.add(tmpVo.getRepairCount()+0.0+"");
		}
		yValues.add(yvalue1);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipText("发货数");
		return chartVo;
		
	}
	
	private ChartObj setData(List<CommonVo> reList, ChartObj chartVo,CommonVo vo) {
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
			xvalues.add(tmpVo.getProductFamily());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
			yvalue2.add(Double.parseDouble(String.format("%.2f", (tmpVo
					.getRepairCount() * 100.0 / tmpVo.getShipCount()))));
			tipValues.add(tmpVo.getShipCount()+"");
		}
		yValues.add(yvalue1);
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipText("发货数");
		return chartVo;
	}
}
