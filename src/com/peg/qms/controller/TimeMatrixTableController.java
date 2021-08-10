package com.peg.qms.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peg.mes.highcharts.ChartObj;
import com.peg.mes.highcharts.ChartsType;
import com.peg.model.CommonVo;
import com.peg.model.FaultReason;
import com.peg.model.LaterSumtime;
import com.peg.model.RepairRateResult;
import com.peg.model.TimeMatrixResultVo;
import com.peg.model.TimeMatrx;
import com.peg.model.TimePCHartVo;
import com.peg.service.CommonServiceI;
import com.peg.service.FaultReasonServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.RepairRateComputeServiceI;
import com.peg.service.TimeMatrxServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.ParseDataUtil;
import com.peg.web.util.StatisUtils;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("timeMatrixTable")
public class TimeMatrixTableController extends BaseController {

	private static final int MAX_MONTHS = 24;// 定义最大显示的月份数
	private static final int MAX_MONTHS_OTHER = 12;// 定义其他最大显示的月份数
	private static final int CUL_MONTHS = 120;// 维修数及维修率列表月份显示
	@Autowired
	private CommonServiceI commonService;

	@Autowired
	private TimeMatrxServiceI timeMatrxService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	
	@Autowired
	private RepairRateComputeServiceI repairRateComputeService;
	
	@Autowired
	private FaultReasonServiceI faultReasonService;
	
	/**
	 * 时间序列正三角阵
	 * @param model
	 * @return
	 */
	@RequestMapping("/sigleMonthReCount")
	public String sigleMonthReCount(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		model.addAttribute("vo", vo);
		List<TimeMatrixResultVo> list = null;
		String[] columnNo = null;
		if (StringUtils.isNotEmpty(vo.getProductType())) { //避免刚打开页面时做查询操作
			list = loadConDiffData(model, vo, CUL_MONTHS, null);
		}
		if (StringUtils.isNotEmpty(vo.getStatisData()) && StringUtils.isNotEmpty(vo.getMaxCount()) && vo.getStatisData().equals("repairCount")) { //维修数时设置最大值
			vo.setRepairCount(Long.parseLong(vo.getMaxCount()));
			vo.setRepairPercent(null);
		} else if (StringUtils.isNotEmpty(vo.getStatisData()) && StringUtils.isNotEmpty(vo.getMaxCount()) && vo.getStatisData().equals("repairRate")) { //维修率时设置最大值
			vo.setRepairPercent(vo.getMaxCount() + "");
			vo.setRepairCount(null);
		}
		if ("year".equals(vo.getStatisType())) { // 年月季度统计
			int length = list.get(0).getReCount().size() / 12;
			if (list.get(0).getReCount().size() % 12 != 0) {
				length++;
			}
			columnNo = StatisUtils.getArryStr(1, length);
			List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
			model.addAttribute("list", yearList);
		} else if ("quarter".equals(vo.getStatisType())) {
			int length = list.get(0).getReCount().size() / 3;
			if (list.get(0).getReCount().size() % 3 != 0) {
				length++;
			}
			columnNo = StatisUtils.getArryStr(1, length);
			List<TimeMatrixResultVo> quarterList = ParseDataUtil.splitData(list, model, vo, 3);
			model.addAttribute("list", quarterList);
		} else {
			columnNo = StatisUtils.getArryStr(1, list != null ? list.get(0).getReCount().size() : 10);
			model.addAttribute("list", list);
		}
		model.addAttribute("columnNo", columnNo);
		model.addAttribute("columnSize", columnNo.length);
		model.addAttribute("vo", vo);
		return "qms/table/time/sigleMonthReCount";
	}
	
	/**
	 * 时间序列倒三角阵
	 * @param model
	 * @return
	 */
	@RequestMapping("/sigleDownMonthReCount")
	public String sigleDownMonthReCount(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		model.addAttribute("vo", vo);
		if (StringUtils.isNotEmpty(vo.getStatisData()) && StringUtils.isNotEmpty(vo.getMaxCount()) && vo.getStatisData().equals("repairCount")) { //维修数时设置最大值
			vo.setRepairCount(Long.parseLong(vo.getMaxCount()));
			vo.setRepairPercent(null);
		} else if (StringUtils.isNotEmpty(vo.getStatisData()) && StringUtils.isNotEmpty(vo.getMaxCount()) && vo.getStatisData().equals("repairRate")) { //维修率时设置最大值
			vo.setRepairPercent(vo.getMaxCount() + "");
			vo.setRepairCount(null);
		}
		List<TimeMatrixResultVo> list = null;
		Set<String> column = null;
		if (StringUtils.isNotEmpty(vo.getProductType())) {
			list = loadConDiffData(model, vo, CUL_MONTHS, null);
			column = WebUtil.getBetweenMonth(list.get(0).getBaseMonth(), vo.getEndTime());
		}
		if ("year".equals(vo.getStatisType())) { // 年月季度统计
			List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
			model.addAttribute("list", yearList);
			column = WebUtil.getBetweenYear(column);
		} else if ("quarter".equals(vo.getStatisType())) {
			List<TimeMatrixResultVo> quarterList = ParseDataUtil.splitData(list, model, vo , 3);
			model.addAttribute("list", quarterList);
			column = WebUtil.getBetweenQuarter(column);
		} else if ("month".equals(vo.getStatisType())) {
			for(int i = 0; i < list.size(); i++){
				list.get(i).setPreDiff(i);
			}
			model.addAttribute("list", list);
		}
		model.addAttribute("column", column);
//		return "qms/table/time/sigleDownMonthReCount";
		return "qms/table/time/sigleMonthReCount";
	}
	
