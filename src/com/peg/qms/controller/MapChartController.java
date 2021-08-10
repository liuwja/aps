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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.mes.highcharts.ChartObj;
import com.peg.mes.highcharts.ChartsType;
import com.peg.model.CommonVo;
import com.peg.service.CommonServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.StatisTypeComparator;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

/**
 * 按地图分类
 * 
 * @author bin
 *
 */
@Controller
@RequestMapping("mapChart")
public class MapChartController extends BaseController {
	
	@Autowired
	private CommonServiceI commonService;
	
	@RequestMapping("/singleMapChart")
	public String singleMapChart(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		model.addAttribute("vo", vo);
		return "qms/chart/map/mapChart";
	}
	
	@RequestMapping("/getMapInfo")
	public void getInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		ChartObj chartsInfo = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			chartsInfo = createReqairData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = 1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	private ChartObj createReqairData(CommonVo vo) throws Exception {
		vo.setPartTypeListTxt(WebUtil.parseToSelectedStr(vo.getPartTypeListTxt()));
		WebUtil.convertMultiSelectCondition(vo);		
		//维修数获取
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		vo.setEndTime(vo.getQueryMonth());
		List<CommonVo> repairlist = commonService.getReCountGroupByProvince(vo);
		
		//获取下线数
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -14));
		vo.setEndTime(WebUtil.rebackMonths(vo.getQueryMonth(), -3));
		List<CommonVo> shiplist = commonService.getShipCountGroupByProvince(vo);
		
		Map<String, CommonVo> shipMap = new HashMap<String, CommonVo>();
		for (int i = 0; i < shiplist.size(); i++) {
			shipMap.put(shiplist.get(i).getProvince(), shiplist.get(i));
		}
		
		for (int i = 0; i < repairlist.size(); i++) {
			CommonVo repairVo = repairlist.get(i);
			CommonVo shipVo = shipMap.get(repairVo.getProvince());
			if (shipVo != null) {
				repairVo.setShipCount(shipVo.getShipCount());
			}
		}
		
		//构造图表对象
		String endMon = vo.getQueryMonth();
		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -11);
		if (TmStringUtils.isNotEmpty(startMon) && startMon.contains("-")) {
			startMon = startMon.replace("-", ".");
		}
		if (TmStringUtils.isNotEmpty(endMon) && endMon.contains("-")) {
			endMon = endMon.replace("-", ".");
		}
		String title = "省份百台维修地图";
		if(StringUtils.isNotEmpty(vo.getPartType())){
			title = vo.getPartType()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getProductLineNumber())){
			title = vo.getProductLineNumber()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getMeshFaultName())){
			title = vo.getMeshFaultName()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getFaultTypeName())){
			title = vo.getFaultTypeName()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getFaultReasonTxt())){
			title = vo.getFaultReasonTxt()+"-" + title;
		}
		else if(StringUtils.isNotEmpty(vo.getProductType())){
			title = vo.getProductType()+"-" + title;
		}
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_MAP);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(title);
		chartVo.setSubtitle("统计期间："+startMon+"~"+endMon);
		chartVo.setxTitle("区域");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		chartVo.setyRightTitle("维修率");
		chartVo.setyRightUnit("%");
		return setData(repairlist, chartVo, vo);
	}
	
	public ChartObj setData(List<CommonVo> reList, ChartObj chartVo, CommonVo vo) {
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();
		List<Double> yvalue2 = new ArrayList<Double>();
		List<String> tipValues = new ArrayList<String>();
		String[] seriesNames = {"维修数", "维修率"};
		//维修率排序
		if(vo.getStatisType()!=null && "1".endsWith(vo.getStatisType())){
			Collections.sort(reList,new StatisTypeComparator());
		}
		for (int i = 0; i < reList.size(); i++) {
			CommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getProvince()==null?null:tmpVo.getProvince().replaceAll("省", ""));
			yvalue1.add(tmpVo.getRepairCount()+0.0);
			if (StringUtils.isNotEmpty(tmpVo.getShipCount() + "") && tmpVo.getShipCount() > 0) {
				yvalue2.add(Double.parseDouble(String.format("%.2f", (tmpVo.getRepairCount() * 100.0 / tmpVo.getShipCount()))));
			} else {
				yvalue2.add(0d);
			}
			tipValues.add(tmpVo.getShipCount()+"");
		}
		List<String> provinceList = checkProvince(xvalues);
		for (String province : provinceList) {
			xvalues.add(province);
			yvalue1.add(0d);
			yvalue2.add(0d);
			tipValues.add("0");
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
	
	private List<String> checkProvince(List<String> xValues) {
		String[] provinces = {"北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东",
				"海南","四川","贵州","云南","陕西","甘肃","青海","台湾","内蒙古","广西","西藏","宁夏","新疆","香港","澳门","南海诸岛"};
		List<String> provinceList = new ArrayList<String>();
		Collections.addAll(provinceList, provinces);
		provinceList.removeAll(xValues);
		return provinceList;
	}
}
