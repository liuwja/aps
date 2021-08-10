package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonVo;
import com.peg.service.CommonServiceI;
import com.peg.web.util.TmStringUtils;

@Controller
@RequestMapping("faultReason2Chart")
public class FaultReasonChartsController extends BaseController{
	
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
		return "qms/chart/faultReason2/singleChart";
	}
	
	@RequestMapping("/getFaultReasonInfo")
	public void getInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
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
	
	private ChartObj createRepairData(CommonVo vo) throws Exception
	{
		int xCount = 20;
		if(vo.getxCount()!=null && vo.getxCount()>0){
			xCount = vo.getxCount();
		}
		
		if(StringUtils.isNotBlank(vo.getFaultReasonTxt())){
			vo.setFaultReasons(vo.getFaultReasonTxt().split(","));
		}
		//维修数获取
		List<CommonVo> repairlist = commonService.getReCountGroupFaultsReason(vo);

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
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(vo.getChartType());
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(vo.getProductType()+"-故障小类百台维修排列图-单月");
		String queryMon = vo.getQueryMonth();
		if(TmStringUtils.isNotEmpty(queryMon)&&queryMon.contains("-")){
			queryMon = queryMon.replace("-",".");
		}
		chartVo.setSubtitle("统计期间："+queryMon);
		chartVo.setxTitle("故障小类");
		chartVo.setyLeftTitle("维修数");
		chartVo.setyLeftUnit("个");
		
		return setData(retList, chartVo);
	}
	
	/**
	 * 设置x轴的值，y轴的值，对应序列的值
	 * @param reList
	 * @param chartVo
	 * @return
	 */
	private ChartObj setData(List<CommonVo> reList, ChartObj chartVo) {
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		String[] seriesNames = {"故障小类"};
		
		//循环设置值
		List<Double> yvalue1 = new ArrayList<Double>();
		for (int i = 0; i < reList.size(); i++) {
			CommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getFaultReasonName());
			yvalue1.add(tmpVo.getRepairCount()+0.0);
		}
		yValues.add(yvalue1);

		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		
		return chartVo;
	}
}