	/**
	 * 时间序列图（单月累计维修数）
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/sigleMonthReTotalCount")
	public String sigleMonthReTotalCount(Model model, CommonVo vo) {
		setBaseData(model);
		model.addAttribute("vo", vo);
		String laterSumtime = (String) model.asMap().get("laterSumtime");
		if (vo.getEndTime() == null || vo.getEndTime()==""||vo.getEndTime().compareTo(laterSumtime)>0) {
			vo.setEndTime(laterSumtime);
		}
		vo.setQueryMonth(vo.getEndTime());
		if (vo.getProductType() != null) {
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS, null);
			model.addAttribute("list", list);
		}
		return "qms/table/time/sigleMonthReTotalCount";
	}

	/**
	 * 时间序列图（三角阵-累计维修数）
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/trgMatrixReTotalCount")
	public String trgMatrixReTotalCount(Model model, CommonVo vo) {
		setBaseData(model);
		model.addAttribute("vo", vo);
		Calendar cal = Calendar.getInstance();
		if (vo.getQueryMonth() == null) {
			String laterSumtime = (String) model.asMap().get("laterSumtime");
			vo.setQueryMonth(laterSumtime);
		}
		String[] dateArr = vo.getQueryMonth().split("-");
		cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1,1);
		vo.setEndTime(vo.getQueryMonth());
		cal.add(Calendar.MONTH, -35);// 往后退36个月
		vo.setStartTime(DateFormatUtils.format(cal, "yyyy-MM"));
		String[] columnNo = StatisUtils.getArryStr(1,12);
		if (vo.getProductType() != null) {
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS_OTHER,null);
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				columnNo = StatisUtils.getArryStr(1,2);
				List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
				model.addAttribute("list", yearList);
			} else if ("quarter".equals(vo.getStatisType())) {
				columnNo = StatisUtils.getArryStr(1,4);
				List<TimeMatrixResultVo> quarterList = ParseDataUtil.splitData(list, model, vo, 3);
				model.addAttribute("list", quarterList);
			} else {
				model.addAttribute("list", list);
			}
		}
		model.addAttribute("columnNo", columnNo);
		return "qms/table/time/trgMatrixReTotalCount";
	}

	/**
	 * 时间序列图（三角阵-累计维修率）
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/trgMatrixRePercent")
	public String trgMatrixRePercent(Model model, CommonVo vo) {
		setBaseData(model);
		model.addAttribute("vo", vo);
		Calendar cal = Calendar.getInstance();
		String laterSumtime = (String) model.asMap().get("laterSumtime");
		if (vo.getQueryMonth() == null) {
			vo.setQueryMonth(laterSumtime);
		}
		String[] columnNo = StatisUtils.getArryStr(1, 12);
		if (vo.getProductType() != null) {
			String[] dateArr = vo.getQueryMonth().split("-");
			cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1, 1);
			vo.setEndTime(vo.getQueryMonth());
			List<TimeMatrx> timeMatrxList = timeMatrxService.getAllByMachineType(vo.getProductType(), vo.getStatisType());
			cal.add(Calendar.MONTH, timeMatrxList.size() > 0 ? -(35 + timeMatrxList.size()) : -35);// 往后退36个月
			vo.setStartTime(DateFormatUtils.format(cal, "yyyy-MM"));
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS_OTHER, null);
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
				columnNo = StatisUtils.getArryStr(1,1);
				model.addAttribute("list", yearList);
			} else if ("quarter".equals(vo.getStatisType())) {
				List<TimeMatrixResultVo> quarterList = ParseDataUtil.splitData(list, model, vo, 3);
				columnNo = StatisUtils.getArryStr(1,4);
				model.addAttribute("list", quarterList);
			} else {
				model.addAttribute("list", list);
			}
		}
		model.addAttribute("columnNo", columnNo);
		return "qms/table/time/trgMatrixRePercent";
	}
	
	/**
	 * 累计时间P控制图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeTotalPChart")
	public String timeTotalPChart(Model model, CommonVo vo) {
		setBaseData(model);
		model.addAttribute("vo", vo);
		String laterSumtime = (String) model.asMap().get("laterSumtime");
		if (vo.getQueryMonth() == null) {
			vo.setQueryMonth(laterSumtime);
		}
		return "qms/table/time/timeTotalPChart";
	}

	private List<TimePCHartVo> getChartData(Model model, CommonVo vo) {
//		@SuppressWarnings("unchecked")
//		List<TimeMatrixResultVo> list = (List<TimeMatrixResultVo>) model.asMap().get("list");
		List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS_OTHER, null);
//		List<TimeMatrixResultVo> list = new ArrayList<TimeMatrixResultVo>();
		List<TimePCHartVo> charts = new ArrayList<TimePCHartVo>();
//		for (TimeMatrixResultVo tempVo : timeMatrixResultList) {
//			if (tempVo.isEffective()) {
//				list.add(tempVo);
//			}
//		}
		for (int i = (list.size() - 1); i >= 0; i--) {
			TimeMatrixResultVo matVo = list.get(i);
			for (int j = 0; (j <= (list.size() - i - 1) && j < 12); j++) {
				if (charts.size() < (j + 1)) {
					TimePCHartVo tmpVo = new TimePCHartVo();
					charts.add(tmpVo);
					tmpVo.setStaticMonth(matVo.getBaseMonth()); //设置生产月份
					tmpVo.setRepairPercent(matVo.getRepairTotalPercent().get(j)); //设置累计维修
					tmpVo.setDownlineCount(matVo.getBaseCount()); //设置当月维修
					
					List<String> repairPercentList = tmpVo.getRepairPercentList();
					repairPercentList.add(matVo.getRepairTotalPercent().get(j) + "");
					tmpVo.setRepairPercentList(repairPercentList);
				}
				TimePCHartVo chartVo = charts.get(j);
				if (chartVo.getCount() < 24 && matVo.isEffective()) {
					chartVo.getBaseMonth().add(matVo.getBaseMonth() + "");
					chartVo.getBaseCountArr().add(matVo.getBaseCount() + "");
					chartVo.getRepairArr().add(matVo.getReTotalCount().get(j) + "");
//					chartVo.setBaseMonth(chartVo.getBaseMonth());
//					chartVo.setBaseCountArr(chartVo.getBaseCountArr());
//					chartVo.setRepairArr(chartVo.getRepairArr());
					
					
					chartVo.setTotalDownlineCount(chartVo.getTotalDownlineCount() + matVo.getBaseCount());
					chartVo.setTotalRepairCount(chartVo.getTotalRepairCount() + matVo.getReTotalCount().get(j));
					chartVo.setCount(chartVo.getCount() + 1);
				}
			}
			if (charts.size() == 12 && charts.get(11).getCount() == 24) {// 最后一个加满24就退出循环
				break;
			}
		}

		List<TimePCHartVo> retList = new ArrayList<TimePCHartVo>();
		for (int i = 0; i < charts.size(); i++) {
			TimePCHartVo chartVo = charts.get(i);
			retList.add(chartVo);
			double pNumber = chartVo.getTotalDownlineCount() == 0 ? 0.0 : (Double.parseDouble(String.format("%.10f", (chartVo.getTotalRepairCount() * 1.0d / chartVo.getTotalDownlineCount()))));
			if(pNumber>1){
				pNumber=1;
			}
			double qNumber = chartVo.getDownlineCount() == 0 ? 0.0 : (Double.parseDouble(String.format("%.10f", (3 * Math.sqrt(pNumber * (1 - pNumber)) / Math.sqrt(chartVo.getDownlineCount())))));
			chartVo.setPNumber(pNumber);
			chartVo.setQNumber(qNumber);
		}

		return retList;
	}

	// 累计时间矩阵图
	@RequestMapping("/getTimeTotalInfo")
	public void getTimeTotalInfo(CommonVo vo, HttpServletResponse respon,Model model) throws Exception{
		vo.setStatisType("month");
		if (StringUtils.isNotEmpty(vo.getMeshFaultName()) && StringUtils.isEmpty(vo.getMeshFaultReasonCode())) {
			StringBuilder sb = new StringBuilder();
			FaultReason fr = new FaultReason();
			fr.setProductType(vo.getProductType());
			fr.setMeshFaultNameArr(vo.getMeshFaultName().split(","));
			for(FaultReason tempFr : faultReasonService.getMeshCodeByMeshName(fr)) {
				sb.append(tempFr.getMeshFaultCode() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			vo.setMeshFaultReasonCode(sb.toString());
		}
		List<TimeMatrx> timeMatrxList = timeMatrxService.getAllByMachineType(vo.getProductType(), vo.getStatisType());
//		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -11));
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), 
				timeMatrxList != null && timeMatrxList.size() > 0 ? -(35 + timeMatrxList.size()) : -35)); //往后退36个月，若有N个无效月则再退36 + N月
		vo.setEndTime(vo.getQueryMonth());
//		List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS, null);
//		model.addAttribute("list", list);
		List<TimePCHartVo> retList = getChartData(model, vo);

		int result = 0;
		String msg = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String title = "P控制图";
		String endMon = vo.getQueryMonth();
		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -11);
		if(TmStringUtils.isNotEmpty(startMon) && startMon.contains("-")){
			startMon = startMon.replace("-",".");
		}
		if(TmStringUtils.isNotEmpty(endMon) && endMon.contains("-")){
			endMon = endMon.replace("-",".");
		}
		ChartObj chartsInfo = new ChartObj();
		try {
			if (StringUtils.isNotEmpty(vo.getPartType())) {
				title = vo.getPartType() + "-" + title;
			} else if (StringUtils.isNotEmpty(vo.getRegion())) {
				title = (vo.getRegion().replaceAll("服务中心", "")) + "-" + title;
			} else if (StringUtils.isNotEmpty(vo.getProductLineNumber())) {
				title = vo.getProductLineNumber() + "-" + title;
			} else if (StringUtils.isNotEmpty(vo.getMeshFaultName())) {
				title = vo.getMeshFaultName() + "-" + title;
			} else if (StringUtils.isNotEmpty(vo.getFaultReasonTxt())) {
				title = vo.getFaultReasonTxt() + "-" + title;
			} else if (StringUtils.isNotEmpty(vo.getFaultTypeName())) {
				title = vo.getFaultTypeName() + "-" + title;
			}else if (StringUtils.isNotEmpty(vo.getProductType())) {
				title = vo.getProductType() + "-" + title;
			}else if (StringUtils.isNotEmpty(vo.getFaultReasonName())) {
				title = vo.getFaultReasonName() + "-" + title;
			}else if (StringUtils.isNotEmpty(vo.getPartNumber())) {
				title = vo.getPartNumber() + "-" + title;
			}

			List<String> xvalues = new ArrayList<String>();
			List<List<Double>> yValues = new ArrayList<List<Double>>();
			List<Double> yvalue0 = new ArrayList<Double>();
			List<Double> yvalue1 = new ArrayList<Double>();
			List<Double> yvalue2 = new ArrayList<Double>();
			String[] seriesNames = { "累计维修率", "上控制线", "下控制线" };
			chartsInfo.setChartType(ChartsType.CHART_SCATTER_LINE);
			chartsInfo.setChartHight(vo.getHight() == 0 ? 600 : vo.getHight());
			chartsInfo.setChartWidth(vo.getWidth() == 0 ? 1100 : vo.getWidth());
			chartsInfo.setDefaultValue(20.0);
			chartsInfo.setTitle(title);
			chartsInfo.setSubtitle("统计期间：" + startMon + "~" + endMon);
			chartsInfo.setxTitle("生产月");
			chartsInfo.setyLeftTitle("");
			chartsInfo.setyLeftUnit("");
			for (int i = (retList.size() - 1); i >= 0; i--) {
				TimePCHartVo tmpObj = retList.get(i);
				xvalues.add("第" + (i + 1) + "月," + tmpObj.getStaticMonth());
				double downPNumber = Double.parseDouble(String.format("%.2f", ((tmpObj.getPNumber() - tmpObj.getQNumber()) * 1000000))); // 下控制
				if(downPNumber < 0){
					downPNumber = 0;
				}
				yvalue2.add(downPNumber);
				yvalue1.add(Double.parseDouble(String.format("%.2f", ((tmpObj.getPNumber() + tmpObj.getQNumber()) * 1000000)))); // 上控制
				yvalue0.add(Double.parseDouble(String.format("%.2f", (tmpObj.getRepairPercent())))); // 基准
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

	/**
	 * 根据条件（年/月/季度）统计维修数/维修率
	 * 
	 * @param model
	 * @param vo
	 * @param maxMonths
	 * @param type :preBack统计返回至2001-01月份
	 */
	private List<TimeMatrixResultVo> loadConDiffData(Model model, CommonVo vo, int maxMonths,String type) {

		if (StringUtils.isNotEmpty(vo.getPartTypeListTxt())) {
			vo.setPartTypes(vo.getPartTypeListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getPlineListTxt())) {
			vo.setPlines(vo.getPlineListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getRegionListTxt())) {
			vo.setRegions(vo.getRegionListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getProductFamilyTxt())) {
			vo.setProductFamilys(vo.getProductFamilyTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getRegion()) && !vo.getRegion().endsWith("服务中心")) {// 恢复服务中心
			vo.setRegion(vo.getRegion() + "服务中心");
		}
		if(StringUtils.isNotEmpty(vo.getGasCategoryTxt())){
			vo.setGasCategorys(vo.getGasCategoryTxt().replaceAll("'","").split(","));
		}

		// 故障小类
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		//合并故障小类
		if(StringUtils.isNotEmpty(vo.getMeshFaultReasonCode())){
			vo.setMeshFaultReasons(vo.getMeshFaultReasonCode().split(","));
		}
		// 故障大类
//		if (StringUtils.isNotEmpty(vo.getFaultTypeTxt())) {
//			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
//		}
		if (StringUtils.isNotEmpty(vo.getFaultTypeCode())) {
			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
		}
		//物料号
		if (StringUtils.isNotEmpty(vo.getPartNumber())) {
			vo.setPartTypes(vo.getPartNumber().split(","));
		}
		//VOC分类
		if(StringUtils.isNotEmpty(vo.getVocTypeID())){
			vo.setVocTypeIDs(vo.getVocTypeID().split(","));
		}

		Long maxCount = 0l; //最大维修数
		Long maxPercent = 0l; //最大维修率
		Calendar cal = Calendar.getInstance();
		List<CommonVo> downlineCountList = commonService.getDownLineCountGroupByMonth(vo);
		List<CommonVo> repairCountList = commonService.getReCountGroupByReDlMonth(vo);
//		if(type=="preBack"){
//			downlineCountList = converDlcount(downlineCountList,vo);
//		}
		int l = WebUtil.getMonthQuantity(vo.getStartTime(), vo.getEndTime());
		downlineCountList = WebUtil.supplementMonth(downlineCountList, "DownLineMonth", vo.getStartTime(), vo.getEndTime());
		TimeMatrx record = new TimeMatrx();
		record.setMachineType(vo.getProductType());
		record.setStatisType(vo.getStatisType());
		Map<String, TimeMatrx> timeIsEffectiveMap = timeMatrxService.getAllByMachineType(record);
		Map<String, Long> reMap = new HashMap<String, Long>();

		for (int i = 0; i < repairCountList.size(); i++) {
			CommonVo reVo = repairCountList.get(i);
			reMap.put(reVo.getDownLineMonth() + "&" + reVo.getRepairMonth(), reVo.getRepairCount());
		}
		List<TimeMatrixResultVo> list = new ArrayList<TimeMatrixResultVo>();
		for (int i = 0; i <= l; i++) {
			CommonVo downlineVo = downlineCountList.get(i);
			if (downlineVo.getDownLineMonth()==null || downlineVo.getDownLineMonth().compareTo(vo.getEndTime()) > 0) {// 只统计到基准月份
				break;
			}
			String[] timeArr = downlineVo.getDownLineMonth().split("-");
			TimeMatrixResultVo tmpVo = new TimeMatrixResultVo();
			tmpVo.setBaseMonth(downlineVo.getDownLineMonth());
			tmpVo.setBaseCount(downlineVo.getDownlineCount());
			String key = vo.getProductType() + "-" + vo.getStatisType() + "-" + tmpVo.getBaseMonth();
			if (timeIsEffectiveMap.containsKey(key)) {
				tmpVo.setEffective(false);
			} else {
				tmpVo.setEffective(true);
			}
			cal.set(Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]) - 1, 1);
			List<Long> reclist = new ArrayList<Long>();// 单月维修
			List<Long> recTotallist = new ArrayList<Long>();// 累计维修
			List<Long> recPercentlist = new ArrayList<Long>();// 单月维修率
			List<Long> recTotalPercentlist = new ArrayList<Long>();// 累计维修率
			for (int j = 0; j < maxMonths; j++) {
				if (j != 0) {
					cal.add(Calendar.MONTH, 1);
				}
				String month = DateFormatUtils.format(cal, "yyyy-MM");
				if (month.compareTo(vo.getEndTime()) > 0) {// 只到当前月份，未来月份不作统计
					continue;
				}

				String mapKey = downlineVo.getDownLineMonth() + "&" + month;
				Long mapValue = reMap.get(mapKey);
				Long mapTotalValue = reMap.get(mapKey);
				if (mapValue == null) { //当某月份没有维修数时，设置为0
					mapValue = 0L;
					mapTotalValue = 0L;
				}
				if (mapValue > maxCount) { //设置最大维修数
					maxCount = mapValue;
				}
				reclist.add(mapValue);// 添加单月维修数
				if (recTotallist.size() != 0) {
					mapTotalValue += recTotallist.get(recTotallist.size() - 1);
				}
				recTotallist.add(mapTotalValue);// 添加单月累计维修数
				if(tmpVo.getBaseCount() != 0L){
					long tempMaxPercent = Math.round(mapValue * 1000000.0 / tmpVo.getBaseCount());
					if (tempMaxPercent > maxPercent) { //最大维修率
						maxPercent = tempMaxPercent;
					}
					recPercentlist.add(tempMaxPercent);// 单月维修率
					recTotalPercentlist.add(Math.round(mapTotalValue * 1000000.0 / tmpVo.getBaseCount()));// 累计维修率
				}else{
					recPercentlist.add(0L);
					recTotalPercentlist.add(0L);
				}
			}
			
