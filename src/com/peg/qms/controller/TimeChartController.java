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
import com.peg.mes.highcharts.ChartsType;
import com.peg.model.CommonVo;
import com.peg.model.RepairRateInput;
import com.peg.model.RepairRateResult;
import com.peg.service.RepairCountInputServiceI;
import com.peg.service.RepairRateComputeServiceI;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

/**
 * 时序图
 * @author song
 *
 */
@Controller
@RequestMapping("timeChart")
public class TimeChartController extends BaseController{
	
	@Autowired
	private RepairRateComputeServiceI repairRateComputeService;
	
	@Autowired
	private RepairCountInputServiceI repairCountInputService;
	
	/**
	 * 时间序列图
	 * @param model
	 * @return
	 */
	@RequestMapping("/singleChart")
	public String singleChart(Model model){
		setBaseData(model);
		String laterSumtime = (String) model.asMap().get("laterSumtime");
		model.addAttribute("queryMonth",laterSumtime);
		return "qms/chart/time/singleChart";
	}
	
	/**
	 * 折线测试
	 * @param model
	 * @return
	 */
	@RequestMapping("/lineChart")
	public String lineChart(Model model){
		setBaseData(model);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		model.addAttribute("queryMonth", DateFormatUtils.format(cal.getTime(), "yyyy-MM"));
		return "qms/chart/time/lineChart";
	}
	
