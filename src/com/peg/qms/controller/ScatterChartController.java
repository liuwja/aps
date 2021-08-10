package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.peg.model.CommonVo;
import com.peg.service.CommonServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;

/**
 * 散点图表
 * @author song
 *
 */
@Controller
@RequestMapping("scatterChart")
public class ScatterChartController extends BaseController {
	
	@Autowired
	private CommonServiceI commonService;
	
	private static final String PART_TYPE = "PART_TYPE";
	
	private static final String REGION = "REGION";
	
	@RequestMapping("/scatterChart")
	public String scatterChart(Model model, CommonVo vo) {
		setBaseData(model);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		vo.setQueryMonth(DateFormatUtils.format(cal.getTime(), "yyyy-MM"));
		vo.setxCount(9);
		model.addAttribute("vo", vo);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		return "qms/chart/partType/scatterChart";
	}
	
	@RequestMapping("installScatterChart")
	public String installScatterChart(Model model, CommonVo vo) {
		setBaseData(model);
		model.addAttribute("vo", vo);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		return "qms/chart/partType/installScatterChart";
	}
	
	@RequestMapping("/getPartTypeInfo")
	public void getPartTypeInfo(Model model, CommonVo vo, HttpServletResponse respon) {
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		int result = 0;		
		String msg = null;
		Map<String,Object> map = new HashMap<String,Object>();
		ChartObj chartsInfo = createChartObj(vo, PART_TYPE);
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/getRegionInfo")
	public void getRegionInfo(Model model, CommonVo vo, HttpServletResponse respon) {
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		int result = 0;		
		String msg = null;
		Map<String,Object> map = new HashMap<String,Object>();
		ChartObj chartsInfo = createChartObj(vo, REGION);
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/execlOutputByPartType")
	public void execlOutputByPartType(Model model, CommonVo vo, HttpServletRequest request, HttpServletResponse response) {
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		String[] CN = {"型号", "安装数", "维修数", "统计维修率", "维修率"};
		List<String[]> excelList = execlOutput(vo, PART_TYPE, CN);
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String fname = System.currentTimeMillis() + ".xls";
		System.out.println(rootPath+fname);
		String filePath = rootPath + "/" + fname;
		try {
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-型号安装四象限分析" + fname);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/execlOutputByRegion")
	public void execlOutputByRegion(Model model, CommonVo vo, HttpServletRequest request, HttpServletResponse response) {
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		String[] CN = {"区域", "安装数", "维修数", "统计维修率", "维修率"};
		List<String[]> excelList = execlOutput(vo, REGION, CN);
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String fname = System.currentTimeMillis() + ".xls";
		System.out.println(rootPath+fname);
		String filePath = rootPath + "/" + fname;
		try {
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-型号安装四象限分析" + fname);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	private ChartObj createChartObj(CommonVo vo, String type) {
		vo = formatCommonVo(vo);
		List<CommonVo> insCountList = null;
		List<CommonVo> repCountList = null;
		String title = null;
		Map<String, CommonVo> insMap = new HashMap<String, CommonVo>();
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yvalue = new ArrayList<List<Double>>();
		List<Double> yvalue1 = new ArrayList<Double>();
		List<Double> yvalue2 = new ArrayList<Double>();
		List<String> installValue = new ArrayList<String>();
		List<Double> recPercentlist = new ArrayList<Double>(); //统计维修率		
		if (type.equals(PART_TYPE)) {
			insCountList = commonService.getInsCountGroupByPartType(vo);
			repCountList = commonService.getInsCountGroupByRePartType(vo);
			title = "型号-安装四象限分析图";
			for (int i = 0; i < repCountList.size(); i++) {
				insMap.put(repCountList.get(i).getPartType(), repCountList.get(i));
			}
			for (CommonVo insCommonVo : insCountList) {
				if (insMap.get(insCommonVo.getPartType()) != null) {
					insCommonVo.setRepairCount(insMap.get(insCommonVo.getPartType()).getRepairCount());
				} else {
					insCommonVo.setRepairCount(0L);
				}
				xvalues.add(insCommonVo.getPartType() == null || insCommonVo.getPartType() == "" ? "空白型号" : insCommonVo.getPartType());
				installValue.add(insCommonVo.getInstallCount() + "");
				yvalue1.add(Double.parseDouble(insCommonVo.getRepairCount() + ""));
				if (insCommonVo.getRepairCount() == 0) {
					yvalue2.add(0D);
				} else {
					yvalue2.add(Double.parseDouble(String.format("%.2f", (insCommonVo.getRepairCount() * 100.0 / insCommonVo.getInstallCount()))));
				}
				if (insCommonVo.getRepairCount() != null && insCommonVo.getRepairCount() != 0 && insCommonVo.getInstallCount() != null && insCommonVo.getInstallCount() != 0) {
					recPercentlist.add(Math.floor(insCommonVo.getRepairCount() * 1000000.0 / insCommonVo.getInstallCount()));
				} else {
					recPercentlist.add(0d);
				}
			}
		} else if (type.equals(REGION)) {
			insCountList = commonService.getInsCountGroupByRegion(vo);
			repCountList = commonService.getInsCountGroupByReRegion(vo);
			title = "区域-安装四象限分析图";
			for (int i = 0; i < repCountList.size(); i++) {
				insMap.put(repCountList.get(i).getRegion(), repCountList.get(i));
			}
			for (CommonVo insCommonVo : insCountList) {
				if (insMap.get(insCommonVo.getRegion()) != null) {
					insCommonVo.setRepairCount(insMap.get(insCommonVo.getRegion()).getRepairCount());
				} else {
					insCommonVo.setRepairCount(0L);
				}
				xvalues.add(insCommonVo.getRegion() == null || insCommonVo.getRegion() == "" ? "空白型号" : insCommonVo.getRegion());
				installValue.add(insCommonVo.getInstallCount() + "");
				yvalue1.add(Double.parseDouble(insCommonVo.getRepairCount() + ""));
				if (insCommonVo.getRepairCount() == 0) {
					yvalue2.add(0D);
				} else {
					yvalue2.add(Double.parseDouble(String.format("%.2f", (insCommonVo.getRepairCount() * 100.0 / insCommonVo.getInstallCount()))));
				}
				if (insCommonVo.getRepairCount() != null && insCommonVo.getRepairCount() != 0 && insCommonVo.getInstallCount() != null && insCommonVo.getInstallCount() != 0) {
					recPercentlist.add(Math.floor(insCommonVo.getRepairCount() * 1000000.0 / insCommonVo.getInstallCount()));
				} else {
					recPercentlist.add(0d);
				}
			}
		}
		yvalue.add(yvalue1);
//		if (StringUtils.isNotEmpty(vo.getStatisData()) && "repairRate".equals(vo.getStatisData())) {
//			yvalue.add(recPercentlist);
//		} else {
//			yvalue.add(yvalue1);
//		}
		yvalue.add(yvalue2);
		yvalue.add(recPercentlist);
		ChartObj chartVo = new ChartObj();
		chartVo.setTitle(title);
		chartVo.setSubtitle("安装期间：" + vo.getInsStartTime() + "~" + vo.getInsEndTime());
		chartVo.setxValue(xvalues);
		chartVo.setyValues(yvalue);
		chartVo.setTipValues(installValue);
		return chartVo;
	}
	
	private List<String[]> execlOutput(CommonVo vo, String type, String[] CN) {
		ChartObj chartObj = createChartObj(vo, type);
		List<String> partTypeList = chartObj.getxValue();
		List<String> installList = chartObj.getTipValues();
		List<Double> repairList = chartObj.getyValues().get(0);
		List<Double> repairArr = chartObj.getyValues().get(1);
		List<String[]> excelList = new ArrayList<String[]>();
		for (int i = 0; i < partTypeList.size(); i++) {
			String[] itemStr = new String[CN.length];
			itemStr[0] = partTypeList.get(i);
			itemStr[1] = installList.get(i);
			itemStr[2] = repairList.get(i) + "";
			if (repairList.get(i) != null && repairList.get(i) != 0 && installList.get(i) != null && installList.get(i) != "0") {
				itemStr[3] = Math.floor(repairList.get(i) * 1000000.0 / Double.parseDouble(installList.get(i))) + "";
			} else {
				itemStr[3] = "0";
			}
			itemStr[4] = repairArr.get(i) + "";
			excelList.add(itemStr);
		}
		return excelList;
	}
	
	private CommonVo formatCommonVo(CommonVo vo) {
		if (StringUtils.isNotEmpty(vo.getPartTypeListTxt())) {
			vo.setPartTypes(vo.getPartTypeListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getRegionListTxt())) {
			vo.setRegions(vo.getRegionListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getProductFamilyTxt())) {
			vo.setProductFamilys(vo.getProductFamilyTxt().replaceAll("'", "").split(","));
		}
		// 故障小类
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		// 故障大类
		if (StringUtils.isNotEmpty(vo.getFaultTypeCode())) {
			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
		}
		return vo;
	}
}