			tmpVo.setReCount(reclist);
			tmpVo.setReTotalCount(recTotallist);
			tmpVo.setRepairPercent(recPercentlist);
			tmpVo.setRepairTotalPercent(recTotalPercentlist);
			list.add(tmpVo);
		}
		if ("repairCount".equals(vo.getStatisData())) {
			if (StringUtils.isNotEmpty(vo.getMaxCount())) {
				model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), Integer.parseInt(vo.getMaxCount())));
			} else {
				model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), null));
			}
		} else if ("repairRate".equals(vo.getStatisData())) {
			if (StringUtils.isNotEmpty(vo.getMaxCount())) {
				model.addAttribute("rangePercentList", StatisUtils.getArrageIntList(Integer.parseInt(maxPercent + ""), Integer.parseInt(vo.getMaxCount())));
			} else {
				model.addAttribute("rangePercentList", StatisUtils.getArrageIntList(Integer.parseInt(maxPercent + ""), null));
			}
		}
		model.addAttribute("vo", vo);
		return list;
	}

	/**
	 * 时间时序图：转换统计时间段
	 * @param downlineCountList
	 * @return
	 */
	public List<CommonVo> converDlcount(List<CommonVo> downlineCountList,CommonVo vo){
		String backMonth = vo.getStartTime();
		if(TmStringUtils.isEmpty(backMonth)){
			backMonth = "2001-01";
		}
		List<String> dateList = StatisUtils.getBetweenMonth(backMonth, vo.getEndTime());
		List<CommonVo> newDcountList = new ArrayList<CommonVo>();
		for(int i=0;i<dateList.size();i++){
			String staicMonth = dateList.get(i);
			CommonVo newComvo = null;
			for(int j=0;j<downlineCountList.size();j++){
				CommonVo downlineVo = downlineCountList.get(j);
				String downMonth = downlineVo.getDownLineMonth();
				if(staicMonth.equals(downMonth)){
					newComvo = downlineVo;
				}
			}
			if(newComvo==null){
				newComvo = new CommonVo();
				newComvo.setDownLineMonth(staicMonth);
				newComvo.setDownlineCount(0L);
			}
			newDcountList.add(newComvo);
		}
		return newDcountList;
	}
	
	/**
	 * 时间序列正三角阵（单月维修数）导出excel
	 */
	@RequestMapping("/excelOutput_sigleMonthReCount")
	public void excelOutput_sigleMonthReCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		String laterSumtime = laterTime.getSumMonth();
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		if (vo.getEndTime() == null || vo.getEndTime()=="" ) {
			vo.setEndTime(laterSumtime);
		}
		vo.setQueryMonth(vo.getEndTime());
		try {
			List<TimeMatrixResultVo> list = null;
			String[] CN = null;
			if (vo.getProductType() != null) {
				list = loadConDiffData(model, vo, CUL_MONTHS, null);
			}
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 12);
				CN = new String[list.size() + 2];
				CN[1] = "";
			} else if ("quarter".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 3);
				CN = new String[list.size() + 2];
			} else {
				CN = new String[list.size() + 2];
			}
			CN[0] = "生产月份";
			CN[1] = "生产台数";
			for (int j = 1; j <= CN.length - 2; j++) {
				CN[j + 1] = j + "";
			}

			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				if (vo.getStatisData().equals("repairCount")) {
					for (int j = 0; j < tmpVO.getReCount().size(); j++) {
						if(j+2>=tmpStr.length){
							continue;
						}
						String Count = null;
						if (tmpVO.getReCount().get(j) == null) {
							Count = " ";
						} else {
							Count = Long.toString(tmpVO.getReCount().get(j));
						}
						tmpStr[j + 2] = Count;
					}
				} else {
					for (int j = 0; j < tmpVO.getRepairPercent().size(); j++) {
						if(j+2>=tmpStr.length){
							continue;
						}
						String Count = null;
						if (tmpVO.getRepairPercent().get(j) == null) {
							Count = " ";
						} else {
							Count = Double.toString(tmpVO.getRepairPercent().get(j));
						}
						tmpStr[j + 2] = Count;
					}
				}
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "时间序列矩阵(单月维修数)" + fname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 时间序列倒三角阵（单月维修数）导出excel
	 */
	@RequestMapping("/excelOutput_sigleDownMonthReCount")
	public void excelOutput_sigleDownMonthReCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		String laterSumtime = laterTime.getSumMonth();
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		if (vo.getEndTime() == null || vo.getEndTime()=="" ) {
			vo.setEndTime(laterSumtime);
		}
		vo.setQueryMonth(vo.getEndTime());
		try {
			List<TimeMatrixResultVo> list = null;
			String[] CN = null;
			Set<String> column = null;
			if (vo.getProductType() != null) {
				list = loadConDiffData(model, vo, CUL_MONTHS, null);
			}
			column = WebUtil.getBetweenMonth(list.get(0).getBaseMonth(), vo.getEndTime());
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 12);
				column = WebUtil.getBetweenYear(column);
			} else if ("quarter".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 3);
				column = WebUtil.getBetweenQuarter(column);
			} else{
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}
			}
			//设置表头
			CN = new String[column.size() + 2];
			CN[0] = "生产月份";
			CN[1] = "生产台数";
			List<String> coluList = new ArrayList<String>();
			coluList.addAll(column);
			for(int j=1;j<=CN.length-2;j++){
				CN[j+1]=coluList.get(j-1);
			}
			//填表内容 
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				if (vo.getStatisData().equals("repairCount")) {
					for (int j = 0; j < tmpVO.getReCount().size(); j++) {
						int diff = tmpVO.getPreDiff();
						if(j+diff + 2>=tmpStr.length){
							continue;
						}
						for(int k=0;k<diff;k++){
							tmpStr[k+2] = " ";
						}
						String Count = null;
						if (tmpVO.getReCount().get(j) == null) {
							Count = " ";
						} else {
							Count = Long.toString(tmpVO.getReCount().get(j));
						}
						tmpStr[j+diff + 2] = Count;
					}
				} else {
					for (int j = 0; j < tmpVO.getRepairPercent().size(); j++) {
						int diff = tmpVO.getPreDiff();
						if(j+diff + 2>=tmpStr.length){
							continue;
						}
						for(int k=0;k<diff;k++){
							tmpStr[k+2] = " ";
						}
						String Count = null;
						if (tmpVO.getRepairPercent().get(j) == null) {
							Count = " ";
						} else {
							Count = Long.toString(tmpVO.getRepairPercent().get(j));
						}
						tmpStr[j+diff + 2] = Count;
					}
				}
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "时间序列倒三角阵(单月维修数)" + fname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 时间序列矩阵-累计维修数导出excel
	 * 
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/excelOutput_sigleMonthReTotalCount")
	public void excelOutput_sigleMonthReTotalCount(CommonVo vo, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS,null);
			String[] CN = { "生产月份", "生产台数", "1", "2", "3", "4", "5", "6", "7",
					"8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
					"18", "19", "20", "21", "22", "23", "24" };
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getReTotalCount().size(); j++) {
					String Count = null;
					if (tmpVO.getReTotalCount().get(j) == null) {
						Count = " ";
					} else {
						Count = Long.toString(tmpVO.getReTotalCount().get(j));
					}
					tmpStr[j + 2] = Count;
				}
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "时间序列矩阵(累计维修数)" + fname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 时间序列矩阵(三角阵-累计维修数)
	 * 
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("/excelOutput_trgMatrixReTotalCount")
	public void excelOutput_trgMatrixReTotalCount(CommonVo vo, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		if (vo.getQueryMonth() == null) {
			vo.setQueryMonth(laterTime.getSumMonth());
		}
		try {
			String[] dateArr = vo.getQueryMonth().split("-");
			cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1,1);
			vo.setEndTime(vo.getQueryMonth());
			cal.add(Calendar.MONTH, -36);// 往后退36个月
			vo.setStartTime(DateFormatUtils.format(cal, "yyyy-MM"));
			String[] columnNo = StatisUtils.getArryStr(1,12);
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS_OTHER,null);
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				columnNo = StatisUtils.getArryStr(1,2);
				list = ParseDataUtil.splitData(list, model, vo, 12);
			} else if ("quarter".equals(vo.getStatisType())) {
				columnNo = StatisUtils.getArryStr(1,5);
				list = ParseDataUtil.splitData(list, model, vo, 3);
			} 
			String[] CN = new String[columnNo.length + 2];
			CN[0] = "生产月份";
			CN[1] = "生产台数";
			for (int j = 1; j <= CN.length - 2; j++) {
				CN[j + 1] = columnNo[j-1];
			}
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getReTotalCount().size(); j++) {
					if(j+2>=tmpStr.length){
						continue;
					}
					String Count = null;
					if (tmpVO.getReTotalCount().get(j) == null) {
						Count = " ";
					} else {
						Count = Long.toString(tmpVO.getReTotalCount().get(j));
					}
					tmpStr[j + 2] = Count;
				}
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "时间序列矩阵(三角阵-累计维修数)" + fname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 时间序列矩阵(三角阵-累计维修率)
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("/excelOutput_trgMatrixRePercent")
	public void excelOutput_trgMatrixRePercent(CommonVo vo, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		if (vo.getQueryMonth() == null) {
			vo.setQueryMonth(laterTime.getSumMonth());
		}

		String[] dateArr = vo.getQueryMonth().split("-");
		cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1,1);
		vo.setEndTime(vo.getQueryMonth());
		cal.add(Calendar.MONTH, -36);// 往后退36个月
		vo.setStartTime(DateFormatUtils.format(cal, "yyyy-MM"));
		try {
			String[] CN =null;
			if ("year".equals(vo.getStatisType())) {
				CN = new String[4];
			} else if ("quarter".equals(vo.getStatisType())) {
				CN = new String[7];
			} else {
				CN = new String[15];
			}
			CN[0] = "生产月份";
			CN[1] = "生产台数";
			CN[CN.length-1] ="是否有效";
			for (int j = 1; j <= CN.length - 3; j++) {
				CN[j + 1] = j + "";
			}
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, MAX_MONTHS_OTHER,null);
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 12);
			} else if ("quarter".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 3);
			}
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getRepairTotalPercent().size(); j++) {
					String Count = null;
					if (tmpVO.getRepairTotalPercent().get(j) == null) {
						Count = " ";
					} else {
						Count = Double.toString(tmpVO.getRepairTotalPercent()
								.get(j));
					}
					tmpStr[j + 2] = Count;
				}

				tmpStr[CN.length-1] = tmpVO.isEffective() ? "是" : "否";
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "时间序列矩阵(三角阵-累计维修率)" + fname);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(TimeMatrx timeMatrx)
	{
		try {
			timeMatrx.setMachineType(decode(timeMatrx.getMachineType()));
			timeMatrx.setStatisType(decode(timeMatrx.getStatisType()));
			timeMatrxService.deleteByPrimaryKey(timeMatrx);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	
		return ajaxDoneSuccess("取消成功");	
	}
	
	@RequestMapping("/saveTimeMatrx")
	public ModelAndView saveTimeMatrx(TimeMatrx timeMatrx) throws UnsupportedEncodingException
	{
		try {
			timeMatrx.setMachineType(decode(timeMatrx.getMachineType()));
			timeMatrxService.insert(timeMatrx);
		} catch (Exception e) {
			logger.error(e);
			return ajaxDoneError("标记失败");
		}
		testGetTimeMatrx();
		return ajaxDoneSuccess("标记成功");
	}

	@RequestMapping("/underWarrantyChart")
	public String underWarrantyChart(Model model, CommonVo vo) throws Exception {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		model.addAttribute("vo", vo);
		return "qms/table/time/underWarrantyChart";
	}
	
	@RequestMapping("/getUnderWarrantyChartData")
	public void getUnderWarrantyChartData(CommonVo vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = new ChartObj();
		try {
			chartsInfo = createUnderWarrantyChart(vo);
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
	
	private ChartObj createUnderWarrantyChart(CommonVo vo) throws Exception {
		Map<String, List<RepairRateResult>> map = repairRateComputeService.getUnderWarrantyRepairRateResultByProdTypeAndMonth(vo);
		String endMon = vo.getQueryMonth();
		String startMon = WebUtil.rebackMonths(vo.getQueryMonth(), -59);
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
		chartVo.setTitle(vo.getProductType()+"-保修时间序列图");
		chartVo.setSubtitle("统计期间："+startMon+"~"+endMon);
		chartVo.setxTitle("月份");
		chartVo.setyLeftTitle("维修率");
		chartVo.setyLeftUnit("%");
		
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();//柱状图
		List<Double> yvalue2 = new ArrayList<Double>();//折线图
		List<Double> yvalue3 = new ArrayList<Double>();
		String[] seriesNames = {"百台维修率"};
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
	
	public void testGetTimeMatrx(){
		TimeMatrx record = new TimeMatrx();
		record.setMachineType("灶具");
		Map<String, TimeMatrx> map = timeMatrxService.getAllByMachineType(record);
		for(Map.Entry<String,TimeMatrx>entry:map.entrySet()){
			String key = entry.getKey().toString();
			   String value = entry.getValue().getMachineType();
			   System.out.println("key=" + key + " value=" + value);
		}
		
	}
	
//	/**
//	 * 时间序列正三角阵（单月维修率）
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/sigleMonthRePercent")
//	public String sigleMonthRePercent(Model model, CommonVo vo) {
//		setBaseData(model);
//		ConditionUtil.loadPartLineRegion(model, vo, commonService);
//		model.addAttribute("vo", vo);
//		if (vo.getEndTime() == null || vo.getEndTime()=="") {
//			String laterSumtime = (String) model.asMap().get("laterSumtime");
//			vo.setEndTime(laterSumtime);
//		}
//		List<TimeMatrixResultVo> list = loadConDiffData(model, vo, CUL_MONTHS,"preBack");
//		String[] columnNo = null;
//		if (vo.getRepairCount() != null && vo.getRepairCount() >= 10) {
//			vo.setRepairPercent(vo.getRepairCount() + "");
//			vo.setRepairCount(null);
//		}
//		if ("year".equals(vo.getStatisType())) { // 年月季度统计
//			columnNo = StatisUtils.getArryStr(1, CUL_MONTHS / 12);
//			List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
//			model.addAttribute("list", yearList);
//		} else if ("quarter".equals(vo.getStatisType())) {
//			List<TimeMatrixResultVo> quarterList = ParseDataUtil.parseToQuarter(list, model);
//			model.addAttribute("list", quarterList);
//			columnNo = StatisUtils.getArryStr(1,CUL_MONTHS / 3);
//		} else {
//			columnNo = StatisUtils.getArryStr(1,CUL_MONTHS);
//			model.addAttribute("list", list);
//		}
//		model.addAttribute("columnNo", columnNo);
//		model.addAttribute("columnSize", columnNo.length);
//		return "qms/table/time/sigleMonthRePercent";
//	}
	
//	/**
//	 * 时间序列倒三角阵（单月维修率）
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/sigleDownMonthRePercent")
//	public String sigleDownMonthRePercent(Model model, CommonVo vo) {
//		setBaseData(model);
//		model.addAttribute("vo", vo);
//		String laterSumtime = (String) model.asMap().get("laterSumtime");
//		if (vo.getEndTime() == null || vo.getEndTime()=="") {
//			vo.setEndTime(laterSumtime);
//		}
//		vo.setQueryMonth(vo.getEndTime());
//		List<TimeMatrixResultVo> list = null;
//		Set<String> column = null;
//		if (vo.getProductType() != null) {
//			if (vo.getRepairCount() != null && vo.getRepairCount() >= 10) {
//				vo.setRepairPercent(vo.getRepairCount() + "");
//				vo.setRepairCount(null);
//			}
//			column = WebUtil.getBetweenMonth(vo.getStartTime(), vo.getEndTime());
//			vo.setMatrixTableType("reverse");
//			list = loadConDiffData(model, vo,column.size(),null);
//		}
//		ConditionUtil.loadPartLineRegion(model, vo, commonService);
//		// 年月季度统计
//		if ("year".equals(vo.getStatisType())) {
//			column = WebUtil.getBetweenYear(column);
//			List<TimeMatrixResultVo> yearList = ParseDataUtil.parseToYear(list, model, vo);
//			model.addAttribute("list", yearList);
//		} else if ("quarter".equals(vo.getStatisType())) {
//			List<TimeMatrixResultVo> quarterList = ParseDataUtil.parseToQuarter(list, model);
//			model.addAttribute("list", quarterList);
//			column = WebUtil.getBetweenQuarter(column);
//		} else {
//			for(int i=0;i<list.size();i++){
//				list.get(i).setPreDiff(i);
//			}
//			model.addAttribute("list", list);
//		}
//		model.addAttribute("column", column);
//		return "qms/table/time/sigleDownMonthRePercent";
//	}
//	/**
//	 * 时间序列矩阵（正）-单月维修率导出excel
//	 */
//	@RequestMapping("/excelOutput_sigleMonthRePercent")
//	public void excelOutput_sigleMonthRePercent(CommonVo vo, Model model,
//			HttpServletRequest request, HttpServletResponse response) {
//		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
//		vo.setQueryMonth(laterTime.getSumMonth());
//		if (vo.getEndTime() == null || vo.getEndTime()==""||vo.getEndTime().compareTo(laterTime.getSumMonth())>0) {
//			vo.setEndTime(laterTime.getSumMonth());
//		}
//		try {
//			List<TimeMatrixResultVo> list = null;
//			String[] CN = null;
//			if (vo.getProductType() != null) {
//				list = loadConDiffData(model, vo, CUL_MONTHS,"preBack");
//			}
//			//年月季度统计
//			if("year".equals(vo.getStatisType())){
//				list = ParseDataUtil.parseToYear(list, model, vo);
//				CN = new String[CUL_MONTHS/10+2];
//				CN[1]="";
//			}else if("quarter".equals(vo.getStatisType())){
//				list = ParseDataUtil.parseToQuarter(list, model);
//				CN = new String[CUL_MONTHS/3+2];
//			}else{
//				CN = new String[CUL_MONTHS+2];
//			}
//			CN[0]="生产月份";
//			CN[1]="生产台数";
//			for(int j=1;j<=CN.length-2;j++){
//				CN[j+1]=j+"";
//			}
//			
//			List<String[]> excelList = new ArrayList<String[]>();
//			for (int i = 0; i < list.size(); i++) {
//				TimeMatrixResultVo tmpVO = list.get(i);
//				String[] tmpStr = new String[CN.length];
//				tmpStr[0] = tmpVO.getBaseMonth();
//				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
//				for (int j = 0; j < tmpVO.getRepairPercent().size(); j++) {
//					if(j+2>=tmpStr.length){
//						continue;
//					}
//					String Count = null;
//					if (tmpVO.getRepairPercent().get(j) == null) {
//						Count = " ";
//					} else {
//						Count = Double
//								.toString(tmpVO.getRepairPercent().get(j));
//					}
//					tmpStr[j + 2] = Count;
//				}
//				excelList.add(tmpStr);
//			}
//			// 获取项目根目录
//			String rootPath = request.getSession().getServletContext()
//					.getRealPath("/");
//			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
//			String filePath = rootPath + "/" + fname;
//			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
//			String contentType = "application/msexcel";
//			ExcelUtilities.downloadExcel(request, response, filePath,
//					contentType, "时间序列矩阵(单月维修率)" + fname);
//
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
//	/**
//	 *  时间序列矩阵（倒三角）-单月维修率导出excel
//	 */
//	@RequestMapping("/excelOutput_sigleMonthDownRePercent")
//	public void excelOutput_sigleMonthDownRePercent(CommonVo vo, Model model,
//			HttpServletRequest request, HttpServletResponse response) {
//		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
//		String laterSumtime = laterTime.getSumMonth();
//		if (vo.getEndTime() == null || vo.getEndTime()=="" ) {
//			vo.setEndTime(laterSumtime);
//		}
//		vo.setQueryMonth(vo.getEndTime());
//		try {
//			List<TimeMatrixResultVo> list = null;
//			Set<String> column = null;
//			if (vo.getProductType() != null) {
//				column = WebUtil.getBetweenMonth(vo.getStartTime(), vo.getEndTime());
//				list = loadConDiffData(model, vo,column.size(),null);
//			}
//			// 年月季度统计
//			if ("year".equals(vo.getStatisType())) {
//				list = ParseDataUtil.parseToYear(list, model, vo);
//				column = WebUtil.getBetweenYear(column);
//			} else if ("quarter".equals(vo.getStatisType())) {
//				list = ParseDataUtil.parseToQuarter(list, model);
//				column = WebUtil.getBetweenQuarter(column);
//			} else{
//				for(int i=0;i<list.size();i++){
//					list.get(i).setPreDiff(i);
//				}
//			}
//			//设置表头
//			String[] CN = new String[column.size() + 2];
//			CN[0] = "生产月份";
//			CN[1] = "生产台数";
//			List<String> coluList = new ArrayList<String>();
//			coluList.addAll(column);
//			for(int j=1;j<=CN.length-2;j++){
//				CN[j+1]=coluList.get(j-1);
//			}
//			//填表体
//			List<String[]> excelList = new ArrayList<String[]>();
//			for (int i = 0; i < list.size(); i++) {
//				TimeMatrixResultVo tmpVO = list.get(i);
//				String[] tmpStr = new String[CN.length];
//				tmpStr[0] = tmpVO.getBaseMonth();
//				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
//				for (int j = 0; j < tmpVO.getRepairPercent().size(); j++) {
//					int diff = tmpVO.getPreDiff();
//					if(j+diff + 2>=tmpStr.length){
//						continue;
//					}
//					for(int k=0;k<diff;k++){
//						tmpStr[k+2] = " ";
//					}
//					String Count = null;
//					if (tmpVO.getRepairPercent().get(j) == null) {
//						Count = " ";
//					} else {
//						Count = Long.toString(tmpVO.getRepairPercent().get(j));
//					}
//					tmpStr[j+diff + 2] = Count;
//				}
//				excelList.add(tmpStr);
//			}
//			// 获取项目根目录
//			String rootPath = request.getSession().getServletContext().getRealPath("/");
//			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
//			String filePath = rootPath + "/" + fname;
//			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
//			String contentType = "application/msexcel";
//			ExcelUtilities.downloadExcel(request, response, filePath,contentType, "时间序列倒三角阵(单月维修率)" + fname);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
}