	/**
	 * 累计时间序列P矩阵图
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeTotalChart")
	public String timeTotalChart(Model model){
		setBaseData(model);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		model.addAttribute("queryMonth", DateFormatUtils.format(cal.getTime(), "yyyy-MM"));
		return "qms/chart/time/timeTotalChart";
	}
	
	//时间序列图
	@RequestMapping("/getTimeInfo")
	public void getInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = new ChartObj();
		try {
			chartsInfo = createTimeDataChart(vo);
			//chartsInfo = createTimeInputDataChart(vo);
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
	
	//时间序列图--仅显示累计维修率
	@RequestMapping("/getTimeOnlyTotalInfo")
	public void getTimeOnlyTotalInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = new ChartObj();
		try {
			chartsInfo = createTimeDataChartForTotal(vo);
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
	
	private ChartObj createTimeDataChart(CommonVo vo) throws Exception{
		Map<String, List<RepairRateResult>> map = repairRateComputeService
				.getRepairRateResultByProdTypeAndMonth(vo);
		String endMon = vo.getQueryMonth();
		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -11);
		if(TmStringUtils.isNotEmpty(startMon)&&startMon.contains("-")){
			startMon = startMon.replace("-",".");
		}
		if(TmStringUtils.isNotEmpty(endMon)&&endMon.contains("-")){
			endMon = endMon.replace("-",".");
		}
		//构造图表对象
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_LINES);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(vo.getProductType()+"-时间序列图");
		chartVo.setSubtitle("统计期间："+startMon+"~"+endMon);
		chartVo.setxTitle("月份");
		chartVo.setyLeftTitle("维修率");
		chartVo.setyLeftUnit("%");
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<Double> yvalue2 = new ArrayList<Double>();//折线图
		List<Double> yvalue3 = new ArrayList<Double>();
		String[] seriesNames = {"目标维修率","百台维修率","单月维修率"};
		List<RepairRateResult> list = map.get(vo.getProductType());
		//附件提示信息
		List<String> tipValues = new ArrayList<String>();
		List<String> tipValues1 = new ArrayList<String>();
		List<String> tipValues2= new ArrayList<String>();
		
		if(list!=null){
			for (int i=0;i<list.size();i++) {
				RepairRateResult tmpVo = list.get(i);
				xvalues.add(tmpVo.getMonth());
				yvalue1.add(tmpVo.getTotalRepairRate());
				yvalue2.add(tmpVo.getSingleRepairRate());
				yvalue3.add(Double.parseDouble(String.format("%.2f",tmpVo.getBaseRepairRate())));
				if((tmpVo.getMonth()).endsWith(vo.getQueryMonth())){
					chartVo.setDefaultValue(tmpVo.getBaseRepairRate());
				}
				tipValues.add(tmpVo.getTotalShipCount()+"");
				tipValues1.add(tmpVo.getSingleRepairCount()+"");
				tipValues2.add(tmpVo.getTotalRepairCount()+"");
			}
		}
		yValues.add(yvalue3);
		yValues.add(yvalue1);
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipValues1(tipValues1);
		chartVo.setTipValues2(tipValues2);
		chartVo.setTipText("累计发货数");
		chartVo.setTipText1("单月维修数");
		chartVo.setTipText2("累计维修数");
		return chartVo;
	}
	
	public ChartObj createTimeInputDataChart(CommonVo vo) throws Exception{
		
		String endMon = vo.getQueryMonth();
		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -11);
		if(TmStringUtils.isNotEmpty(startMon)&&startMon.contains("-")){
			startMon = startMon.replace("-",".");
		}
		if(TmStringUtils.isNotEmpty(endMon)&&endMon.contains("-")){
			endMon = endMon.replace("-",".");
		}
		//构造图表对象
		ChartObj chartVo = new ChartObj();
		chartVo.setChartType(ChartsType.CHART_LINES);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(vo.getProductType()+"-时间序列图");
		chartVo.setSubtitle("统计期间："+startMon+"~"+endMon);
		chartVo.setxTitle("月份");
		chartVo.setyLeftTitle("维修率");
		chartVo.setyLeftUnit("%");
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<Double> yvalue2 = new ArrayList<Double>();//折线图
		List<Double> yvalue3 = new ArrayList<Double>();
		String[] seriesNames = {"目标维修率","累计百台维修率","百台维修率"};
		List<RepairRateInput> list = repairCountInputService.queryTimeChart(vo.getProductType(), vo.getQueryMonth());
		//附件提示信息
		List<String> tipValues = new ArrayList<String>();
		List<String> tipValues1 = new ArrayList<String>();
		List<String> tipValues2= new ArrayList<String>();
		
		if(list!=null){
			for (int i=0;i<list.size();i++) {
				RepairRateInput tmpVo = list.get(i);
				xvalues.add(tmpVo.getRepairMonth());
				yvalue1.add(tmpVo.getRepairTotalRate().doubleValue());
				yvalue2.add(tmpVo.getRepairRate().doubleValue());
				yvalue3.add(Double.parseDouble(String.format("%.2f",tmpVo.getBaseRepairRate())));
				if((tmpVo.getRepairMonth()).endsWith(vo.getQueryMonth())){
					chartVo.setDefaultValue(tmpVo.getBaseRepairRate());
				}
				tipValues.add(tmpVo.getTotalShipCount()+"");
				tipValues1.add(tmpVo.getRepairedCount()+"");
				tipValues2.add(tmpVo.getTotalRepairedCount()+"");
			}
		}
		yValues.add(yvalue3);
		yValues.add(yvalue1);
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipValues1(tipValues1);
		chartVo.setTipValues2(tipValues2);
		chartVo.setTipText("累计发货数");
		chartVo.setTipText1("单月维修数");
		chartVo.setTipText2("累计维修数");
		return chartVo;
	}
	
	//获取累计维修率数据
	public ChartObj createTimeDataChartForTotal(CommonVo vo) throws Exception{
		if(StringUtils.isNotBlank(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")){//恢复服务中心
			vo.setRegion(vo.getRegion()+"服务中心");
		}
		String title = "时序图";
		Map<String, List<RepairRateResult>> map = repairRateComputeService.getRepairRateResultByProdTypeAndMonth(vo);
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
		chartVo.setChartType(ChartsType.CHART_LINE);
		chartVo.setChartHight(vo.getHight()==0?600:vo.getHight());
		chartVo.setChartWidth(vo.getWidth()==0?1100:vo.getWidth());
		chartVo.setTitle(title);
		chartVo.setxTitle("月份");
		chartVo.setyLeftTitle("维修率");
		chartVo.setyLeftUnit("");
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();
		String[] seriesNames = {"累计百台维修率"};
		List<String> tipValues = new ArrayList<String>();//获取焦点时自定义提示值
		List<String> tipValues1 = new ArrayList<String>();//获取焦点时自定义提示值
		List<String> tipValues2 = new ArrayList<String>();//获取焦点时自定义提示值
		List<RepairRateResult> list = map.get(vo.getProductType());
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				RepairRateResult tmpVo = list.get(i);
				xvalues.add(tmpVo.getMonth());
				yvalue1.add(tmpVo.getTotalRepairRate());
				tipValues.add(tmpVo.getTotalShipCount()+"");
				tipValues1.add(tmpVo.getTotalRepairCount()+"");
				tipValues2.add(tmpVo.getSingleRepairCount()+"");
				if((tmpVo.getMonth()).endsWith(vo.getQueryMonth())){
					chartVo.setDefaultValue(tmpVo.getBaseRepairRate());
				}
			} 
		}
		yValues.add(yvalue1);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		chartVo.setTipValues(tipValues);
		chartVo.setTipValues1(tipValues1);
		chartVo.setTipValues2(tipValues2);
		chartVo.setTipUnit("%");
		chartVo.setTipText("累计发货数");
		chartVo.setTipText1("累计维修数");
		chartVo.setTipText2("单月维修数");
		
		return chartVo;
	}
	
	//折线图--test
	@RequestMapping("/getLineInfo")
	public void getLineInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = new ChartObj();
		try {
			List<String> xvalues = new ArrayList<String>();
			List<List<Double>> yValues = new ArrayList<List<Double>>();
			List<Double> yvalue1 = new ArrayList<Double>();
			String[] seriesNames = {"北京"};
			chartsInfo.setChartType(ChartsType.CHART_LINE);
			chartsInfo.setChartHight(600);
			chartsInfo.setChartWidth(1100);
			chartsInfo.setTitle("测试标题");
			chartsInfo.setxTitle("x测试");
			chartsInfo.setyLeftTitle("y测试");
			chartsInfo.setyLeftUnit("度");
			for (int i = 0; i < 12; i++) {
				xvalues.add((i+1)+"月");
				if(i<7){
					yvalue1.add(i+ Math.random()*20);
				}else{
					yvalue1.add(Math.random()*20);
				}
			}
			yValues.add(yvalue1);
			chartsInfo.setxValue(xvalues);
			chartsInfo.setSeriesNames(seriesNames);
			chartsInfo.setyValues(yValues);
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
	
	//累计时间矩阵图--test
	@RequestMapping("/getTimeTotalInfo")
	public void getTimeTotalInfo(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = new ChartObj();
		try {
			List<String> xvalues = new ArrayList<String>();
			List<List<Double>> yValues = new ArrayList<List<Double>>();
			List<Double> yvalue0 = new ArrayList<Double>();
			List<Double> yvalue1 = new ArrayList<Double>();
			List<Double> yvalue2 = new ArrayList<Double>();
			String[] seriesNames = {"基准","上控制线","下控制线"};
			chartsInfo.setChartType(ChartsType.CHART_SCATTER_LINE);
			chartsInfo.setChartHight(600);
			chartsInfo.setChartWidth(1100);
			chartsInfo.setDefaultValue(20.0);
			chartsInfo.setTitle("控制图");
			chartsInfo.setxTitle("生产月");
			chartsInfo.setyLeftTitle("");
			chartsInfo.setyLeftUnit("");
			for (int i = 0; i < 12; i++) {
				xvalues.add((i+1)+"月");
				int a = (int) (Math.random()*20*1000);
				yvalue2.add((double)(a - 2000));//下控制
				yvalue1.add((double)(i*1000+ a));//上控制
				yvalue0.add((double)(a));//基准
			}
			yValues.add(yvalue0);
			yValues.add(yvalue1);
			yValues.add(yvalue2);
			chartsInfo.setxValue(xvalues);
			chartsInfo.setSeriesNames(seriesNames);
			chartsInfo.setyValues(yValues);
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
}
